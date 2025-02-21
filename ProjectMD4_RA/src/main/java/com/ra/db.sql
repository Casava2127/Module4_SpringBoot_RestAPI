-- Nếu cần, xóa cơ sở dữ liệu cũ (chỉ dùng trong môi trường thử nghiệm)
DROP DATABASE IF EXISTS course_management1;

-- Tạo cơ sở dữ liệu và sử dụng nó
CREATE DATABASE course_management1;
USE course_management1;

-------------------------------------------------
-- 1. Bảng người dùng (users)
--    Hợp nhất thông tin địa chỉ và số điện thoại
-------------------------------------------------
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       fullname VARCHAR(100) NOT NULL,
                       status BIT DEFAULT 1,                -- 1: Active, 0: Blocked
                       password VARCHAR(255) NOT NULL,        -- Đã mã hóa
                       avatar VARCHAR(255),
                       phone VARCHAR(15) UNIQUE,              -- Định dạng VN, không trùng lặp
                       address VARCHAR(255) NOT NULL,         -- Địa chỉ cá nhân
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       is_deleted BIT DEFAULT 0               -- 0: Hoạt động, 1: Bị xóa
);

-------------------------------------------------
-- 2. Bảng quyền (roles)
-------------------------------------------------
CREATE TABLE roles (
                       role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name ENUM('ADMIN', 'STUDENT', 'INSTRUCTOR') NOT NULL
);

-------------------------------------------------
-- 3. Bảng phân quyền người dùng (user_roles)
-------------------------------------------------
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 4. Bảng danh mục khóa học (categories)
-------------------------------------------------
CREATE TABLE categories (
                            category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL,
                            description TEXT,
                            status BIT DEFAULT 1                 -- 1: Active, 0: Inactive
);

