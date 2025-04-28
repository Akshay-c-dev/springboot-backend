package com.example.service;

import java.util.List;

import com.example.entity.Pharmacy;

public interface PharmacyService 
{

	Pharmacy addPharmacy(Pharmacy pharmacy);
	public Pharmacy loginValidate(Pharmacy pharmacy);
	List<Pharmacy> getAllPharmacies();
	Pharmacy getPharmacyById(Long pharmacyId);
	boolean updatePharmacy(Pharmacy pharmacy);
	boolean deletePharmacy(Long pharmacyId);
}
