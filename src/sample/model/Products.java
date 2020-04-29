package sample.model;

import java.util.Arrays;

public class Products {
    private int productID;
    private int userID;
    private String productName;
    private double productPrice;
    private String productCategory;
    private String deliveryMethod;
    private int advertisementLength;
    private String advertisementTimestamp;
    private byte[] image;

    public Products(int productID, int userID, String productName, double productPrice, String productCategory, String deliveryMethod, int advertisementLength, String advertisementTimestamp, byte[] image) {
        this.productID = productID;
        this.userID = userID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.deliveryMethod = deliveryMethod;
        this.advertisementLength = advertisementLength;
        this.advertisementTimestamp = advertisementTimestamp;
        this.image = image;
    }

    public Products(int productID, int userID, String productName, double productPrice, String productCategory, String deliveryMethod, int advertisementLength, String advertisementTimestamp) {
        this.productID = productID;
        this.userID = userID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.deliveryMethod = deliveryMethod;
        this.advertisementLength = advertisementLength;
        this.advertisementTimestamp = advertisementTimestamp;
    }

    public Products(int productID, String productName, double productPrice, int advertisementLength, String advertisementTimestamp, byte[] image) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.advertisementLength = advertisementLength;
        this.advertisementTimestamp = advertisementTimestamp;
        this.image = image;
    }

    public Products(String productName, double productPrice, byte[] image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.image = image;
    }

    public Products() {
    }

    public int getProductID() {
        return productID;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public int getAdvertisementLength() {
        return advertisementLength;
    }

    public void setAdvertisementLength(int advertisementLength) {
        this.advertisementLength = advertisementLength;
    }

    public String getAdvertisementTimestamp() {
        return advertisementTimestamp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", userID=" + userID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", advertisementLength=" + advertisementLength +
                ", advertisementTimestamp='" + advertisementTimestamp + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}