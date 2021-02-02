package fr.roguelite.model.entitiy;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EntityContainer {
    /**
     * The FXML Parent who contains the Entity View
     */
    private Group group = new Group();

    /**
     * The ObservableList containing the Entities and Entities View
     */
    private ObservableMap<Entity, Parent> entities = FXCollections.observableHashMap();

    /**
     * Adds an entity to the List and loads it to the View
     * @param entity The entity to add
     */
    public void add(Entity entity) {
        Platform.runLater(() -> {
            Parent parent = entity.getSprite().loadSprite();
            group.getChildren().add(parent);
            entities.put(entity, parent);
        });
    }

    /**
     * Removes an entity from the List and unloads it from the View
     * @param entity The entity to remove
     */
    public void remove(Entity entity) {
        Platform.runLater(() -> {
            if(! entities.containsKey(entity)) return;

            Parent parent = entities.get(entity);

            group.getChildren().remove(parent);
            entities.remove(entity);
        });

    }


    public Group getGroup() {
        return group;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.keySet());
    }

    public void clear() {
        group.getChildren().clear();
        entities.clear();
    }
}
