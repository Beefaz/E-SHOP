package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import sample.model.Products;
import sample.model.ProductsDAO;
import sample.model.Users;
import sample.model.UsersDAO;
import sample.utils.Constants;
import sample.utils.Validation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    /*login nodes*/
    @FXML
    private ImageView log_icon_valid_username;
    @FXML
    private ImageView log_icon_valid_password;
    @FXML
    private TextField log_username_field;
    @FXML
    private PasswordField log_password_field;
    @FXML
    private Label log_error_field;

    /*registration nodes*/
    @FXML
    private TextField reg_username_field;
    @FXML
    private TextField reg_email_field;
    @FXML
    private PasswordField reg_password_field;
    @FXML
    private PasswordField reg_password_field1;
    @FXML
    private Label reg_error_field;
    @FXML
    private Button reg_register;
    @FXML
    private CheckBox reg_admin_checkbox;

    /*dashboard nodes*/
    @FXML
    private Label dash_user_is_admin;
    @FXML
    private TextField dash_id_field;
    @FXML
    private Label dash_username_label;
    @FXML
    private TextField dash_phone;
    @FXML
    private TextField dash_city;
    @FXML
    private TextField dash_product_name;
    @FXML
    private TextField dash_price_euro;
    @FXML
    private TextField dash_price_cents;
    @FXML
    private ImageView dash_image_drop;
    @FXML
    private Label dash_image_box_text;
    @FXML
    private CheckBox check_box1, check_box2, check_box3, check_box4, check_box5, check_box6, check_box7, check_box8, check_box9, check_box10, check_box11;
    @FXML
    private RadioButton radio_button_1, radio_button_2, radio_button_3, radio_button_4, radio_button_5;
    @FXML
    private ComboBox dash_advertisement_length;
    @FXML
    private Label dash_error_field;
    @FXML
    private Button dash_create_btn, dash_update_btn, dash_delete_btn, dash_search_btn;
    @FXML
    private TableView dash_table;


    /*global variables, images, etc...*/
    private Stage stage = new Stage();
    private Image check = new Image(getClass().getResource("../img/check.png").toExternalForm());
    private Image cross = new Image(getClass().getResource("../img/cross.png").toExternalForm());
    private ResultSet results;
    private ObservableList<ObservableList> tableContents = FXCollections.observableArrayList();


    /*preloads nodes, events, etc.. into 1st window*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    public void loadRegistration(ActionEvent previousWindowCloser) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Constants.REGISTRATION_FILE_LOCATION));
            stage.setTitle("Registration");
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(previousWindowCloser);
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
            stage.setScene(new Scene(root));
            stage.show();
            closePreviousWindow(previousWindowCloser);
            //This will work only after everything is loaded. Adds username text, adds admin text, enables button Update if admin logged in
            Label dashUsername = (Label) root.lookup("#dash_username_label");
            if (dashUsername != null) {
                dashUsername.setText(userName);
            }
            if (UsersDAO.selectUserID(userName).getUserAdmin()) {
                Label dashAdmin = (Label) root.lookup("#dash_user_is_admin");
                dashAdmin.setText("Admin");
                TextField id = (TextField) root.lookup("#dash_id_field");
                id.setVisible(true);

                Button delete = (Button) root.lookup("#dash_delete_btn");
                delete.setDisable(false);

                Button update = (Button) root.lookup("#dash_update_btn");
                update.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*checks for valid fields' input in login form, displays errorcode accordingly*/
    public void loginErrorCheck(ActionEvent previousWindowCloser) {
        String userName = log_username_field.getText();
        String userEmail = log_username_field.getText();
        String loginPass = log_password_field.getText();
        if (userName.isEmpty()) {
            log_error_field.setText("Please enter your username.");
        } else if (loginPass.isEmpty()) {
            log_error_field.setText("Please enter your password.");
        } else if (!Validation.isValidUserName(userName) && !Validation.isValidEmail(userEmail)) {
            log_error_field.setText("Invalid username or e-mail.");
        } else if (!UsersDAO.selectUsername(userName).toString().contains(userName) && !UsersDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            log_error_field.setText("Username or e-mail does not exist.");
        } else if (!Validation.isValidPassword(loginPass)) {
            log_error_field.setText("Invalid password. Minimum 5 letters and numbers.");
        } else if (!UsersDAO.selectUsernamePass(userName).toString().contains(loginPass) && !UsersDAO.selectEmailPass(userEmail).toString().contains(loginPass)) {
            log_error_field.setText("Wrong password");
        } else {
            loadDashboard(previousWindowCloser, userName);
        }
    }

    /*checks for valid fields' input in registration form, displays errorcode accordingly*/
    public void registrationErrorCheck() {
        String userName = reg_username_field.getText();
        String regPass = reg_password_field.getText();
        String regPass1 = reg_password_field1.getText();
        String userEmail = reg_email_field.getText();
        boolean isAdmin = reg_admin_checkbox.isSelected();
        if (userName.isEmpty()) {
            reg_error_field.setText("Please enter your username.");
        } else if (userEmail.isEmpty()) {
            reg_error_field.setText("Please enter your e-mail.");
        } else if (regPass.isEmpty()) {
            reg_error_field.setText("Please enter your password.");
        } else if (regPass1.isEmpty()) {
            reg_error_field.setText("Please confirm your password.");
        } else if (!Validation.isValidUserName(userName)) {
            reg_error_field.setText("Invalid username. Minimum 5 letters and numbers");
        } else if (UsersDAO.selectUsername(userName).toString().contains(userName)) {
            reg_error_field.setText("Username already exists.");
        } else if (!Validation.isValidEmail(userEmail)) {
            reg_error_field.setText("Invalid e-mail. Example: mail@example.com");
        } else if (UsersDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            reg_error_field.setText("This e-mail is already in use.");
        } else if (!Validation.isValidPassword(regPass) && !Validation.isValidPassword(regPass1)) {
            reg_error_field.setText("Invalid password. Minimum 5 letters and numbers and/or #,_");
        } else if (userName.contains(regPass) || regPass.contains(userName)) {
            reg_error_field.setText("Please do not use password similar to username.");
        } else if (!regPass.equals(regPass1)) {
            reg_error_field.setText("Passwords don't match.");
        } else {
            UsersDAO.insert(new Users(userName, regPass, userEmail, isAdmin));
            reg_error_field.setTextFill(Color.GREEN);
            reg_error_field.setText("Registration successful.");
            reg_register.setText("Login");
            reg_register.setOnAction(previousWindowCloser -> loadDashboard(previousWindowCloser, userName));
        }
    }

    /*closes previous stage*/
    public void closePreviousWindow(ActionEvent previousWindowCloser) {
        Stage root = (Stage) ((Node) previousWindowCloser.getSource()).getScene().getWindow();
        root.hide();
    }

    /*checks if typed chars in username field are valid, changes the image accordingly*/
    public void checkValidTypingOfUsernameField() {
        if (!Validation.isValidTypedUsernameEmail(log_username_field.getText())) {
            log_icon_valid_username.setImage(cross);
        } else {
            log_icon_valid_username.setImage(check);
        }
    }

    /*checks if typed chars in password field are valid, changes the image accordingly*/
    public void checkValidTypingOfPasswordField() {
        if (!Validation.isValidTypedPassword(log_password_field.getText())) {
            log_icon_valid_password.setImage(cross);
        } else {
            log_icon_valid_password.setImage(check);
        }
    }

    //creates checkbox group, disables unchecked checkboxes (hardcoded cb's for now)
    public void checkBoxDisable(ActionEvent event) {
        /*makes list of checkboxes, hardcoded for now*/
        ObservableSet<CheckBox> checkBoxList = FXCollections.observableSet(check_box1, check_box2, check_box3, check_box4, check_box5, check_box6, check_box7, check_box8, check_box9, check_box10, check_box11);
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isDisabled()) {
                checkBox.setDisable(false);
            } else if (!checkBox.isSelected()) {
                checkBox.setDisable(true);
            }
        }
        CheckBox eventSourceBox = (CheckBox) event.getSource();
        eventSourceBox.setDisable(false);
    }

    public void createProduct(ActionEvent event) {
        String text = ((Button) event.getSource()).getText();
        String phone = dash_phone.getText();
        String city = dash_city.getText();
        String product = dash_product_name.getText();
        String price_euro = dash_price_euro.getText();
        String price_cents = dash_price_cents.getText();
        ArrayList productCategory = new ArrayList();
        ArrayList deliveryMethods = new ArrayList();

        /*makes list of checkboxes, checks if any checkbox was selected, hardcoded (can't use static method) for now*/
        ObservableSet<CheckBox> checkBoxList = FXCollections.observableSet(check_box1, check_box2, check_box3, check_box4, check_box5, check_box6, check_box7, check_box8, check_box9, check_box10, check_box11);
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                productCategory.add(checkBox.getText());
            }
        }
        /*makes list of radioboxes, checks if any radiobox was selected, hardcoded (can't use static method) for now*/
        ObservableSet<RadioButton> radioButtonList = FXCollections.observableSet(radio_button_1, radio_button_2, radio_button_3, radio_button_4, radio_button_5);
        for (RadioButton radioButton : radioButtonList) {
            if (radioButton.isSelected()) {
                deliveryMethods.add(radioButton.getText());
            }
        }

        /*validations*/
        if (product.isEmpty()) {
            dash_error_field.setText("Įveskite prekės pavadinimą");
        } else if (phone.isEmpty()) {
            dash_error_field.setText("Įveskite kontaktinį telefoną");
        } else if (!Validation.isValidPhone(phone) && !Validation.isValidInternationalPhone(phone)) {
            dash_error_field.setText("Neteisingai įvestas telefono nr. Pvz +370**, 8**");
        } else if (city.isEmpty()) {
            dash_error_field.setText("Įveskite miestą");
        } else if (dash_image_drop.getImage().isError()) {
            dash_error_field.setText("Nepasirinkote nuotraukos");
        } else if (price_euro.isEmpty()) {
            dash_error_field.setText("Įveskite kainą");
        } else if (productCategory.isEmpty()) {
            dash_error_field.setText("Pasirinkite produktų grupę");
        } else if (deliveryMethods.isEmpty()) {
            dash_error_field.setText("Pasirinkime pristatymo būdą");
        } else if (dash_advertisement_length.getSelectionModel().isEmpty()) {
            dash_error_field.setText("Pasirinkite skelbimo galiojimo laiką");
        } else {

            /*object double need to be converted to ints. WORKS, PARTIALLY, puts BIN instead of image to SQ:, and it's empty. Keeping this for personal interest :)
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int height = (int) dash_image_drop.getImage().getHeight();
            int width = (int) dash_image_drop.getImage().getWidth();
            byte[]buffer = new byte [width * height * 4];
            dash_image_drop.getImage().getPixelReader().getPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);
            byteArrayOutputStream.writeBytes(buffer);
            System.out.println(byteArrayOutputStream.toByteArray());
            */

            /*buffers image into byte array*/
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(dash_image_drop.getImage(), null);
            try {
                ImageIO.write(bufferedImage, "jpg", output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] imageData = output.toByteArray();

            String advertisementLength = dash_advertisement_length.getSelectionModel().getSelectedItem().toString();
            Users userID = UsersDAO.selectUserID(dash_username_label.getText());
            ProductsDAO.insert(new Products(userID.getUserID(), phone, city, product, Double.parseDouble(price_euro) + Double.parseDouble(price_cents) / 100, productCategory.toString(), deliveryMethods.toString(), Integer.parseInt(advertisementLength), imageData));
            dash_error_field.setTextFill(Color.GREEN);
            dash_error_field.setText("Skelbimas patalpintas");
        }
    }

    public void getImage(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void showImage(DragEvent dragEvent) throws FileNotFoundException {
        List<File> files = dragEvent.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        dash_image_drop.setImage(img);
        dash_image_box_text.setText("");
    }

    public void update() {
        String phone = dash_phone.getText();
        String city = dash_city.getText();
        String product = dash_product_name.getText();
        String price_euro = dash_price_euro.getText();
        String price_cents = dash_price_cents.getText();
        ArrayList productCategory = new ArrayList();
        ArrayList deliveryMethods = new ArrayList();
        String productID = dash_id_field.getText();

        /*makes list of checkboxes, checks if any checkbox was selected, hardcoded (can't use static method) for now*/
        ObservableSet<CheckBox> checkBoxList = FXCollections.observableSet(check_box1, check_box2, check_box3, check_box4, check_box5, check_box6, check_box7, check_box8, check_box9, check_box10, check_box11);
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                productCategory.add(checkBox.getText());
            }
        }
        /*makes list of radioboxes, checks if any radiobox was selected, hardcoded (can't use static method) for now*/
        ObservableSet<RadioButton> radioButtonList = FXCollections.observableSet(radio_button_1, radio_button_2, radio_button_3, radio_button_4, radio_button_5);
        for (RadioButton radioButton : radioButtonList) {
            if (radioButton.isSelected()) {
                deliveryMethods.add(radioButton.getText());
            }
        }
        if (productID.isEmpty()) {
            dash_error_field.setText("Pasirinkite produkto ID redagavimui");
        } else if (product.isEmpty()) {
            dash_error_field.setText("Įveskite prekės pavadinimą");
        } else if (phone.isEmpty()) {
            dash_error_field.setText("Įveskite kontaktinį telefoną");
        } else if (!Validation.isValidPhone(phone) && !Validation.isValidInternationalPhone(phone)) {
            dash_error_field.setText("Neteisingai įvestas telefono nr. Pvz +370**, 8**");
        } else if (city.isEmpty()) {
            dash_error_field.setText("Įveskite miestą");
        } else if (price_euro.isEmpty()) {
            dash_error_field.setText("Įveskite kainą");
        } else if (productCategory.isEmpty()) {
            dash_error_field.setText("Pasirinkite produktų grupę");
        } else if (deliveryMethods.isEmpty()) {
            dash_error_field.setText("Pasirinkime pristatymo būdą");
        } else {
            Products products = new Products(Integer.parseInt(productID), phone, city, product, Double.parseDouble(price_euro) + Double.parseDouble(price_cents) / 100, productCategory.toString(), deliveryMethods.toString());
            ProductsDAO productsDAO = new ProductsDAO();
            productsDAO.editByID(products);
            try {
                getProductTable("");
            } catch (Exception e) {
                dash_error_field.setText("Toks įrašas neegzistuoja");
                e.printStackTrace();
            }
        }
    }

    public void searchDB() throws SQLException {
        getProductTable(dash_product_name.getText());
    }

    public void delete() throws SQLException {
        dash_error_field.setText("");
        String id = dash_id_field.getText();
        if (id.isEmpty()) {
            dash_error_field.setText("Įveskite produkto ID");
        } else if (!Validation.isValidID(id)) {
            dash_error_field.setText("ID privalo suaryti skaičiai");
        } else {
            int productID = Integer.parseInt(dash_id_field.getText());
            ProductsDAO productsDAO = new ProductsDAO();
            productsDAO.deleteById(productID);
            getProductTable("");
        }
    }

    public void getProductTable(String productName) throws SQLException {
        ProductsDAO productsDAO = new ProductsDAO();
        UsersDAO usersDAO = new UsersDAO();
        Users users = usersDAO.selectUserDetails(dash_username_label.getText());
        results = productsDAO.selectByProductUser(productName, users);
        getColumnList();
        getRowList();
    }

    private void getColumnList() throws SQLException {
        dash_table.getColumns().clear();
        if (results != null) {
            for (int i = 0; i < results.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn tableColumn = new TableColumn(results.getMetaData().getColumnName(i + 1).toUpperCase());
                tableColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                dash_table.getColumns().removeAll(tableColumn);
                dash_table.getColumns().addAll(tableColumn);
            }
        } else {
            dash_error_field.setText("No columns to display");
        }
    }

    private void getRowList() throws SQLException {
        tableContents.clear();
        if (results != null) {
            while (results.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= results.getMetaData().getColumnCount() - 1; i++) {
                    row.add(results.getString(i));
                }
                Image image = new Image(new ByteArrayInputStream(results.getBytes(results.getMetaData().getColumnCount())));
                ImageView imageView = new ImageView(image);
                row.add(imageView);
                tableContents.add(row);
            }
            dash_table.setItems(tableContents);
        } else {
            dash_error_field.setText("No rows to display");
        }
    }
}