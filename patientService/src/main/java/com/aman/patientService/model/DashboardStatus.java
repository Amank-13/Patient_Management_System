package com.aman.patientService.model;

import java.util.List;

public class DashboardStatus {

    private long totalPatients;
    private long activePatients;
    private long newPatients;

    public long getCriticalPatients() {
        return criticalPatients;
    }

    public void setCriticalPatients(long criticalPatients) {
        this.criticalPatients = criticalPatients;
    }

    private long criticalPatients;
    private List<Appointments> todayAppointments;

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public List<Appointments> getTodayAppointments() {
        return todayAppointments;
    }

    public void setTodayAppointments(List<Appointments> todayAppointments) {
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
