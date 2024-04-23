package com.example.indisky;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MyBookingsFetchData {

    public static List<MyBookingItems> fetchData(Context context, int userId) {
        List<MyBookingItems> dataList = new ArrayList<>();

        // Step 1: Get all booking IDs associated with the provided user ID
        BookingDB bookingDB = new BookingDB(context);
        List<Integer> bookingIds = bookingDB.getBookingIDsByUserID(userId);

        if(!bookingIds.isEmpty()) {
            // Step 2: Iterate over each booking ID to fetch flight and passenger details
            for (int bookingId : bookingIds) {
                // Step 3: Get Flight ID using the obtained Booking ID
                String flightId = bookingDB.getFlightIDByBookingID(bookingId);

                if (flightId != null) {
                    // Step 4: Get Origin, Destination, Departure Date, and Price using Flight ID
                    FlightDB flightDB = new FlightDB(context);
                    MyBookingsFlightDetails myBookingsFlightDetails = flightDB.getFlightDetailsByFlightID(flightId);

                    if (myBookingsFlightDetails != null) {
                        // Step 5: Get Passenger Name using the obtained Booking ID
                        PassengerDB passengerDB = new PassengerDB(context);
                        String passengerName = passengerDB.getNameByBookingID(bookingId);

                        // Step 6: Populate the details into a custom data class
                        MyBookingItems myBookingItems = new MyBookingItems(
                                myBookingsFlightDetails.getOrigin(),
                                myBookingsFlightDetails.getDestination(),
                                myBookingsFlightDetails.getDepartureDate(),
                                myBookingsFlightDetails.getPrice(),
                                bookingId,
                                passengerName,
                                flightId
                        );

                        // Step 7: Add the custom data to the list
                        dataList.add(myBookingItems);
                    }
                }
            }
        }

        return dataList;
    }
}

