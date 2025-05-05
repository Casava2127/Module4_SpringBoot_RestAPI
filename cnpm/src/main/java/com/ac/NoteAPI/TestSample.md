Dưới đây là một số mẫu API để test trong Postman cho 3 chức năng: Đăng nhập, Đăng ký và Thay đổi mật khẩu.

---

### 1. Đăng ký (Registration)

- **Method:** POST  
- **URL:** `http://localhost:8080/api/v1/auth/register`  
- **Request Body (JSON):**

```json
{
  "email": "student@example.com",
  "password": "123456",
  "fullName": "Nguyen Van A",
  "phoneNumber": "0123456789"
}
```

- **Sample Success Response (HTTP 201 Created):**

```json
{
  "userId": 1,
  "email": "student@example.com",
  "fullName": "Nguyen Van A",
  "phoneNumber": "0123456789",
  "role": "SINH_VIEN",
  "createdAt": "2025-03-18T10:15:30.000+00:00",
  "updatedAt": "2025-03-18T10:15:30.000+00:00"
}
```

- **Sample Error Response (HTTP 400 Bad Request):**

```json
"Email đã được sử dụng"
```

---

### 2. Đăng nhập (Login)

- **Method:** POST  
- **URL:** `http://localhost:8080/api/v1/auth/login`  
- **Request Body (JSON):**

```json
{
  "email": "student@example.com",
  "password": "123456"
}
```

- **Sample Success Response (HTTP 200 OK):**

```json
{
  "userId": 1,
  "email": "student@example.com",
  "fullName": "Nguyen Van A",
  "phoneNumber": "0123456789",
  "role": "SINH_VIEN",
  "createdAt": "2025-03-18T10:15:30.000+00:00",
  "updatedAt": "2025-03-18T10:15:30.000+00:00"
}
```

- **Sample Error Response (HTTP 401 Unauthorized):**

```json
"Thông tin đăng nhập không hợp lệ"
```

---

### 3. Thay đổi mật khẩu (Change Password)

- **Method:** POST  
- **URL:** `http://localhost:8080/api/v1/auth/change-password`  
- **Request Body (JSON):**

```json
{
  "userId": 1,
  "oldPassword": "123456",
  "newPassword": "abcdef"
}
```

- **Sample Success Response (HTTP 200 OK):**

```json
"Đổi mật khẩu thành công"
```

- **Sample Error Response (HTTP 400 Bad Request):**

```json
"Mật khẩu cũ không đúng"
```

---

### Hướng dẫn test với Postman

1. **Đăng ký:**  
   - Mở Postman, tạo một request mới với method `POST` và nhập URL `http://localhost:8080/api/v1/auth/register`.  
   - Chuyển sang tab **Body** và chọn **raw** với định dạng **JSON**.  
   - Dán đoạn JSON mẫu như trên vào khung request, sau đó nhấn **Send**.

2. **Đăng nhập:**  
   - Tạo một request mới với method `POST` và URL `http://localhost:8080/api/v1/auth/login`.  
   - Trong tab **Body**, chọn **raw** với định dạng **JSON** và nhập đoạn JSON mẫu đăng nhập.  
   - Nhấn **Send** để kiểm tra kết quả.

3. **Thay đổi mật khẩu:**  
   - Tạo một request mới với method `POST` và URL `http://localhost:8080/api/v1/auth/change-password`.  
   - Trong tab **Body**, chọn **raw** với định dạng **JSON** và dán đoạn JSON mẫu thay đổi mật khẩu.  
   - Nhấn **Send** để thực hiện thay đổi mật khẩu.

Lưu ý:  
- Đảm bảo rằng server của bạn đang chạy và lắng nghe tại địa chỉ và cổng được cung cấp.  
- Các response mẫu có thể khác nhau tùy thuộc vào cấu hình thực tế và dữ liệu trong database.  
- Nếu muốn mở rộng, bạn có thể thêm kiểm tra validate đầu vào, thông báo lỗi chi tiết hoặc tích hợp JWT cho việc đăng nhập.

