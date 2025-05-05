như cái này liệt kê nè: Dưới đây là danh sách **các actor** và **use case** tương ứng trong hệ thống:

---

## **1. Actor: Admin**
### **Use Cases:**
✅ **Quản lý người dùng**
- Tạo tài khoản cho BTC, Nhà trường, Giảng viên
- Khóa/mở khóa tài khoản người dùng
- Cập nhật thông tin tài khoản người dùng
- Xóa tài khoản người dùng

✅ **Quản lý sự kiện**
- Xem danh sách sự kiện
- Xóa sự kiện vi phạm

✅ **Giám sát hoạt động hệ thống**
- Xem báo cáo thống kê từ các sự kiện
- Xem danh sách sinh viên tham gia các sự kiện

---

## **2. Actor: Ban tổ chức (BTC)**
### **Use Cases:**
✅ **Quản lý sự kiện**
- Tạo sự kiện mới
- Cập nhật thông tin sự kiện
- Xóa sự kiện
- Xem danh sách sự kiện đã tạo

✅ **Quản lý đăng ký tham gia sự kiện**
- Xem danh sách sinh viên đã đăng ký
- Duyệt hoặc từ chối đăng ký tham gia

✅ **Quản lý điểm danh**
- Tạo mã QR check-in
- Xóa mã QR hết hạn
- Xem danh sách sinh viên đã điểm danh
- Duyệt hoặc từ chối xác nhận điểm danh

✅ **Quản lý minh chứng tham gia**
- Cấp minh chứng cho sinh viên tham gia sự kiện
- Hủy minh chứng nếu phát hiện gian lận

✅ **Quản lý bài đăng sự kiện**
- Đăng bài giới thiệu sự kiện
- Chỉnh sửa hoặc xóa bài viết

✅ **Báo cáo sự kiện cho Nhà trường**
- Gửi báo cáo về số lượng sinh viên tham gia
- Thống kê số lượng đăng ký và số lượng điểm danh

---

## **3. Actor: Sinh viên**
### **Use Cases:**
✅ **Quản lý tài khoản**
- Đăng ký và đăng nhập
- Cập nhật thông tin cá nhân
- Đổi mật khẩu

✅ **Tìm kiếm và quản lý sự kiện**
- Xem danh sách sự kiện (sắp diễn ra, mới nhất)
- Tìm kiếm và lọc sự kiện theo tên, đơn vị tổ chức

✅ **Đăng ký tham gia sự kiện**
- Đăng ký tham gia sự kiện
- Hủy đăng ký nếu sự kiện chưa diễn ra

✅ **Điểm danh sự kiện**
- Quét mã QR để check-in
- Xem lịch sử điểm danh

✅ **Quản lý minh chứng tham gia**
- Xem danh sách sự kiện đã tham gia
- Xuất danh sách minh chứng để nộp cho Nhà trường

✅ **Theo dõi sự kiện**
- Xem các bài đăng của BTC về sự kiện
- Xem cảm nghĩ của các sinh viên khác

---

## **4. Actor: Nhà trường (Phòng Công Tác Sinh Viên - PCTSV)**
### **Use Cases:**
✅ **Kiểm duyệt sự kiện**
- Xem danh sách sự kiện chờ phê duyệt
- Chấp thuận hoặc từ chối sự kiện với lý do

✅ **Quản lý điểm hoạt động cộng đồng**
- Quy định số điểm cho từng loại sự kiện
- Cập nhật mức điểm nếu cần thiết

✅ **Thống kê và báo cáo**
- Xem báo cáo tổng hợp từ các sự kiện
- Thống kê số lượng sinh viên tham gia hoạt động cộng đồng

---

📌 Nếu bạn cần bổ sung hoặc điều chỉnh use case, hãy cho mình biết nhé! 🚀











Dưới đây là các chức năng chính của các thực thể trong CSDL này:

### **1. Users (Người dùng)**
- Quản lý tài khoản người dùng (Admin, Giảng viên, Sinh viên, BTC, Nhà trường).
- Đăng nhập, đăng ký, xác thực tài khoản.
- Cập nhật thông tin cá nhân (email, số điện thoại, tên...).
- Phân quyền người dùng theo vai trò.
- Khóa/mở khóa tài khoản (Admin quản lý).

### **2. Events (Sự kiện)**
- Tạo mới sự kiện (BTC hoặc Nhà trường).
- Cập nhật thông tin sự kiện.
- Xóa sự kiện.
- Duyệt hoặc từ chối sự kiện (Nhà trường).
- Theo dõi trạng thái sự kiện: **PENDING**, **APPROVED**, **REJECTED**, **ONGOING**, **COMPLETED**.
- Xem danh sách sự kiện sắp diễn ra.

### **3. Event_Registrations (Đăng ký sự kiện)**
- Sinh viên đăng ký tham gia sự kiện trong thời gian cho phép.
- Hủy đăng ký nếu sự kiện chưa diễn ra.
- Quản lý danh sách sinh viên đăng ký (BTC, Nhà trường).
- Duyệt hoặc từ chối đơn đăng ký của sinh viên.

