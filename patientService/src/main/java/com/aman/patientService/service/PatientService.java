package com.aman.patientService.service;


import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.exception.EmailAlreadyExistsException;
import com.aman.patientService.mapper.PatientMapper;
import com.aman.patientService.model.Patient;
import com.aman.patientService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


}

