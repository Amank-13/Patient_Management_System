package com.aman.patientService.service;


import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.exception.EmailAlreadyExistsException;
import com.aman.patientService.exception.PatientNotFoundException;
import com.aman.patientService.grpc.BillingServiceGrpcClient;
import com.aman.patientService.mapper.PatientMapper;
import com.aman.patientService.model.Patient;
import com.aman.patientService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final BillingServiceGrpcClient billingServiceGrpcClient;


    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
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
        // Call Billing Service to create billing account
        billingServiceGrpcClient.createBillingAccount(savedPatient.getId().toString(), savedPatient.getName(), savedPatient.getEmail());

        return PatientMapper.toDTO(savedPatient);
    }

    public  PatientResponseDTO updatePatient(PatientRequestDTO  patientRequestDTO, UUID patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
        {
            if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), patientId)) {
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
    public void deletePatient(UUID patientId) {
        patientRepository.deleteById(patientId);
    }

}

