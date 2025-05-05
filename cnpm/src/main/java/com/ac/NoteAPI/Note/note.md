Dưới đây là tài liệu API cho các endpoint trong `AuthController`, bao gồm URL, request, response mẫu và mô tả chi tiết chức năng. Bạn có thể sử dụng tài liệu này để test với Postman.

---

# **Tài liệu API - Authentication**

## **1. Đăng nhập (Login)**
### **Endpoint:**
- **URL:** `POST /api/v1/auth/login`
- **Chức năng:** Xác thực thông tin đăng nhập của người dùng.

### **Request**
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  {
    "userId": 1,
    "email": "user@example.com",
    "fullName": "Nguyen Van A",
    "role": "USER"
  }
  ```
#### **Trường hợp thất bại (401 Unauthorized)**
- **Body (JSON)**
  ```json
  {
    "message": "Thông tin đăng nhập không hợp lệ"
  }
  ```

---

## **2. Đăng ký (Register)**
### **Endpoint:**
- **URL:** `POST /api/v1/auth/register`
- **Chức năng:** Đăng ký tài khoản mới.

### **Request**
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
  ```json
  {
    "email": "newuser@example.com",
    "password": "password123",
    "fullName": "Nguyen Van B",
    "phoneNumber": "0123456789"
  }
  ```

### **Response**
#### **Trường hợp thành công (201 Created)**
- **Body (JSON)**
  ```json
  {
    "userId": 2,
    "email": "newuser@example.com",
    "fullName": "Nguyen Van B",
    "phoneNumber": "0123456789"
  }
  ```
#### **Trường hợp thất bại (400 Bad Request)**
- **Body (JSON)**
  ```json
  {
    "message": "Email đã tồn tại trong hệ thống"
  }
  ```

---

## **3. Thay đổi mật khẩu (Change Password)**
### **Endpoint:**
- **URL:** `POST /api/v1/auth/change-password`
- **Chức năng:** Cập nhật mật khẩu mới sau khi xác thực mật khẩu cũ.

### **Request**
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
  ```json
  {
    "userId": 1,
    "oldPassword": "oldpassword123",
    "newPassword": "newpassword456"
  }
  ```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  {
    "message": "Đổi mật khẩu thành công"
  }
  ```
#### **Trường hợp thất bại (400 Bad Request)**
- **Body (JSON)**
  ```json
  {
    "message": "Mật khẩu cũ không đúng"
  }
  ```

---

## **Hướng dẫn Test API bằng Postman**
1. **Login**
    - Mở Postman, chọn **POST**, nhập URL `http://localhost:8080/api/v1/auth/login`
    - Chọn **Body → raw → JSON**, nhập dữ liệu đăng nhập, sau đó nhấn **Send**.

2. **Register**
    - Chọn **POST**, nhập URL `http://localhost:8080/api/v1/auth/register`
    - Nhập dữ liệu đăng ký vào **Body**, nhấn **Send**.

3. **Change Password**
    - Chọn **POST**, nhập URL `http://localhost:8080/api/v1/auth/change-password`
    - Nhập dữ liệu vào **Body**, nhấn **Send**.

---

Tài liệu này giúp dễ dàng kiểm thử API trên Postman hoặc bất kỳ công cụ test API nào khác. 🚀


Dưới đây là tài liệu API chi tiết cho `AttendanceController`, bao gồm URL, request, response mẫu và mô tả chi tiết chức năng. Bạn có thể sử dụng tài liệu này để test với Postman.

---

# **Tài liệu API - Attendance**

## **1. Sinh viên điểm danh qua QR Code**
### **Endpoint:**
- **URL:** `POST /api/v1/attendances`
- **Chức năng:** Nhận yêu cầu điểm danh từ sinh viên với các thông tin như `studentId`, `eventId`, và `qrId`.

