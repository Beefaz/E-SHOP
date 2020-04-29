package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.utils.Constants;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent window = FXMLLoader.load(getClass().getResource(Constants.FIRST_WINDOW_FILE_LOCATION));
        window.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        primaryStage.setTitle("Welcome to E-Shop!");
        primaryStage.setScene(new Scene(window));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
