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
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
        return returningArray;
    }

    public static ArrayList selectUsernamePass(String userName) {
        String query = "SELECT user_name, user_password FROM users WHERE user_name LIKE '" + userName + "'";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_name"), rezultatas.getString("user_password")));
            }
            uzklausa.close();
        } catch (SQLException e) {
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
                returningArray.add(new Users(rezultatas.getString("user_email"), rezultatas.getString("user_password")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returningArray;
    }

    public static ArrayList selectALL(int userID, String userName, String userEmail) {
        String query = "SELECT * FROM users WHERE (user_id LIKE '%" + userID + "%' OR user_name LIKE '%" + userName + "%' OR user_email LIKE '%" + userEmail + "%')";
        ArrayList<Users> returningArray = new ArrayList<>();
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            while (rezultatas.next()) {
                returningArray.add(new Users(rezultatas.getString("user_email"), rezultatas.getString("user_creation_time"), rezultatas.getString("user_name"), rezultatas.getString("user_email"), rezultatas.getBoolean("user_is_admin")));
            }
            uzklausa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returningArray;
    }


    public static Users selectUserID(String userName) {
        Connection prisijungimas = null;
        PreparedStatement uzklausa = null;
        ResultSet rezultatas = null;
        boolean isAdmin = false;
        Users users = null;
        try {
            prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            uzklausa = prisijungimas.prepareStatement("SELECT user_id, user_is_admin FROM users WHERE user_name = ?");
            uzklausa.setString(1, userName);
            rezultatas = uzklausa.executeQuery();
            while (rezultatas.next()) {
                int id = rezultatas.getInt("user_id");
                boolean userAdmin = rezultatas.getBoolean("user_is_admin");
                users = new Users(id, userAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Users selectUserDetails(String userName) {
        Connection prisijungimas = null;
        PreparedStatement uzklausa = null;
        ResultSet rezultatas = null;
        Users users = null;
        try {
            prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            uzklausa = prisijungimas.prepareStatement("SELECT * FROM users WHERE user_name = ?");
            uzklausa.setString(1, userName);
            rezultatas = uzklausa.executeQuery();
            while (rezultatas.next()) {
                userName = rezultatas.getString("user_name");
                int userID = rezultatas.getInt("user_id");
                String userCreationTime = rezultatas.getString("user_creation_time");
                String userPassword = rezultatas.getString("user_password");
                String userEmail = rezultatas.getString("user_email");
                boolean userAdmin = rezultatas.getBoolean("user_is_admin");
                users = new Users(userID, userCreationTime, userName, userPassword, userEmail, userAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
