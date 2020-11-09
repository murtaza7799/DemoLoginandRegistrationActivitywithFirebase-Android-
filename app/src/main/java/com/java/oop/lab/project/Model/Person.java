package com.java.oop.lab.project.Model;

public class Person {
    private String name;
    private String picture;
    private String email;
    private double phNo;
    private boolean gender;

    public Person(String name, String picture, String email, double phNo, boolean gender) {
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.phNo = phNo;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public double getPhNo() {
        return phNo;
    }

    public boolean isGender() {
        return gender;
    }
}
