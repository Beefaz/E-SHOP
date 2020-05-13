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
}
