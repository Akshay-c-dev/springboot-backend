package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Appointments;

@Repository
public interface AppointmentsRepository extends MongoRepository<Appointments, Long>
{
    List<Appointments> findByDoctorId(long doctorId);
    List<Appointments> findByUserId(long userId);
}
