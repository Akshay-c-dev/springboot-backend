package com.example.repository;

import com.example.entity.PrescriptionTransfer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionTransferRepository extends MongoRepository<PrescriptionTransfer, Long>
{
	 List<PrescriptionTransfer> findByPharmacyId(long pharmacyId);
	 
	 List<PrescriptionTransfer> findByUserId(long userId);

}
