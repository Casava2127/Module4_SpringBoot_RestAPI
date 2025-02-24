-- Nếu cần, xóa cơ sở dữ liệu cũ (chỉ dùng trong môi trường thử nghiệm)
DROP DATABASE IF EXISTS course_management;

-- Tạo cơ sở dữ liệu và sử dụng nó
CREATE DATABASE course_management;
USE course_management;
INSERT INTO roles (role_name) VALUES ('STUDENT'), ('ADMIN'), ('INSTRUCTOR');


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





-- Chèn dữ liệu vào bảng users
INSERT INTO users (username, email, fullname, status, password, avatar, phone, address) VALUES
                                                                                            ('john_doe', 'john@example.com', 'John Doe', 1, 'hashed_pw1', 'avatar1.jpg', '0987654321', 'Hanoi'),
                                                                                            ('jane_smith', 'jane@example.com', 'Jane Smith', 1, 'hashed_pw2', 'avatar2.jpg', '0987654322', 'HCM City'),
                                                                                            ('alice_brown', 'alice@example.com', 'Alice Brown', 1, 'hashed_pw3', 'avatar3.jpg', '0987654323', 'Da Nang'),
                                                                                            ('bob_green', 'bob@example.com', 'Bob Green', 1, 'hashed_pw4', 'avatar4.jpg', '0987654324', 'Hai Phong'),
                                                                                            ('charlie_white', 'charlie@example.com', 'Charlie White', 1, 'hashed_pw5', 'avatar5.jpg', '0987654325', 'Can Tho');

-- Chèn dữ liệu vào bảng roles
INSERT INTO roles (role_name) VALUES
                                  ('ADMIN'),
                                  ('STUDENT'),
                                  ('INSTRUCTOR');

-- Chèn dữ liệu vào bảng user_roles
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1), (2, 3), (3, 2), (4, 2), (5, 3);

-- Chèn dữ liệu vào bảng categories
INSERT INTO categories (category_name, description) VALUES
                                                        ('Programming', 'Learn programming languages and software development.'),
                                                        ('Design', 'Graphic design, UI/UX, and more.'),
                                                        ('Business', 'Business management, marketing, entrepreneurship.'),
                                                        ('Language', 'Learn new languages.'),
                                                        ('Health', 'Fitness, nutrition, mental health.');

-- Chèn dữ liệu vào bảng courses
INSERT INTO courses (sku, course_name, description, price, available_slots, image, category_id, instructor_id) VALUES
                                                                                                                   ('UUID1', 'Python Basics', 'Introduction to Python programming.', 99.99, 30, 'python.jpg', 1, 2),
                                                                                                                   ('UUID2', 'Web Design', 'Learn HTML, CSS, and JavaScript.', 149.99, 25, 'webdesign.jpg', 2, 5),
                                                                                                                   ('UUID3', 'Digital Marketing', 'Marketing strategies for the digital age.', 199.99, 20, 'marketing.jpg', 3, 2),
                                                                                                                   ('UUID4', 'Spanish for Beginners', 'Basic Spanish language course.', 79.99, 40, 'spanish.jpg', 4, 5),
                                                                                                                   ('UUID5', 'Yoga Fundamentals', 'Beginner yoga and mindfulness.', 59.99, 50, 'yoga.jpg', 5, 2);

-- Chèn dữ liệu vào bảng course_lessons
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, sort_order) VALUES
                                                                                         (1, 'Introduction to Python', 'Overview of Python syntax.', 'video1.mp4', 1),
                                                                                         (1, 'Variables and Data Types', 'Learn about variables.', 'video2.mp4', 2),
                                                                                         (2, 'HTML Basics', 'Introduction to HTML.', 'video3.mp4', 1),
                                                                                         (2, 'CSS Styling', 'Styling web pages.', 'video4.mp4', 2),
                                                                                         (3, 'SEO Fundamentals', 'Search Engine Optimization.', 'video5.mp4', 1);

-- Chèn dữ liệu vào bảng enrollments (cập nhật status thành COMPLETED)
INSERT INTO enrollments (serial_number, user_id, total_price, status, note) VALUES
                                                                                ('ENROLL1', 3, 99.99, 'COMPLETED', 'Enrolled in Python Basics'),
                                                                                ('ENROLL2', 3, 149.99, 'COMPLETED', 'Enrolled in Web Design'),
                                                                                ('ENROLL3', 4, 199.99, 'COMPLETED', 'Digital Marketing Course'),
                                                                                ('ENROLL4', 4, 79.99, 'COMPLETED', 'Spanish Course'),
                                                                                ('ENROLL5', 3, 59.99, 'COMPLETED', 'Yoga Course');

-- Chèn dữ liệu vào bảng enrollment_details
INSERT INTO enrollment_details (enrollment_id, course_id, course_name, unit_price, quantity) VALUES
                                                                                                 (1, 1, 'Python Basics', 99.99, 1),
                                                                                                 (2, 2, 'Web Design', 149.99, 1),
                                                                                                 (3, 3, 'Digital Marketing', 199.99, 1),
                                                                                                 (4, 4, 'Spanish for Beginners', 79.99, 1),
                                                                                                 (5, 5, 'Yoga Fundamentals', 59.99, 1);

-- Chèn dữ liệu vào bảng course_cart
INSERT INTO course_cart (course_id, user_id, quantity) VALUES
                                                           (1, 3, 1), (2, 3, 1), (3, 4, 1), (4, 4, 1), (5, 3, 1);

-- Chèn dữ liệu vào bảng favorite_courses
INSERT INTO favorite_courses (user_id, course_id) VALUES
                                                      (3, 1), (3, 2), (4, 3), (4, 4), (5, 5);

