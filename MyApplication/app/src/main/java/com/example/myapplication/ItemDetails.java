package com.example.myapplication;

public class ItemDetails {

    private int id;
    private  String name;
    private String category;
    private String brand;
    private double price;
    private String purchasedDate;
    private String warrantyDuration;


    public ItemDetails(int id, String name, String category, String brand, double price, String purchasedDate, String warrantyDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.purchasedDate = purchasedDate;
        this.warrantyDuration = warrantyDate;
    }

    public ItemDetails() {
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", purchasedDate=" + purchasedDate +
                ", warrantyDate=" + warrantyDuration +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getWarrantyDuration() {
        return warrantyDuration;
    }

    public void setWarrantyDate(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }
}
