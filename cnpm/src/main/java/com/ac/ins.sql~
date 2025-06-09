INSERT INTO Users (email, password, full_name, phone_number, role) VALUES
                                                                       ('admin@example.com', 'admin123', 'Admin User', '0123456789', 'ADMIN'),
                                                                       ('giangvien1@example.com', 'password123', 'Giảng Viên A', '0987654321', 'GIANG_VIEN'),
                                                                       ('sinhvien1@example.com', 'password123', 'Sinh Viên B', '0909090909', 'SINH_VIEN'),
                                                                       ('btc1@example.com', 'password123', 'Ban Tổ Chức C', '0888888888', 'BTC'),
                                                                       ('truong@example.com', 'password123', 'Nhà Trường D', '0777777777', 'NHA_TRUONG');
INSERT INTO Events (event_name, description, organizer_id, start_date, end_date, registration_deadline, status) VALUES
                                                                                                                    ('Hội Thảo AI', 'Hội thảo về trí tuệ nhân tạo', 2, '2025-04-10 09:00:00', '2025-04-10 12:00:00', '2025-04-08 23:59:59', 'APPROVED'),
                                                                                                                    ('Hội Thảo Blockchain', 'Ứng dụng Blockchain', 2, '2025-05-15 14:00:00', '2025-05-15 17:00:00', '2025-05-13 23:59:59', 'PENDING'),
                                                                                                                    ('Workshop UI/UX', 'Thiết kế UI/UX chuyên sâu', 4, '2025-06-01 10:00:00', '2025-06-01 15:00:00', '2025-05-30 23:59:59', 'APPROVED'),
                                                                                                                    ('Coding Bootcamp', 'Lập trình chuyên sâu', 4, '2025-07-20 09:00:00', '2025-07-25 17:00:00', '2025-07-18 23:59:59', 'ONGOING'),
                                                                                                                    ('Cuộc thi Lập Trình', 'Thi đấu lập trình với giải thưởng hấp dẫn', 5, '2025-08-05 08:00:00', '2025-08-05 18:00:00', '2025-08-03 23:59:59', 'PENDING');
INSERT INTO Event_Registrations (student_id, event_id, status, registration_date) VALUES
                                                                                      (3, 1, 'APPROVED',NOW()),
                                                                                      (3, 2, 'PENDING',NOW()),
                                                                                      (3, 3, 'APPROVED',NOW()),
                                                                                      (3, 4, 'CANCELLED',NOW()),
                                                                                      (3, 5, 'PENDING',NOW());
INSERT INTO QR_Codes (event_id, qr_code, valid_from, valid_until) VALUES
                                                                      (1, 'QR12345', '2025-04-10 09:00:00', '2025-04-10 12:00:00'),
                                                                      (2, 'QR67890', '2025-05-15 14:00:00', '2025-05-15 17:00:00'),
                                                                      (3, 'QR11111', '2025-06-01 10:00:00', '2025-06-01 15:00:00'),
                                                                      (4, 'QR22222', '2025-07-20 09:00:00', '2025-07-25 17:00:00'),
                                                                      (5, 'QR33333', '2025-08-05 08:00:00', '2025-08-05 18:00:00');
INSERT INTO Attendance (student_id, event_id, qr_id, status,scan_time) VALUES
                                                                           (3, 1, 1, 'APPROVED',NOW()),
                                                                           (3, 2, 2, 'PENDING',NOW()),
                                                                           (3, 3, 3, 'APPROVED',NOW()),
                                                                           (3, 4, 4, 'REJECTED',NOW()),
                                                                           (3, 5, 5, 'APPROVED',NOW());
-- INSERT cho Certificates (Minh chứng tham gia sự kiện)
INSERT INTO Certificates (student_id, event_id, issue_date) VALUES
                                                                (3, 1, NOW()),
                                                                (3, 2, NOW()),
                                                                (3, 3, NOW()),
                                                                (3, 4, NOW()),
                                                                (3, 5, NOW());

-- INSERT cho Event_Posts (Bài đăng về sự kiện)
INSERT INTO Event_Posts (event_id, organizer_id, content, created_at, updated_at) VALUES
                                                                                      (1, 2, 'Hội thảo AI sắp diễn ra!', NOW(), NOW()),
                                                                                      (2, 2, 'Blockchain: Cơ hội và thách thức.', NOW(), NOW()),
                                                                                      (3, 4, 'Workshop UI/UX: Thông tin chi tiết.', NOW(), NOW()),
                                                                                      (4, 4, 'Coding Bootcamp: Hãy đăng ký ngay!', NOW(), NOW()),
                                                                                      (5, 5, 'Cuộc thi Lập Trình: Chuẩn bị tinh thần!', NOW(), NOW());

-- INSERT cho Event_Scores (Quy định điểm cho từng sự kiện)
INSERT INTO Event_Scores (event_id, min_score, max_score) VALUES
                                                              (1, 5, 10),
                                                              (2, 6, 12),
                                                              (3, 4, 8),
                                                              (4, 7, 15),
                                                              (5, 3, 6);

-- INSERT cho Reports (Báo cáo thống kê sự kiện)
INSERT INTO Reports (event_id, created_by, report_content, created_at) VALUES
                                                                           (1, 2, 'Hội thảo AI thành công với hơn 100 người tham gia.', NOW()),
                                                                           (2, 2, 'Hội thảo Blockchain thu hút nhiều doanh nghiệp.', NOW()),
                                                                           (3, 4, 'Workshop UI/UX nhận được phản hồi tích cực.', NOW()),
                                                                           (4, 4, 'Coding Bootcamp đạt chuẩn thực hành cao.', NOW()),
                                                                           (5, 5, 'Cuộc thi Lập Trình với giải thưởng hấp dẫn.', NOW());
