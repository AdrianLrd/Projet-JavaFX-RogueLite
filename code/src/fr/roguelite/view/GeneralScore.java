package fr.roguelite.view;

import fr.roguelite.launch.Launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralScore {

    @FXML
    TableView tableView;

    @FXML private Button retour;

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {
        Stage stage = Launch.getStage();
        Parent myNewScene = null;
        String name = "null";

        if (event.getSource() == retour) {
            name = "MainWindow";
            myNewScene = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle(name);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();
    }



    public void initialize(){
        TableColumn pseudo = new TableColumn<>("Pseudo");
        pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        TableColumn time = new TableColumn<>("Time");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        TableColumn date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.getItems().addAll(Launch.getManager().getGame().getScores());
        tableView.getColumns().addAll(pseudo, time, date);

    }
}
