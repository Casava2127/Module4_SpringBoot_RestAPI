package com.ac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StatisticsReportResponse {
    // Số điểm danh được phê duyệt
    private int approvedAttendances;
    // Số điểm danh bị từ chối
    private int rejectedAttendances;

    // Tổng điểm của mỗi sinh viên (key: studentId, value: tổng điểm)
    private Map<Long, Integer> totalScorePerStudent;

    // Tổng điểm theo sự kiện (key: eventId, value: tổng điểm)
    private Map<Long, Integer> totalScorePerEvent;
}
