package com.OptimumPool.BookRide.Service;

import com.OptimumPool.BookRide.Configuration.OfferDTO;
import com.OptimumPool.BookRide.Model.Bookings;
import com.OptimumPool.BookRide.Model.Invoice;
import com.OptimumPool.BookRide.Model.Offerride;
import com.OptimumPool.BookRide.Repository.BookRideRepository;
import com.OptimumPool.BookRide.Repository.BookingRepository;
import com.OptimumPool.BookRide.Repository.InvoiceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookRideService {

    @Autowired
    private BookRideRepository repo;

    @Autowired
    private BookingRepository repo2;

    @Autowired
    private InvoiceRepository repo3;

    public String function1(){
        return "Hello from service class";
    }

    public List<Offerride> getAllRides(){
        List<Offerride> l1 = repo.findAll();
        return l1;
    }

    public Offerride getRide(int id){
        Offerride ride = repo.findById(id).get();
        return ride;
    }

    public Bookings bookRide(int booking_id, int id, String customerName, int no_seat_want,int distance){
        Offerride ride = repo.findById(id).get();
        Bookings booking = new Bookings(booking_id,ride,customerName,no_seat_want,distance);
        repo2.save(booking);
        return booking;
    }

    public Bookings getBooking(int id){
        Bookings bookingDetail = repo2.findById(id).get();
        return bookingDetail;
    }

    public Invoice getInvoice(int id){
        Bookings booking = repo2.findById(id).get();
        Random r1 = new Random();
        int Invoice_Id = r1.nextInt(1000);
        int bill_generated=200;
//        int bill_generated = booking.getDistance()*booking.getNo_seat_want()*booking.getOfferObject().getCharge_per_km();
        Invoice invoice = new Invoice(Invoice_Id,booking,bill_generated);
        repo3.save(invoice);
        return invoice;
    }


    public String settleRide(int Invoice_Id,int bill_amount){
        Invoice invoice = repo3.findById(Invoice_Id).get();
        int booking_id = invoice.getBooking_obj().getId();
        if(invoice.getBill_generated()==bill_amount) {
            repo3.deleteById(Invoice_Id);
            repo2.deleteById(booking_id);
            return "Bill amount is paid successfully, your ride has been completed";
        }
        else
        {
            return "Payment failed, Please give the bill amount as mentioned in invoice";
        }
    }

//    @RabbitListener(queues = "offer_queue")
//    public void recieveData(OfferDTO todo){
//        String s = todo.getJsonObject().toString();
//        System.out.println(s);
//    }

}
