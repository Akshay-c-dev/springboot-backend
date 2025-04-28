package com.example.controller;

import com.example.entity.Wallet;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletService walletService;
    
    @Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;
    
    @PostMapping("/createByUserId/{userId}")
    public ResponseEntity<Object> createWallet1(@PathVariable long userId) {
        
        // Create a new wallet with default values
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(0.0); // Set initial balance
        wallet.setCurrency("USD"); // Set default currency

        // Generate wallet ID
        wallet.setWalletId(databaseaSequencesGeneratorService.generateSequence(Wallet.SEQUENCE_NAME));
        
        // Save the new wallet
        walletService.addWallet(wallet);
        
        return new ResponseEntity<>("Wallet created successfully with walletId: " + wallet.getWalletId(), HttpStatus.CREATED);
    }

    
    @PostMapping("/addWallet")
    public ResponseEntity<String> createWallet(@RequestBody Wallet wallet) {
        try {
            // Check if a wallet already exists for the given userId
            Wallet existingWallet = walletService.getWalletByUserId(wallet.getUserId());
            if (existingWallet != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A wallet already exists for userId: " + wallet.getUserId() + " with walletId: " + existingWallet.getWalletId());
            }

            // Create a new wallet if no existing wallet is found
            wallet.setWalletId(databaseaSequencesGeneratorService.generateSequence(Wallet.SEQUENCE_NAME));
            walletService.addWallet(wallet);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Wallet created successfully with walletId: " + wallet.getWalletId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating wallet: " + e.getMessage());
        }
    }



    @PostMapping("/create/{userId}")
    public ResponseEntity<Object> createWallet(@PathVariable long userId) {
        Wallet wallet = walletService.createWallet(userId);
        return new ResponseEntity<>("Wallet created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/addMoney")
    public ResponseEntity<Object> addMoneyToWallet(@RequestParam long userId, @RequestParam double amount) {
        try {
            Wallet updatedWallet = walletService.addMoneyToWallet(userId, amount);
            return new ResponseEntity<>("Money added successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getWalletByUserId/{userId}")
    public ResponseEntity<Object> getWalletByUserId(@PathVariable long userId) {
        try {
            Wallet wallet = walletService.getWalletByUserId(userId);
            return new ResponseEntity<>(wallet, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateWallet/{userId}")
    public ResponseEntity<Object> updateWallet(@PathVariable long userId, @RequestBody Wallet wallet) {
        Wallet existingWallet = walletService.getWalletByUserId(userId);
        if (existingWallet != null) {
            wallet.setWalletId(existingWallet.getWalletId()); // Set the wallet ID from the existing wallet
            boolean updated = walletService.updateWallet(wallet);
            if (updated) {
                return new ResponseEntity<>("Wallet updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update wallet", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Wallet not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteWallet/{userId}")
    public ResponseEntity<Object> deleteWallet(@PathVariable long userId) {
        try {
            walletService.deleteWallet(userId);
            return new ResponseEntity<>("Wallet deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
