package com.example.suburban;

public class addtoproductsITEM {


    String id;
    String name;
    String brand;
    String dprice;
    String oprice;
    String image_uri;
    String size;

    public addtoproductsITEM() {
    }

    public addtoproductsITEM(String id, String name, String brand, String dprice, String oprice, String image_uri, String size) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.dprice = dprice;
        this.oprice = oprice;
        this.image_uri = image_uri;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
        this.dprice = dprice;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }





}
