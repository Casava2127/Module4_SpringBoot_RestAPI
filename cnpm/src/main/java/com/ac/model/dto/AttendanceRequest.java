package com.ac.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {
    private Long studentId;
    private Long eventId;
    private Long qrId;
}