### **4. QR_Codes (Mã điểm danh)**
- BTC tạo mã QR cho sự kiện để điểm danh.
- Mã QR có thời gian hiệu lực và gắn liền với sự kiện.
- Xóa mã QR khi không còn cần thiết.

### **5. Attendance (Điểm danh)**
- Sinh viên quét mã QR để điểm danh sự kiện.
- Lưu lịch sử quét mã.
- BTC hoặc Nhà trường xác thực điểm danh (APPROVED/REJECTED).
- Xem danh sách điểm danh của từng sinh viên.

### **6. Certificates (Minh chứng tham gia sự kiện)**
- Cấp minh chứng tham gia sự kiện nếu sinh viên được phê duyệt điểm danh.
- Xem danh sách minh chứng của sinh viên.
- Xuất file PDF minh chứng để nộp cho khoa.

### **7. Event_Posts (Bài đăng về sự kiện)**
- BTC đăng bài viết về sự kiện.
- Cập nhật, chỉnh sửa hoặc xóa bài viết.
- Ẩn/hiện bài viết nếu cần.

### **8. Event_Scores (Quy định điểm sự kiện)**
- Nhà trường quy định điểm cho từng sự kiện.
- Lưu mức điểm tối thiểu và tối đa của một sự kiện.
- Áp dụng điểm vào minh chứng của sinh viên.

### **9. Reports (Báo cáo sự kiện)**
- BTC hoặc Nhà trường tạo báo cáo sự kiện.
- Lưu thông tin báo cáo chi tiết (số lượng đăng ký, danh sách sinh viên, tình trạng sự kiện).
- Xem danh sách báo cáo theo từng sự kiện.

CREATE DATABASE IF NOT EXISTS CommunityActivityDB;
USE CommunityActivityDB;

-- Bảng Users (Lưu thông tin người dùng: Admin, Giảng viên, Sinh viên, BTC, Nhà trường)
CREATE TABLE Users (
user_id INT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
full_name VARCHAR(255) NOT NULL,
phone_number VARCHAR(15),
role ENUM('ADMIN', 'GIANG_VIEN', 'SINH_VIEN', 'BTC', 'NHA_TRUONG') NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng Events (Lưu thông tin sự kiện)
CREATE TABLE Events (
event_id INT AUTO_INCREMENT PRIMARY KEY,
event_name VARCHAR(255) NOT NULL,
description TEXT,
organizer_id INT NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME NOT NULL,
registration_deadline DATETIME NOT NULL,
status ENUM('PENDING', 'APPROVED', 'REJECTED', 'ONGOING', 'COMPLETED') DEFAULT 'PENDING',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (organizer_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- Bảng Event_Registrations (Lưu đăng ký sự kiện của sinh viên)
CREATE TABLE Event_Registrations (
registration_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT NOT NULL,
event_id INT NOT NULL,
registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
status ENUM('PENDING', 'APPROVED', 'CANCELLED') DEFAULT 'PENDING',
FOREIGN KEY (student_id) REFERENCES Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE
);

-- Bảng QR_Codes (Lưu mã QR điểm danh)
CREATE TABLE QR_Codes (
qr_id INT AUTO_INCREMENT PRIMARY KEY,
event_id INT NOT NULL,
qr_code VARCHAR(255) UNIQUE NOT NULL,
valid_from DATETIME NOT NULL,
valid_until DATETIME NOT NULL,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE
);

-- Bảng Attendance (Lưu điểm danh của sinh viên)
CREATE TABLE Attendance (
attendance_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT NOT NULL,


event_id INT NOT NULL,
qr_id INT NOT NULL,
scan_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
FOREIGN KEY (student_id) REFERENCES Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE,
FOREIGN KEY (qr_id) REFERENCES QR_Codes(qr_id) ON DELETE CASCADE
);

-- Bảng Certificates (Lưu minh chứng tham gia sự kiện)
CREATE TABLE Certificates (
certificate_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT NOT NULL,
event_id INT NOT NULL,
issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (student_id) REFERENCES Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE
);

-- Bảng Event_Posts (Lưu bài đăng về sự kiện)
CREATE TABLE Event_Posts (
post_id INT AUTO_INCREMENT PRIMARY KEY,
event_id INT NOT NULL,
organizer_id INT NOT NULL,
content TEXT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE,
FOREIGN KEY (organizer_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- Bảng Event_Scores (Lưu quy định điểm cho từng sự kiện)
CREATE TABLE Event_Scores (
score_id INT AUTO_INCREMENT PRIMARY KEY,
event_id INT NOT NULL,
min_score INT NOT NULL,
max_score INT NOT NULL,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE
);

-- Bảng Reports (Lưu báo cáo thống kê sự kiện)
CREATE TABLE Reports (
report_id INT AUTO_INCREMENT PRIMARY KEY,
event_id INT NOT NULL,
created_by INT NOT NULL,
report_content TEXT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE CASCADE,
FOREIGN KEY (created_by) REFERENCES Users(user_id) ON DELETE CASCADE
);


--------
