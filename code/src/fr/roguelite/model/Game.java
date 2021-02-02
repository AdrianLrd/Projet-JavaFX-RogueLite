package fr.roguelite.model;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.attribute.Attribute;
import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.collider.collision.EntityCollision;
import fr.roguelite.model.collider.collision.WallCollision;
import fr.roguelite.model.difficulty.Difficulty;
import fr.roguelite.model.entitiy.*;
import fr.roguelite.model.entitiy.entities.Arrow;
import fr.roguelite.model.entitiy.entities.Player;
import fr.roguelite.model.mover.Mover;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    /**
     * Define if the game is running or not
     */
    private boolean running;

    /**
     * The time since the start of the game
     */
    private IntegerProperty time = new SimpleIntegerProperty(0);

    /**
     * Contains all the Entities of the game
     */
    private EntityContainer entityContainer = new EntityContainer();

    /**
     * The difficulty of the game
     */
    private Difficulty difficulty = Difficulty.NORMAL;

    /**
     * The game loop
     */
    private Thread gameLoop = new Thread(this::gameLoopCode);

    /**
     * The animation loop
     */
    private Thread animationLoop = new Thread(this::animationLoopCode);

    /**
     * The current player
     */
    private Player player = new Player("resources/texture/entity/player/elf_m.png");

    /**
     * The manager for this game
     */
    private Manager manager;

    /**
     * Contains all the pressed keys
     */
    public List<KeyCode> pressedKeys = new ArrayList<>();

    /**
     * Contains all the scores for the differents players
     */
    private List<Score> scores = new ArrayList<>();


    /**
     * That will handle the pressed keys of the player
     */
    private Keyboard keyboard = new Keyboard(player);


    /**
     * the pseudo of the player
     */
    private StringProperty pseudo = new SimpleStringProperty("RandomPlayer");

    /**
     * How many time per second the game will be updated
     */
    private static final int GAME_FPS = 1000 / 30;

    /**
     * How many time per second the animations will be updated
     */
    private static final int ANIMATION_FPS = 1000 / 8;


    /**
     * Create a new game
     * @param manager The manager that is creating a new Game
     */
    public Game(Manager manager) {
        this.manager = manager;

        player.getLocation().setX(100);
        player.getLocation().setY(100);
    }

    /**
     * Starts the Game
     */
    public void start() {
        running = true;

        entityContainer.add(player);

        gameLoop.start();
        animationLoop.start();
    }

    /**
     * Game loop
     */
    private void gameLoopCode() {
        List<Entity> toDelete = new LinkedList<>();
        Mover mover = manager.getMover();

        int frames = 0;
        int nextMonsterIn = 3 * GAME_FPS;

        while(running) {
            // Update game time
            if(frames >= GAME_FPS) {
                Platform.runLater(() -> { time.setValue(time.getValue() + 1); });
                frames = 0;
            }
            frames++;

            // Spawn new monster
            if(nextMonsterIn <= 0) {
                Entity monster;
                if(Math.random()<0.1)
                   monster = manager.getEntityGenerator().generate(difficulty.getBoss());
                else{
                   monster = manager.getEntityGenerator().generate(difficulty.getRandomMonster());
                }
                if (monster != null) entityContainer.add(monster);

                nextMonsterIn = 3 * GAME_FPS - (int) (Math.min(time.getValue() * 2 , GAME_FPS * 2.2));

            }
            nextMonsterIn--;

            // Handle player inputs
            keyboard.handleKeys();

            // Handle arrow collisions
            entityContainer.getEntities().stream().filter(entity -> entity instanceof Arrow).forEach(arrow -> {
                List<Collision> collisions;
                if(arrow.getSprite().getDirection() == EntityDirection.RIGHT) collisions = mover.moveRight(arrow);
                else collisions = mover.moveLeft(arrow);

                if(collisions.size() != 0) {
                    if(collisions.stream().filter(collision -> collision instanceof WallCollision).count() > 0) toDelete.add(arrow);

                    List<Entity> killed =
                            collisions
                            .stream()
                            .filter(collision -> collision instanceof EntityCollision)
                            .map( collision -> ((EntityCollision) collision).getEntity())
                            .peek(entity -> {
                                entity.getAttributes().set(Attribute.HEALTH_POINT, entity.getAttributes().getInt(Attribute.HEALTH_POINT) - arrow.getAttributes().getInt(Attribute.DAMAGE_POINT));
                            })
                            .filter(entity -> entity.getAttributes().getInt(Attribute.HEALTH_POINT) <= 0)
                            .collect(Collectors.toList());

                    toDelete.add(arrow);
                    if(killed.size() > 0) { toDelete.addAll(killed); }
                }
            });


            // Handle monster moves
            BoundingBox playerBB = player.getBoundingBox();
            int playerCenterX = playerBB.getTopLeft().getX() + playerBB.getDx() / 2;
            int playerCenterY = playerBB.getTopLeft().getY() + playerBB.getDy() / 2;

            entityContainer.getEntities().stream().filter(entity -> entity instanceof Monster).forEach(monster -> {
                BoundingBox monterBB = monster.getBoundingBox();

                int monsterCenterX = monterBB.getTopLeft().getX() + monterBB.getDx() / 2;
                int monsterCenterY = monterBB.getTopLeft().getY() + monterBB.getDy() / 2;

                List<Collision> collisions = new LinkedList<>();

                if(monsterCenterX - playerCenterX > 20) collisions.addAll(mover.moveLeft(monster));
                if(monsterCenterX - playerCenterX < -20) collisions.addAll(mover.moveRight(monster));

                if(monsterCenterY - playerCenterY > 20)  collisions.addAll(mover.moveUp(monster));
                if(monsterCenterY - playerCenterY < -20) collisions.addAll(mover.moveDown(monster));


                List<Entity> killed =
                        collisions
                                .stream()
                                .filter(collision -> collision instanceof EntityCollision)
                                .map( collision -> ((EntityCollision) collision).getEntity())
                                .filter(entity -> entity instanceof Player)
                                .collect(Collectors.toList());

                    if(killed.size() > 0) {
                        Platform.runLater(() -> {
                            try {
                                running = false;
                                entityContainer.clear();

                                DateFormat format = DateFormat.getDateTimeInstance(
                                        DateFormat.SHORT,
                                        DateFormat.SHORT, new Locale("FR","fr"));

                                scores.add(new Score(pseudo.getValue(), time.getValue().toString(), format.format(new Date())));

                                Stage stage = Launch.getStage();;

                                FXMLLoader gameover = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"));

                                Parent myNewScene = gameover.load();

                                Scene scene = new Scene(myNewScene);
                                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

                                stage.setScene(scene);
                                stage.setTitle("GameOver");
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
            });

            // Remove dead entities
            toDelete.forEach(entity -> entityContainer.remove(entity));
            toDelete.clear();

            try {
                Thread.sleep(GAME_FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Animation loop
     */
    private void animationLoopCode() {
        while(running) {
            // Update all entities
            entityContainer.getEntities().forEach(entity -> entity.getSprite().setAnimationIndex(entity.getSprite().getAnimationIndex() + 1));

            try {
                Thread.sleep(ANIMATION_FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public EntityContainer getEntityContainer() {
        return entityContainer;
    }

    public List<KeyCode> getPressedKeys() {
        return pressedKeys;
    }

    public int getTime() {
        return time.get();
    }

    public IntegerProperty timeProperty() {
        return time;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public StringProperty pseudoProperty() {
        return pseudo;
    }
}
