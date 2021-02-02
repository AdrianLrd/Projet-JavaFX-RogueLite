package fr.roguelite.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Entity {

    @FXML
    private ImageView imageDisplay;

    public void bind(fr.roguelite.model.entitiy.Entity entity) {
        imageDisplay.imageProperty().bind(entity.getSprite().imageProperty());
        imageDisplay.xProperty().bind(entity.getLocation().xProperty());
        imageDisplay.yProperty().bind(entity.getLocation().yProperty());

        imageDisplay.scaleXProperty().bind(entity.getSprite().getDirectionProperty());
    }


}
