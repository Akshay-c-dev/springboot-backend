package com.example.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Long>
{
    List<Payment> findByUserId(long userId);
    
    List<Payment> findByDoctorId(long doctroId);


}
