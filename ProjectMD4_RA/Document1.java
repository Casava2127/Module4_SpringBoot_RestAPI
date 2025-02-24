Dưới đây là phân tích chức năng của từng bảng trong hệ thống CSDL **Quản lý khóa học trực tuyến**, kèm theo việc chỉ rõ các bảng chịu trách nhiệm chính.

---

## **Nhóm bảng quản lý người dùng và phân quyền**
### **1. `users` (Người dùng)**
- Quản lý thông tin người dùng: tài khoản, email, họ tên, địa chỉ, số điện thoại, trạng thái hoạt động, mật khẩu, ảnh đại diện.
- Chịu trách nhiệm chính về danh tính người dùng.

### **2. `roles` (Quyền)**
- Quản lý các vai trò của người dùng: `ADMIN`, `STUDENT`, `INSTRUCTOR`.

### **3. `user_roles` (Phân quyền)**
- Liên kết người dùng (`users`) với vai trò (`roles`).
- Một người dùng có thể có nhiều vai trò.

---

## **Nhóm bảng quản lý khóa học**
### **4. `categories` (Danh mục khóa học)**
- Quản lý các danh mục giúp phân loại khóa học.

### **5. `courses` (Khóa học)**
- Chịu trách nhiệm chính về thông tin các khóa học: mã khóa học, tên, mô tả, giá, số lượng chỗ trống, giảng viên.
- Liên kết với danh mục khóa học (`categories`) và giảng viên (`users`).

### **6. `course_lessons` (Bài giảng của khóa học)**
- Quản lý các bài giảng thuộc từng khóa học, gồm tiêu đề, nội dung, video đính kèm.

---

## **Nhóm bảng quản lý đăng ký và thanh toán**
### **7. `enrollments` (Đăng ký khóa học)**
- Chịu trách nhiệm chính về việc người dùng đăng ký khóa học.
- Trạng thái: `WAITING`, `CONFIRMED`, `IN_PROGRESS`, `COMPLETED`, `CANCELED`, `DENIED`.

### **8. `enrollment_details` (Chi tiết đăng ký khóa học)**
- Chi tiết từng khóa học trong một lần đăng ký.

### **9. `course_cart` (Giỏ khóa học)**
- Quản lý giỏ hàng của người dùng trước khi đăng ký khóa học.

### **10. `favorite_courses` (Danh sách yêu thích)**
- Lưu danh sách khóa học yêu thích của người dùng.

### **11. `payments` (Thanh toán)**
- Quản lý giao dịch thanh toán: phương thức thanh toán (`CREDIT_CARD`, `PAYPAL`, `BANK_TRANSFER`), trạng thái (`PENDING`, `SUCCESS`, `FAILED`).

---

## **Nhóm bảng quản lý tương tác khóa học**
### **12. `course_reviews` (Đánh giá khóa học)**
- Người dùng có thể đánh giá và bình luận về khóa học.

### **13. `notifications` (Thông báo)**
- Lưu các thông báo gửi đến người dùng.

---

## **Nhóm bảng quản lý bài tập và đánh giá**
### **14. `assignments` (Bài tập)**
- Quản lý bài tập của từng khóa học.

### **15. `submissions` (Nộp bài)**
- Quản lý bài nộp của học viên cho từng bài tập, bao gồm điểm số và phản hồi.

---

## **Nhóm bảng thảo luận và tin nhắn**
### **16. `discussions` (Diễn đàn thảo luận)**
- Quản lý các chủ đề thảo luận trong khóa học.

### **17. `discussion_comments` (Bình luận diễn đàn)**
- Lưu các bình luận trong các chủ đề thảo luận.

### **18. `private_messages` (Tin nhắn riêng)**
- Hệ thống tin nhắn giữa người dùng.

---

## **Nhóm bảng chứng nhận và học trực tuyến**
### **19. `certificates` (Chứng chỉ)**
- Quản lý chứng chỉ cấp cho học viên sau khi hoàn thành khóa học.

### **20. `live_sessions` (Lịch học trực tuyến)**
- Quản lý lịch học trực tuyến của từng khóa học.

---

## **Bảng chịu trách nhiệm chính trong hệ CSDL**
Dưới đây là các bảng quan trọng nhất ảnh hưởng trực tiếp đến hoạt động hệ thống:
1. **`users`** - Quản lý người dùng.
2. **`roles` & `user_roles`** - Phân quyền người dùng.
3. **`courses`** - Quản lý khóa học.
4. **`course_lessons`** - Nội dung bài giảng.
5. **`enrollments`** - Đăng ký khóa học.
6. **`enrollment_details`** - Chi tiết khóa học trong đăng ký.
7. **`payments`** - Quản lý thanh toán.
8. **`certificates`** - Quản lý cấp chứng chỉ.

---

Hệ thống này được thiết kế khá đầy đủ để hỗ trợ quản lý khóa học trực tuyến một cách hiệu quả! 🚀