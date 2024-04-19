package com.example.indisky;

public class MyBookingsFlightDetails {
    private String origin;
    private String destination;
    private String departureDate;
    private int price;

    public MyBookingsFlightDetails(String origin, String destination, String departureDate, int price) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public int getPrice() {
        return price;
    }
}

