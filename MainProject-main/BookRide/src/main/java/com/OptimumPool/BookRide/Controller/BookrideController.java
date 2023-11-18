package com.OptimumPool.BookRide.Controller;

import com.OptimumPool.BookRide.Model.Bookings;
import com.OptimumPool.BookRide.Model.Invoice;
import com.OptimumPool.BookRide.Model.Offerride;
import com.OptimumPool.BookRide.Service.BookRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookrideController {

    @Autowired
    BookRideService service;

    @GetMapping("bookrides")
    public ResponseEntity<?> getRides(){
        List<Offerride> demoList = service.getAllRides();
        return new ResponseEntity<>(demoList, HttpStatus.OK);
    }

    @GetMapping("bookrides/{id}")
    public ResponseEntity<?> getRide(@PathVariable int id) {
        Offerride ride = service.getRide(id);
        return new ResponseEntity<>(ride,HttpStatus.OK);
    }

    @PostMapping("bookrides/{booking_id}/{id}/{customerName}/{no_seat_want}/{distance}")
    public ResponseEntity<?> bookRide(@PathVariable int booking_id, @PathVariable int id , @PathVariable String customerName ,@PathVariable int no_seat_want,@PathVariable int distance){
        Bookings booking = service.bookRide(booking_id,id,customerName,no_seat_want,distance);
        return new ResponseEntity<>(booking,HttpStatus.CREATED);
    }

    @GetMapping("booking/{id}")
    public ResponseEntity<?> getBookingDetailsById(@PathVariable int id){
        Bookings booking = service.getBooking(id);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    @GetMapping("invoice/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable int id){
        Invoice invoice = service.getInvoice(id);
        return new ResponseEntity<>(invoice,HttpStatus.OK);
    }

    @PostMapping("invoice/{id}/{bill_amount}")
    public String billPaid(@PathVariable int id,@PathVariable int bill_amount){
        String message = service.settleRide(id, bill_amount);
        return message;
    }




}
