package com.example.rx.controller;

import com.example.rx.model.Prescription;
import com.example.rx.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;

@Controller
public class PrescriptionController {
    @Autowired
    private PrescriptionService service;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/prescriptions")
    public String listPrescriptions(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        LocalDate start;
        LocalDate end;

        try {
            start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : YearMonth.now().atDay(1);
        } catch (Exception e) {
            start = YearMonth.now().atDay(1);
        }

        try {
            end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : YearMonth.now().atEndOfMonth();
        } catch (Exception e) {
            end = YearMonth.now().atEndOfMonth();
        }

        model.addAttribute("prescriptions", service.getPrescriptionsByDateRange(start, end));
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);
        return "prescription-list";
    }

    @GetMapping("/prescriptions/new")
    public String showCreateForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "prescription-form";
    }

    @PostMapping("/prescriptions")
    public String createPrescription(@Valid Prescription prescription, BindingResult result) {
        if (result.hasErrors()) {
            return "prescription-form";
        }
        service.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/prescriptions/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Prescription prescription = service.getPrescriptionById(id);
        if (prescription == null) {
            return "redirect:/prescriptions";
        }
        model.addAttribute("prescription", prescription);
        return "prescription-form";
    }

    @PostMapping("/prescriptions/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {
        service.deletePrescription(id);
        return "redirect:/prescriptions";
    }

    @GetMapping("/report")
    public String showReport(Model model) {
        model.addAttribute("reportData", service.getDailyPrescriptionReport());
        return "report";
    }
}
