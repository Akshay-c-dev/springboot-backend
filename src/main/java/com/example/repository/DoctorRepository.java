package com.example.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, Long>
{
	public Doctor findByMobileAndPassword(String mobile, String password);

	Doctor findByMobile(String mobile);
	
    List<Doctor> findBySpecilizationAndHospitalLocation(String specilization, String hospitalLocation);

//    @Query("SELECT DISTINCT d.location FROM Doctor d")
//    List<String> findAllLocations();
//    
//    @Query("SELECT DISTINCT d.specialization FROM Doctor d")
//    List<String> findAllSpecializations();
    default List<String> getAllSpecializations() {
        return findAll().stream()
                .map(Doctor::getSpecilization)
                .filter(spec -> spec != null && !spec.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }

    default List<String> getAllLocations() {
        return findAll().stream()
                .map(Doctor::getHospitalLocation)
                .filter(loc -> loc != null && !loc.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }
}
