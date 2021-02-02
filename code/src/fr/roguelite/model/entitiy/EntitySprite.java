package fr.roguelite.model.entitiy;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.utils.ResourceLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EntitySprite {
    /**
     * The Entity that correspond to the Sprite
     */
    private Entity entity;

    /**
     * An array containing all the Animations phases
     */
    private List<Animation> animations = Arrays.asList(Animation.IDLE, Animation.WALKING);

    /**
     * The current Animation phase
     */
    private Animation currentAnimation = animations.get(0);

    /**
     * The current index in the current Animation Phase
     */
    private int animationIndex;

    /**
     * The Entity Direction
     */
    private EntityDirection direction = EntityDirection.LEFT;

    /**
     * The scale of the Entity
     */
    private float scaleFactor = 1;

    /**
     * The Sprite Property
     */
    private ObjectProperty<Image> imageProperty;

    /**
     * The Direction Property usef to flip the Entity
     */
    private IntegerProperty directionProperty;


    /**
     * Number of frame in the Sprite
     */
    private int nbFrames;

    /**
     * The texture for the Entity
     */
    private Image defaultImage;

    /**
     * Create a new Sprite from an Entity
     * @param entity The Entity corresponding to the Sprite
     */
    public EntitySprite(Entity entity) {
        this.entity = entity;

        imageProperty = new SimpleObjectProperty<>();
        directionProperty = new SimpleIntegerProperty(-1);

        defaultImage = ResourceLoader.getImage(entity.gettexturePath());

        setAnimations(Arrays.asList(Animation.NONE));

        defaultImage = resample(defaultImage, (int) scaleFactor);

        setAnimationIndex(0);
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex % currentAnimation.getNbFrames();

        Image image = defaultImage;
        PixelReader reader = image.getPixelReader();

        Image input;

        if(currentAnimation.getNbFrames() == 1) {
            input = defaultImage;
        }
        else {
            input = new WritableImage(reader, (int) ((image.getWidth() / nbFrames) * (this.animationIndex)), 0, (int) (image.getWidth() / nbFrames), (int) image.getHeight());
        }

        // Display the bounding box
        if(Launch.debug && entity.getBoundingBox() != null) {
            int c = new Color(255, 0, 0).getRGB();
            if(direction == EntityDirection.LEFT) c = new Color(0, 0, 255).getRGB();

            BoundingBox box = entity.getBoundingBox();

            int W = (int) input.getWidth();
            int H = (int) input.getHeight();

            WritableImage output = new WritableImage( W , H );

            reader = input.getPixelReader();
            PixelWriter writer = output.getPixelWriter();

            for(int x = 0; x < W; x++) {
                for(int y = 0; y < H; y++) {
                    writer.setArgb(x, y, reader.getArgb(x, y));
                }
            }

            for(int x = 0; x <  + box.getDx(); x++) {
                writer.setArgb(box.getOffsetx() + x, box.getOffsety() , c);
            }

            for(int x = 0; x < box.getDx(); x++) {
                writer.setArgb(box.getOffsetx() + x, box.getOffsety() + box.getDy() - 1, c);
            }

            for(int y = 0; y < box.getDy(); y++) {
                writer.setArgb(box.getOffsetx(), box.getOffsety() + y, c);
            }

            for(int y = 0; y < box.getDy(); y++) {
                writer.setArgb(box.getOffsetx() + box.getDx() - 1, box.getOffsety() + y, c);
            }

            imageProperty.setValue(output);
        }
        else {
            imageProperty.setValue(input);
        }
    }

    /**
     * Create a new EntityView
     * @return the Parent that contains the ImageView
     */
    public Parent loadSprite() {
        try {
            setAnimationIndex(0);

            FXMLLoader character = new FXMLLoader(getClass().getResource("/fxml/Entity.fxml"));

            Parent parent = character.load();

            character.<fr.roguelite.view.Entity>getController().bind(entity);

            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Image getImage() {
        return imageProperty.get();
    }

    public void setImage(Image image) {
        imageProperty.set(image);
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public EntityDirection getDirection() {
        return direction;
    }

    public void setDirection(EntityDirection direction) {
        if(direction == this.direction) return;

        directionProperty.setValue(direction.getValue());
        this.direction = direction;
    }

    public IntegerProperty getDirectionProperty() {
        return directionProperty;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(List<Animation> animations) {
        this.animations = animations;

        this.nbFrames = animations
                .stream()
                .map(Animation::getNbFrames)
                .reduce(Integer::sum)
                .get();

        this.currentAnimation = this.animations.get(0);
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;

        defaultImage = resample(defaultImage, scaleFactor);
    }

    public Entity getEntity() {
        return entity;
    }

    public static Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }

}
