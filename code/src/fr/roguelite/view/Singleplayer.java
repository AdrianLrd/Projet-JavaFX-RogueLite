package fr.roguelite.view;

import fr.roguelite.launch.Launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Singleplayer {
    @FXML
    public TextField pseudo;

    @FXML
    private Button jouer;

    @FXML
    private Button retour;

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {
        Stage stage = Launch.getStage();
        Parent myNewScene = null;
        String name = "null";

        if (event.getSource() == jouer){
            name = "Jouer";
            myNewScene = FXMLLoader.load(getClass().getResource("/fxml/Dungeon.fxml"));
        } else if(event.getSource() == retour){
            name = "MainWindow";
            myNewScene = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());


        stage.setScene(scene);
        stage.setTitle(name);
        stage.show();
    }

    public void initialize() {
        pseudo.textProperty().bindBidirectional(Launch.getManager().getGame().pseudoProperty());
    }
}