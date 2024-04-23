package com.example.indisky;

public class MyBookingItems {
    String origin, dest, date, name, flightID;
    int price, bookingID;

    public MyBookingItems(String origin, String dest, String date, int price, int bookingID, String name, String flightID) {
        this.origin = origin;
        this.dest = dest;
        this.date = date;
        this.bookingID=bookingID;
        this.name = name;
        this.price = price;
        this.flightID = flightID;
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

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
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

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
}
