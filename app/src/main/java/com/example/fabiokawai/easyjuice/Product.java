package com.example.fabiokawai.easyjuice;

public class Product {
    private int id;
    private String name;
    private double price;
    private String location;
    private int size;

    public String toString(){
        return "Produto: " + name + " , R$" + Double.toString(price) + " em " + location;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
