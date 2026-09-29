package com.aman.patientService.dto;

import com.aman.patientService.model.Appointments;
import com.aman.patientService.model.Patient;

import java.util.List;

public class DashboardStatusDto {

    private long totalPatients;
    private long critical;
    private long newPatients;
    private List<AppointmentsDto> todayAppointments;
    private  long ActivePatients;

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getNewPatients() {
        return newPatients;
    }

    public void setNewPatients(long newPatients) {
        this.newPatients = newPatients;
    }

    public List<AppointmentsDto> getTodayAppointments() {
        return todayAppointments;
    }

    public void setTodayAppointments(List<AppointmentsDto> todayAppointments) {
        this.todayAppointments = todayAppointments;
    }


    public long getActivePatients() {
        return ActivePatients;
    }
    public void setActivePatients(long activePatients) {
        this.ActivePatients = activePatients;
    }

}
