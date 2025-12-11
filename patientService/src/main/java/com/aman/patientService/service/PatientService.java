package com.aman.patientService.service;


import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.exception.EmailAlreadyExistsException;
import com.aman.patientService.exception.PatientNotFoundException;
import com.aman.patientService.mapper.PatientMapper;
import com.aman.patientService.model.Patient;
import com.aman.patientService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO savePatient(PatientRequestDTO patientRequestDTO)
    {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this Email " + "already exists" + patientRequestDTO.getEmail());
        }

        Patient savedPatient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));
        return PatientMapper.toDTO(savedPatient);
    }

    public  PatientResponseDTO updatePatient(PatientRequestDTO  patientRequestDTO, UUID patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
        {
            if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
                throw new EmailAlreadyExistsException("A patient with this Email " + "already exists" + patientRequestDTO.getEmail());
            }

            patient.setName(patientRequestDTO.getName());
            patient.setEmail(patientRequestDTO.getEmail());
            patient.setAddress(patientRequestDTO.getAddress());
            patient.setDateOfBirth(java.time.LocalDate.parse(patientRequestDTO.getDateOfBirth()));
            Patient updatedPatient = patientRepository.save(patient);
            return PatientMapper.toDTO(updatedPatient);
        }
    }

}

