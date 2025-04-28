package com.example.service;

import java.util.List;

import com.example.entity.PrescriptionTransfer;

public interface PrescriptionTransferService
{
    PrescriptionTransfer savePrescriptionTransfer(PrescriptionTransfer prescriptionTransfer);
    
    List<PrescriptionTransfer> getPrescriptionsByPharmacyId(long pharmacyId);
    
    PrescriptionTransfer updateOrderStatus(long id, String status);
    
    List<PrescriptionTransfer> getOrdersByUserId(long userId);
 } 
