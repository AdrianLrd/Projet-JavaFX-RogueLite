package fr.roguelite.view;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    @FXML
    private Label label;

    public void initialize() {
        label.setText("Vous avez survécu " + Launch.getManager().getGame().getTime() + " secondes");
    }

    public void handleMenuButtonAction(ActionEvent event) throws IOException {
        Stage stage = Launch.getStage();;
        Parent myNewScene = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));;
        String name = "MainWindow";

        Scene scene = new Scene(myNewScene);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle(name);
        stage.show();
    }
}
