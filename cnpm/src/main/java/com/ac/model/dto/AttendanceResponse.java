package com.ac.model.dto;

import com.ac.model.entity.Attendance;
import com.ac.model.entity.AttendanceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AttendanceResponse {
    private Long id;
    private Long studentId;
    private Long eventId;
    private Long qrId;
    private AttendanceStatus status;
    private LocalDateTime scanTime;

    public static AttendanceResponse fromEntity(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getAttendanceId())
                .studentId(attendance.getStudent().getUserId()) // Sửa lại getId()
                .eventId(attendance.getEvent().getEventId())
                .qrId(attendance.getQrCode().getQrId())
                .status(attendance.getStatus())
                .scanTime(attendance.getScanTime())
                .build();
    }
}
