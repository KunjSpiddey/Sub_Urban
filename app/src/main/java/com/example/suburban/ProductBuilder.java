package com.example.suburban;
public class ProductBuilder {
    private String name;
    private String imageUri;
    private String brand;
    private String color;
    private String contains;
    private String productId;
    private String productCategory;
    private String deliveryCharge;
    private String description;
    private String discountPrice;
    private String originalPrice;
    private String quantity;
    private String size;
    private String productType;
    private String returnPolicy;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public ProductBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ProductBuilder setContains(String contains) {
        this.contains = contains;
        return this;
    }

    public ProductBuilder setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public ProductBuilder setProductCategory(String productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public ProductBuilder setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public ProductBuilder setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public ProductBuilder setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public ProductBuilder setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
        return this;
    }

    public addedProducts build() {
        addedProducts item = new addedProducts();
        item.setProductName(name);
        item.setImage_uri(imageUri);
        item.setBrand(brand);
        item.setColor(color);
        item.setContains(contains);
        item.setId(productId);
        item.setProductCategory(productCategory);
        item.setProductDeliveryCharge(deliveryCharge);
        item.setProductDescription(description);
        item.setProductDiscountPrice(discountPrice);
        item.setProductOriginalPrice(originalPrice);
        item.setProductQuantity(quantity);
        item.setProductSize(size);
        item.setProductType(productType);
        item.setReturn(returnPolicy);
        return item;
    }
}
