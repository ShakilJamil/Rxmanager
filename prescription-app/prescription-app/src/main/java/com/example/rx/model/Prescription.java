package com.example.rx.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Prescription date is required")
    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    @NotBlank(message = "Patient name is required")
    @Column(name = "patient_name")
    private String patientName;

    @NotNull(message = "Patient age is required")
    @Min(value = 0, message = "Age must be positive")
    @Max(value = 150, message = "Age must be less than 150")
    @Column(name = "patient_age")
    private Integer patientAge;

    @NotBlank(message = "Gender is required")
    @Column(name = "patient_gender")
    private String patientGender;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "medicines")
    private String medicines;

    @Column(name = "next_visit_date")
    private LocalDate nextVisitDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public LocalDate getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDate nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }
}