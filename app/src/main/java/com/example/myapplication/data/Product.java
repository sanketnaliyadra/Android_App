package com.example.myapplication.data;

public class Product {

    public int price;
    public String title;
    public String subTitle;
    public int imageId;
    public float cartCount = 0;

    public Product() {
    }

    public Product(int price, String title, String subTitle, int imageId) {
        this.price = price;
        this.title = title;
        this.subTitle = subTitle;
        this.imageId = imageId;
    }

    public Product(int price, String title, String subTitle, int imageId, int cartCount) {
        this.price = price;
        this.title = title;
        this.subTitle = subTitle;
        this.imageId = imageId;
        this.cartCount = cartCount;
    }

    public float getCartCount() {
        return cartCount;
    }

    public void setCartCount(float cartCount) {
        this.cartCount = cartCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
