package com.example.rx.controller;

import com.example.rx.model.Prescription;
import com.example.rx.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionApiController {
    @Autowired
    private PrescriptionService service;

    @GetMapping
    @Operation(summary = "Get list of prescriptions")
    public List<Prescription> getPrescriptions() {
        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();
        return service.getPrescriptionsByDateRange(start, end);
    }
}
