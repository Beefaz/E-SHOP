package sample.model;

import sample.utils.Constants;
import java.sql.*;
import java.util.ArrayList;

public class UsersDAO {
    public static void insert(Users users) {
        String query = "INSERT INTO users (user_name, user_password, user_email, user_is_admin) VALUES (?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = connection.prepareStatement(query);
            uzklausa.setString(1, users.getUserName());
            uzklausa.setString(2, users.getUserPassword());
            uzklausa.setString(3, users.getUserEmail());
            uzklausa.setBoolean(4, users.getUserAdmin());
            uzklausa.executeUpdate();
            uzklausa.close();
            System.out.println(Constants.ACTION_SUCCESSFUL);
        } catch (SQLException e) {
            System.out.println(Constants.ACTION_FAILED);
            e.printStackTrace();
        }
    }

    public static ArrayList selectUsername(String userName) {
        String query = "SELECT user_name, user_email FROM users WHERE user_name LIKE '" + userName + "'";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_name")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            System.out.println("failed1");
            e.printStackTrace();
        }
        return returningArray;
    }

    public static ArrayList selectEmail(String userEmail) {
        String query = "SELECT user_email FROM users WHERE user_email LIKE '" + userEmail + "'";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_email")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            System.out.println("failed2");
            e.printStackTrace();
        }
        return returningArray;
    }

    public static ArrayList selectUsernamePass(String userName) {
        String query = "SELECT user_name, user_email FROM users WHERE user_name LIKE '" + userName + "'";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_name")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            System.out.println("failed3");
            e.printStackTrace();
        }
        return returningArray;
    }

    public static ArrayList selectEmailPass(String userEmail) {
        String query = "SELECT user_email, user_password FROM users WHERE user_email LIKE '" + userEmail + "'";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_email")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            System.out.println("failed4");
            e.printStackTrace();
        }
        return returningArray;
    }
}
