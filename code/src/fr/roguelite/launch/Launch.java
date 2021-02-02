package fr.roguelite.launch;

import fr.roguelite.model.Manager;
import fr.roguelite.model.Score;
import fr.roguelite.model.utils.FileUtil;
import fr.roguelite.model.utils.SerializableUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Launch extends Application {
    public static boolean debug;

    private static Manager manager;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Launch.stage = stage;

        manager = new Manager();

        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent parent = leLoader.load();


        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, ke -> {
            if(!manager.getGame().getPressedKeys().contains(ke.getCode()) && manager.getGame().isRunning())
                manager.getGame().getPressedKeys().add(ke.getCode());
        });

        stage.addEventFilter(KeyEvent.KEY_RELEASED, ke -> {
            if(manager.getGame().getPressedKeys().contains(ke.getCode()) && manager.getGame().isRunning())
                manager.getGame().getPressedKeys().remove(ke.getCode());
        });


        stage.setScene(scene);
        stage.setTitle("RogueLite");

        stage.show();

        FileUtil.createIfDoesNotExists("save.dat");
        String fileContent = FileUtil.readFile("save.dat");

        if(fileContent.equals("")) manager.getGame().setScores(new ArrayList<Score>());
        else manager.getGame().setScores((List<Score>) SerializableUtil.deserialize(fileContent));
    }

    @Override
    public void stop() {
        manager.getGame().setRunning(false);


        FileUtil.saveFile("save.dat", SerializableUtil.serialize(manager.getGame().getScores()));
    }

    public static Manager getManager() {
        return manager;
    }

    public static Stage getStage() {
        return stage;
    }
}