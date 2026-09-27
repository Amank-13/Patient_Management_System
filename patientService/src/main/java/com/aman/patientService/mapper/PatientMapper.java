package com.aman.patientService.mapper;


import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getPatientId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static Patient toEntity(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(java.time.LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegistrationDate(java.time.LocalDate.parse(patientRequestDTO.getRegistrationDate()));
        patient.setActive(patientRequestDTO.getActive());
        patient.setStatus(patientRequestDTO.getStatus());
        patient.setAppointments(patientRequestDTO.getAppointments());
        return patient;
    }
}
