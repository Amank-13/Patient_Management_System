package com.aman.patientService.mapper;

import com.aman.patientService.dto.DashboardStatusDto;
import com.aman.patientService.model.DashboardStatus;

public class DashboardMapper {

    public DashboardStatusDto toDTO(DashboardStatus dashboardStatus ){

        DashboardStatusDto dashboardStatusDto = new DashboardStatusDto();
        dashboardStatusDto.setTotalPatients(dashboardStatus.getTotalPatients());
        dashboardStatusDto.setNewPatients(dashboardStatus.getNewPatients());
        dashboardStatusDto.setCritical(dashboardStatus.getCriticalPatients());
        dashboardStatusDto.setTodayAppointments(dashboardStatus.getTodayAppointments());
        dashboardStatusDto.setActivePatients(dashboardStatus.getActivePatients());

        return dashboardStatusDto;
    }
}
