package com.example.service;

import java.util.List;

import com.example.entity.Payment;

public interface PaymentService
{
	
	Payment createPayment(Payment payment);

	Payment getPaymentById(long paymentId);

	List<Payment> getAllPayments();

	void deletePayment(long paymentId);

	boolean updatePayment(Payment payment);

	boolean isPaymentExist(long paymentId);
	
	List<Payment> getPaymentsByUserId(long userId);
	
	List<Payment> getPaymentsByDoctorId(long doctorId); 
}
