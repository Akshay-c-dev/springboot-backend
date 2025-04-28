package com.example.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "prescription")
public class Prescription
{
    @Transient
    public static final String SEQUENCE_NAME = "prescription_sequence";

    @Id
    private long prescriptionId;

    @NotBlank
    @CreatedDate
    private String prescriptionDate;

    @Field("appointmentId")
    private long appointmentId;

    @NotBlank
    private String prescription;

    @NotBlank
    private double totalAmount; 
    
    private List<MedicineRow> medicineRows;
    
    @NotBlank
    private long doctorId; 
}
