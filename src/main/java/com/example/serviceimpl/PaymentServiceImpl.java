package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Payment;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService
{
	@Autowired
	PaymentRepository paymentReposiotry;

	@Override
	public Payment createPayment(Payment payment)
	{
		return paymentReposiotry.save(payment);
	}

	@Override
	public Payment getPaymentById(long paymentId) 
	{
		return paymentReposiotry.findById(paymentId).orElse(null);
	}

	@Override
	public List<Payment> getAllPayments()
	{
		return paymentReposiotry.findAll();
	}

	@Override
	public void deletePayment(long paymentId)
	{
		paymentReposiotry.deleteById(paymentId);
		
	}

	@Override
	public boolean updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPaymentExist(long paymentId)
	{
		return paymentReposiotry.existsById(paymentId);
	}

	@Override
	public List<Payment> getPaymentsByUserId(long userId) {
	    return paymentReposiotry.findByUserId(userId);
	}

	@Override
	public List<Payment> getPaymentsByDoctorId(long doctorId) {
		return paymentReposiotry.findByDoctorId(doctorId);
	}

	
}
