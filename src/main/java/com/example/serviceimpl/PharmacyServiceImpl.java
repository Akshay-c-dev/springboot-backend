package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Pharmacy;
import com.example.repository.PharmacyRepository;
import com.example.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService
{

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Override
	public Pharmacy addPharmacy(Pharmacy pharmacy) 
	{
		return pharmacyRepository.save(pharmacy);
	}

	@Override
	public List<Pharmacy> getAllPharmacies() 
	{
		return pharmacyRepository.findAll();
	}

	@Override
	public Pharmacy getPharmacyById(Long pharmacyId)
	{
		return pharmacyRepository.findById(pharmacyId).orElse(null);
	}

	@Override
	public boolean updatePharmacy(Pharmacy pharmacy)
	{
		if (pharmacyRepository.existsById(pharmacy.getPharmacyId()))
		{
            pharmacyRepository.save(pharmacy);
            return true;
        }

		return false;
	}

	@Override
	public boolean deletePharmacy(Long pharmacyId)
	{
		if (pharmacyRepository.existsById(pharmacyId))
		{
		pharmacyRepository.deleteById(pharmacyId);
		return true;
		}
		return false;
		
	}

	@Override
	public Pharmacy loginValidate(Pharmacy pharmacy)
	{
		Pharmacy pharmacy1=pharmacyRepository.findByMobileAndPassword(pharmacy.getMobile(), pharmacy.getPassword());
		System.out.println("Pharmacy object is="+pharmacy1);
		return pharmacy1;
	}
	
	
	
}
