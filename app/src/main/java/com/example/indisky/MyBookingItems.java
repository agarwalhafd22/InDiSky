package com.example.indisky;

public class MyBookingItems {
    String origin, dest, date, name;
    int price;

    public MyBookingItems(String origin, String dest, String date, int price, String name) {
        this.origin = origin;
        this.dest = dest;
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
