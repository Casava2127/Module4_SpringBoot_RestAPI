package com.ac.controller;


import com.ac.model.dto.ApprovalRequest;
import com.ac.model.dto.UpdateRegistrationStatusRequest;
import com.ac.model.entity.*;
import com.ac.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventManagementController {

    @Autowired private EventManagementService service;

    // -------------------------- Users --------------------------
    @GetMapping("/users") // Lay danh sach tat ca nguoi dung
    public List<User> getAllUsers() { return service.getAllUsers(); }

    @GetMapping("/users/{id}") // Lay thong tin chi tiet cua nguoi dung theo ID
    public Optional<User> getUserById(@PathVariable Long id) { return service.getUserById(id); }

    @PostMapping("/users") // Tao nguoi dung moi
    public User createUser(@RequestBody User user) { return service.createUser(user); }

    @PutMapping("/users/{id}") // Cap nhat thong tin nguoi dung theo ID
    public User updateUser(@PathVariable Long id, @RequestBody User user) { return service.updateUser(id, user); }

    @DeleteMapping("/users/{id}") // Xoa nguoi dung theo ID
    public void deleteUser(@PathVariable Long id) { service.deleteUser(id); }

    // -------------------------- Quan ly su kien --------------------------
    @GetMapping("/events") // Lay danh sach tat ca su kien
    public List<Event> getAllEvents() { return service.getAllEvents(); }

    @GetMapping("/events/{id}") // Lay thong tin chi tiet su kien theo ID
    public Optional<Event> getEventById(@PathVariable Long id) { return service.getEventById(id); }

    @PostMapping("/events") // Tao su kien moi
    public Event createEvent(@RequestBody Event event) { return service.createEvent(event); }

    @PutMapping("/events/{id}") // Cap nhat thong tin su kien theo ID
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) { return service.updateEvent(id, event); }

    @DeleteMapping("/events/{id}") // Xoa su kien theo ID
    public void deleteEvent(@PathVariable Long id) { service.deleteEvent(id); }

    @PutMapping("events/{eventId}/approval")
    public ResponseEntity<Event> updateEventApproval(@PathVariable Long eventId,
                                                     @RequestBody ApprovalRequest request) {
        Event updatedEvent = service.updateEventApproval(eventId, request.getStatus());
        return ResponseEntity.ok(updatedEvent);
    }
    // Endpoint lấy danh sách sinh viên tham gia theo sự kiện cụ thể (id)
    @GetMapping("/events/{eventId}/participants")
    public ResponseEntity<List<User>> getEventParticipants(@PathVariable Long eventId) {
        List<User> participants = service.getEventParticipants(eventId);
        return ResponseEntity.ok(participants);
    }
    // -------------------------- Dang ky tham gia su kien --------------------------
    @GetMapping("/registrations") // Lay danh sach tat ca dang ky su kien
    public List<EventRegistration> getAllEventRegistrations() { return service.getAllEventRegistrations(); }

    @GetMapping("/registrations/{id}") // Lay thong tin chi tiet dang ky theo ID
    public Optional<EventRegistration> getEventRegistrationById(@PathVariable Long id) { return service.getEventRegistrationById(id); }

    @PostMapping("/registrations") // Tao dang ky su kien moi
    public EventRegistration createEventRegistration(@RequestBody EventRegistration reg) { return service.createEventRegistration(reg); }

    @PutMapping("/registrations/{id}") // Cap nhat thong tin dang ky theo ID
    public EventRegistration updateEventRegistration(@PathVariable Long id, @RequestBody EventRegistration reg) { return service.updateEventRegistration(id, reg); }

    @DeleteMapping("/registrations/{id}") // Xoa dang ky su kien theo ID
    public void deleteEventRegistration(@PathVariable Long id) { service.deleteEventRegistration(id); }

    @PutMapping("/registrations/{id}/approval")
    public ResponseEntity<?> updateRegistrationStatus(@PathVariable Long id,
                                                      @RequestBody UpdateRegistrationStatusRequest request) {
        try {
            EventRegistration updatedRegistration = service.updateRegistrationStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedRegistration);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // -------------------------- Ma QR cho diem danh --------------------------
    @GetMapping("/qrcodes") // Lay danh sach tat ca ma QR
    public List<QRCode> getAllQRCodes() { return service.getAllQRCodes(); }

    @GetMapping("/qrcodes/{id}") // Lay thong tin ma QR theo ID
    public Optional<QRCode> getQRCodeById(@PathVariable Long id) { return service.getQRCodeById(id); }

    @PostMapping("/qrcodes") // Tao ma QR moi
    public QRCode createQRCode(@RequestBody QRCode qr) { return service.createQRCode(qr); }

    @PutMapping("/qrcodes/{id}") // Cap nhat ma QR theo ID
    public QRCode updateQRCode(@PathVariable Long id, @RequestBody QRCode qr) { return service.updateQRCode(id, qr); }

    @DeleteMapping("/qrcodes/{id}") // Xoa ma QR theo ID
    public void deleteQRCode(@PathVariable Long id) { service.deleteQRCode(id); }

    // -------------------------- Attendance --------------------------

    @GetMapping("/attendance") // Lay danh sach tat ca diem danh
    public List<Attendance> getAllAttendanceRecords() { return service.getAllAttendanceRecords(); }

    @GetMapping("/attendance/{id}") // Lay chi tiet diem danh theo ID
    public Optional<Attendance> getAttendanceById(@PathVariable Long id) { return service.getAttendanceById(id); }

    @PostMapping("/attendance") // Tao moi diem danh
    public Attendance createAttendance(@RequestBody Attendance att) { return service.createAttendance(att); }

    @PutMapping("/attendance/{id}") // Cap nhat thong tin diem danh
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance att) { return service.updateAttendance(id, att); }

    @DeleteMapping("/attendance/{id}") // Xoa diem danh
    public void deleteAttendance(@PathVariable Long id) { service.deleteAttendance(id); }

    // -------------------------- Certificates --------------------------
    @GetMapping("/certificates") // Lay danh sach tat ca chung chi
    public List<Certificate> getAllCertificates() { return service.getAllCertificates(); }

    @GetMapping("/certificates/{id}") // Lay chi tiet chung chi theo ID
    public Optional<Certificate> getCertificateById(@PathVariable Long id) { return service.getCertificateById(id); }

    @PostMapping("/certificates") // Tao moi chung chi
    public Certificate createCertificate(@RequestBody Certificate cert) { return service.createCertificate(cert); }

    @PutMapping("/certificates/{id}") // Cap nhat thong tin chung chi
    public Certificate updateCertificate(@PathVariable Long id, @RequestBody Certificate cert) { return service.updateCertificate(id, cert); }

    @DeleteMapping("/certificates/{id}") // Xoa chung chi
    public void deleteCertificate(@PathVariable Long id) { service.deleteCertificate(id); }

    // -------------------------- EventPosts --------------------------
    @GetMapping("/posts") // Lay danh sach bai viet su kien
    public List<EventPost> getAllEventPosts() { return service.getAllEventPosts(); }

    @GetMapping("/posts/{id}") // Lay chi tiet bai viet theo ID
    public Optional<EventPost> getEventPostById(@PathVariable Long id) { return service.getEventPostById(id); }

    @PostMapping("/posts") // Tao moi bai viet su kien
    public EventPost createEventPost(@RequestBody EventPost post) { return service.createEventPost(post); }

    @PutMapping("/posts/{id}") // Cap nhat bai viet su kien
    public EventPost updateEventPost(@PathVariable Long id, @RequestBody EventPost post) { return service.updateEventPost(id, post); }

    @DeleteMapping("/posts/{id}") // Xoa bai viet su kien
    public void deleteEventPost(@PathVariable Long id) { service.deleteEventPost(id); }

    // -------------------------- EventScores --------------------------
    @GetMapping("/scores") // Lay danh sach diem su kien
    public List<EventScore> getAllEventScores() { return service.getAllEventScores(); }

    @GetMapping("/scores/{id}") // Lay diem su kien theo ID
    public Optional<EventScore> getEventScoreById(@PathVariable Long id) { return service.getEventScoreById(id); }

    @PostMapping("/scores") // Tao diem su kien moi
    public EventScore createEventScore(@RequestBody EventScore score) { return service.createEventScore(score); }

    @PutMapping("/scores/{id}") // Cap nhat diem su kien
    public EventScore updateEventScore(@PathVariable Long id, @RequestBody EventScore score) { return service.updateEventScore(id, score); }

    @DeleteMapping("/scores/{id}") // Xoa diem su kien
    public void deleteEventScore(@PathVariable Long id) { service.deleteEventScore(id); }

    // -------------------------- Reports --------------------------
    @GetMapping("/reports") // Lay danh sach bao cao
    public List<Report> getAllReports() { return service.getAllReports(); }

    @GetMapping("/reports/{id}") // Lay chi tiet bao cao theo ID
    public Optional<Report> getReportById(@PathVariable Long id) { return service.getReportById(id); }

    @PostMapping("/reports") // Tao bao cao moi
    public Report createReport(@RequestBody Report rep) { return service.createReport(rep); }

    @PutMapping("/reports/{id}") // Cap nhat bao cao
    public Report updateReport(@PathVariable Long id, @RequestBody Report rep) { return service.updateReport(id, rep); }

    @DeleteMapping("/reports/{id}") // Xoa bao cao
    public void deleteReport(@PathVariable Long id) { service.deleteReport(id); }
}