Với các API mẫu trên, bạn có thể test dễ dàng trong Postman các chức năng đăng ký, đăng nhập và thay đổi mật khẩu cho người dùng.





Dưới đây là ví dụ về cách triển khai các lớp Controller, Service và Repository cho chức năng duyệt/từ chối đăng ký sự kiện của sinh viên (approval/rejection) theo mô hình MVC:

---

## 1. Repository

Giả sử bạn đã có repository cho entity đăng ký sự kiện, ta sử dụng như sau:

**EventRegistrationRepository.java**
```java
package com.ac.repository;

import com.ac.model.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    Optional<EventRegistration> findById(Long id);
}
```

---

## 2. Service

Trong service, chúng ta thêm phương thức để cập nhật trạng thái đăng ký (ví dụ: từ PENDING sang APPROVED hoặc REJECTED).  
**EventManagementService.java**
```java
package com.ac.service;

import com.ac.model.entity.EventRegistration;
import com.ac.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EventManagementService {

    @Autowired
    private EventRegistrationRepository registrationRepository;
    
    // Các phương thức CRUD khác đã có...
    
    /**
     * Cập nhật trạng thái đăng ký sự kiện.
     * @param registrationId ID của đăng ký
     * @param status Trạng thái mới (ví dụ: "APPROVED" hoặc "REJECTED")
     * @return đăng ký đã được cập nhật
     */
    public EventRegistration updateRegistrationStatus(Long registrationId, String status) {
        Optional<EventRegistration> regOpt = registrationRepository.findById(registrationId);
        if (regOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đăng ký với ID: " + registrationId);
        }
        EventRegistration registration = regOpt.get();
        // Giả định rằng entity EventRegistration có thuộc tính status kiểu String
        registration.setStatus(status);
        return registrationRepository.save(registration);
    }
}
```

---

## 3. Controller

Chúng ta tạo endpoint riêng để duyệt hoặc từ chối đăng ký sự kiện. Ở đây, endpoint nhận vào một DTO chứa trạng thái cập nhật.

**UpdateRegistrationStatusRequest.java** (DTO)
```java
package com.ac.model.dto;

public class UpdateRegistrationStatusRequest {
    private String status; // Ví dụ: "APPROVED" hoặc "REJECTED"
    
    // Getters and Setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
```

**EventManagementController.java** (phần mở rộng cho chức năng approval)
```java
package com.ac.controller;

import com.ac.model.dto.UpdateRegistrationStatusRequest;
import com.ac.model.entity.EventRegistration;
import com.ac.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EventManagementController {

    @Autowired 
    private EventManagementService service;
    
    // --- Các endpoint khác đã có cho Users, Events, v.v.
    
    // -------------------------- Đăng ký sự kiện --------------------------
    // Endpoint CRUD đã có:
    // @GetMapping("/registrations")
    // @PostMapping("/registrations")
    // @PutMapping("/registrations/{id}")
    // @DeleteMapping("/registrations/{id}")
    
    // -------------------------- Duyệt/Từ chối đăng ký sự kiện --------------------------
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
}
```

---

## 4. API Mẫu để Test bằng Postman

### **Duyệt đăng ký sự kiện (Approve/Reject Registration)**

- **Method:** PUT
- **URL:**
  ```
  http://localhost:8080/api/v1/registrations/{id}/approval
  ```
  Trong đó, `{id}` là ID của đăng ký cần duyệt/từ chối.

- **Request Body (JSON):**
  ```json
  {
    "status": "APPROVED"
  }
  ```
  hoặc
  ```json
  {
    "status": "REJECTED"
  }
  ```

- **Sample Success Response (HTTP 200 OK):**
  ```json
  {
    "registrationId": 1,
    "studentId": 10,
    "eventId": 5,
    "registrationDate": "2025-03-18T10:15:30.000+00:00",
    "status": "APPROVED"
  }
  ```

- **Sample Error Response (HTTP 400 Bad Request):**
  ```json
  "Không tìm thấy đăng ký với ID: 1"
  ```

---