-- Chèn dữ liệu vào bảng course_reviews
INSERT INTO course_reviews (course_id, user_id, rating, comment) VALUES
                                                                     (1, 3, 5, 'Great course!'),
                                                                     (2, 3, 4, 'Very informative.'),
                                                                     (3, 4, 5, 'Loved the content.'),
                                                                     (4, 4, 4, 'Good for beginners.'),
                                                                     (5, 3, 5, 'Relaxing and helpful.');

-- Chèn dữ liệu vào bảng payments
INSERT INTO payments (enrollment_id, user_id, amount, payment_method, status, paid_at) VALUES
                                                                                           (1, 3, 99.99, 'CREDIT_CARD', 'SUCCESS', NOW()),
                                                                                           (2, 3, 149.99, 'PAYPAL', 'SUCCESS', NOW()),
                                                                                           (3, 4, 199.99, 'BANK_TRANSFER', 'SUCCESS', NOW()),
                                                                                           (4, 4, 79.99, 'CREDIT_CARD', 'SUCCESS', NOW()),
                                                                                           (5, 3, 59.99, 'PAYPAL', 'SUCCESS', NOW());

-- Chèn dữ liệu vào bảng notifications
INSERT INTO notifications (user_id, title, message) VALUES
                                                        (3, 'Welcome', 'Welcome to our platform!'),
                                                        (3, 'Enrollment Confirmed', 'Your enrollment is confirmed.'),
                                                        (4, 'New Course', 'Check out the new courses.'),
                                                        (2, 'Payment Received', 'Payment for course received.'),
                                                        (5, 'Upcoming Session', 'Your live session starts soon.');

-- Chèn dữ liệu vào bảng assignments
INSERT INTO assignments (course_id, title, description, due_date) VALUES
                                                                      (1, 'Python Quiz 1', 'Variables and data types quiz.', NOW() + INTERVAL 7 DAY),
                                                                      (1, 'Python Project', 'Build a simple application.', NOW() + INTERVAL 14 DAY),
                                                                      (2, 'HTML Assignment', 'Create a webpage.', NOW() + INTERVAL 5 DAY),
                                                                      (3, 'Marketing Plan', 'Develop a marketing strategy.', NOW() + INTERVAL 10 DAY),
                                                                      (4, 'Spanish Test 1', 'Basic vocabulary test.', NOW() + INTERVAL 3 DAY);

-- Chèn dữ liệu vào bảng submissions
INSERT INTO submissions (assignment_id, user_id, file_url, grade) VALUES
                                                                      (1, 3, 'submission1.pdf', 9.5),
                                                                      (2, 3, 'submission2.zip', 8.5),
                                                                      (3, 4, 'submission3.html', 10.0),
                                                                      (4, 4, 'submission4.docx', 7.5),
                                                                      (5, 3, 'submission5.pdf', 9.0);

-- Chèn dữ liệu vào bảng discussions
INSERT INTO discussions (course_id, user_id, title, content) VALUES
                                                                 (1, 3, 'Question about variables', 'How do I declare a variable?'),
                                                                 (2, 4, 'CSS issue', 'My styles are not applying.'),
                                                                 (3, 4, 'Marketing trends', 'What are the latest trends?'),
                                                                 (4, 3, 'Spanish pronunciation', 'How to pronounce...'),
                                                                 (5, 5, 'Yoga benefits', 'Discussing the benefits.');

-- Chèn dữ liệu vào bảng discussion_comments
INSERT INTO discussion_comments (discussion_id, user_id, comment) VALUES
                                                                      (1, 2, 'Use the assignment operator.'),
                                                                      (1, 3, 'Thanks!'),
                                                                      (2, 5, 'Check your CSS selectors.'),
                                                                      (3, 4, 'Social media is big now.'),
                                                                      (4, 3, 'I found this resource helpful.');

-- Chèn dữ liệu vào bảng private_messages
INSERT INTO private_messages (sender_id, receiver_id, subject, content) VALUES
                                                                            (2, 3, 'Regarding your submission', 'Well done on the assignment!'),
                                                                            (3, 2, 'Thank you', 'Appreciate your feedback.'),
                                                                            (4, 5, 'Course inquiry', 'When is the next session?'),
                                                                            (5, 4, 'Reply', 'Next week.'),
                                                                            (3, 4, 'Study group', 'Want to join?');

-- Chèn dữ liệu vào bảng certificates
INSERT INTO certificates (enrollment_id, certificate_url) VALUES
                                                              (1, 'certificates/enroll1-cert.pdf'),
                                                              (2, 'certificates/enroll2-cert.pdf'),
                                                              (3, 'certificates/enroll3-cert.pdf'),
                                                              (4, 'certificates/enroll4-cert.pdf'),
                                                              (5, 'certificates/enroll5-cert.pdf');

-- Chèn dữ liệu vào bảng live_sessions
# INSERT INTO live_sessions (course_id, session_title, session_description, scheduled_at, duration) VALUES
#                                                                                                       (1, 'Python Live Q&A', 'Ask your questions live.', NOW() + INTERVAL 2 DAY, 60),
#                                                                                                       (2, 'Web Design Workshop', 'Hands-on design session.', NOW() + INTERVAL 3 DAY, 90),
#                                                                                                       (3, 'Marketing Webinar', 'Latest trends discussion.', NOW() + INTERVAL 1 DAY, 120),
#                                                                                                       (4, 'Spanish Conversation', 'Practice speaking.', NOW() + INTERVAL 5 DAY, 60),
#                                                                                                       (5, 'Yoga Live Session', 'Morning yoga practice.', NOW() + INTERVAL 7 DAY, 45);
