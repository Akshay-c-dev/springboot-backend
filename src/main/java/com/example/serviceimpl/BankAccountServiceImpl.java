package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.BankAccount;
import com.example.repository.BankAccountRepository;
import com.example.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService
{
	@Autowired
	BankAccountRepository bankaccRepository;

	@Override
	public BankAccount createBankAccount(BankAccount bankaccount)
	{
		return bankaccRepository.save(bankaccount);
	}

	@Override
	public BankAccount getBankAccountById(long id)
	{
		return bankaccRepository.findById(id).orElse(null);
	}

	@Override
	public List<BankAccount> getAllBankAccounts()
	{
		return bankaccRepository.findAll();
	}

	@Override
	public void deleteBankAccountById(long id)
	{
		bankaccRepository.deleteById(id);

		
	}

	@Override
	public boolean updateBankAccount(BankAccount bankaccount)
	{
		if (bankaccRepository.existsById(bankaccount.getId()))
		{
            bankaccRepository.save(bankaccount);
            return true;
        }

		return false;
	}

	@Override
	public boolean isBankAccountExist(long id)
	{
		return bankaccRepository.existsById(id);

	}

	@Override
	public List<BankAccount> getAccountDetailsByUserId(long userId)
	{
		return bankaccRepository.getAccountDetailsByUserId(userId);
	}

	@Override
	public BankAccount findBankAccountByCardNumber(String cardNo)
	{
		return bankaccRepository.findByCardNo(cardNo);
	}

	@Override
	public BankAccount updateBankAccount(Long id, BankAccount bankAccount)
	{
		Optional<BankAccount> existingBankAccountOptional = bankaccRepository.findById(id);
        if (existingBankAccountOptional.isPresent())
        {
            BankAccount existingBankAccount = existingBankAccountOptional.get();
            existingBankAccount.setBalAmt(bankAccount.getBalAmt());
            return bankaccRepository.save(existingBankAccount);
        }
        return null;
    }

	@Override
	public BankAccount updateBalanceByUserId(long userId, double newBalance) {
        BankAccount bankAccount = bankaccRepository.findByUserId(userId);
        if (bankAccount != null) {
            bankAccount.setBalAmt(newBalance);
            return bankaccRepository.save(bankAccount);
        }
        return null;
    }

	@Override
	public BankAccount getBankAccountByUserId(long userId) {
		return bankaccRepository.findById(userId).orElse(null);
	}

}
