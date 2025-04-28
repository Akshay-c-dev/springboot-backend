package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Reviews;

public interface ReviewsRepository extends MongoRepository<Reviews, Long>
{
    List<Reviews> findByDoctorId(long doctorId);

}
