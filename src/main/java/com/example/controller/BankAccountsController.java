package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.BankAccount;
import com.example.entity.Prescription;
import com.example.entity.Schedule;
import com.example.entity.Wallet;
import com.example.service.BankAccountService;
import com.example.service.DatabaseaSequencesGeneratorService;
import com.example.service.PrescriptionService;
import com.example.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class BankAccountsController {
	@Autowired
	BankAccountService bankaccservice;

	@Autowired
	WalletService walletService;

	@Autowired
	private DatabaseaSequencesGeneratorService databaseaSequencesGeneratorService;

	@PostMapping("/addBankaccount")
	public ResponseEntity<Object> createBAnkAccount(@RequestBody BankAccount bankaccount) {
		bankaccount.setId(databaseaSequencesGeneratorService.generateSequence(BankAccount.SEQUENCE_NAME));
		bankaccservice.createBankAccount(bankaccount);
		return new ResponseEntity<>("Bank account added", HttpStatus.CREATED);
	}

	@GetMapping("getBankAccountById/{id}")
	public ResponseEntity<Object> getBankAccountById(@PathVariable("id") long id) {
		BankAccount bankaccount = bankaccservice.getBankAccountById(id);
		if (bankaccount != null) {
			return new ResponseEntity<>(bankaccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("bank account not found", HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping("getBankAccountByUserId/{userId}")
	public ResponseEntity<Object> getBankAccountByUserId(@PathVariable long userId) {
		BankAccount bankaccount = bankaccservice.getBankAccountByUserId(userId);
		if (bankaccount != null) {
			return new ResponseEntity<>(bankaccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("bank account not found for user", HttpStatus.NOT_FOUND);

		}
	}
	@PutMapping("/addMoneyFromWallet/{userId}")
	public ResponseEntity<String> addMoneyFromWallet(@PathVariable long userId, @RequestBody double amount) {
	    try {
	        // Fetch bank account and wallet
	        BankAccount bankAccount = bankaccservice.getAccountDetailsByUserId(userId).stream().findFirst()
	                .orElse(null);
	        if (bankAccount == null) {
	            return new ResponseEntity<>("Bank account not found", HttpStatus.NOT_FOUND);
	        }

	        Wallet wallet = walletService.getWalletByUserId(userId);
	        if (wallet == null || wallet.getBalance() < amount) {
	            return new ResponseEntity<>("Insufficient wallet balance", HttpStatus.BAD_REQUEST);
	        }

	        // Update bank account balance
	        bankAccount.setBalAmt(bankAccount.getBalAmt() + amount);
	        bankaccservice.updateBankAccount(bankAccount);

	        // Deduct amount from wallet
	        walletService.decrementBalance(userId, amount);

	        return new ResponseEntity<>("Money added to bank account successfully", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error occurred while adding money", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/getAccountDetailsByUserId/{userId}")
	public ResponseEntity<List<BankAccount>> getAccountDetailsByUserId(@PathVariable long userId) {
		List<BankAccount> account = bankaccservice.getAccountDetailsByUserId(userId);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping("/getAllBankAccounts")
	public ResponseEntity<Object> getAllBankAccounts() {
		List<BankAccount> account = bankaccservice.getAllBankAccounts();
		ResponseEntity<Object> entity = new ResponseEntity<>(account, HttpStatus.OK);
		return entity;
	}

	@DeleteMapping("deleteBankAccountsById/{id}")
	public void deleteBankAccountsById(@PathVariable("id") long id) {
		bankaccservice.deleteBankAccountById(id);
	}

	@PutMapping("/updateBankAccount/{Id}")
	public ResponseEntity<Object> updatebankaccount(@PathVariable("Id") long Id, @RequestBody BankAccount bankaccount) {
		boolean updated = bankaccservice.updateBankAccount(bankaccount);
		if (updated) {
			return new ResponseEntity<>("BankAccount updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("BankAccount not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getBankAccountByCardNumber")
	public ResponseEntity<BankAccount> getBankAccountByCardNumber(@PathVariable("cardNo") String cardNo) {
		try {
			BankAccount bankAccount = bankaccservice.findBankAccountByCardNumber(cardNo);
			if (bankAccount != null) {
				return new ResponseEntity<>(bankAccount, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateBankAccountByUserId/{userId}")
	public ResponseEntity<BankAccount> updateBankAccountByUserId(@PathVariable long userId,
			@RequestBody BankAccount bankAccount) {
		BankAccount updatedBankAccount = bankaccservice.updateBalanceByUserId(userId, bankAccount.getBalAmt());
		if (updatedBankAccount != null) {
			return ResponseEntity.ok(updatedBankAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getBalAmountByUserId/{userId}")
	public ResponseEntity<Double> getBalAmountByUserId(@PathVariable long userId) {
		BankAccount account = bankaccservice.getAccountDetailsByUserId(userId).stream().findFirst().orElse(null);
		if (account != null) {
			return new ResponseEntity<>(account.getBalAmt(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//    @PutMapping("/updateBankAccount/{id}")
//    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
//        try {
//            BankAccount updatedBankAccount = bankaccservice.updateBankAccount(id, bankAccount);
//            return new ResponseEntity<>(updatedBankAccount, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
