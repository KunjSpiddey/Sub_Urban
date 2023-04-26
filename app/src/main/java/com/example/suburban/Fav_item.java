package com.example.suburban;

public class Fav_item {
    String id;
    String name;
    String image_uri;
    String oprice;
    String dprice;

    boolean state;

    public Fav_item(String id, String name, String image_uri, String oprice, String dprice) {
        this.id = id;
        this.name = name;
        this.image_uri = image_uri;
        this.oprice = oprice;
        this.dprice = dprice;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Fav_item() {
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

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
        this.dprice = dprice;
    }
}
