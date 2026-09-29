package com.aman.patientService.model;

import com.aman.patientService.dto.AppointmentsDto;

import java.util.List;

public class DashboardStatus {

    private long totalPatients;
    private long activePatients;
    private long newPatients;
    private long criticalPatients;
    private List<AppointmentsDto> todayAppointments;


    public long getCriticalPatients() {
        return criticalPatients;
    }

    public void setCriticalPatients(long criticalPatients) {
        this.criticalPatients = criticalPatients;
    }


    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public List<AppointmentsDto> getTodayAppointments() {
        return todayAppointments;
    }

    public void setTodayAppointments(List<AppointmentsDto> todayAppointments) {
        this.todayAppointments = todayAppointments;
    }

    public long getActivePatients() {
        return activePatients;
    }

    public void setActivePatients(long activePatients) {
        this.activePatients = activePatients;
    }

    public long getNewPatients() {
        return newPatients;
    }

    public void setNewPatients(long newPatients) {
        this.newPatients = newPatients;
    }


}
