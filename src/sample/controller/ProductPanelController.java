package sample.controller;

import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import sample.model.Products;
import sample.model.ProductsDAO;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ProductPanelController {
    //Gets product objects as Arraylist from ProductsDAO(->SQL), creates panes
    public static FlowPane createNewProductPane(int setNumberOfPanes, int productOffset) {
        ScrollPane scrollPane = new ScrollPane();
        FlowPane flowPane = new FlowPane();

        //Products to panels iterator, creates view and style for each parameter.
        ArrayList<Products> productsArrayList = ProductsDAO.selectNewProducts(setNumberOfPanes, productOffset);
        for (Products products : productsArrayList) {
            Label newOffer = new Label("New Offer");
            Label productName = new Label(products.getProductName());
            Label productPrice = new Label("Kaina: " + products.getProductPrice() + "\u20AC");
            Label time = new Label("Liko laiko: ");
            Button order = new Button("Užsakyti");
            Image image = new Image(new ByteArrayInputStream(products.getImage()));
            GridPane gridPane = new GridPane();

            gridPane.setMaxWidth(300);
            gridPane.setMaxHeight(300);

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setStyle("-fx-border-color: black");

            newOffer.setTranslateY(-60);
            newOffer.setId("new_offer");

            gridPane.setPadding(new Insets(10));
            gridPane.setHalignment(order, HPos.RIGHT);
            gridPane.setHalignment(productPrice, HPos.RIGHT);
            GridPane.setVgrow(imageView, Priority.ALWAYS);
            GridPane.setHgrow(imageView, Priority.ALWAYS);
            gridPane.add(imageView, 0, 0, 2, 1);
            gridPane.add(newOffer, 0, 0);
            gridPane.add(productName, 0, 1);
            gridPane.add(productPrice, 1, 1);
            gridPane.add(time, 0, 2);
            gridPane.add(order, 1, 2);
            gridPane.setStyle("-fx-border-color: black");

            flowPane.getChildren().add(gridPane);
        }

        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPrefWrapLength(930);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        return flowPane;
    }
}
