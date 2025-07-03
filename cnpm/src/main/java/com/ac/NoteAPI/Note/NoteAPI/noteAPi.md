1. UserController (/api/v1/users):  
   GET /api/v1/users — Lấy tất cả user
   GET /api/v1/users/{id} — Lấy user theo id
   POST /api/v1/users — Tạo user mới
   PUT /api/v1/users/{id} — Cập nhật user
   DELETE /api/v1/users/{id} — Xóa user
2. AuthController (/api/v1/auth):  
   POST /api/v1/auth/login — Đăng nhập   
   POST /api/v1/auth/register — Đăng ký  -OK
   POST /api/v1/auth/change-password — Đổi mật khẩu
3. CertificateController (/api/v1/certificates):  
   GET /api/v1/certificates — Lấy tất cả certificate
   GET /api/v1/certificates/{id} — Lấy certificate theo id
   POST /api/v1/certificates — Tạo certificate mới
   PUT /api/v1/certificates/{id} — Cập nhật certificate
   DELETE /api/v1/certificates/{id} — Xóa certificate
4. EventController (/api/v1/events):  
   GET /api/v1/events — Lấy tất cả event
   GET /api/v1/events/{id} — Lấy event theo id
   POST /api/v1/events — Tạo event mới
   PUT /api/v1/events/{id} — Cập nhật event
   DELETE /api/v1/events/{id} — Xóa event
   PUT /api/v1/events/{eventId}/approval — Cập nhật trạng thái duyệt event
   GET /api/v1/events/{eventId}/participants — Lấy danh sách người tham gia event
5. EventPostController (/api/v1/posts):  
   GET /api/v1/posts — Lấy tất cả event post
   GET /api/v1/posts/{id} — Lấy event post theo id
   POST /api/v1/posts — Tạo event post mới
   PUT /api/v1/posts/{id} — Cập nhật event post
   DELETE /api/v1/posts/{id} — Xóa event post
6. EventRegistrationController (/api/v1/registrations):  
   GET /api/v1/registrations — Lấy tất cả registration
   GET /api/v1/registrations/{id} — Lấy registration theo id
   POST /api/v1/registrations — Tạo registration mới
   PUT /api/v1/registrations/{id} — Cập nhật registration
   DELETE /api/v1/registrations/{id} — Xóa registration
   PUT /api/v1/registrations/{id}/approval — Cập nhật trạng thái duyệt registration
7. EventScoreController (/api/v1/scores):  
   GET /api/v1/scores — Lấy tất cả event score
   GET /api/v1/scores/{id} — Lấy event score theo id
   POST /api/v1/scores — Tạo event score mới
   PUT /api/v1/scores/{id} — Cập nhật event score
   DELETE /api/v1/scores/{id} — Xóa event score
8. QRCodeController (/api/v1/qrcodes):  
   GET /api/v1/qrcodes — Lấy tất cả QR code
   GET /api/v1/qrcodes/{id} — Lấy QR code theo id
   POST /api/v1/qrcodes — Tạo QR code mới
   PUT /api/v1/qrcodes/{id} — Cập nhật QR code
   DELETE /api/v1/qrcodes/{id} — Xóa QR code
9. AttendanceController (/api/v1/attendances):  
   POST /api/v1/attendances — Điểm danh
   PUT /api/v1/attendances/{attendanceId}/status/{status} — Cập nhật trạng thái điểm danh
   GET /api/v1/attendances/event/{eventId} — Lấy danh sách điểm danh theo event
   GET /api/v1/attendances/student/{studentId} — Lấy danh sách điểm danh theo student
10. ReportController (/api/v1/reports):
    GET /api/v1/reports/statistics — Lấy thống kê hoạt động cộng đồng
    GET /api/v1/reports — Lấy tất cả report
    GET /api/v1/reports/{id} — Lấy report theo id
    POST /api/v1/reports — Tạo report mới
    PUT /api/v1/reports/{id} — Cập nhật report
    DELETE /api/v1/reports/{id} — Xóa report


1. Đăng ký (Registration)

- **Method:** POST
- **URL:** `http://localhost:8080/api/v1/auth/register`
- **Request Body (JSON):**
{
"email": "rum01285800687@gmail.com",
"password": "0935601022",
"fullName": "Nguyen Minh Quan",
"phoneNumber": "0935601022",
"role" : "NHA_TRUONG"
}
2. Đăng nhập (Login)

- **Method:** POST
- **URL:** `http://localhost:8080/api/v1/auth/login`
- **Request Body (JSON):**

```json
{
  "email": "rum01285800687@gmail.com",
  "password": "0935601022"
}
```
3. Thay đổi mật khẩu (Change Password)

- **Method:** POST
- **URL:** `http://localhost:8080/api/v1/auth/change-password`
- **Request Body (JSON):**

```json
{
  "userId": 33,
  "oldPassword": "123456",
  "newPassword": "abcdef"
}
```
Sinh viên điểm danh qua QR Code**
### **Endpoint:**
- **URL:** `http://localhost:8080/api/v1/attendances`
- **Chức năng:** Nhận yêu cầu điểm danh từ sinh viên với các thông tin như `studentId`, `eventId`, và `qrId`.
- **Body (JSON):**
  ```json
  {
    "studentId": 123,
    "eventId": 456,
    "qrId": "QR_CODE_ABC123"
  }