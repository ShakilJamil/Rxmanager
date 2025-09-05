package com.example.rx.repo;

import com.example.rx.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPrescriptionDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p.prescriptionDate, COUNT(p) FROM Prescription p GROUP BY p.prescriptionDate")
    List<Object[]> getDailyPrescriptionCount();
}
