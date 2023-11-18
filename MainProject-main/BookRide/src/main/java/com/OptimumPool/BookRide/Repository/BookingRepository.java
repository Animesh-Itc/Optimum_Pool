package com.OptimumPool.BookRide.Repository;

import com.OptimumPool.BookRide.Model.Bookings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Bookings,Integer> {

}