-------------------------------------------------
-- 5. Bảng khóa học (courses)
-------------------------------------------------
CREATE TABLE courses (
                         course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         sku VARCHAR(100) NOT NULL UNIQUE,      -- Mã khóa học dạng UUID ngẫu nhiên
                         course_name VARCHAR(100) NOT NULL UNIQUE,
                         description TEXT,
                         price DECIMAL(10,2) NOT NULL,
                         available_slots INT DEFAULT 0 CHECK (available_slots >= 0),
                         image VARCHAR(255),
                         category_id BIGINT,
                         instructor_id BIGINT,                  -- ID giảng viên
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL,
                         FOREIGN KEY (instructor_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-------------------------------------------------
-- 6. Bảng bài giảng của khóa học (course_lessons)
-------------------------------------------------
CREATE TABLE course_lessons (
                                lesson_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                course_id BIGINT NOT NULL,
                                lesson_title VARCHAR(255) NOT NULL,
                                content TEXT,
                                video_url VARCHAR(255),
                                sort_order INT DEFAULT 0,
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 7. Bảng đăng ký khóa học (enrollments)
--    Đại diện cho việc học viên đăng ký tham gia khóa học
-------------------------------------------------
CREATE TABLE enrollments (
                             enrollment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             serial_number VARCHAR(100) NOT NULL UNIQUE,  -- Mã đăng ký dạng UUID tự sinh
                             user_id BIGINT NOT NULL,
                             total_price DECIMAL(10,2) NOT NULL,
                             status ENUM('WAITING', 'CONFIRMED', 'IN_PROGRESS', 'COMPLETED', 'CANCELED', 'DENIED') NOT NULL,
                             note VARCHAR(100),
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 8. Bảng chi tiết đăng ký khóa học (enrollment_details)
-------------------------------------------------
CREATE TABLE enrollment_details (
                                    enrollment_id BIGINT NOT NULL,
                                    course_id BIGINT NOT NULL,
                                    course_name VARCHAR(100),
                                    unit_price DECIMAL(10,2),
                                    quantity INT NOT NULL CHECK (quantity > 0),
                                    PRIMARY KEY (enrollment_id, course_id),
                                    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id) ON DELETE CASCADE,
                                    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 9. Bảng giỏ khóa học (course_cart)
-------------------------------------------------
CREATE TABLE course_cart (
                             cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             course_id BIGINT NOT NULL,
                             user_id BIGINT NOT NULL,
                             quantity INT NOT NULL CHECK (quantity > 0),
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 10. Bảng danh sách yêu thích khóa học (favorite_courses)
-------------------------------------------------
CREATE TABLE favorite_courses (
                                  favorite_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  user_id BIGINT NOT NULL,
                                  course_id BIGINT NOT NULL,
                                  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                  FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 11. Bảng đánh giá khóa học (course_reviews)
-------------------------------------------------
CREATE TABLE course_reviews (
                                review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                course_id BIGINT NOT NULL,
                                user_id BIGINT NOT NULL,
                                rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
                                comment TEXT,
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 12. Bảng giao dịch thanh toán (payments)
-------------------------------------------------
CREATE TABLE payments (
                          payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          enrollment_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          amount DECIMAL(10,2) NOT NULL,
                          payment_method ENUM('CREDIT_CARD', 'PAYPAL', 'BANK_TRANSFER') NOT NULL,
                          status ENUM('PENDING', 'SUCCESS', 'FAILED') NOT NULL,
                          paid_at DATETIME,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 13. Bảng thông báo (notifications)
-------------------------------------------------
CREATE TABLE notifications (
                               notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               title VARCHAR(255) NOT NULL,
                               message TEXT NOT NULL,
                               is_read BIT DEFAULT 0,  -- 0: chưa đọc, 1: đã đọc
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 14. Bảng bài tập (assignments)
-------------------------------------------------
CREATE TABLE assignments (
                             assignment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             course_id BIGINT NOT NULL,
                             title VARCHAR(255) NOT NULL,
                             description TEXT,
                             due_date DATETIME NOT NULL,
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 15. Bảng nộp bài (submissions)
-------------------------------------------------
CREATE TABLE submissions (
                             submission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             assignment_id BIGINT NOT NULL,
                             user_id BIGINT NOT NULL,
                             file_url VARCHAR(255),
                             grade DECIMAL(5,2),      -- Ví dụ: 0.00 đến 10.00
                             feedback TEXT,
                             submitted_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (assignment_id) REFERENCES assignments(assignment_id) ON DELETE CASCADE,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 16. Bảng diễn đàn thảo luận (discussions)
-------------------------------------------------
CREATE TABLE discussions (
                             discussion_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             course_id BIGINT NOT NULL,
                             user_id BIGINT NOT NULL,
                             title VARCHAR(255) NOT NULL,
                             content TEXT NOT NULL,
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 17. Bảng bình luận diễn đàn (discussion_comments)
-------------------------------------------------
CREATE TABLE discussion_comments (
                                     comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     discussion_id BIGINT NOT NULL,
                                     user_id BIGINT NOT NULL,
                                     comment TEXT NOT NULL,
                                     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (discussion_id) REFERENCES discussions(discussion_id) ON DELETE CASCADE,
                                     FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 18. Bảng tin nhắn riêng (private_messages)
-------------------------------------------------
CREATE TABLE private_messages (
                                  message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  sender_id BIGINT NOT NULL,
                                  receiver_id BIGINT NOT NULL,
                                  subject VARCHAR(255),
                                  content TEXT NOT NULL,
                                  is_read BIT DEFAULT 0,  -- 0: chưa đọc, 1: đã đọc
                                  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  FOREIGN KEY (sender_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                  FOREIGN KEY (receiver_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 19. Bảng chứng chỉ (certificates)
-------------------------------------------------
CREATE TABLE certificates (
                              certificate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              enrollment_id BIGINT NOT NULL,
                              certificate_url VARCHAR(255) NOT NULL,
                              issued_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id) ON DELETE CASCADE
);

-------------------------------------------------
-- 20. Bảng lịch học trực tuyến (live_sessions)
-------------------------------------------------
CREATE TABLE live_sessions (
                               live_session_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               course_id BIGINT NOT NULL,
                               session_title VARCHAR(255) NOT NULL,
                               session_description TEXT,
                               scheduled_at DATETIME NOT NULL,
                               duration INT,  -- Thời gian (phút)
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);
