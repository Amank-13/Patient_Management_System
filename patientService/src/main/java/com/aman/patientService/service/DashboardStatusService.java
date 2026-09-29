package com.aman.patientService.service;


import com.aman.patientService.dto.DashboardStatusDto;
import com.aman.patientService.mapper.AppointmentMapper;
import com.aman.patientService.mapper.DashboardMapper;
import com.aman.patientService.model.Appointments;
import com.aman.patientService.model.DashboardStatus;
import com.aman.patientService.model.Patient;
import com.aman.patientService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DashboardStatusService {

    PatientRepository patientRepository;

    PatientService patientService;


    public DashboardStatusService(PatientRepository patientRepository,PatientService patientService){
        this.patientRepository=patientRepository;
        this.patientService = patientService;
    };

    DashboardMapper  dashboardMapper = new DashboardMapper();



    public DashboardStatusDto getStatus() {

        List<Patient> patients = patientRepository.getStatus();

        List<Patient> patientsHavingTodayAppointment = patients.stream()
                .filter(p -> p.getAppointments().stream()
                        .anyMatch(a ->
                                Objects.equals(a.getCreatedDate(), LocalDate.now())
                        ))
                .toList();

        DashboardStatus dashboardStatus = new DashboardStatus();

        dashboardStatus.setTotalPatients(patientRepository.count());

        dashboardStatus.setActivePatients(
                patientService.getActivePatients().size()
        );

        dashboardStatus.setCriticalPatients(
                patients.stream()
                        .filter(p -> p.getAppointments().stream()
                                .anyMatch(a ->
                                        Objects.equals(a.getCreatedDate(), LocalDate.now())
                                                && a.getCritical()
                                ))
                        .count()
        );
        dashboardStatus.setNewPatients(patientsHavingTodayAppointment.size());

        AppointmentMapper appointmentMapper = new AppointmentMapper();
        dashboardStatus.setTodayAppointments(appointmentMapper.toDTOList(patientsHavingTodayAppointment.stream()
                .flatMap(p -> p.getAppointments().stream())
                .toList()));
        return dashboardMapper.toDTO(dashboardStatus);

    }
}