### **Request**
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
  ```json
  {
    "studentId": 123,
    "eventId": 456,
    "qrId": "QR_CODE_ABC123"
  }
  ```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  {
    "attendanceId": 1,
    "studentId": 123,
    "eventId": 456,
    "status": "PENDING"
  }
  ```
#### **Trường hợp thất bại (400 Bad Request)**
- **Body (JSON)**
  ```json
  {
    "message": "QR Code không hợp lệ hoặc đã được sử dụng"
  }
  ```

---

## **2. BTC/Nhà trường phê duyệt hoặc từ chối điểm danh**
### **Endpoint:**
- **URL:** `PUT /api/v1/attendances/{attendanceId}/status/{status}`
- **Chức năng:** Cập nhật trạng thái điểm danh (`APPROVED` hoặc `REJECTED`) dựa trên yêu cầu của BTC hoặc Nhà trường.

### **Request**
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Path Variables:**
    - `attendanceId`: ID của bản ghi điểm danh cần cập nhật.
    - `status`: Trạng thái mới (`APPROVED` hoặc `REJECTED`).

### **Ví dụ URL**
```
PUT /api/v1/attendances/1/status/APPROVED
```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  {
    "attendanceId": 1,
    "studentId": 123,
    "eventId": 456,
    "status": "APPROVED"
  }
  ```
#### **Trường hợp thất bại (400 Bad Request)**
- **Body (JSON)**
  ```json
  {
    "message": "Trạng thái không hợp lệ"
  }
  ```

---

## **3. Lấy danh sách điểm danh theo sự kiện**
### **Endpoint:**
- **URL:** `GET /api/v1/attendances/event/{eventId}`
- **Chức năng:** Truy vấn và trả về danh sách điểm danh của một sự kiện cụ thể.

### **Request**
- **Path Variables:**
    - `eventId`: ID của sự kiện cần lấy danh sách điểm danh.

### **Ví dụ URL**
```
GET /api/v1/attendances/event/456
```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  [
    {
      "attendanceId": 1,
      "studentId": 123,
      "eventId": 456,
      "status": "APPROVED"
    },
    {
      "attendanceId": 2,
      "studentId": 124,
      "eventId": 456,
      "status": "PENDING"
    }
  ]
  ```
#### **Trường hợp không tìm thấy (404 Not Found)**
- **Body (JSON)**
  ```json
  {
    "message": "Không có bản ghi điểm danh nào cho sự kiện này"
  }
  ```

---

## **4. Lấy danh sách điểm danh theo sinh viên**
### **Endpoint:**
- **URL:** `GET /api/v1/attendances/student/{studentId}`
- **Chức năng:** Truy vấn và trả về lịch sử điểm danh của một sinh viên cụ thể.

### **Request**
- **Path Variables:**
    - `studentId`: ID của sinh viên cần lấy danh sách điểm danh.

### **Ví dụ URL**
```
GET /api/v1/attendances/student/123
```

### **Response**
#### **Trường hợp thành công (200 OK)**
- **Body (JSON)**
  ```json
  [
    {
      "attendanceId": 1,
      "eventId": 456,
      "status": "APPROVED"
    },
    {
      "attendanceId": 3,
      "eventId": 789,
      "status": "PENDING"
    }
  ]
  ```
#### **Trường hợp không tìm thấy (404 Not Found)**
- **Body (JSON)**
  ```json
  {
    "message": "Sinh viên này chưa có lịch sử điểm danh"
  }
  ```

---

## **Hướng dẫn Test API bằng Postman**
1. **Điểm danh bằng QR Code**
    - Chọn **POST**, nhập URL `http://localhost:8080/api/v1/attendances`
    - Chọn **Body → raw → JSON**, nhập dữ liệu điểm danh, sau đó nhấn **Send**.

2. **Phê duyệt / từ chối điểm danh**
    - Chọn **PUT**, nhập URL `http://localhost:8080/api/v1/attendances/{attendanceId}/status/{status}`
    - Chọn `status` là `APPROVED` hoặc `REJECTED`, nhấn **Send**.

3. **Lấy danh sách điểm danh theo sự kiện**
    - Chọn **GET**, nhập URL `http://localhost:8080/api/v1/attendances/event/{eventId}`
    - Nhấn **Send**.

4. **Lấy danh sách điểm danh theo sinh viên**
    - Chọn **GET**, nhập URL `http://localhost:8080/api/v1/attendances/student/{studentId}`
    - Nhấn **Send**.

---

Tài liệu này giúp dễ dàng kiểm thử API trên Postman hoặc bất kỳ công cụ test API nào khác. 🚀