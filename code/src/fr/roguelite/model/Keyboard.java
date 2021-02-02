package fr.roguelite.model;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.entitiy.EntityDirection;
import fr.roguelite.model.entitiy.entities.Arrow;
import fr.roguelite.model.entitiy.entities.Player;
import javafx.scene.input.KeyCode;

public class Keyboard {
    private Player player;

    private int nextArrowIn = 0;

    /**
     * Create and handle all the pressed keys for the given player
     * @param player The player that will do the actions
     */
    public Keyboard(Player player) {
        this.player = player;
    }

    /**
     * Process the key pressed
     */
    public void handleKeys() {
        Game game = Launch.getManager().getGame();

        if(game.getPressedKeys().contains(KeyCode.Z)) Launch.getManager().getMover().moveUp(player);
        if(game.getPressedKeys().contains(KeyCode.S)) Launch.getManager().getMover().moveDown(player);

        if(game.getPressedKeys().contains(KeyCode.D)) Launch.getManager().getMover().moveRight(player);
        if(game.getPressedKeys().contains(KeyCode.Q)) Launch.getManager().getMover().moveLeft(player);

        if(game.getPressedKeys().contains(KeyCode.F)) Launch.debug = true;
        else Launch.debug = false;

        if(game.getPressedKeys().contains(KeyCode.SPACE) && nextArrowIn == 0) {
            Arrow newArrow = new Arrow();
            newArrow.getLocation().setY(player.getLocation().getY() + 80);

            if(game.getPlayer().getSprite().getDirection() == EntityDirection.RIGHT)
                newArrow.getLocation().setX(player.getLocation().getX() + 40);
            else
                newArrow.getLocation().setX(player.getLocation().getX() - 40);


            newArrow.setShooter(player);
            newArrow.getSprite().setDirection(player.getSprite().getDirection());

            game.getEntityContainer().add(newArrow);

            nextArrowIn = 10;
        }

        if(nextArrowIn > 0) nextArrowIn--;
    }
}