Với cấu trúc trên, bạn đã có endpoint riêng phục vụ chức năng duyệt/từ chối đăng ký sự kiện của sinh viên. Các lớp Controller, Service và Repository được triển khai theo mô hình MVC giúp mở rộng chức năng hiện có của hệ thống.




Dưới đây là tập hợp API test hoàn chỉnh dành cho Postman để kiểm tra chức năng điểm danh.

---

## **📌 1. Sinh viên điểm danh (Mark Attendance)**
**Method:** `POST`  
**URL:** `/api/v1/attendances`  
**Body (JSON):**
```json
{
  "studentId": 1,
  "eventId": 2,
  "qrId": 3
}
```
**Expected Response (200 OK)**
```json
{
  "id": 10,
  "studentId": 1,
  "eventId": 2,
  "qrId": 3,
  "status": "PENDING",
  "scanTime": "2025-03-18T14:30:00"
}
```

---

## **📌 2. Phê duyệt/Từ chối điểm danh**
### ✅ **Phê duyệt (Approve)**
**Method:** `PUT`  
**URL:** `/api/v1/attendances/10/status/APPROVED`

### ❌ **Từ chối (Reject)**
**Method:** `PUT`  
**URL:** `/api/v1/attendances/10/status/REJECTED`

**Expected Response (200 OK)**
```json
{
  "id": 10,
  "studentId": 1,
  "eventId": 2,
  "qrId": 3,
  "status": "APPROVED",
  "scanTime": "2025-03-18T14:30:00"
}
```
_(hoặc `"status": "REJECTED"` nếu từ chối)_

---

## **📌 3. Lấy danh sách điểm danh theo sự kiện**
**Method:** `GET`  
**URL:** `/api/v1/attendances/event/2`

**Expected Response (200 OK)**
```json
[
  {
    "id": 10,
    "studentId": 1,
    "eventId": 2,
    "qrId": 3,
    "status": "APPROVED",
    "scanTime": "2025-03-18T14:30:00"
  },
  {
    "id": 11,
    "studentId": 4,
    "eventId": 2,
    "qrId": 5,
    "status": "PENDING",
    "scanTime": "2025-03-18T15:00:00"
  }
]
```

---

## **📌 4. Lấy danh sách điểm danh theo sinh viên**
**Method:** `GET`  
**URL:** `/api/v1/attendances/student/1`

**Expected Response (200 OK)**
```json
[
  {
    "id": 10,
    "studentId": 1,
    "eventId": 2,
    "qrId": 3,
    "status": "APPROVED",
    "scanTime": "2025-03-18T14:30:00"
  }
]
```

---

### **📌 Hướng dẫn Import API vào Postman**
1. Mở **Postman**.
2. Tạo **New Collection**: `Attendance API Test`.
3. Thêm **Requests** với phương thức và URL tương ứng.
4. Copy **JSON Body** vào tab **Body (raw, JSON)** khi cần.
5. Kiểm tra kết quả và debug nếu cần.

⚡ **Nếu có lỗi "The given id must not be null"**, hãy kiểm tra lại:
- `User.java`, `Event.java`, `QRCode.java` có đúng annotation `@Column(name = "id")` không?
- Truy vấn DB xem `studentId`, `eventId`, `qrId` có tồn tại không?

Bạn có cần tôi viết thêm **test case JUnit** để kiểm thử API này không? 🚀





Dưới đây là ví dụ hoàn chỉnh mở rộng chức năng duyệt sự kiện (approve/reject event) dành cho Nhà trường, gồm các lớp DTO, Service và Controller.

Giả sử entity **Event** của bạn đã có thuộc tính `status` kiểu enum (ví dụ: PENDING, APPROVED, REJECTED, ONGOING, COMPLETED).

---

### 1. DTO ApprovalRequest

Tạo lớp DTO để nhận dữ liệu cập nhật trạng thái duyệt sự kiện từ phía client.

```java
package com.ac.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequest {
    // Giá trị hợp lệ: APPROVED hoặc REJECTED
    private String status;
}
```

---

### 2. Service: EventService

Thêm phương thức để cập nhật trạng thái duyệt sự kiện. Phương thức này sẽ kiểm tra xem status được gửi lên có phải là APPROVED hoặc REJECTED không, sau đó cập nhật event.

