package fr.roguelite.view;

import com.sun.media.jfxmediaimpl.platform.Platform;
import fr.roguelite.launch.Launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

        @FXML private Button single;
        @FXML private Button multi;
        @FXML private Button quit;
        @FXML private Button score;

        @FXML
        private void handleMenuButtonAction (ActionEvent event) throws IOException {
            Stage stage = Launch.getStage();
            Parent myNewScene = null;
            String name = "null";

            if (event.getSource() == single){
                name = "Singleplayer";
                stage = (Stage) single.getScene().getWindow();
                myNewScene = FXMLLoader.load(getClass().getResource("/fxml/Singleplayer.fxml"));
            } else if (event.getSource() == multi){
                name = "Multiplayer";
                stage = (Stage) multi.getScene().getWindow();
                myNewScene = FXMLLoader.load(getClass().getResource("/fxml/Multiplayer.fxml"));
            } else if (event.getSource() == quit) {
                System.exit(0);
            } else if (event.getSource() == score ) {
                name = "Score General";
                stage = (Stage) score.getScene().getWindow();
                myNewScene = FXMLLoader.load(getClass().getResource("/fxml/GeneralScore.fxml"));
            }

            Scene scene = new Scene(myNewScene);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            stage.setScene(scene);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
