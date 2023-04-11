package com.example.myapplication;

public class Property {

    private String address;
    private String garage;
    private String backYardSize;
    private String frontYardSize;
    private String bedroom;
    private String bathroom;
    private String propertyName;

    public Property(String address, String garage, String backYardSize, String frontYardSize, String bedroom, String bathroom, String propertyName) {
        this.address = address;
        this.garage = garage;
        this.backYardSize = backYardSize;
        this.frontYardSize = frontYardSize;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.propertyName = propertyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getBackYardSize() {
        return backYardSize;
    }

    public void setBackYardSize(String backYardSize) {
        this.backYardSize = backYardSize;
    }

    public String getFrontYardSize() {
        return frontYardSize;
    }

    public void setFrontYardSize(String frontYardSize) {
        this.frontYardSize = frontYardSize;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
