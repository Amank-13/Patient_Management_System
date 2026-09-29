package com.aman.patientService.mapper;

import com.aman.patientService.dto.AppointmentsDto;
import com.aman.patientService.model.Appointments;

import java.util.List;

public class AppointmentMapper {

    public List<AppointmentsDto> toDTOList(List<Appointments> list) {
        return list.stream().map(this::toDTO).toList();
    }

    private AppointmentsDto toDTO(Appointments appointment) {
        AppointmentsDto appointmentsDto = new AppointmentsDto();

        appointmentsDto.setAppointmentId(appointment.getAppointmentId());
        appointmentsDto.setDoctorName(appointment.getDoctorName());
        appointmentsDto.setCritical(appointment.getCritical());
        appointmentsDto.setCreatedDate(appointment.getCreatedDate());
        return appointmentsDto;
    }
}
