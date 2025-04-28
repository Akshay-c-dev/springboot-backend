package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Document(collection = "wallet")
@Data
public class Wallet {
	
	@Transient
    public static final String SEQUENCE_NAME = "wallet_sequence";  //this name can be any

    
    @Id
    private long walletId;
    
    @Field("userId")
    private long userId;
    
    @Field("balance")
    private double balance; // Stores the current balance in the wallet

    @NotBlank
    private String currency; // Optional: To store the currency type (e.g., USD, EUR)
    
    // You can add more fields like createdDate, modifiedDate, etc. if needed
}