```java
package com.ac.service;

import com.ac.model.entity.Event;
import com.ac.model.entity.EventStatus; // Giả sử enum này định nghĩa: PENDING, APPROVED, REJECTED, ONGOING, COMPLETED
import com.ac.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    // Các phương thức CRUD hiện có...

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
}
```

---

### 3. Controller: EventController

Thêm endpoint cho Nhà trường duyệt (approve/reject) sự kiện được tạo bởi BTC hoặc các đơn vị khác.

```java
package com.ac.controller;

import com.ac.model.dto.ApprovalRequest;
import com.ac.model.entity.Event;
import com.ac.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // Các endpoint hiện có: tạo, cập nhật, xoá sự kiện,...

    // Endpoint phê duyệt/từ chối sự kiện
    @PutMapping("/{eventId}/approval")
    public ResponseEntity<Event> updateEventApproval(@PathVariable Long eventId,
                                                     @RequestBody ApprovalRequest request) {
        Event updatedEvent = eventService.updateEventApproval(eventId, request.getStatus());
        return ResponseEntity.ok(updatedEvent);
    }
}
```

---

### 4. API Test trên Postman

#### **1. Phê duyệt sự kiện**

- **Method:** PUT
- **URL:** `http://localhost:8080/api/v1/events/{eventId}/approval`  
  (ví dụ: `http://localhost:8080/api/v1/events/100/approval`)

- **Request Body (JSON):**
```json
{
  "status": "APPROVED"
}
```

- **Expected Response (200 OK):**
```json
{
  "eventId": 100,
  "eventName": "Tên sự kiện",
  "description": "Mô tả sự kiện",
  "organizerId": 5,
  "startDate": "2025-04-01T08:00:00",
  "endDate": "2025-04-01T12:00:00",
  "registrationDeadline": "2025-03-31T23:59:59",
  "status": "APPROVED",
  "createdAt": "2025-03-25T10:00:00",
  "updatedAt": "2025-03-26T09:30:00"
}
```

#### **2. Từ chối duyệt sự kiện**

- **Method:** PUT
- **URL:** `http://localhost:8080/api/v1/events/{eventId}/approval`  
  (ví dụ: `http://localhost:8080/api/v1/events/100/approval`)

- **Request Body (JSON):**
```json
{
  "status": "REJECTED"
}
```

- **Expected Response (200 OK):**
```json
{
  "eventId": 100,
  "eventName": "Tên sự kiện",
  "description": "Mô tả sự kiện",
  "organizerId": 5,
  "startDate": "2025-04-01T08:00:00",
  "endDate": "2025-04-01T12:00:00",
  "registrationDeadline": "2025-03-31T23:59:59",
  "status": "REJECTED",
  "createdAt": "2025-03-25T10:00:00",
  "updatedAt": "2025-03-26T09:30:00"
}
```

---

### **Tóm lại:**
- **ApprovalRequest**: DTO nhận giá trị status.
- **EventService**: Phương thức `updateEventApproval` cập nhật trạng thái event.
- **EventController**: Endpoint PUT `/api/v1/events/{eventId}/approval` để duyệt/từ chối event.
- **API Test**: Gửi request JSON với status APPROVED hoặc REJECTED.

Với cấu trúc này, Nhà trường có thể duyệt hoặc từ chối các sự kiện do BTC tạo ra thông qua API. Nếu có thêm yêu cầu về phân quyền, bạn có thể bổ sung kiểm tra xác thực ở tầng Controller hoặc Service.





Dưới đây là cách triển khai endpoint lấy danh sách sinh viên tham gia theo sự kiện. Chức năng này sẽ trả về danh sách các sinh viên đã được phê duyệt đăng ký hoặc điểm danh (APPROVED) cho một sự kiện cụ thể.

---

### 1. **Cập nhật Service: EventManagementService**

Trong service, ta thêm phương thức `getEventParticipants(Long eventId)` để thực hiện các bước sau:

