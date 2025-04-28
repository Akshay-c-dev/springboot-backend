package com.example.service;

import java.util.List;

import com.example.entity.MedicalHistory;

public interface MedicalHistoryService
{
	
	MedicalHistory createMedicalHistory(MedicalHistory medicalhistory);

	MedicalHistory getMedicalHistoryByMedicalId(long medicalId);

	List<MedicalHistory> getAllMedicalHistory();

	void deleteMedicalHistory(long medicalId);

	boolean updateMedicalHistory(MedicalHistory medicalhistory);

	boolean isMedicalHistoryExist(long medicalId);
	
	List<MedicalHistory> getMedicalHistoryByUserId(Long userId);
}
