package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Wallet;
import com.example.repository.WalletRepository;
import com.example.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService
{
	@Autowired
	WalletRepository walletRepository;

	public Wallet createWallet(long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(0.0);
        wallet.setCurrency("USD"); // Set a default currency
        return walletRepository.save(wallet);
    }

    public Wallet addMoneyToWallet(long userId, double amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user: " + userId));
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByUserId(long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user: " + userId));
    }

    // Get all wallets
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    // Update wallet
    public boolean updateWallet(Wallet wallet) {
        if (walletRepository.existsById(wallet.getWalletId())) {
            walletRepository.save(wallet);
            return true;
        } else {
            return false;
        }
    }

    // Delete wallet
    public void deleteWallet(long walletId) {
        walletRepository.deleteById(walletId);
    }

    // Add money to wallet
    public boolean addMoney(long walletId, double amount) {
        Wallet wallet = getWalletById(walletId);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance() + amount);
            walletRepository.save(wallet);
            return true;
        } else {
            return false;
        }
    }

	@Override
	public Wallet getWalletById(long walletId) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        return wallet.orElse(null);
    }

	@Override
	public Wallet addWallet(Wallet wallet)
	{
		return walletRepository.save(wallet);
	}

	@Override
	 public void decrementBalance(long userId, double amount) {
        Wallet wallet = walletRepository.findWalletByUserId(userId);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance() - amount);
            walletRepository.save(wallet);
        }
    }
}
