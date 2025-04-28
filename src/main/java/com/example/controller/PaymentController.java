package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Payment;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/api/v1")
public class PaymentController 
{
	@Autowired
	private PaymentService paymentservice;
	
	@Autowired
    private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
	
	@PostMapping("/addPayment")
    public ResponseEntity<Object> addPayment(@RequestBody Payment payment)
	{
		payment.setPaymentId(databaseaSequencesGeneratorService.generateSequence(Payment.SEQUENCE_NAME));
        paymentservice.createPayment(payment);
        return new ResponseEntity<>("Payment added succesfully",HttpStatus.CREATED);
    }
	
	@GetMapping("/getPaymentById/{paymentId}")
    public ResponseEntity<Object> getPaymentById(@PathVariable("paymentId") long paymentId)
	{
		Payment payment= paymentservice.getPaymentById(paymentId);
		if(payment!=null)
		{
        return new ResponseEntity<>("Payment found",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Payment not found",HttpStatus.NOT_FOUND);
		}
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<Object> getAllPayments()
    {
        List<Payment>payments=paymentservice.getAllPayments();
        ResponseEntity<Object>entity= new ResponseEntity<>(payments,HttpStatus.OK);
        return entity;
    }

    @DeleteMapping("deletePayment/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") long paymentId) {
        paymentservice.deletePayment(paymentId);
    }
    
    @PutMapping(value = "/updatePayment/{paymentId}")
    public ResponseEntity<Object> updatepayment(@PathVariable("paymentId") long paymentId, @RequestBody Payment payment) {
        boolean updated = paymentservice.updatePayment(payment);
        if (updated) {
            return new ResponseEntity<>("Payment updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getPaymentsByUserId/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable("userId") long userId) {
        List<Payment> payments = paymentservice.getPaymentsByUserId(userId);
        if (payments != null && !payments.isEmpty()) {
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getPaymentsByDoctorId/{doctorId}")
    public ResponseEntity<List<Payment>> getPaymentsByDoctorId(@PathVariable("doctorId") long doctorId) {
        List<Payment> payments = paymentservice.getPaymentsByDoctorId(doctorId);
        if (payments != null && !payments.isEmpty()) {
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
