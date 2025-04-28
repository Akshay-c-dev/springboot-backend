package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Medicine;
import com.example.repository.MedicineRepository;
import com.example.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService
{
	@Autowired
    private MedicineRepository medicineRepository;

	@Override
	public Medicine createMedicine(Medicine medicine)
	{
		return medicineRepository.save(medicine);

	}

	@Override
	public Optional<Medicine> getMedicineById(long id)
	{
		return medicineRepository.findById(id);

	}

	@Override
	public List<Medicine> getAllMedicines()
	{
		return medicineRepository.findAll();
	}

	@Override
	public void deleteMedicine(long id)
	{
		medicineRepository.deleteById(id);
		
	}

	@Override
	public boolean updateMedicine(Medicine medicine)
	{
		if (isMedicineExist(medicine.getId())) {
            medicineRepository.save(medicine);
            return true;
        }

		return false;
	}

	@Override
	public boolean isMedicineExist(long id)
	{
		return medicineRepository.existsById(id);

	}

	@Override
	public List<Medicine> createMedicines(List<Medicine> medicines)
	{
		return medicineRepository.saveAll(medicines);
	}

	@Override
	public Optional<Medicine> getMedicineByMedicineName(String medicineName)
	{
        return medicineRepository.findByMedicineName(medicineName);
    }
	
}
