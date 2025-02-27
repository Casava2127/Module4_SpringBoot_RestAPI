Dưới đây là một script SQL mở rộng, chèn từ 5 đến 10 dữ liệu cho hầu hết các bảng, đảm bảo các mối liên hệ và ràng buộc giữa các bảng được duy trì. Bạn có thể chạy script này trên hệ quản trị CSDL của mình để kiểm tra tính toàn vẹn và hoạt động của các quan hệ.

```sql
----------------------------------------------------------------------
-- 1. Bảng users (10 người dùng)
----------------------------------------------------------------------
INSERT INTO users (username, email, fullname, password, phone, address, created_at, is_deleted, status, updated_at, avatar)
VALUES
  ('john_doe', 'john@example.com', 'John Doe', 'hashed123', '0912345678', '123 Main St', NOW(), 0, 1, NOW(), 'john.jpg'),
  ('jane_smith', 'jane@example.com', 'Jane Smith', 'hashed456', '0923456789', '456 Oak Ave', NOW(), 0, 1, NOW(), 'jane.jpg'),
  ('mr_instructor', 'instructor@example.com', 'Mike Johnson', 'hashed789', '0934567890', '789 Pine Rd', NOW(), 0, 1, NOW(), 'instructor.jpg'),
  ('alice_wang', 'alice@example.com', 'Alice Wang', 'hashed101', '0945678901', '101 Elm St', NOW(), 0, 1, NOW(), 'alice.jpg'),
  ('bob_lee', 'bob@example.com', 'Bob Lee', 'hashed202', '0956789012', '202 Maple St', NOW(), 0, 1, NOW(), 'bob.jpg'),
  ('charlie_brown', 'charlie@example.com', 'Charlie Brown', 'hashed303', '0967890123', '303 Birch Rd', NOW(), 0, 1, NOW(), 'charlie.jpg'),
  ('david_kim', 'david@example.com', 'David Kim', 'hashed404', '0978901234', '404 Cedar Ln', NOW(), 0, 1, NOW(), 'david.jpg'),
  ('emily_clark', 'emily@example.com', 'Emily Clark', 'hashed505', '0989012345', '505 Spruce St', NOW(), 0, 1, NOW(), 'emily.jpg'),
  ('frank_miller', 'frank@example.com', 'Frank Miller', 'hashed606', '0990123456', '606 Walnut Ave', NOW(), 0, 1, NOW(), 'frank.jpg'),
  ('grace_hopper', 'grace@example.com', 'Grace Hopper', 'hashed707', '0911123456', '707 Poplar Dr', NOW(), 0, 1, NOW(), 'grace.jpg');

----------------------------------------------------------------------
-- 2. Bảng roles (RoleName: ADMIN, STUDENT, INSTRUCTOR)
----------------------------------------------------------------------
-- Vì kiểu dữ liệu ENUM chỉ cho phép 3 giá trị nên chỉ cần thêm 3 bản ghi duy nhất
INSERT INTO roles (role_name)
VALUES
  ('ADMIN'),
  ('STUDENT'),
  ('INSTRUCTOR');

----------------------------------------------------------------------
-- 3. Bảng user_roles (gán vai trò cho người dùng)
----------------------------------------------------------------------
-- Giả sử:
-- user_id 1,2,4,5,6,8,9 là STUDENT (role_id = 2)
-- user_id 3 và 7 là INSTRUCTOR (role_id = 3)
-- user_id 10 là ADMIN (role_id = 1)
INSERT INTO user_roles (user_id, role_id)
VALUES
  (1, 2),
  (2, 2),
  (4, 2),
  (5, 2),
  (6, 2),
  (8, 2),
  (9, 2);

INSERT INTO user_roles (user_id, role_id)
VALUES
  (3, 3),
  (7, 3);

INSERT INTO user_roles (user_id, role_id)
VALUES
  (10, 1);

----------------------------------------------------------------------
-- 4. Bảng categories (5 danh mục)
----------------------------------------------------------------------
INSERT INTO categories (category_name, description, status)
VALUES
  ('Programming', 'Courses on coding and development', 1),
  ('Design', 'Courses on graphic & UI/UX design', 1),
  ('Data Science', 'Courses on data analysis and machine learning', 1),
  ('Business', 'Courses on business management and entrepreneurship', 1),
  ('Marketing', 'Courses on digital marketing and strategy', 1);

----------------------------------------------------------------------
-- 5. Bảng courses (10 khóa học)
----------------------------------------------------------------------
INSERT INTO courses (sku, course_name, description, price, category_id, instructor_id, available_slots, updated_at, created_at)
VALUES
  (UUID(), 'Python Basics', 'Introduction to Python programming', 299.99, 1, 3, 50, NOW(), NOW()),
  (UUID(), 'UI Design', 'Fundamentals of user interface design', 399.99, 2, 3, 30, NOW(), NOW()),
  (UUID(), 'Machine Learning', 'Basics of machine learning', 499.99, 3, 7, 40, NOW(), NOW()),
  (UUID(), 'Entrepreneurship', 'Starting your own business', 349.99, 4, 7, 25, NOW(), NOW()),
  (UUID(), 'Data Structures', 'Understanding core data structures', 299.99, 1, 3, 45, NOW(), NOW()),
  (UUID(), 'Advanced Python', 'Deep dive into advanced Python topics', 399.99, 1, 3, 35, NOW(), NOW()),
  (UUID(), 'Graphic Design', 'Principles of graphic design', 299.99, 2, 3, 30, NOW(), NOW()),
  (UUID(), 'Digital Marketing', 'Introduction to digital marketing strategies', 349.99, 5, 7, 40, NOW(), NOW()),
  (UUID(), 'Statistics', 'Fundamentals of statistics', 399.99, 3, 7, 50, NOW(), NOW()),
  (UUID(), 'Business Strategy', 'Analyzing competitive business strategies', 449.99, 4, 7, 20, NOW(), NOW());

----------------------------------------------------------------------
-- 6. Bảng course_lessons (2 bài giảng cho mỗi khóa học -> 20 dòng)
----------------------------------------------------------------------
-- Khóa học 1: Python Basics
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (1, 'Introduction to Python', 'Overview of Python language', 'video1a.url', NOW(), NOW()),
  (1, 'Python Variables', 'Understanding variables in Python', 'video1b.url', NOW(), NOW());

-- Khóa học 2: UI Design
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (2, 'Basics of UI', 'Introduction to UI concepts', 'video2a.url', NOW(), NOW()),
  (2, 'Design Principles', 'Core principles of design', 'video2b.url', NOW(), NOW());

-- Khóa học 3: Machine Learning
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (3, 'Introduction to ML', 'What is Machine Learning?', 'video3a.url', NOW(), NOW()),
  (3, 'Supervised Learning', 'Overview of supervised methods', 'video3b.url', NOW(), NOW());

-- Khóa học 4: Entrepreneurship
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (4, 'Entrepreneurship Fundamentals', 'Basics of starting a business', 'video4a.url', NOW(), NOW()),
  (4, 'Business Model Canvas', 'Planning your business model', 'video4b.url', NOW(), NOW());

-- Khóa học 5: Data Structures
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (5, 'Understanding Data Structures', 'Introduction to data structures', 'video5a.url', NOW(), NOW()),
  (5, 'Implementing Algorithms', 'Algorithm implementation basics', 'video5b.url', NOW(), NOW());

-- Khóa học 6: Advanced Python
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (6, 'Advanced Concepts', 'In-depth Python topics', 'video6a.url', NOW(), NOW()),
  (6, 'Decorators and Generators', 'Understanding decorators and generators', 'video6b.url', NOW(), NOW());

-- Khóa học 7: Graphic Design
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (7, 'Graphic Design Basics', 'Fundamental principles of design', 'video7a.url', NOW(), NOW()),
  (7, 'Color Theory', 'Understanding colors in design', 'video7b.url', NOW(), NOW());

-- Khóa học 8: Digital Marketing
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (8, 'Digital Marketing 101', 'Introduction to digital marketing', 'video8a.url', NOW(), NOW()),
  (8, 'SEO Strategies', 'Basics of SEO', 'video8b.url', NOW(), NOW());

-- Khóa học 9: Statistics
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (9, 'Descriptive Statistics', 'Understanding descriptive statistics', 'video9a.url', NOW(), NOW()),
  (9, 'Inferential Statistics', 'Basics of inferential statistics', 'video9b.url', NOW(), NOW());

-- Khóa học 10: Business Strategy
INSERT INTO course_lessons (course_id, lesson_title, content, video_url, updated_at, created_at)
VALUES
  (10, 'Business Strategy Concepts', 'Introduction to business strategies', 'video10a.url', NOW(), NOW()),
  (10, 'Competitive Analysis', 'How to analyze competitors', 'video10b.url', NOW(), NOW());

----------------------------------------------------------------------
-- 7. Bảng enrollments (10 bản ghi, mỗi bản ghi đại diện cho 1 phiên đăng ký)
----------------------------------------------------------------------
INSERT INTO enrollments (serial_number, user_id, total_price, status, created_at)
VALUES
  (UUID(), 1, 599.98, 'CONFIRMED', NOW()),
  (UUID(), 2, 399.99, 'IN_PROGRESS', NOW()),
  (UUID(), 4, 499.99, 'WAITING', NOW()),
  (UUID(), 5, 349.99, 'COMPLETED', NOW()),
  (UUID(), 6, 299.99, 'CANCELED', NOW()),
  (UUID(), 8, 399.99, 'DENIED', NOW()),
  (UUID(), 9, 299.99, 'CONFIRMED', NOW()),
  (UUID(), 1, 349.99, 'IN_PROGRESS', NOW()),
  (UUID(), 2, 399.99, 'CONFIRMED', NOW()),
  (UUID(), 4, 449.99, 'WAITING', NOW());

----------------------------------------------------------------------
-- 8. Bảng enrollment_details (mỗi đăng ký chứa 1 khóa học)
----------------------------------------------------------------------
INSERT INTO enrollment_details (enrollment_id, course_id, course_name, unit_price, quantity)
VALUES
  (1, 1, 'Python Basics', 299.99, 2),
  (2, 2, 'UI Design', 399.99, 1),
  (3, 3, 'Machine Learning', 499.99, 1),
  (4, 4, 'Entrepreneurship', 349.99, 1),
  (5, 5, 'Data Structures', 299.99, 1),
  (6, 6, 'Advanced Python', 399.99, 1),
  (7, 7, 'Graphic Design', 299.99, 1),
  (8, 8, 'Digital Marketing', 349.99, 1),
  (9, 9, 'Statistics', 399.99, 1),
  (10, 10, 'Business Strategy', 449.99, 1)
ON DUPLICATE KEY UPDATE quantity = VALUES(quantity);

----------------------------------------------------------------------
-- 9. Bảng course_cart (10 dòng)
----------------------------------------------------------------------
INSERT INTO course_cart (course_id, user_id, quantity, created_at)
VALUES
  (2, 1, 1, NOW()),
  (3, 2, 1, NOW()),
  (1, 4, 1, NOW()),
  (4, 5, 1, NOW()),
  (5, 6, 1, NOW()),
  (6, 8, 1, NOW()),
  (7, 9, 1, NOW()),
  (8, 1, 1, NOW()),
  (9, 2, 1, NOW()),
  (10, 4, 1, NOW());

----------------------------------------------------------------------
-- 10. Bảng favorite_courses (10 dòng)
----------------------------------------------------------------------
INSERT INTO favorite_courses (user_id, course_id, created_at)
VALUES
  (1, 1, NOW()),
  (2, 2, NOW()),
  (4, 3, NOW()),
  (5, 4, NOW()),
  (6, 5, NOW()),
  (8, 6, NOW()),
  (9, 7, NOW()),
  (1, 8, NOW()),
  (2, 9, NOW()),
  (4, 10, NOW());

----------------------------------------------------------------------
-- 11. Bảng course_reviews (10 dòng)
----------------------------------------------------------------------
INSERT INTO course_reviews (course_id, user_id, rating, comment, created_at)
VALUES
  (1, 1, 5, 'Excellent course!', NOW()),
  (2, 2, 4, 'Very good course.', NOW()),
  (3, 4, 5, 'Amazing content!', NOW()),
  (4, 5, 4, 'Very practical.', NOW()),
  (5, 6, 3, 'Average course.', NOW()),
  (6, 8, 5, 'Loved it!', NOW()),
  (7, 9, 4, 'Good insights.', NOW()),
  (8, 1, 4, 'Informative.', NOW()),
  (9, 2, 3, 'Could be improved.', NOW()),
  (10, 4, 5, 'Outstanding!', NOW());

----------------------------------------------------------------------
-- 12. Bảng payments (10 dòng)
----------------------------------------------------------------------
INSERT INTO payments (enrollment_id, user_id, amount, payment_method, status, paid_at, created_at)
VALUES
  (1, 1, 599.98, 'CREDIT_CARD', 'SUCCESS', NOW(), NOW()),
  (2, 2, 399.99, 'PAYPAL', 'SUCCESS', NOW(), NOW()),
  (3, 4, 499.99, 'CREDIT_CARD', 'SUCCESS', NOW(), NOW()),
  (4, 5, 349.99, 'PAYPAL', 'SUCCESS', NOW(), NOW()),
  (5, 6, 299.99, 'BANK_TRANSFER', 'PENDING', NOW(), NOW()),
  (6, 8, 399.99, 'CREDIT_CARD', 'SUCCESS', NOW(), NOW()),
  (7, 9, 299.99, 'PAYPAL', 'SUCCESS', NOW(), NOW()),
  (8, 1, 349.99, 'BANK_TRANSFER', 'SUCCESS', NOW(), NOW()),
  (9, 2, 399.99, 'CREDIT_CARD', 'SUCCESS', NOW(), NOW()),
  (10, 4, 449.99, 'PAYPAL', 'SUCCESS', NOW(), NOW());

----------------------------------------------------------------------
-- 13. Bảng notifications (10 dòng)
----------------------------------------------------------------------
INSERT INTO notifications (user_id, title, message, is_read, created_at)
VALUES
  (1, 'Welcome', 'Thanks for joining our platform!', 1, NOW()),
  (2, 'Update', 'New lesson has been added.', 0, NOW()),
  (4, 'Reminder', 'Your assignment is due soon.', 0, NOW()),
  (5, 'Announcement', 'A new course is available.', 1, NOW()),
  (6, 'Alert', 'Your payment was successful.', 1, NOW()),
  (8, 'Welcome', 'Welcome to our learning community!', 0, NOW()),
  (9, 'Update', 'A new discussion topic is live.', 1, NOW()),
  (1, 'Info', 'Please check your messages.', 0, NOW()),
  (2, 'Alert', 'Your review has been posted.', 1, NOW()),
  (4, 'Reminder', 'Live session starts soon.', 0, NOW());

----------------------------------------------------------------------
-- 14. Bảng assignments (10 dòng, 1 bài tập cho mỗi khóa học)
----------------------------------------------------------------------
INSERT INTO assignments (course_id, title, description, due_date, created_at)
VALUES
  (1, 'Python Quiz 1', 'Test your Python basics', DATE_ADD(NOW(), INTERVAL 7 DAY), NOW()),
  (2, 'UI Design Assignment', 'Design a sample UI', DATE_ADD(NOW(), INTERVAL 10 DAY), NOW()),
  (3, 'ML Project', 'Build a simple ML model', DATE_ADD(NOW(), INTERVAL 14 DAY), NOW()),
  (4, 'Entrepreneurship Case Study', 'Analyze a startup case', DATE_ADD(NOW(), INTERVAL 12 DAY), NOW()),
  (5, 'Data Structures Homework', 'Solve algorithm problems', DATE_ADD(NOW(), INTERVAL 8 DAY), NOW()),
  (6, 'Advanced Python Challenge', 'Implement advanced Python features', DATE_ADD(NOW(), INTERVAL 9 DAY), NOW()),
  (7, 'Graphic Design Project', 'Create a design portfolio', DATE_ADD(NOW(), INTERVAL 11 DAY), NOW()),
  (8, 'Digital Marketing Analysis', 'Analyze a marketing campaign', DATE_ADD(NOW(), INTERVAL 13 DAY), NOW()),
  (9, 'Statistics Exercise', 'Solve statistical problems', DATE_ADD(NOW(), INTERVAL 10 DAY), NOW()),
  (10, 'Business Strategy Paper', 'Write a strategy analysis', DATE_ADD(NOW(), INTERVAL 15 DAY), NOW());

----------------------------------------------------------------------
-- 15. Bảng submissions (10 dòng, 1 bài nộp cho mỗi bài tập)
----------------------------------------------------------------------
INSERT INTO submissions (assignment_id, user_id, file_url, grade, submitted_at)
VALUES
  (1, 1, 'submission1.url', 9.5, NOW()),
  (2, 2, 'submission2.url', 8.8, NOW()),
  (3, 4, 'submission3.url', 9.0, NOW()),
  (4, 5, 'submission4.url', 8.5, NOW()),
  (5, 6, 'submission5.url', 9.2, NOW()),
  (6, 8, 'submission6.url', 8.7, NOW()),
  (7, 9, 'submission7.url', 9.3, NOW()),
  (8, 1, 'submission8.url', 8.9, NOW()),
  (9, 2, 'submission9.url', 8.5, NOW()),
  (10, 4, 'submission10.url', 9.0, NOW());

----------------------------------------------------------------------
-- 16. Bảng discussions (10 dòng)
----------------------------------------------------------------------
INSERT INTO discussions (course_id, user_id, title, content, created_at)
VALUES
  (1, 1, 'Question on loops', 'How to use loops effectively in Python?', NOW()),
  (2, 2, 'UI Layout', 'What are best practices for UI layout design?', NOW()),
  (3, 4, 'ML Algorithms', 'Which algorithm works best for classification?', NOW()),
  (4, 5, 'Startup Tips', 'How to pitch to potential investors?', NOW()),
  (5, 6, 'Data Structures Query', 'Difference between array and linked list?', NOW()),
  (6, 8, 'Advanced Python Tips', 'How to optimize Python performance?', NOW()),
  (7, 9, 'Design Inspiration', 'Where to find creative design ideas?', NOW()),
  (8, 1, 'Marketing Strategy', 'What are the best digital marketing channels?', NOW()),
  (9, 2, 'Statistics Help', 'How to interpret p-values correctly?', NOW()),
  (10, 4, 'Business Strategy', 'Key factors in competitive analysis?', NOW());

----------------------------------------------------------------------
-- 17. Bảng discussion_comments (10 dòng)
----------------------------------------------------------------------
INSERT INTO discussion_comments (discussion_id, user_id, comment, created_at)
VALUES
  (1, 3, 'Try using for loops in your code.', NOW()),
  (2, 3, 'Consider using grid layouts.', NOW()),
  (3, 3, 'Start with logistic regression for classification.', NOW()),
  (4, 7, 'Focus on market research first.', NOW()),
  (5, 3, 'Linked lists offer dynamic memory usage.', NOW()),
  (6, 7, 'Use profiling tools to optimize performance.', NOW()),
  (7, 3, 'Check out popular design portfolios online.', NOW()),
  (8, 7, 'SEO is crucial for digital marketing.', NOW()),
  (9, 3, 'Review hypothesis testing methods.', NOW()),
  (10, 7, 'Analyze competitors thoroughly before strategizing.', NOW());

----------------------------------------------------------------------
-- 18. Bảng private_messages (10 dòng)
----------------------------------------------------------------------
INSERT INTO private_messages (sender_id, receiver_id, subject, content, is_read, created_at)
VALUES
  (3, 1, 'Assignment Feedback', 'Great job on your assignment!', 1, NOW()),
  (3, 2, 'Project Update', 'New materials have been added.', 1, NOW()),
  (7, 4, 'ML Project', 'Keep up the good work on the project.', 0, NOW()),
  (7, 5, 'Business Case', 'Please review the case study details.', 0, NOW()),
  (3, 6, 'Data Structures', 'Check your homework submission.', 1, NOW()),
  (7, 8, 'Design Query', 'Clarify your design choice for better results.', 0, NOW()),
  (3, 9, 'Course Query', 'Feel free to ask any questions.', 1, NOW()),
  (7, 1, 'Live Session', 'Join the live session tomorrow!', 0, NOW()),
  (3, 2, 'Reminder', 'Submit your assignment on time.', 1, NOW()),
  (7, 4, 'Feedback', 'Excellent participation in class!', 1, NOW());

----------------------------------------------------------------------
-- 19. Bảng certificates (10 dòng, mỗi chứng chỉ ứng với 1 enrollment)
----------------------------------------------------------------------
INSERT INTO certificates (enrollment_id, certificate_url, issued_at)
VALUES
  (1, 'certificate1.url', NOW()),
  (2, 'certificate2.url', NOW()),
  (3, 'certificate3.url', NOW()),
  (4, 'certificate4.url', NOW()),
  (5, 'certificate5.url', NOW()),
  (6, 'certificate6.url', NOW()),
  (7, 'certificate7.url', NOW()),
  (8, 'certificate8.url', NOW()),
  (9, 'certificate9.url', NOW()),
  (10, 'certificate10.url', NOW());

----------------------------------------------------------------------
-- 20. Bảng live_sessions (10 dòng)
----------------------------------------------------------------------
INSERT INTO live_sessions (course_id, session_title, session_description, scheduled_at, duration, created_at)
VALUES
  (1, 'Python Q&A', 'Live coding session and Q&A', DATE_ADD(NOW(), INTERVAL 3 DAY), 60, NOW()),
  (2, 'Design Workshop', 'Review latest design trends live', DATE_ADD(NOW(), INTERVAL 5 DAY), 90, NOW()),
  (3, 'ML Q&A', 'Discuss various ML concepts live', DATE_ADD(NOW(), INTERVAL 7 DAY), 60, NOW()),
  (4, 'Startup Pitch', 'Practice your pitch in a live session', DATE_ADD(NOW(), INTERVAL 10 DAY), 90, NOW()),
  (5, 'Data Structures Review', 'Live review of algorithms and structures', DATE_ADD(NOW(), INTERVAL 4 DAY), 60, NOW()),
  (6, 'Advanced Python', 'Deep dive into advanced Python features', DATE_ADD(NOW(), INTERVAL 6 DAY), 60, NOW()),
  (7, 'Graphic Design Q&A', 'Discuss design challenges live', DATE_ADD(NOW(), INTERVAL 8 DAY), 60, NOW()),
  (8, 'Digital Marketing Trends', 'Explore current trends in digital marketing', DATE_ADD(NOW(), INTERVAL 9 DAY), 90, NOW()),
  (9, 'Statistics Workshop', 'Hands-on statistics session', DATE_ADD(NOW(), INTERVAL 11 DAY), 60, NOW()),
  (10, 'Business Strategy Forum', 'Discuss and analyze business strategies', DATE_ADD(NOW(), INTERVAL 12 DAY), 90, NOW());
```

