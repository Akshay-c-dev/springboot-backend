package com.example.service;

import java.util.List;

import com.example.entity.BankAccount;
import com.example.entity.Schedule;

public interface BankAccountService
{
	
	List<BankAccount> getAccountDetailsByUserId(long userId);
	
	BankAccount createBankAccount(BankAccount bankaccount);

	BankAccount getBankAccountById(long id);
	
	BankAccount getBankAccountByUserId(long userId);

	List<BankAccount> getAllBankAccounts();

	void deleteBankAccountById(long id);

	boolean updateBankAccount(BankAccount bankaccount);

	boolean isBankAccountExist(long id);
	
	BankAccount findBankAccountByCardNumber(String cardNo);
	
	BankAccount updateBankAccount(Long id, BankAccount bankAccount);
	
	BankAccount updateBalanceByUserId(long userId, double newBalance);
}
