package com.aman.patientService.dto;

import com.aman.patientService.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registration Date can not be null")
    private String registrationDate;

    public @NotBlank(message = "Address can not be blank") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address can not be blank") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date of Birth can not be blank") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of Birth can not be blank") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "Email can not be blank") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email can not be blank") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Name can not be blank") @Size(max = 100, message = "Name can not exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name can not be blank") @Size(max = 100, message = "Name can not exceed 100 characters") String name) {
        this.name = name;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate (String registrationDate) {
        this.registrationDate = registrationDate;
    }



}
