package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.Users;
import sample.model.UsersDAO;
import sample.utils.Constants;
import sample.utils.Validation;

public class Controller {

    /*current offers nodes*/
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    /*login nodes*/
    @FXML
    private ImageView logImageForUsername;
    @FXML
    private ImageView logImageForPassword;
    @FXML
    private TextField logUsernameField;
    @FXML
    private PasswordField logPasswordField;
    @FXML
    private Label logErrorField;
    @FXML
    private Button logLoginButton;
    @FXML
    private Button logRegisterButton;

    /*registration nodes*/
    @FXML
    private TextField regUsernameField;
    @FXML
    private TextField regEmailField;
    @FXML
    private PasswordField regPasswordField;
    @FXML
    private PasswordField regPasswordField1;
    @FXML
    private Label regErrorField;
    @FXML
    private Button regRegisterButton;
    @FXML
    private CheckBox regAdminRightsCheckbox;

    /*dashboard nodes*/
    @FXML
    private Button dashCreateButton;
    @FXML
    private Button dashUpdateButton;
    @FXML
    private Button dashDeleteButton;
    @FXML
    private Button dashSearchButton;
    @FXML
    private Label dashUsername;

    /*global variables, images, etc...*/
    Stage stage = new Stage();
    Image check = new Image(getClass().getResource("../img/check.png").toExternalForm());
    Image cross = new Image(getClass().getResource("../img/cross.png").toExternalForm());

    /*preloads nodes*/
    public void initialize() {
    }

    /*loads login*/
    public void loadLogin(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.LOGIN_LOCATION));
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*loads registration form*/
    public void loadRegistration(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.REGISTRATION_LOCATION));
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*loads dashboard*/
    public void loadDashboard(ActionEvent actionEvent, String userName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.DASHBOARD_LOCATION));
            stage.setTitle("Dashboard");
            stage.setMaximized(true);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setOnShown((WindowEvent event) -> {
                loadUsernameField(userName);
            });
            closePreviousWindow(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*checks for valid fields' input in login form, displays errorcode accordingly*/
    public void loginErrorCheck(ActionEvent actionEvent) {
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
            loadDashboard(actionEvent, userName);
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
            regErrorField.setText("Invalid username. Minimum 5 letters and numbers.");
        } else if (UsersDAO.selectUsername(userName).toString().contains(userName)) {
            regErrorField.setText("Username already exists.");
        } else if (!Validation.isValidEmail(userEmail)) {
            regErrorField.setText("Invalid e-mail. Example: mail@example.com");
        } else if (UsersDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            regErrorField.setText("This e-mail is already in use.");
        } else if (!Validation.isValidPassword(regPass) && !Validation.isValidPassword(regPass1)) {
            regErrorField.setText("Invalid password. Minimum 5 letters and numbers.");
        } else if (!regPass.equals(regPass1)) {
            regErrorField.setText("Passwords don't match.");
        } else {
            Users users = new Users(userName, regPass, userEmail, isAdmin);
            UsersDAO.insert(users);
            regErrorField.setTextFill(Color.GREEN);
            regErrorField.setText("Registration successful.");
            regErrorField.setVisible(true);
            regRegisterButton.setText("Login");
            regRegisterButton.setOnAction(actionEvent -> loadDashboard(actionEvent, userName)
            );
        }
    }

    /*closes previous stage*/
    public void closePreviousWindow(ActionEvent actionEvent) {
        Stage root = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        root.hide();
    }

    /*-not working yet. Loading of username into dashboard*/
    public void loadUsernameField(String userName) {
        dashUsername.setText(userName);
        System.out.println(userName);
    }

    /*checks if typed chars in username field are valid, changes the image accordingly*/
    public void checkValidTypingOfUsername() {
        if (!Validation.isValidTyping(logUsernameField.getText())) {
            logImageForUsername.setImage(cross);
        } else {
            logImageForUsername.setImage(check);
        }
    }

    /*checks if typed chars in password field are valid, changes the image accordingly*/
    public void checkValidTypingOfPassword() {
        if (!Validation.isValidTyping(logPasswordField.getText())) {
            logImageForPassword.setImage(cross);
        } else {
            logImageForPassword.setImage(check);
        }
    }
}
