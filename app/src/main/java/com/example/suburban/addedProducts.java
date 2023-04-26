package com.example.suburban;

public class addedProducts {


    String productTitle;
    String Id;
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

    public addedProducts(String Id ,String Image_uri, String productName, String productDescription, String productQuantity, String productOriginalPrice, String productDeliveryCharge, String productSize, String productDiscountPrice, String productCategory, String productType) {

        this.productName = productName;
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
    }

    public addedProducts() {

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


}
