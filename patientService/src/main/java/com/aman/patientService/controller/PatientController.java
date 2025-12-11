package com.aman.patientService.controller;

import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PutMapping("/updatePatient/{patientId}")
    public ResponseEntity<PatientResponseDTO> updatePatient( @RequestBody PatientRequestDTO patientRequestDTO ,
                                                            @PathVariable("patientId") UUID patientId) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(patientRequestDTO, patientId);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
}
