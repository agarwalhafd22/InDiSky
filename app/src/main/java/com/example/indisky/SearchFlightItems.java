package com.example.indisky;

public class SearchFlightItems {
    String originDate, destDate, flightID;
    int price;

    public SearchFlightItems(String originDate, String destDate, String flightID, int price) {
        this.originDate = originDate;
        this.destDate = destDate;
        this.flightID = flightID;
        this.price = price;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(String originDate) {
        this.originDate = originDate;
    }

    public String getDestDate() {
        return destDate;
    }

    public void setDestDate(String destDate) {
        this.destDate = destDate;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
