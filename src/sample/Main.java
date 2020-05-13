package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.ProductPanelController;
import sample.utils.Constants;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = FXMLLoader.load(getClass().getResource("view/productPanel.fxml"));
        vBox.getChildren().addAll(ProductPanelController.createNewProductPane(Constants.IMAGE_COUNT_SHOWN_IN_1ST_WINDOW, 0));
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        primaryStage.setTitle("Welcome to E-Shop!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
