package com.ac.service;

import com.ac.model.entity.*;
import com.ac.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    @Autowired
    private QRCodeRepository qrCodeRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private EventPostRepository eventPostRepository;
    @Autowired
    private EventScoreRepository eventScoreRepository;
    @Autowired
    private ReportRepository reportRepository;

    // CRUD cho Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        // Cập nhật thêm các trường khác nếu cần
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // CRUD cho Events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        ((com.ac.model.entity.Event) event).setEventName(eventDetails.getEventName());
        event.setDescription(eventDetails.getDescription());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setRegistrationDeadline(eventDetails.getRegistrationDeadline());
        event.setStatus(eventDetails.getStatus());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Phê duyệt hoặc từ chối duyệt sự kiện
    public Event updateEventApproval(Long eventId, String approvalStatus) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Chỉ cho phép duyệt với APPROVED hoặc REJECTED
        if (!"APPROVED".equalsIgnoreCase(approvalStatus) && !"REJECTED".equalsIgnoreCase(approvalStatus)) {
            throw new IllegalArgumentException("Invalid status. Allowed values: APPROVED or REJECTED");
        }

        event.setStatus(EventStatus.valueOf(approvalStatus.toUpperCase()));
        return eventRepository.save(event);
    }
    // Phương thức lấy danh sách sinh viên tham gia theo sự kiện
    public List<User> getEventParticipants(Long eventId) {
        // Lấy danh sách đăng ký của sự kiện
        List<EventRegistration> registrations = eventRegistrationRepository.findByEvent_EventId(eventId);
        // Lấy danh sách điểm danh của sự kiện
        List<Attendance> attendances = attendanceRepository.findByEvent_EventId(eventId);

        Set<User> participants = new HashSet<>();

        // Thêm sinh viên từ đăng ký (chỉ lấy đăng ký được phê duyệt)
        for (EventRegistration reg : registrations) {
            // Giả sử trạng thái đăng ký được lưu dưới dạng chuỗi (ví dụ "APPROVED")
            if ("APPROVED".equalsIgnoreCase(String.valueOf(reg.getStatus()))) {
                participants.add(reg.getStudent());
            }
        }

        // Thêm sinh viên từ điểm danh (chỉ lấy điểm danh được phê duyệt)
        for (Attendance att : attendances) {
            if (att.getStatus() == AttendanceStatus.APPROVED) {
                participants.add(att.getStudent());
            }
        }
        return new ArrayList<>(participants);
    }

    // CRUD cho EventRegistrations
    public List<EventRegistration> getAllEventRegistrations() {
        return eventRegistrationRepository.findAll();
    }

    public Optional<EventRegistration> getEventRegistrationById(Long id) {
        return eventRegistrationRepository.findById(id);
    }

    public EventRegistration createEventRegistration(EventRegistration eventRegistration) {
        return eventRegistrationRepository.save(eventRegistration);
    }

    public EventRegistration updateEventRegistration(Long id, EventRegistration details) {
        EventRegistration reg = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
        reg.setStatus(details.getStatus());
        // Cập nhật thêm các trường nếu cần
        return eventRegistrationRepository.save(reg);
    }

    public void deleteEventRegistration(Long id) {
        eventRegistrationRepository.deleteById(id);
    }

    public EventRegistration updateRegistrationStatus(Long registrationId, String status) {
        Optional<EventRegistration> regOpt = eventRegistrationRepository.findById(registrationId);
        if (regOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đăng ký với ID: " + registrationId);
        }
        EventRegistration registration = regOpt.get();
        // Giả định rằng entity EventRegistration có thuộc tính status kiểu String
        registration.setStatus(RegistrationStatus.valueOf(status));
        return eventRegistrationRepository.save(registration);
    }

    // CRUD cho QR_Codes
    public List<QRCode> getAllQRCodes() {
        return qrCodeRepository.findAll();
    }

    public Optional<QRCode> getQRCodeById(Long id) {
        return qrCodeRepository.findById(id);
    }

    public QRCode createQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QRCode updateQRCode(Long id, QRCode details) {
        QRCode qr = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QRCode not found with id: " + id));
        qr.setQrCode(details.getQrCode());
        qr.setValidFrom(details.getValidFrom());
        qr.setValidUntil(details.getValidUntil());
        return qrCodeRepository.save(qr);
    }

    public void deleteQRCode(Long id) {
        qrCodeRepository.deleteById(id);
    }

    // CRUD cho Attendance
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Long id, Attendance details) {
        Attendance att = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        att.setStatus(details.getStatus());
        // Cập nhật các trường khác nếu cần
        return attendanceRepository.save(att);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    // CRUD cho Certificates
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Optional<Certificate> getCertificateById(Long id) {
        return certificateRepository.findById(id);
    }

    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    // CRUD cho Certificates
    public Certificate updateCertificate(Long id, Certificate details) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));
        // Cập nhật các trường khác nếu có (thông thường issueDate không thay đổi)
        // Ví dụ: cert.setSomeField(details.getSomeField());
        return certificateRepository.save(cert);
    }

    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }

    // CRUD cho EventPosts
    public List<EventPost> getAllEventPosts() {
        return eventPostRepository.findAll();
    }

    public Optional<EventPost> getEventPostById(Long id) {
        return eventPostRepository.findById(id);
    }

    public EventPost createEventPost(EventPost eventPost) {
        return eventPostRepository.save(eventPost);
    }

    public EventPost updateEventPost(Long id, EventPost details) {
        EventPost post = eventPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventPost not found with id: " + id));
        post.setContent(details.getContent());
        // Cập nhật thêm các trường khác nếu cần
        return eventPostRepository.save(post);
    }

    public void deleteEventPost(Long id) {
        eventPostRepository.deleteById(id);
    }

    // CRUD cho EventScores
    public List<EventScore> getAllEventScores() {
        return eventScoreRepository.findAll();
    }

    public Optional<EventScore> getEventScoreById(Long id) {
        return eventScoreRepository.findById(id);
    }

    public EventScore createEventScore(EventScore eventScore) {
        return eventScoreRepository.save(eventScore);
    }

    public EventScore updateEventScore(Long id, EventScore details) {
        EventScore score = eventScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventScore not found with id: " + id));
        score.setMinScore(details.getMinScore());
        score.setMaxScore(details.getMaxScore());
        return eventScoreRepository.save(score);
    }

    public void deleteEventScore(Long id) {
        eventScoreRepository.deleteById(id);
    }

    // CRUD cho Reports
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report details) {
        Report rep = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        rep.setReportContent(details.getReportContent());
        // Cập nhật thêm các trường khác nếu cần
        return reportRepository.save(rep);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }


}
