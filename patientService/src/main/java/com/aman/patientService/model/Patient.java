package com.aman.patientService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Email
    @NotNull
    @Column(unique = true, name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @NotNull
    @Column(name = "DATEOFBIRTH")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "REGISTRATIONDATE")
    private LocalDate registrationDate;

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public @NotNull LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(@NotNull LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }


}
