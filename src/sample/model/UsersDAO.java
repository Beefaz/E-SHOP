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


    public static Users selectUserID(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isAdmin = false;
        Users users = null;
        try {
            connection = DriverManager.getConnection(Constants.URL, "root", "");

            preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE user_name = ?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                //int id, String username, String password, String email, boolean admin
                users = new Users (id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    /*public static Users selectUserID(String userName) {
        Users users = null;
        String query = "SELECT user_id FROM users WHERE user_name = '" + userName + "'";
        try {
            Connection prisijungimas = DriverManager.getConnection(Constants.URL, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            ResultSet rezultatas = uzklausa.executeQuery(query);
            System.out.println(rezultatas.toString());
            while (rezultatas.next()) {
                System.out.println(rezultatas.toString());
                int userID = rezultatas.getInt("user_id");
                System.out.println(userID);
                users = new Users (rezultatas.getInt(userID));
                System.out.println(users.toString());
            }
            uzklausa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }*/
}
