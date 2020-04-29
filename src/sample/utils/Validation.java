package sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USER_NAME_REGEX_PATTERN = "^[a-zA-Z0-9]{5,20}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9#_]{5,20}$"; //"^[a-zA-Z0-9@!*_]{5,20}$";
    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9]{3,20}+@[a-zA-Z0-9]{3,20}+.[a-zA-Z0-9]{2,20}$";
    public static final String TYPED_USERNAME_EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9@._]{1,20}$";
    public static final String TYPED_PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9#_]{1,20}$";

    public static boolean isValidUserName(String userName){
        Pattern pattern = Pattern.compile(USER_NAME_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return matcher.find();
    }
    public static boolean isValidPassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public static boolean isValidEmail(String Email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(Email);
        return matcher.find();
    }
    public static boolean isValidTypedUsernameEmail(String typedUsernameEmail){
        Pattern pattern = Pattern.compile(TYPED_USERNAME_EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(typedUsernameEmail);
        return matcher.find();
    }

    public static boolean isValidTypedPassword(String typedPassword){
        Pattern pattern = Pattern.compile(TYPED_PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(typedPassword);
        return matcher.find();
    }
}