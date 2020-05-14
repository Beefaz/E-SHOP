package sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USER_NAME_REGEX_PATTERN = "^[a-zA-Z0-9]{5,20}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9#_]{5,20}$"; //"^[a-zA-Z0-9@!*_]{5,20}$";
    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9]{3,20}+@[a-zA-Z0-9]{3,20}+.[a-zA-Z0-9]{2,20}$";
    public static final String TYPED_USERNAME_EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9@._]{1,20}$";
    public static final String TYPED_PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9#_]{1,20}$";
    public static final String LOCAL_PHONE_PATTERN = "^[0-9]{9,10}$";
    public static final String INTERNATIONAL_LT_PHONE_PATTERN = "^[0-9+]{12,13}$";
    public static final String ID_REGEX_PATTERN = "^[0-9]{1,15}$";

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
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    public static boolean isValidTypedUsernameEmail(String usernameOrEmailTypo){
        Pattern pattern = Pattern.compile(TYPED_USERNAME_EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(usernameOrEmailTypo);
        return matcher.find();
    }

    public static boolean isValidTypedPassword(String passwordTypo){
        Pattern pattern = Pattern.compile(TYPED_PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(passwordTypo);
        return matcher.find();
    }

    public static boolean isValidPhone(String phone){
        Pattern pattern = Pattern.compile(LOCAL_PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static boolean isValidInternationalPhone(String phone){
        Pattern pattern = Pattern.compile(INTERNATIONAL_LT_PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static boolean isValidID(String id){
        Pattern pattern = Pattern.compile(ID_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }
}