package com.example.rx.service;

import com.example.rx.model.Prescription;
import com.example.rx.repo.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository repository;

    public List<Prescription> getPrescriptionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findByPrescriptionDateBetween(startDate, endDate);
    }

    public Prescription savePrescription(Prescription prescription) {
        return repository.save(prescription);
    }

    public Prescription getPrescriptionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePrescription(Long id) {
        repository.deleteById(id);
    }

    public List<Object[]> getDailyPrescriptionReport() {
        return repository.getDailyPrescriptionCount();
    }
}
