package com.ac.Note.controller;

import com.ac.model.dto.AttendanceRequest;
import com.ac.model.dto.AttendanceResponse;
import com.ac.model.entity.AttendanceStatus;
import com.ac.Note.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Sinh viên điểm danh qua QR Code
    @PostMapping
    public ResponseEntity<AttendanceResponse> markAttendance(@RequestBody AttendanceRequest request) {
        AttendanceResponse response = attendanceService.markAttendance(
                request.getStudentId(), request.getEventId(), request.getQrId());
        return ResponseEntity.ok(response);
    }

    // BTC/Nhà trường phê duyệt hoặc từ chối điểm danh
    @PutMapping("/{attendanceId}/status/{status}")
    public ResponseEntity<AttendanceResponse> updateAttendanceStatus(
            @PathVariable Long attendanceId, @PathVariable String status) {

        AttendanceStatus attendanceStatus = AttendanceStatus.valueOf(status.toUpperCase());
        AttendanceResponse response = attendanceService.updateAttendanceStatus(attendanceId, attendanceStatus);
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách điểm danh theo sự kiện
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendancesByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(attendanceService.getAttendancesByEvent(eventId));
    }

    // Lấy danh sách điểm danh theo sinh viên
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendancesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendancesByStudent(studentId));
    }
}
//Controller này có các chức năng chính sau:
//
//1. **Sinh viên điểm danh qua QR Code**
//   - **Endpoint:** `POST /api/v1/attendances`
//   - **Chức năng:** Nhận yêu cầu điểm danh từ sinh viên với các thông tin như studentId, eventId và qrId.
//   - **Quá trình:** Gọi service để tạo bản ghi điểm danh với trạng thái ban đầu là PENDING.
//
//2. **BTC/Nhà trường phê duyệt hoặc từ chối điểm danh**
//   - **Endpoint:** `PUT /api/v1/attendances/{attendanceId}/status/{status}`
//   - **Chức năng:** Cập nhật trạng thái điểm danh (APPROVED hoặc REJECTED) dựa trên yêu cầu của BTC hoặc Nhà trường.
//   - **Quá trình:** Lấy attendance theo ID, sau đó cập nhật trạng thái mới và lưu lại.
//
//3. **Lấy danh sách điểm danh theo sự kiện**
//   - **Endpoint:** `GET /api/v1/attendances/event/{eventId}`
//   - **Chức năng:** Truy vấn và trả về danh sách điểm danh của một sự kiện cụ thể.
//   - **Quá trình:** Gọi service để lấy danh sách điểm danh liên quan đến eventId.
//
//4. **Lấy danh sách điểm danh theo sinh viên**
//   - **Endpoint:** `GET /api/v1/attendances/student/{studentId}`
//   - **Chức năng:** Truy vấn và trả về lịch sử điểm danh của một sinh viên cụ thể.
//   - **Quá trình:** Gọi service để lấy danh sách điểm danh liên quan đến studentId.
//
//Mỗi chức năng trên được triển khai qua các phương thức RESTful và sử dụng lớp Service tương ứng để thực hiện nghiệp vụ, sau đó trả về dữ liệu dưới dạng DTO (`AttendanceResponse`).