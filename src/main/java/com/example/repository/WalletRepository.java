package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Wallet;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, Long>
{
    Optional<Wallet> findByUserId(long userId);
    
    Wallet findWalletByUserId(long userId);

}