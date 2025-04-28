package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.PrescriptionTransfer;
import com.example.repository.PrescriptionTransferRepository;
import com.example.service.PrescriptionTransferService;

@Service
public class PrescriptionTransferServiceImpl implements  PrescriptionTransferService
{
	@Autowired
    private PrescriptionTransferRepository repository;

	@Override
    public PrescriptionTransfer savePrescriptionTransfer(PrescriptionTransfer prescriptionTransfer)
    {
        return repository.save(prescriptionTransfer);
    }

	@Override
	  public List<PrescriptionTransfer> getPrescriptionsByPharmacyId(long pharmacyId)
	{
        return repository.findByPharmacyId(pharmacyId);
    }

	@Override
	 public PrescriptionTransfer updateOrderStatus(long id, String status)
	{
        Optional<PrescriptionTransfer> optionalPrescriptionTransfer = repository.findById(id);
        if (optionalPrescriptionTransfer.isPresent()) {
            PrescriptionTransfer prescriptionTransfer = optionalPrescriptionTransfer.get();
            prescriptionTransfer.setOrderStatus(status);
            return repository.save(prescriptionTransfer);
        } else {
            return null;
        }
    }

	@Override
	 public List<PrescriptionTransfer> getOrdersByUserId(long userId)
	{
        return repository.findByUserId(userId);
    }
}
