package sample.model;

import sample.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class ProductsDAO {
    public static void insert(Products products) {
        String query = "INSERT INTO products (user_id, product_name, product_price, product_category, delivery_method, advertisement_length, image) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = connection.prepareStatement(query);
            uzklausa.setInt(1, products.getUserID());
            uzklausa.setString(2, products.getProductName());
            uzklausa.setDouble(3, products.getProductPrice());
            uzklausa.setString(4, products.getProductCategory());
            uzklausa.setString(5, products.getDeliveryMethod());
            uzklausa.setInt(6, products.getAdvertisementLength());
            uzklausa.setBytes(7, products.getImage());
            uzklausa.executeUpdate();
            uzklausa.close();
            System.out.println(Constants.ACTION_SUCCESSFUL);
        } catch (SQLException e) {
            System.out.println(Constants.ACTION_FAILED);
            e.printStackTrace();
        }
    }

    public static ArrayList<Products> selectNewProducts() {
        int imageViewCount = Constants.IMAGE_COUNT_SHOWN_IN_1ST_WINDOW;
        String query = "SELECT product_name, product_price, image FROM products ORDER BY advertisement_timestamp DESC LIMIT " + imageViewCount;
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
            System.out.println("failed1");
            e.printStackTrace();
        }
        return returningArray;
    }
}
