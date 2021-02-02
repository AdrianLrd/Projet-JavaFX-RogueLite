package fr.roguelite.view;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.Manager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;

public class Dungeon  {

    @FXML
    private Group group;

    @FXML
    private Label time;

    private IntegerProperty min = new SimpleIntegerProperty();
    private StringProperty sec = new SimpleStringProperty("0");

    public void initialize() {
        Manager manager = Launch.getManager();

        manager.getGame().start();

        min.bind(Bindings.divide(manager.getGame().timeProperty(), 60));

        manager.getGame().timeProperty().addListener((observableValue, oldValue, newValue) -> {
            sec.setValue(String.valueOf(newValue.intValue() % 60));
        });

        time.textProperty().bind(Bindings.format("%d:%s", min, sec));

        //time.textProperty().bindBidirectional(manager.getGame().timeProperty(), new NumberStringConverter());

        group.getChildren().add(manager.getGame().getEntityContainer().getGroup());
    }
}