---

### Giải thích tổng quan

- **Users & Roles:** 10 người dùng được thêm vào với các thông tin chi tiết, sau đó phân bổ vai trò (STUDENT, INSTRUCTOR, ADMIN) qua bảng `user_roles`.
- **Categories & Courses:** 5 danh mục và 10 khóa học với SKU dạng UUID, mỗi khóa học được gán danh mục và giảng viên (user 3 hoặc 7) phù hợp.
- **Course Lessons:** Mỗi khóa học có 2 bài giảng (tổng cộng 20 dòng) với nội dung và URL video khác nhau.
- **Enrollments & Enrollment Details:** 10 phiên đăng ký, mỗi phiên có 1 dòng chi tiết ghi nhận khóa học được đăng ký.
- **Course Cart & Favorite Courses:** 10 dòng dữ liệu cho giỏ hàng và danh sách yêu thích, đảm bảo liên kết giữa người dùng và khóa học.
- **Course Reviews:** 10 đánh giá với mức điểm và nhận xét khác nhau.
- **Payments:** 10 giao dịch thanh toán với các phương thức và trạng thái (chủ yếu SUCCESS, một số PENDING).
- **Notifications:** 10 thông báo gửi đến người dùng với các trạng thái đọc/chưa đọc.
- **Assignments & Submissions:** Mỗi khóa học có một bài tập và 1 bài nộp tương ứng từ học viên.
- **Discussions & Discussion Comments:** 10 chủ đề thảo luận và 10 bình luận (do giảng viên hoặc người dùng khác) trả lời.
- **Private Messages:** 10 tin nhắn riêng giữa các người dùng (đặc biệt giữa giảng viên và học viên).
- **Certificates:** 10 chứng chỉ ứng với 10 phiên đăng ký.
- **Live Sessions:** 10 buổi học trực tuyến được lên lịch cho các khóa học khác nhau.

Script trên đã mở rộng dữ liệu theo yêu cầu, bạn có thể tùy chỉnh các giá trị như giá, số lượng, thời gian,… cho phù hợp với kịch bản thực tế của hệ thống.