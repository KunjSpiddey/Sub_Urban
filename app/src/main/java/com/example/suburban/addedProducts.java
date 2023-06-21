package com.example.suburban;

public class addedProducts {
/////////////////

    String productTitle;
    String Id;
    String brand;
    String productDescription;
    String productQuantity;
    String productOriginalPrice;
    String productDeliveryCharge;
    String productSize;
    String productDiscountPrice;
    String productCategory;
    String productType ;
    String Image_uri;
    String productName;
    String color;
   Double Shoulder;
   Double Chest;

   String Fabric;
   String Contains;
   String Return;

   String randomKey;

    public addedProducts(String Id ,String Image_uri, String productName, String productDescription, String productQuantity, String productOriginalPrice, String productDeliveryCharge, String productSize, String productDiscountPrice
            , String productCategory, String productType, String brand , String color , String contains , String Return) {

        this.color = color;
        this.Contains = contains;
        this.productName = productName;
        this.Return = Return;
        this.Id = Id;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.productOriginalPrice = productOriginalPrice;
        this.productDeliveryCharge = productDeliveryCharge;
        this.productSize = productSize;
        this.productDiscountPrice = productDiscountPrice;
        this.productCategory = productCategory;
        this.productType = productType;
        this.Image_uri = Image_uri;
        this.brand = brand;
        this.randomKey = randomKey;

    }

    public addedProducts() {

    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContains() {
        return Contains;
    }

    public void setContains(String contains) {
        Contains = contains;
    }

    public String getReturn() {
        return Return;
    }

    public void setReturn(String aReturn) {
        Return = aReturn;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProductName() {
        return productName;
    }

    public String getImage_uri() {
        return Image_uri;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setImage_uri(String image_uri) {
        Image_uri = image_uri;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(String productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public String getProductDeliveryCharge() {
        return productDeliveryCharge;
    }

    public void setProductDeliveryCharge(String productDeliveryCharge) {
        this.productDeliveryCharge = productDeliveryCharge;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public addedProducts build() {
        addedProducts item = new addedProducts();
        item.setProductName(productName);
        item.setImage_uri(Image_uri);
        item.setBrand(brand);
        item.setColor(color);
        item.setContains(Contains);
        item.setId(Id);
        item.setProductCategory(productCategory);
        item.setProductDeliveryCharge(productDeliveryCharge);
        item.setProductDescription(productDescription);
        item.setProductDiscountPrice(productDiscountPrice);
        item.setProductOriginalPrice(productOriginalPrice);
        item.setProductQuantity(productQuantity);
        item.setProductSize(productSize);
        item.setProductType(productType);
        item.setReturn(Return);
        return item;
    }

}
