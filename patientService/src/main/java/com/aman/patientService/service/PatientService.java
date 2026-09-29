package com.aman.patientService.service;


import com.aman.patientService.dto.PatientRequestDTO;
import com.aman.patientService.dto.PatientResponseDTO;
import com.aman.patientService.exception.EmailAlreadyExistsException;
import com.aman.patientService.exception.PatientNotFoundException;
import com.aman.patientService.grpc.BillingServiceGrpcClient;
import com.aman.patientService.kafka.KafkaProducer;
import com.aman.patientService.mapper.PatientMapper;
import com.aman.patientService.model.Appointments;
import com.aman.patientService.model.Patient;
import com.aman.patientService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final BillingServiceGrpcClient billingServiceGrpcClient;

    private final KafkaProducer kafkaProducer;



    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO savePatient(List<PatientRequestDTO> patientRequestDTOs)
    {
        Patient savedPatient = null;

        for (PatientRequestDTO patientRequestDTO : patientRequestDTOs) {
            if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
                throw new EmailAlreadyExistsException("A patient with this Email " + "already exists" + patientRequestDTO.getEmail());
            }
            Patient patient = PatientMapper.toEntity(patientRequestDTO);

            for (Appointments appointment : patient.getAppointments()) {
                appointment.setPatient(patient);
            }

            savedPatient = patientRepository.save(patient);
            // Call Billing Service to create billing account
            // billingServiceGrpcClient.createBillingAccount(savedPatient.getPatientId().toString(), savedPatient.getName(), savedPatient.getEmail());

            // kafkaProducer.sendEvent(savedPatient);

        }

        if(savedPatient == null){
            throw new RuntimeException("No patients were saved.");
        }
        return PatientMapper.toDTO(savedPatient);

    }

    public  PatientResponseDTO updatePatient(PatientRequestDTO  patientRequestDTO, UUID patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
        {
            if (patientRepository.existsByEmailAndPatientIdIsNot(patientRequestDTO.getEmail(), patientId)) {
                throw new EmailAlreadyExistsException("A patient with this Email " + "already exists" + patientRequestDTO.getEmail());
            }

            patient.setName(patientRequestDTO.getName());
            patient.setEmail(patientRequestDTO.getEmail());
            patient.setAddress(patientRequestDTO.getAddress());
            patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
            Patient updatedPatient = patientRepository.save(patient);
            return PatientMapper.toDTO(updatedPatient);
        }
    }
    public void deletePatient(UUID patientId) {
        patientRepository.deleteById(patientId);
    }

    public List<Patient> getActivePatients() {
        return patientRepository.findByIsActiveTrue();
    }
}

