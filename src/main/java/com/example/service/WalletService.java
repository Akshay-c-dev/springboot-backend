package com.example.service;

import java.util.List;

import com.example.entity.Wallet;

public interface WalletService
{
	Wallet addWallet(Wallet wallet);
	
	Wallet createWallet(long userId);
	
	Wallet addMoneyToWallet(long userId, double amount);
	
	Wallet getWalletByUserId(long userId);
	
	List<Wallet> getAllWallets();
	
	boolean updateWallet(Wallet wallet);
	
	void deleteWallet(long walletId);
	
	boolean addMoney(long walletId, double amount);
	
	 Wallet getWalletById(long walletId);
	
	 void decrementBalance(long userId, double amount);
}
