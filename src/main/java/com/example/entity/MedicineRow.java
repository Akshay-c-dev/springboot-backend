package com.example.entity;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicineRow
{
	@NotBlank
	private String medicineName;

	@NotBlank
	private double amount; // Amount per unit of medicine

	@NotNull
	private int quantity;
}
