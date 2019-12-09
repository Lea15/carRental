package com.efrei.JPAExample;

public class Car {

    private long id;
    private String plateNumber;
    private String brand;
    private int numberOfSeats;
    private int price;
    private boolean rented;
    private int numberOfDays;
    private Person person;

    public Car() {
        super();
    }

    public Car(long id, String plateNumber, String brand, int numberOfSeats, int price, boolean rented, int numberOfDays) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.rented = rented;
        this.numberOfDays = numberOfDays;
    }



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }
    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
