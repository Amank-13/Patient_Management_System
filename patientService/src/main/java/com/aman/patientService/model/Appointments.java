package com.aman.patientService.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Appointments {

    @Id
    @Column(name = "appointment_Id")
    private int AppointmentId;

    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "is_Critical")
    private Boolean isCritical;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "patientId",nullable = false)
    private Patient patient;

    public int getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Boolean getCritical() {
        return isCritical;
    }

    public void setCritical(Boolean critical) {
        isCritical = critical;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
