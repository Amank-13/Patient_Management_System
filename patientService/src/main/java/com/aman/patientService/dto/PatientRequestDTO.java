package com.aman.patientService.dto;

import com.aman.patientService.Enums.Status;
import com.aman.patientService.dto.validators.CreatePatientValidationGroup;
import com.aman.patientService.model.Appointments;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class PatientRequestDTO {

    Logger logger = LoggerFactory.getLogger(PatientRequestDTO.class);
    @NotBlank(message = "Name can not be blank")
    @Size(max = 100, message = "Name can not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address can not be blank")
    private String address;

    @NotBlank(message = "Date of Birth can not be blank")
    private LocalDate dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registration Date can not be null")
    private LocalDate registrationDate;

    @NotNull
    private Boolean isActive ;

    @NotNull
    private Status Status;

    @NotNull
    private List<Appointments> appointments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date of Birth can not be blank") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }



}
