package com.aman.patientService.controller;

import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getPatients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);

    }

    @PostMapping("/addPatient")
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.savePatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }
}
