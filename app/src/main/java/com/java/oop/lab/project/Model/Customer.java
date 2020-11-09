package com.java.oop.lab.project.Model;

public class Customer extends Person implements CustomerBhaviour{

    private String address;
    private String totalPrice;

    public Customer(String name, String picture, String email, double phNo, boolean gender, String address, String totalPrice) {
        super(name, picture, email, phNo, gender);
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void placeOrder() {

    }

    @Override
    public void trackingFood() {

    }
}
