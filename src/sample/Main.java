package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.ProductPanelController;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(ProductPanelController.createNewProductPane());
        scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        primaryStage.setTitle("Welcome to E-Shop!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
