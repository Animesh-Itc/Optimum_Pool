package com.OptimumPool.BookRide.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings_col")
public class Bookings {
    @Id
    private int booking_id;
    private Offerride offerObject ;
    private String customerName;

    private int no_seat_want;
    private int distance ;

    public int getId() {
        return booking_id;
    }

    public void setId(int id) {
        this.booking_id = id;
    }

    public Offerride getOfferObject() {
        return offerObject;
    }

    public void setOfferObject(Offerride offerObject) {
        this.offerObject = offerObject;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNo_seat_want() {
        return no_seat_want;
    }

    public void setNo_seat_want(int no_seat_want) {
        this.no_seat_want = no_seat_want;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Bookings(int id, Offerride offerObject, String customerName, int no_seat_want, int distance) {
        this.booking_id = id;
        this.offerObject = offerObject;
        this.customerName = customerName;
        this.no_seat_want = no_seat_want;
        this.distance = distance;
    }

    public Bookings() {
    }
}