- Lấy danh sách đăng ký sự kiện theo `eventId` từ bảng đăng ký (EventRegistration).
- Lấy danh sách điểm danh theo `eventId` từ bảng điểm danh (Attendance).
- Lọc ra các sinh viên có trạng thái phê duyệt (đối với đăng ký: status là "APPROVED" và đối với điểm danh: status = AttendanceStatus.APPROVED).
- Hợp nhất (với kiểu Set để loại trùng lặp) và trả về danh sách các sinh viên.

Ví dụ:

```java
package com.ac.service;

import com.ac.model.entity.Attendance;
import com.ac.model.entity.AttendanceStatus;
import com.ac.model.entity.EventRegistration;
import com.ac.model.entity.User;
import com.ac.repository.AttendanceRepository;
import com.ac.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventManagementService {

    // Các service và repository hiện có...
    private final EventRegistrationRepository eventRegistrationRepository;
    private final AttendanceRepository attendanceRepository;

    // Các phương thức CRUD hiện có cho Users, Events, Registrations, v.v.

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
            if ("APPROVED".equalsIgnoreCase(reg.getStatus())) {
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
}
```

> **Lưu ý:**
> - Đảm bảo rằng entity `EventRegistration` có phương thức `getStatus()` trả về trạng thái (chuỗi hoặc enum) và `getStudent()` trả về đối tượng User.
> - Repository `EventRegistrationRepository` cần có phương thức:
    >   ```java
>   List<EventRegistration> findByEvent_EventId(Long eventId);
>   ```
> - Tương tự, `AttendanceRepository` cần có phương thức:
    >   ```java
>   List<Attendance> findByEvent_EventId(Long eventId);
>   ```

---

### 2. **Cập nhật Controller: EventManagementController**

Thêm endpoint mới để lấy danh sách sinh viên tham gia cho một sự kiện:

```java
package com.ac.controller;

import com.ac.model.entity.User;
import com.ac.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventManagementController {

    @Autowired 
    private EventManagementService service;

    // Các endpoint khác...

    // Endpoint lấy danh sách sinh viên tham gia theo sự kiện
    @GetMapping("/events/{eventId}/participants")
    public ResponseEntity<List<User>> getEventParticipants(@PathVariable Long eventId) {
        List<User> participants = service.getEventParticipants(eventId);
        return ResponseEntity.ok(participants);
    }
}
```

Endpoint này sẽ:
- Nhận `eventId` dưới dạng path variable.
- Gọi service `getEventParticipants(eventId)` để lấy danh sách sinh viên.
- Trả về danh sách sinh viên dưới dạng JSON.

---

### 3. **API Test trên Postman**

#### **Endpoint: Lấy danh sách sinh viên tham gia theo sự kiện**

- **Method:** `GET`
- **URL:**
  ```
  http://localhost:8080/api/v1/events/{eventId}/participants
  ```
  Ví dụ:
  ```
  http://localhost:8080/api/v1/events/10/participants
  ```

- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "email": "student1@example.com",
      "fullName": "Nguyễn Văn A",
      "phoneNumber": "0123456789",
      "role": "SINH_VIEN",
      "createdAt": "2025-03-18T10:15:30",
      "updatedAt": "2025-03-18T10:15:30"
    },
    {
      "id": 2,
      "email": "student2@example.com",
      "fullName": "Trần Thị B",
      "phoneNumber": "0987654321",
      "role": "SINH_VIEN",
      "createdAt": "2025-03-18T11:00:00",
      "updatedAt": "2025-03-18T11:00:00"
    }
  ]
  ```

> **Chú ý:**
> - Các thuộc tính của đối tượng User sẽ phụ thuộc vào entity của bạn. Ví dụ trên sử dụng `id` nếu bạn đã cấu hình lại entity `User` để có `@Column(name = "id")` hoặc sử dụng trường thích hợp (ví dụ `userId`).

---

Với cấu trúc này, bạn đã triển khai endpoint GET `/api/v1/events/{eventId}/participants` trả về danh sách sinh viên tham gia cho một sự kiện cụ thể dựa trên đăng ký và điểm danh đã được phê duyệt.