package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.MedicalHistory;
import com.example.repository.MedicalHistoryRepository;
import com.example.service.MedicalHistoryService;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService 
{
	@Autowired
	MedicalHistoryRepository medicalhistoryrepository;

	@Override
	public MedicalHistory createMedicalHistory(MedicalHistory medicalhistory) 
	{
		 return medicalhistoryrepository.save(medicalhistory);
	}

	@Override
	public MedicalHistory getMedicalHistoryByMedicalId(long medicalId)
	{
		return medicalhistoryrepository.findById(medicalId).orElse(null);
	}

	@Override
	public List<MedicalHistory> getAllMedicalHistory()
	{
		return medicalhistoryrepository.findAll();
	}

	@Override
	public void deleteMedicalHistory(long medicalId)
	{
		medicalhistoryrepository.deleteById(medicalId);
		
	}

	@Override
	public boolean updateMedicalHistory(MedicalHistory medicalhistory)
	{
		if (isMedicalHistoryExist(medicalhistory.getMedicalId()))
		{
            medicalhistoryrepository.save(medicalhistory);
            return true;
        }

		return false;
	}

	@Override
	public boolean isMedicalHistoryExist(long medicalId)
	{
		return medicalhistoryrepository.existsById(medicalId);

	}

	@Override
	public List<MedicalHistory> getMedicalHistoryByUserId(Long userId)
	{
		 return medicalhistoryrepository.findByUserId(userId);
	}
	
}
