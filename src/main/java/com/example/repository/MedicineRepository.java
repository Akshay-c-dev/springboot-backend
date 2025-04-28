package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Medicine;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine, Long>
{
    Optional<Medicine> findByMedicineName(String medicineName);

}
