package sample.model;


public class Users {
    private int userID;
    private String userCreationTime;
    private String userName;
    private String userPassword;
    private String userEmail;
    private boolean userAdmin;

    public Users(int userID, String userCreationTime, String userName, String userPassword, String userEmail, boolean admin) {
        this.userID = userID;
        this.userCreationTime = userCreationTime;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdmin = admin;
    }


    public Users(String userCreationTime, String userName, String userPassword, String userEmail, boolean admin) {
        this.userCreationTime = userCreationTime;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdmin = admin;
    }

    //kurimui
    public Users(String userName, String userPassword, String userEmail, boolean userAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdmin = userAdmin;
    }

    public Users(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public Users(int userID) {
        this.userID = userID;
    }

    public Users() {
    }

    public int getUserID() {
        return userID;
    }

    public String getUserCreationTime() {
        return userCreationTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean admin) {
        this.userAdmin = admin;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userCreationTime='" + userCreationTime + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAdmin=" + userAdmin +
                '}';
    }
}