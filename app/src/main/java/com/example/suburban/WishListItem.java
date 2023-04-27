package com.example.suburban;

public class WishListItem {

    String id;
    String name;
    String img;
    String oprice;
    String dprice;
    String checked;

    public WishListItem(String id, String name, String img, String oprice, String dprice) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.oprice = oprice;
        this.dprice = dprice;
        this.checked = checked;

    }

    public WishListItem(String id) {
        this.id = id;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