//Controller này cung cấp một bộ API quản lý toàn diện các thực thể trong hệ thống. Cụ thể, nó bao gồm các chức năng sau:
//
//---
//
//### **1. Quản lý Người dùng (Users)**
//- **Lấy danh sách tất cả người dùng:**
//  `GET /api/v1/users`
//- **Lấy thông tin chi tiết của người dùng theo ID:**
//  `GET /api/v1/users/{id}`
//- **Tạo người dùng mới:**
//  `POST /api/v1/users`
//- **Cập nhật thông tin người dùng theo ID:**
//  `PUT /api/v1/users/{id}`
//- **Xoá người dùng theo ID:**
//  `DELETE /api/v1/users/{id}`
//
//---
//
//### **2. Quản lý Sự kiện (Events)**
//- **Lấy danh sách tất cả sự kiện:**
//  `GET /api/v1/events`
//- **Lấy thông tin chi tiết của sự kiện theo ID:**
//  `GET /api/v1/events/{id}`
//- **Tạo sự kiện mới:**
//  `POST /api/v1/events`
//- **Cập nhật thông tin sự kiện theo ID:**
//  `PUT /api/v1/events/{id}`
//- **Xoá sự kiện theo ID:**
//  `DELETE /api/v1/events/{id}`
//- **Duyệt sự kiện (phê duyệt/từ chối):**
//  `PUT /api/v1/events/{eventId}/approval`
//  (Nhận đối tượng `ApprovalRequest` để cập nhật trạng thái duyệt)
//
//---
//
//### **3. Quản lý Đăng ký tham gia Sự kiện (EventRegistrations)**
//- **Lấy danh sách tất cả đăng ký sự kiện:**
//  `GET /api/v1/registrations`
//- **Lấy thông tin chi tiết đăng ký theo ID:**
//  `GET /api/v1/registrations/{id}`
//- **Tạo đăng ký sự kiện mới:**
//  `POST /api/v1/registrations`
//- **Cập nhật thông tin đăng ký theo ID:**
//  `PUT /api/v1/registrations/{id}`
//- **Xoá đăng ký sự kiện theo ID:**
//  `DELETE /api/v1/registrations/{id}`
//- **Duyệt/từ chối đăng ký sự kiện:**
//  `PUT /api/v1/registrations/{id}/approval`
//  (Nhận đối tượng `UpdateRegistrationStatusRequest` chứa trạng thái)
//
//---
//
//### **4. Quản lý Mã QR cho Điểm danh (QRCode)**
//- **Lấy danh sách tất cả mã QR:**
//  `GET /api/v1/qrcodes`
//- **Lấy thông tin mã QR theo ID:**
//  `GET /api/v1/qrcodes/{id}`
//- **Tạo mã QR mới:**
//  `POST /api/v1/qrcodes`
//- **Cập nhật thông tin mã QR theo ID:**
//  `PUT /api/v1/qrcodes/{id}`
//- **Xoá mã QR theo ID:**
//  `DELETE /api/v1/qrcodes/{id}`
//
//---
//
//### **5. Quản lý Điểm danh (Attendance)**
//- **Lấy danh sách tất cả điểm danh:**
//  `GET /api/v1/attendance`
//- **Lấy chi tiết điểm danh theo ID:**
//  `GET /api/v1/attendance/{id}`
//- **Tạo mới điểm danh:**
//  `POST /api/v1/attendance`
//- **Cập nhật thông tin điểm danh theo ID:**
//  `PUT /api/v1/attendance/{id}`
//- **Xoá điểm danh:**
//  `DELETE /api/v1/attendance/{id}`
//
//---
//
//### **6. Quản lý Minh chứng (Certificates)**
//- **Lấy danh sách tất cả chứng chỉ:**
//  `GET /api/v1/certificates`
//- **Lấy chi tiết chứng chỉ theo ID:**
//  `GET /api/v1/certificates/{id}`
//- **Tạo mới chứng chỉ:**
//  `POST /api/v1/certificates`
//- **Cập nhật chứng chỉ theo ID:**
//  `PUT /api/v1/certificates/{id}`
//- **Xoá chứng chỉ:**
//  `DELETE /api/v1/certificates/{id}`
//
//---
//
//### **7. Quản lý Bài đăng Sự kiện (EventPosts)**
//- **Lấy danh sách tất cả bài đăng sự kiện:**
//  `GET /api/v1/posts`
//- **Lấy thông tin chi tiết bài đăng theo ID:**
//  `GET /api/v1/posts/{id}`
//- **Tạo bài đăng sự kiện mới:**
//  `POST /api/v1/posts`
//- **Cập nhật bài đăng sự kiện theo ID:**
//  `PUT /api/v1/posts/{id}`
//- **Xoá bài đăng sự kiện:**
//  `DELETE /api/v1/posts/{id}`
//
//---
//
//### **8. Quản lý Điểm Sự kiện (EventScores)**
//- **Lấy danh sách tất cả điểm sự kiện:**
//  `GET /api/v1/scores`
//- **Lấy điểm sự kiện theo ID:**
//  `GET /api/v1/scores/{id}`
//- **Tạo điểm sự kiện mới:**
//  `POST /api/v1/scores`
//- **Cập nhật điểm sự kiện theo ID:**
//  `PUT /api/v1/scores/{id}`
//- **Xoá điểm sự kiện:**
//  `DELETE /api/v1/scores/{id}`
//
//---
//
//### **9. Quản lý Báo cáo Sự kiện (Reports)**
//- **Lấy danh sách tất cả báo cáo:**
//  `GET /api/v1/reports`
//- **Lấy chi tiết báo cáo theo ID:**
//  `GET /api/v1/reports/{id}`
//- **Tạo báo cáo mới:**
//  `POST /api/v1/reports`
//- **Cập nhật báo cáo theo ID:**
//  `PUT /api/v1/reports/{id}`
//- **Xoá báo cáo:**
//  `DELETE /api/v1/reports/{id}`
//
//---
//
//Mỗi nhóm chức năng trên được triển khai với các endpoint RESTful, và controller này hoạt động như một trung tâm quản lý các thực thể trong hệ thống, từ người dùng, sự kiện, đăng ký, điểm danh cho đến báo cáo và chứng chỉ.