package sample.controller;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import sample.model.Products;
import sample.model.ProductsDAO;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ProductPanelController {
    //Gets product objects as Arraylist from ProductsDAO(->SQL), creates panes
    public static ScrollPane createNewProductPane() {
        Controller controller = new Controller();
        ScrollPane scrollPane = new ScrollPane();
        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = new FlowPane();
        Button welcomeToShopBtn = new Button("Enter the Shop");

        //Products to panels iterator
        ArrayList<Products> productsArrayList = ProductsDAO.selectNewProducts();
        for (Products products : productsArrayList) {
            //gridpane
            GridPane gridPane = new GridPane();
            gridPane.setMaxWidth(300);
            gridPane.setMaxHeight(300);

            //images
            Image image = new Image(new ByteArrayInputStream(products.getImage()));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setStyle("-fx-border-color: black");

            //labels
            Label newOffer = new Label("Naujas pasiūlymas");
            Label productName = new Label(products.getProductName());
            Label productPrice = new Label("Kaina: " + products.getProductPrice() + "\u20AC");
            Label time = new Label("Liko laiko: ");
            newOffer.setTranslateY(-60);
            newOffer.setId("new_offer");

            //buttons
            Button order = new Button("Užsakyti");

            //modifies stuff in gridpane and adds all stuff to flowpane
            gridPane.setPadding(new Insets(10,10,10,10));
            gridPane.setHalignment(order, HPos.RIGHT);
            gridPane.setHalignment(productPrice, HPos.RIGHT);
            gridPane.add(imageView, 0, 0, 2, 1);
            gridPane.add(newOffer, 0, 0);
            gridPane.add(productName, 0, 1);
            gridPane.add(productPrice, 1, 1);
            gridPane.add(time, 0, 2);
            gridPane.add(order,1, 2);
            gridPane.setStyle("-fx-border-color: black");
            flowPane.getChildren().add(gridPane);
        }

        //panel wrapping and style parameters
        welcomeToShopBtn.setPadding(new Insets(10,10,10,10));
        welcomeToShopBtn.setId("welcome_to_shop");
        welcomeToShopBtn.setOnAction(previousWindowCloser -> controller.loadLogin(previousWindowCloser));
        borderPane.setTop(welcomeToShopBtn);
        borderPane.setCenter(flowPane);
        BorderPane.setAlignment(flowPane, Pos.CENTER);
        BorderPane.setAlignment(welcomeToShopBtn, Pos.CENTER);
        scrollPane.setContent(borderPane);
        return scrollPane;
    }
}
