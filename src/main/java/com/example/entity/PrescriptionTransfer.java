package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "prescription_transfers")
public class PrescriptionTransfer {

    @Transient
    public static final String SEQUENCE_NAME = "prescriptiontransfer_sequence";
    
    @Id
    private long id;

    @NotBlank
    private long pharmacyId;
    
    @NotBlank
    private String name; 
    
    @NotBlank
    private String pharmacyName;

    @NotBlank
    private String prescription;
    
    @NotBlank
    private double totalAmount;

    @NotBlank
    private long userId; // Add userId field
    
    @NotBlank
    private long doctorId;
    
    @NotBlank
    private String orderStatus = "Pending"; // Add orderStatus field with default value "Pending"

}
