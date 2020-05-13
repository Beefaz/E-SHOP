package sample.model;

import sample.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

public class ProductsDAO {

    public static void insert(Products products) {
        String query = "INSERT INTO products (user_id, phone, city, product_name, product_price, product_category, delivery_method, advertisement_length, image) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = connection.prepareStatement(query);
            uzklausa.setInt(1, products.getUserID());
            uzklausa.setString(2, products.getPhone());
            uzklausa.setString(3, products.getCity());
            uzklausa.setString(4, products.getProductName());
            uzklausa.setDouble(5, products.getProductPrice());
            uzklausa.setString(6, products.getProductCategory());
            uzklausa.setString(7, products.getDeliveryMethod());
            uzklausa.setInt(8, products.getAdvertisementLength());
            uzklausa.setBytes(9, products.getImage());
            uzklausa.executeUpdate();
            uzklausa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Products> selectNewProducts(int LimitCount, int offsetCount) {
        String query = "SELECT product_name, product_price, image FROM products WHERE (image is NOT NULL) ORDER BY advertisement_timestamp DESC LIMIT " + LimitCount + " OFFSET " + offsetCount;
        ArrayList<Products> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Products(rezultatas.getString("product_name"), rezultatas.getDouble("product_price"), rezultatas.getBytes("image")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returningArray;
    }

    public ResultSet selectByProductUser(String productName, Users users) {
        String uzklausa = "";
        if (users.getUserAdmin()) {
            if (productName.equals("")) {
                uzklausa = "SELECT * FROM products";
            } else {
                uzklausa = "SELECT * FROM products WHERE product_name LIKE '" + productName + "'";
            }
        } else {
            if (productName.equals("")) {
                uzklausa = "SELECT * FROM products WHERE user_id = '" + users.getUserID() + "'";
            } else {
                uzklausa = "SELECT * FROM products WHERE user_id = '" + users.getUserID() + "' AND product_name LIKE '" + productName + "'";
            }
        }
        ResultSet rezultatas = null;
        Connection prisijungimas = null;
        PreparedStatement uzklausa2 = null;
        try {
            prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            uzklausa2 = prisijungimas.prepareStatement(uzklausa);
            rezultatas = uzklausa2.executeQuery(uzklausa);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultatas;
    }

    public void editByID(Products products) {
        String uzklausa = "UPDATE products SET phone=?, city=?, product_name=?, product_price=?, product_category=?, delivery_method=?, advertisement_length=? " +
                " WHERE product_id=?";

        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement preparedStatement = prisijungimas.prepareStatement(uzklausa);
            preparedStatement.setString(1, products.getPhone());
            preparedStatement.setString(2, products.getCity());
            preparedStatement.setString(3, products.getProductName());
            preparedStatement.setDouble(4, products.getProductPrice());
            preparedStatement.setString(5, products.getProductCategory());
            preparedStatement.setString(6, products.getDeliveryMethod());
            preparedStatement.setInt(7, products.getAdvertisementLength());
            preparedStatement.setInt(8, products.getProductID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int productId) {
        String uzklausa = "DELETE FROM products WHERE id=?";
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement preparedStatement = prisijungimas.prepareStatement(uzklausa);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
