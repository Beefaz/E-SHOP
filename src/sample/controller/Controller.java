package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Products;
import sample.model.ProductsDAO;
import sample.model.Users;
import sample.model.UsersDAO;
import sample.utils.Constants;
import sample.utils.Validation;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Controller {

    /*Products 1st window*/
    @FXML private FlowPane mainFlowPane;
    @FXML private ScrollPane mainScrollPane;
    @FXML private Button mainJoinButton;

    /*login nodes*/
    @FXML private ImageView logImageForUsername;
    @FXML private ImageView logImageForPassword;
    @FXML private TextField logUsernameField;
    @FXML private PasswordField logPasswordField;
    @FXML private Label logErrorField;
    @FXML private Button logLoginButton;
    @FXML private Button logRegisterButton;

    /*registration nodes*/
    @FXML private TextField regUsernameField;
    @FXML private TextField regEmailField;
    @FXML private PasswordField regPasswordField;
    @FXML private PasswordField regPasswordField1;
    @FXML private Label regErrorField;
    @FXML private Button regRegisterButton;
    @FXML private CheckBox regAdminRightsCheckbox;

    /*dashboard nodes*/
    @FXML private ScrollPane dashScrollPane;
    @FXML private Label dashUserNameLabel;
    @FXML private Button dashCreateButton;
    @FXML private Button dashUpdateButton;
    @FXML private Button dashDeleteButton;
    @FXML private Button dashSearchButton;
    @FXML private TableView tableView;

    /*global variables, images, etc...*/
    private Stage stage = new Stage();
    private Image check = new Image(getClass().getResource("../img/check.png").toExternalForm());
    private Image cross = new Image(getClass().getResource("../img/cross.png").toExternalForm());

    /*preloads nodes, events, etc.. into 1st window*/
    public void initialize() {
        //returned Arraylist manipulation, creates first window panes for each product. BRAIN IS DEAD....
        ArrayList<Products> productsArrayList = ProductsDAO.selectNewProducts();
        for (Products products : productsArrayList) {
            createNewProductPane(products);
        }
    }

    /*loads login*/
    public void loadLogin(ActionEvent previousWindowCloser) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.LOGIN_FILE_LOCATION));
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(previousWindowCloser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*loads registration form*/
    public void loadRegistration(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.REGISTRATION_FILE_LOCATION));
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*loads dashboard*/
    public void loadDashboard(ActionEvent previousWindowCloser, String userName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.DASHBOARD_FILE_LOCATION));
            stage.setTitle("Dashboard");
            stage.setMaximized(true);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            closePreviousWindow(previousWindowCloser);

            //This will work only after css is loaded. Adds username text
            Label dashUsernameLabel = (Label) root.lookup("#dashUserNameLabel");
            if (dashUsernameLabel != null) dashUsernameLabel.setText(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*checks for valid fields' input in login form, displays errorcode accordingly*/
    public void loginErrorCheck(ActionEvent previousWindowCloser) {
        String userName = logUsernameField.getText();
        String userEmail = logUsernameField.getText();
        String loginPass = logPasswordField.getText();
        if (userName.isEmpty()) {
            logErrorField.setText("Please enter your username.");
        } else if (loginPass.isEmpty()) {
            logErrorField.setText("Please enter your password.");
        } else if (!Validation.isValidUserName(userName) && !Validation.isValidEmail(userEmail)) {
            logErrorField.setText("Invalid username or e-mail.");
        } else if (!UsersDAO.selectUsername(userName).toString().contains(userName) && !UsersDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            logErrorField.setText("Username or e-mail does not exist.");
        } else if (!Validation.isValidPassword(loginPass)) {
            logErrorField.setText("Invalid password. Minimum 5 letters and numbers.");
        } else if (!UsersDAO.selectUsernamePass(userName).toString().contains(loginPass) && !UsersDAO.selectEmailPass(userEmail).toString().contains(loginPass)) {
            logErrorField.setText("Wrong password");
        } else {
            loadDashboard(previousWindowCloser, userName);
        }
    }

    /*checks for valid fields' input in registration form, displays errorcode accordingly*/
    public void registrationErrorCheck() {
        String userName = regUsernameField.getText();
        String regPass = regPasswordField.getText();
        String regPass1 = regPasswordField1.getText();
        String userEmail = regEmailField.getText();
        boolean isAdmin = regAdminRightsCheckbox.isSelected();
        if (userName.isEmpty()) {
            regErrorField.setText("Please enter your username.");
        } else if (userEmail.isEmpty()) {
            regErrorField.setText("Please enter your e-mail.");
        } else if (regPass.isEmpty()) {
            regErrorField.setText("Please enter your password.");
        } else if (regPass1.isEmpty()) {
            regErrorField.setText("Please confirm your password.");
        } else if (!Validation.isValidUserName(userName)) {
            regErrorField.setText("Invalid username. Minimum 5 letters and numbers");
        } else if (UsersDAO.selectUsername(userName).toString().contains(userName)) {
            regErrorField.setText("Username already exists.");
        } else if (!Validation.isValidEmail(userEmail)) {
            regErrorField.setText("Invalid e-mail. Example: mail@example.com");
        } else if (UsersDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            regErrorField.setText("This e-mail is already in use.");
        } else if (!Validation.isValidPassword(regPass) && !Validation.isValidPassword(regPass1)) {
            regErrorField.setText("Invalid password. Minimum 5 letters and numbers and/or #,_");
        } else if (userName.contains(regPass) || regPass.contains(userName)) {
            regErrorField.setText("Please do not use password similar to username.");
        } else if (!regPass.equals(regPass1)) {
            regErrorField.setText("Passwords don't match.");
        } else {
            Users users = new Users(userName, regPass, userEmail, isAdmin);
            UsersDAO.insert(users);
            regErrorField.setTextFill(Color.GREEN);
            regErrorField.setText("Registration successful.");
            regErrorField.setVisible(true);
            regRegisterButton.setText("Login");
            regRegisterButton.setOnAction(previousWindowCloser -> loadDashboard(previousWindowCloser, userName)
            );
        }
    }

    /*closes previous stage*/
    public void closePreviousWindow(ActionEvent previousWindowCloser) {
        Stage root = (Stage) ((Node) previousWindowCloser.getSource()).getScene().getWindow();
        root.hide();
    }

    /*checks if typed chars in username field are valid, changes the image accordingly*/
    public void checkValidTypingOfUsernameField() {
        if (!Validation.isValidTypedUsernameEmail(logUsernameField.getText())) {
            logImageForUsername.setImage(cross);
        } else {
            logImageForUsername.setImage(check);
        }
    }

    /*checks if typed chars in password field are valid, changes the image accordingly*/
    public void checkValidTypingOfPasswordField() {
        if (!Validation.isValidTypedPassword(logPasswordField.getText())) {
            logImageForPassword.setImage(cross);
        } else {
            logImageForPassword.setImage(check);
        }
    }

    public void createNewProductPane(Products products) {
        GridPane gridPane = new GridPane();

        Image image = new Image(new ByteArrayInputStream(products.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        gridPane.add(imageView, 0, 0, 3, 1);

        Label newOffer = new Label("Naujas pasiūlymas");
        newOffer.setId("new_offer");
        newOffer.setMinHeight(100);
        gridPane.setValignment(newOffer, VPos.TOP);
        gridPane.add(newOffer, 0, 0);

        Label productName = new Label(products.getProductName());
        gridPane.add(productName, 0, 1);

        Label productPrice = new Label(Double.toString(products.getProductPrice()) + "\u20AC");
        gridPane.add(productPrice, 1, 1);

        Label time = new Label("Time remaining");
        gridPane.add(time, 2, 1);

        Button welcomeToShop = new Button("Užekite į parduotuvę");
        gridPane.add(welcomeToShop, 1, 2);

        mainFlowPane.getChildren().add(gridPane);
    }
}