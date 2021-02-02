package fr.roguelite.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Navigateur {
    private Scene scene;

    public Navigateur(Scene scene) {
        this.scene = scene;
    }

    public void setMenuPrincipale() throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent parent = leLoader.load();

        scene.setRoot(parent);
    }
}
