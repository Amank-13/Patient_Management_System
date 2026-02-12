package com.aman.patientService.controller;

import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.dto.validators.CreatePatientValidationGroup;
import com.aman.patientService.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "Endpoints for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getPatients")
    @Operation(summary = "Get All Patients", description = "Retrieve a list of all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);

    }

    @PostMapping("/addPatient")
    @Operation(summary = "Add New Patient", description = "Add a new patient to the system")
    public ResponseEntity<PatientResponseDTO> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.savePatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/updatePatient/{patientId}")
    @Operation(summary = "Update Patient", description = "Update an existing patient's information")
    public ResponseEntity<PatientResponseDTO> updatePatient( @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO ,
                                                            @PathVariable("patientId") UUID patientId) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(patientRequestDTO, patientId);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/deletePatient/{patientId}")
    @Operation(summary = "Delete Patient", description = "Delete a patient from the system")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientId") UUID patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }
}
