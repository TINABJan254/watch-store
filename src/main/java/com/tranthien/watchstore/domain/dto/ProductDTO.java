package com.tranthien.watchstore.domain.dto;

public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private String factory;
    private long soldQuantity;

    public ProductDTO(long id, String name, double price, String factory, long soldQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.factory = factory;
        this.soldQuantity = soldQuantity;
    }

    public ProductDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getSoldQuantity() {
        return this.soldQuantity;
    }

    public void setSoldQuantity(long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
