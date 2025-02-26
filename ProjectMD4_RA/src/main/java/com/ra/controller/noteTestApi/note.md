**1. Đăng ký tài khoản (Sign-Up)**

**Endpoint:**  
`POST http://localhost:8080/api/v1/auth/sign-up`

**Headers:**
```
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "username": "newuser123",
  "email": "newuser123@example.com",
  "fullname": "New User 123",
  "password": "password123",
  "phone": "0123456789",
  "address": "123 Test Street",
  "avatar": "https://example.com/avatar.jpg"
}
```

**Ví dụ cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/sign-up \
  -H "Content-Type: application/json" \
  -d '{
        "username": "newuser123",
        "email": "newuser123@example.com",
        "fullname": "New User 123",
        "password": "password123",
        "phone": "0123456789",
        "address": "123 Test Street",
        "avatar": "https://example.com/avatar.jpg"
      }'
```

**Mô tả:**  
Endpoint này nhận dữ liệu đăng ký từ client. Nếu đăng ký thành công, API trả về thông báo "User registered successfully!" và HTTP status 201 (Created).

---

**2. Đăng nhập (Sign-In)**

**Endpoint:**  
`POST http://localhost:8080/api/v1/auth/sign-in`

**Headers:**
```
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "username": "newuser123",
  "password": "password123"
}
```

**Ví dụ cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/sign-in \
  -H "Content-Type: application/json" \
  -d '{
        "username": "newuser123",
        "password": "password123"
      }'
```

**Mô tả:**  
Endpoint này nhận dữ liệu đăng nhập từ client. Nếu xác thực thành công, API trả về accessToken kèm tokenType (ví dụ: Bearer) và HTTP status 200 (OK).


Dưới đây là thiết kế lại các API theo cách bạn yêu cầu, cho các chức năng quản lý tài khoản người dùng.

---
**2. Quan ly tài khoản**

### **1. Lấy thông tin tài khoản (Get Account Info)**

**Endpoint:**  
`GET http://localhost:8080/api/v1/users/{id}`

**Headers:**
```
Authorization: Bearer your_token_here
```

**Mô tả:**  
Endpoint này trả về thông tin chi tiết của người dùng theo ID.

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/v1/users/1 \
  -H "Authorization: Bearer your_token_here"
```

**Response JSON mẫu:**
```json
{
  "userId": 1,
  "username": "john_doe",
  "email": "john.doe@example.com",
  "fullname": "John Doe",
  "status": true,
  "avatar": "https://example.com/avatar.jpg",
  "phone": "0123456789",
  "address": "123 Main Street",
  "createdAt": "2024-02-25T12:00:00",
  "updatedAt": "2024-02-26T14:30:00"
}
```

---

### **2. Cập nhật thông tin tài khoản (Update Account Info)**

**Endpoint:**  
`PUT http://localhost:8080/api/v1/users/update/{id}`

**Headers:**
```
Authorization: Bearer your_token_here
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "username": "john_updated",
  "email": "john.updated@example.com",
  "fullname": "John Updated",
  "password": "new_password_123",
  "avatar": "https://example.com/avatar_updated.jpg",
  "phone": "0987654321",
  "address": "456 New Street"
}
```

**Ví dụ cURL:**
```bash
curl -X PUT http://localhost:8080/api/v1/users/update/1 \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json" \
  -d '{
        "username": "john_updated",
        "email": "john.updated@example.com",
        "fullname": "John Updated",
        "password": "new_password_123",
        "avatar": "https://example.com/avatar_updated.jpg",
        "phone": "0987654321",
        "address": "456 New Street"
      }'
```

**Response JSON mẫu:**
```json
{
  "userId": 1,
  "username": "john_updated",
  "email": "john.updated@example.com",
  "fullname": "John Updated",
  "status": true,
  "avatar": "https://example.com/avatar_updated.jpg",
  "phone": "0987654321",
  "address": "456 New Street",
  "createdAt": "2024-02-25T12:00:00",
  "updatedAt": "2024-02-26T15:00:00"
}
```

---

### **3. Đổi mật khẩu (Change Password)**

**Endpoint:**  
`PUT http://localhost:8080/api/v1/users/change-password`

**Headers:**
```
Authorization: Bearer your_token_here
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "oldPassword": "old_password_123",
  "newPassword": "new_password_456",
  "confirmNewPassword": "new_password_456"
}
```

**Ví dụ cURL:**
```bash
curl -X PUT http://localhost:8080/api/v1/users/change-password \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json" \
  -d '{
        "oldPassword": "old_password_123",
        "newPassword": "new_password_456",
        "confirmNewPassword": "new_password_456"
      }'
```

**Response JSON mẫu (200 OK):**
```json
{
  "message": "Password updated successfully!"
}
```

**Response JSON mẫu (400 Bad Request - Mật khẩu cũ không đúng):**
```json
{
  "message": "Old password is incorrect!"
}
```

---

### **4. Xóa tài khoản người dùng (Delete User)**

**Endpoint:**  
`DELETE http://localhost:8080/api/v1/users/delete/{id}`

**Headers:**
```
Authorization: Bearer your_token_here
```

**Ví dụ cURL:**
```bash
curl -X DELETE http://localhost:8080/api/v1/users/delete/1 \
  -H "Authorization: Bearer your_token_here"
```

**Response JSON mẫu (204 No Content - Xóa thành công):**
```json
{
  "message": "User deleted successfully"
}
```

**Response JSON mẫu (404 Not Found - Không tìm thấy user):**
```json
{
  "message": "User not found"
}
```

---

### **Tóm tắt**
- **Lấy thông tin tài khoản** (GET): Truy xuất thông tin người dùng.
- **Cập nhật thông tin tài khoản** (PUT): Cập nhật thông tin cá nhân của người dùng.
- **Đổi mật khẩu** (PUT): Thay đổi mật khẩu cho người dùng.
- **Xóa tài khoản người dùng** (DELETE): Xóa tài khoản người dùng theo ID.

Cách viết này giúp bạn dễ dàng test các API qua Postman hoặc cURL, đồng thời cung cấp hướng dẫn chi tiết để bạn có thể sử dụng trong môi trường phát triển.

Cần giúp gì thêm không? 🚀


Dưới đây là thiết kế API mẫu để test trên **Postman**, bao gồm các thông tin cần thiết như **Endpoint, Headers, Payload, cURL request** và **Response JSON mẫu**.

---

## **1. Lấy danh sách thông báo (Get Notifications)**

**Endpoint:**  
`GET http://localhost:8080/api/v1/user/notifications`

**Headers:**
```
Authorization: Bearer your_token_here
```

**Query Parameters (Optional):**
- `userId` (Long) - Lọc danh sách thông báo theo ID của người dùng.

### **Ví dụ cURL (Lấy tất cả thông báo):**
```bash
curl -X GET http://localhost:8080/api/v1/user/notifications \
  -H "Authorization: Bearer your_token_here"
```

### **Ví dụ cURL (Lấy thông báo của một người dùng cụ thể - userId = 1):**
```bash
curl -X GET "http://localhost:8080/api/v1/user/notifications?userId=1" \
  -H "Authorization: Bearer your_token_here"
```

### **Response JSON mẫu (200 OK):**
```json
[
  {
    "notificationId": 101,
    "userName": "john_doe",
    "title": "New Course Available",
    "message": "A new Java course has been added to your dashboard.",
    "isRead": false,
    "createdAt": "2024-02-26T10:30:00"
  },
  {
    "notificationId": 102,
    "userName": "john_doe",
    "title": "Payment Successful",
    "message": "Your payment for 'Spring Boot Masterclass' was successful.",
    "isRead": true,
    "createdAt": "2024-02-25T14:00:00"
  }
]
```

---

## **2. Đánh dấu thông báo đã đọc (Mark Notification as Read)**

**Endpoint:**  
`PUT http://localhost:8080/api/v1/user/notifications/{notificationId}/read`

**Headers:**
```
Authorization: Bearer your_token_here
Content-Type: application/json
```

**Path Variable:**
- `{notificationId}` - ID của thông báo cần đánh dấu là đã đọc.

**Request Body:**
```json
{
  "userId": 1
}
```

### **Ví dụ cURL:**
```bash
curl -X PUT http://localhost:8080/api/v1/user/notifications/101/read \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json" \
  -d '{
        "userId": 1
      }'
```

### **Response JSON mẫu (200 OK - Thành công):**
```json
{
  "message": "Notification marked as read"
}
```

### **Response JSON mẫu (404 Not Found - Không tìm thấy thông báo hoặc không thuộc về user):**
```json
{
  "message": "Notification not found or does not belong to user"
}
```

---

## **Tóm tắt API Postman**
| Chức năng                          | HTTP Method | Endpoint                                              | Headers                                    | Body (JSON) |
|-------------------------------------|------------|-------------------------------------------------------|---------------------------------------------|-------------|
| **Lấy danh sách thông báo**        | `GET`      | `/api/v1/user/notifications?userId={userId}` (tùy chọn) | `Authorization: Bearer your_token_here`    | Không       |
| **Đánh dấu thông báo đã đọc**       | `PUT`      | `/api/v1/user/notifications/{notificationId}/read`   | `Authorization: Bearer your_token_here`, `Content-Type: application/json` | `{ "userId": 1 }` |


Dưới đây là thiết kế API mẫu để test trên **Postman**, bao gồm **Endpoint, Headers, cURL request, Payload, và Response JSON mẫu**. 🚀

---

## **1. Lấy danh sách danh mục khóa học (Get Course Categories)**

**Endpoint:**  
`GET http://localhost:8080/api/v1/courses/categories`

**Headers:**
```
Authorization: Bearer your_token_here
```

### **Ví dụ cURL (Lấy tất cả danh mục):**
```bash
curl -X GET http://localhost:8080/api/v1/courses/categories \
  -H "Authorization: Bearer your_token_here"
```

### **Response JSON mẫu (200 OK):**
```json
[
  {
    "categoryId": 1,
    "categoryName": "Programming",
    "description": "Courses related to programming languages.",
    "categoryStatus": true
  },
  {
    "categoryId": 2,
    "categoryName": "Design",
    "description": "Courses related to graphic and UI/UX design.",
    "categoryStatus": true
  }
]
```

---

## **2. Lấy danh mục khóa học theo ID (Get Courses by Category ID)**

**Endpoint:**  
`GET http://localhost:8080/api/v1/courses/categories/{categoryId}`

**Headers:**
```
Authorization: Bearer your_token_here
```

**Path Variable:**
- `{categoryId}` - ID của danh mục cần lấy thông tin.

### **Ví dụ cURL (Lấy danh mục có ID = 1):**
```bash
curl -X GET http://localhost:8080/api/v1/courses/categories/1 \
  -H "Authorization: Bearer your_token_here"
```

### **Response JSON mẫu (200 OK - Danh mục hợp lệ):**
```json
{
  "categoryId": 1,
  "categoryName": "Programming",
  "description": "Courses related to programming languages.",
  "categoryStatus": true
}
```

### **Response JSON mẫu (404 Not Found - Không tìm thấy danh mục):**
```json
{
  "message": "Category not found"
}
```

---

## **Tóm tắt API Postman**

| Chức năng                               | HTTP Method | Endpoint                                  | Headers                                      | Body (JSON) |
|-----------------------------------------|------------|-------------------------------------------|----------------------------------------------|-------------|
| **Lấy danh sách danh mục khóa học**     | `GET`      | `/api/v1/courses/categories`             | `Authorization: Bearer your_token_here`     | Không       |
| **Lấy danh mục khóa học theo ID**       | `GET`      | `/api/v1/courses/categories/{categoryId}` | `Authorization: Bearer your_token_here`     | Không       |

Dưới đây là thiết kế **API mẫu** để test trên **Postman**, bao gồm **cURL request, Headers, Query Parameters và Response JSON mẫu**. 🚀

---

## **1. Lấy danh sách khóa học (Có phân trang và sắp xếp)**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses`

📌 **Headers:**
```
Authorization: Bearer your_token_here
```

📌 **Query Parameters:**  
| Tham số      | Kiểu dữ liệu | Mô tả                                       | Giá trị mẫu |
|-------------|------------|--------------------------------------------|------------|
| `page`      | `int`      | Số trang (bắt đầu từ 0)                     | `0`        |
| `size`      | `int`      | Số lượng khóa học trên mỗi trang             | `10`       |
| `sort`      | `string`   | Trường cần sắp xếp (`courseName`, `price`)   | `courseName,asc` |

📌 **Ví dụ cURL (Lấy danh sách khóa học trang 1, mỗi trang 10 khóa, sắp xếp theo tên)**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses?page=1&size=10&sort=courseName,asc" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK)**:
```json
{
  "content": [
    {
      "courseId": 1,
      "sku": "CS101",
      "courseName": "Java Programming",
      "description": "Learn Java from scratch",
      "price": 99.99,
      "availableSlots": 50,
      "image": "java.png",
      "categoryId": 2,
      "categoryName": "Programming",
      "instructorId": 5,
      "instructorName": "John Doe",
      "createdAt": "2025-02-01T10:00:00",
      "updatedAt": "2025-02-20T12:00:00"
    }
  ],
  "totalPages": 5,
  "totalElements": 50,
  "size": 10,
  "page": 1
}
```

---

## **2. Tìm kiếm khóa học theo từ khóa**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/search`

📌 **Query Parameters:**  
| Tham số   | Kiểu dữ liệu | Mô tả                 | Giá trị mẫu |
|----------|------------|--------------------|------------|
| `keyword` | `string`   | Từ khóa tìm kiếm    | `java` |

📌 **Ví dụ cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses/search?keyword=java" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK)**:
```json
[
  {
    "courseId": 1,
    "courseName": "Java Programming",
    "description": "Learn Java from scratch",
    "price": 99.99
  }
]
```

---

## **3. Lấy danh sách khóa học nổi bật**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/featured`

📌 **Ví dụ cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses/featured" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK)**:
```json
[
  {
    "courseId": 3,
    "courseName": "React for Beginners",
    "description": "Master React step by step",
    "price": 79.99
  }
]
```

---

## **4. Lấy danh sách khóa học mới**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/new`

📌 **Ví dụ cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses/new" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK)**:
```json
[
  {
    "courseId": 5,
    "courseName": "Machine Learning Basics",
    "description": "Get started with ML",
    "price": 119.99
  }
]
```

---

## **5. Lấy danh sách khóa học phổ biến**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/popular`

📌 **Ví dụ cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses/popular" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK)**:
```json
[
  {
    "courseId": 2,
    "courseName": "Python for Data Science",
    "description": "Master Python for ML & AI",
    "price": 129.99
  }
]
```

---

## **6. Lấy chi tiết khóa học theo `courseId`**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/{courseId}`

📌 **Ví dụ cURL (Lấy khóa học có ID = 1):**
```bash
curl -X GET "http://localhost:8080/api/v1/courses/1" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK - Khóa học hợp lệ):**
```json
{
  "courseId": 1,
  "sku": "CS101",
  "courseName": "Java Programming",
  "description": "Learn Java from scratch",
  "price": 99.99,
  "availableSlots": 50,
  "image": "java.png",
  "categoryId": 2,
  "categoryName": "Programming",
  "instructorId": 5,
  "instructorName": "John Doe",
  "createdAt": "2025-02-01T10:00:00",
  "updatedAt": "2025-02-20T12:00:00"
}
```

📌 **Response JSON mẫu (404 Not Found - Không tìm thấy khóa học):**
```json
{
  "message": "Course not found"
}
```

---

## **Tóm tắt API Postman**

| Chức năng                               | HTTP Method | Endpoint                                  | Query Parameters                           |
|-----------------------------------------|------------|-------------------------------------------|--------------------------------------------|
| **Lấy danh sách khóa học (có phân trang, sắp xếp)** | `GET`      | `/api/v1/courses`                         | `page, size, sort`                         |
| **Tìm kiếm khóa học theo tên/mô tả**    | `GET`      | `/api/v1/courses/search`                 | `keyword`                                  |
| **Lấy danh sách khóa học nổi bật**      | `GET`      | `/api/v1/courses/featured`               | Không                                      |
| **Lấy danh sách khóa học mới**          | `GET`      | `/api/v1/courses/new`                    | Không                                      |
| **Lấy danh sách khóa học phổ biến**     | `GET`      | `/api/v1/courses/popular`                | Không                                      |
| **Lấy chi tiết khóa học**               | `GET`      | `/api/v1/courses/{courseId}`             | `courseId` (Path Variable)                |

Bạn có thể copy-paste **cURL request** vào **Postman** để test trực tiếp! Nếu cần bổ sung hoặc chỉnh sửa, cứ nói nhé! 😊


Dưới đây là tài liệu API cho **Bài giảng của khóa học** theo đúng format bạn yêu cầu. 🚀

---

# **API Bài Giảng Của Khóa Học**

## **1. Lấy danh sách bài giảng của khóa học**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/{courseId}/lessons`

📌 **Ví dụ cURL:**
```bash
curl -X GET "http://localhost:8080/api/v1/courses/1/lessons" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK - Thành công):**
```json
[
  {
    "lessonId": 101,
    "courseId": 1,
    "lessonTitle": "Introduction to Java",
    "content": "Basics of Java",
    "videoUrl": "https://example.com/video1.mp4",
    "sortOrder": 1,
    "createdAt": "2025-02-01T10:00:00",
    "updatedAt": "2025-02-20T12:00:00"
  },
  {
    "lessonId": 102,
    "courseId": 1,
    "lessonTitle": "Java Variables and Data Types",
    "content": "Understanding Java data types",
    "videoUrl": "https://example.com/video2.mp4",
    "sortOrder": 2,
    "createdAt": "2025-02-02T14:00:00",
    "updatedAt": "2025-02-21T15:00:00"
  }
]
```

📌 **Response JSON mẫu (404 Not Found - Không tìm thấy khóa học hoặc bài giảng):**
```json
{
  "message": "Course not found or has no lessons"
}
```

---

## **2. Lấy chi tiết bài giảng**
📌 **Endpoint:**  
`GET http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`

📌 **Ví dụ cURL:**
```bash
curl -X GET "http://localhost:8080/api/v1/courses/1/lessons/2" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (200 OK - Thành công):**
```json
{
  "lessonId": 101,
  "courseId": 1,
  "lessonTitle": "Introduction to Java",
  "content": "Basics of Java",
  "videoUrl": "https://example.com/video1.mp4",
  "sortOrder": 1,
  "createdAt": "2025-02-01T10:00:00",
  "updatedAt": "2025-02-20T12:00:00"
}
```

📌 **Response JSON mẫu (404 Not Found - Không tìm thấy bài giảng):**
```json
{
  "message": "Lesson not found"
}
```

---

## **3. Tạo mới bài giảng (Admin/Instructor)**
📌 **Endpoint:**  
`POST http://localhost:8080/api/v1/courses/{courseId}/lessons`

📌 **Ví dụ cURL:**
```bash
curl -X POST "http://localhost:8080/api/v1/courses/1/lessons" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your_token_here" \
  -d '{
    "lessonTitle": "Java Loops",
    "content": "Introduction to loops in Java",
    "videoUrl": "https://example.com/video3.mp4",
    "sortOrder": 3
  }'
```

📌 **Response JSON mẫu (201 Created - Thành công):**
```json
{
  "lessonId": 103,
  "courseId": 1,
  "lessonTitle": "Java Loops",
  "content": "Introduction to loops in Java",
  "videoUrl": "https://example.com/video3.mp4",
  "sortOrder": 3,
  "createdAt": "2025-02-22T08:30:00",
  "updatedAt": "2025-02-22T08:30:00"
}
```

📌 **Response JSON mẫu (400 Bad Request - Dữ liệu không hợp lệ):**
```json
{
  "message": "Lesson title is required"
}
```

---

## **4. Cập nhật bài giảng (Admin/Instructor)**
📌 **Endpoint:**  
`PUT http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`

📌 **Ví dụ cURL:**
```bash
curl -X PUT "http://localhost:8080/api/v1/courses/1/lessons/7" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your_token_here" \
  -d '{
    "lessonTitle": "Updated Java Introduction",
    "content": "Updated content for Java basics",
    "videoUrl": "https://example.com/new-video1.mp4",
    "sortOrder": 1
  }'
```

📌 **Response JSON mẫu (200 OK - Thành công):**
```json
{
  "lessonId": 101,
  "courseId": 1,
  "lessonTitle": "Updated Java Introduction",
  "content": "Updated content for Java basics",
  "videoUrl": "https://example.com/new-video1.mp4",
  "sortOrder": 1,
  "createdAt": "2025-02-01T10:00:00",
  "updatedAt": "2025-02-22T09:00:00"
}
```

📌 **Response JSON mẫu (404 Not Found - Không tìm thấy bài giảng):**
```json
{
  "message": "Lesson not found"
}
```

---

## **5. Xóa bài giảng (Admin/Instructor)**
📌 **Endpoint:**  
`DELETE http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`

📌 **Ví dụ cURL:**
```bash
curl -X DELETE "http://localhost:8080/api/v1/courses/1/lessons/101" \
  -H "Authorization: Bearer your_token_here"
```

📌 **Response JSON mẫu (204 No Content - Xóa thành công):**  
_Không có nội dung trả về._

📌 **Response JSON mẫu (404 Not Found - Không tìm thấy bài giảng):**
```json
{
  "message": "Lesson not found"
}
```

---

# **Tóm tắt API Postman**

| Chức năng                            | HTTP Method | Endpoint                                        | Mô tả                                  |
|--------------------------------------|------------|------------------------------------------------|---------------------------------------|
| **Lấy danh sách bài giảng**          | `GET`      | `/api/v1/courses/{courseId}/lessons`          | Truy xuất danh sách bài giảng của khóa học |
| **Lấy chi tiết bài giảng**           | `GET`      | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Lấy thông tin bài giảng cụ thể         |
| **Tạo mới bài giảng**                | `POST`     | `/api/v1/courses/{courseId}/lessons`          | Thêm bài giảng mới vào khóa học        |
| **Cập nhật bài giảng**               | `PUT`      | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Chỉnh sửa nội dung bài giảng            |
| **Xóa bài giảng**                    | `DELETE`   | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Xóa bài giảng khỏi khóa học            |


Dưới đây là thiết kế API chi tiết để bạn có thể dễ dàng test trên **Postman**:

---

# 📌 **Giỏ khóa học (Course Cart) API**
## **1. Lấy danh sách khóa học trong giỏ**
**📍 Endpoint:**  
`GET http://localhost:8080/api/v1/user/cart/{userId}`

**📍 Ví dụ cURL:**
```bash
curl -X GET "http://localhost:8080/api/v1/user/cart/1" \
  -H "Authorization: Bearer your_token_here"
```

**📍 Response JSON (200 OK):**
```json
[
  {
    "cartId": 101,
    "courseId": 1,
    "courseName": "Java Programming",
    "quantity": 1,
    "courseImage": "java.png",
    "price": 99.99
  },
  {
    "cartId": 102,
    "courseId": 2,
    "courseName": "Spring Boot",
    "quantity": 1,
    "courseImage": "spring.png",
    "price": 79.99
  }
]
```

---

## **2. Thêm khóa học vào giỏ**
**📍 Endpoint:**  
`POST http://localhost:8080/api/v1/user/cart?userId={userId}`

**📍 Request Body:**
```json
{
  "courseId": 1,
  "quantity": 1
}
```

**📍 Ví dụ cURL:**
```bash
curl -X POST "http://localhost:8080/api/v1/user/cart?userId=1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your_token_here" \
  -d '{
    "courseId": 1,
    "quantity": 1
  }'
```

**📍 Response JSON (200 OK):**
```json
{
  "message": "Course added to cart successfully"
}
```

---

## **3. Cập nhật số lượng khóa học trong giỏ**
**📍 Endpoint:**  
`PUT http://localhost:8080/api/v1/user/cart/{cartId}?userId={userId}&quantity={quantity}`

**📍 Ví dụ cURL:**
```bash
curl -X PUT "http://localhost:8080/api/v1/user/cart/101?userId=1&quantity=2" \
  -H "Authorization: Bearer your_token_here"
```

**📍 Response JSON (200 OK):**
```json
{
  "message": "Cart item updated successfully"
}
```

---

## **4. Xóa một mục trong giỏ**
**📍 Endpoint:**  
`DELETE http://localhost:8080/api/v1/user/cart/{cartId}?userId={userId}`

**📍 Ví dụ cURL:**
```bash
curl -X DELETE "http://localhost:8080/api/v1/user/cart/101?userId=1" \
  -H "Authorization: Bearer your_token_here"
```

**📍 Response JSON (200 OK):**
```json
{
  "message": "Cart item removed successfully"
}
```

---

## **5. Xóa toàn bộ giỏ hàng**
**📍 Endpoint:**  
`DELETE http://localhost:8080/api/v1/user/cart/clear?userId={userId}`

**📍 Ví dụ cURL:**
```bash
curl -X DELETE "http://localhost:8080/api/v1/user/cart/clear?userId=1" \
  -H "Authorization: Bearer your_token_here"
```

**📍 Response JSON (200 OK):**
```json
{
  "message": "Cart cleared successfully"
}
```

---

## **6. Thanh toán giỏ hàng – Đăng ký khóa học**
**📍 Endpoint:**  
`POST http://localhost:8080/api/v1/user/cart/checkout?userId={userId}`

**📍 Ví dụ cURL:**
```bash
curl -X POST "http://localhost:8080/api/v1/user/cart/checkout?userId=1" \
  -H "Authorization: Bearer your_token_here"
```

**📍 Response JSON (200 OK):**
```json
{
  "message": "Checkout successful. Courses enrolled."
}
```

---

📌 **Bây giờ bạn có thể copy & paste các request này vào Postman để test API!** 🚀
Dưới đây là khung API mẫu cho chức năng **Đăng ký khóa học** (cho hệ thống không dùng giỏ hàng) để bạn test trên Postman:

---

## **1. Đăng ký khóa học mới**

**Endpoint:**  
`POST http://localhost:8080/api/v1/user/enrollments`

**Headers:**
```
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "userId": 1,
  "courseId": 2,
  "paymentMethod": "CREDIT_CARD",
  "totalPrice": 150.00,
  "note": "Đăng ký khóa học lập trình Java"
}
```

**Ví dụ cURL:**
```bash
curl -X POST "http://localhost:8080/api/v1/user/enrollments" \
  -H "Content-Type: application/json" \
  -d '{
        "userId": 1,
        "courseId": 2,
        "paymentMethod": "CREDIT_CARD",
        "totalPrice": 150.00,
        "note": "Đăng ký khóa học lập trình Java"
      }'
```

**Mô tả:**  
Endpoint này cho phép người dùng đăng ký tham gia một khóa học mới. Payload bao gồm thông tin người dùng, ID của khóa học, phương thức thanh toán, tổng giá và ghi chú (nếu cần).

---

## **2. Lấy chi tiết đơn đăng ký**

**Endpoint:**  
`GET http://localhost:8080/api/v1/user/enrollments/{enrollmentId}`

**Headers:**
```
Authorization: Bearer your_token_here
```

**Ví dụ cURL (Lấy đơn đăng ký có ID = 10):**
```bash
curl -X GET "http://localhost:8080/api/v1/user/enrollments/10" \
  -H "Authorization: Bearer your_token_here"
```

**Response JSON mẫu (200 OK):**
```json
{
  "enrollmentId": 10,
  "serialNumber": "e3f1c2d4-5b6a-7d8e-9f0a-b1c2d3e4f5g6",
  "totalPrice": 150.00,
  "status": "WAITING",
  "createdAt": "2025-02-26T15:00:00"
}
```

**Mô tả:**  
Endpoint này trả về thông tin chi tiết của đơn đăng ký (enrollment) dựa trên ID. Thông tin bao gồm mã đơn, tổng giá, trạng thái và thời gian tạo đơn.

---

## **3. Hủy đơn đăng ký**

**Endpoint:**  
`PUT http://localhost:8080/api/v1/user/enrollments/{enrollmentId}/cancel`

**Headers:**
```
Authorization: Bearer your_token_here
Content-Type: application/json
```

**Ví dụ cURL (Hủy đơn đăng ký có ID = 10):**
```bash
curl -X PUT "http://localhost:8080/api/v1/user/enrollments/10/cancel" \
  -H "Authorization: Bearer your_token_here" \
  -H "Content-Type: application/json"
```

**Response JSON mẫu (200 OK):**
```json
{
  "message": "Enrollment canceled successfully"
}
```

**Mô tả:**  
Endpoint này cho phép người dùng hủy đơn đăng ký khóa học nếu đơn ở trạng thái “chờ xác nhận” (WAITING). Sau khi hủy, hệ thống sẽ cập nhật trạng thái đơn đăng ký thành `CANCELED`.

---

Bạn có thể copy các khung API trên vào Postman để test chức năng đăng ký khóa học. Nếu cần thêm thông tin hoặc chỉnh sửa, hãy cho mình biết nhé!

### 🔍 **Phân tích vấn đề trong mã nguồn**
1. **Lỗi `Required request parameter 'userId' for method parameter type Long is not present`**
    - Do `@RequestParam Long userId, @RequestParam Long courseId` trong `addFavorite()`, nhưng request có thể đang gửi JSON body mà không có các tham số này trên URL.

2. **Lỗi trong Service Layer**
    - Trong `FavoriteCourseServiceImp`, method `addFavorite(@RequestBody Long userId, Long courseId)` có annotation `@RequestBody`, điều này không hợp lệ vì `Long` không phải là một object chứa dữ liệu JSON.

---

### ✅ **Cách khắc phục**
#### **1. Chỉnh sửa Controller**
Thay vì dùng `@RequestParam`, hãy nhận dữ liệu từ `@RequestBody` với DTO.

```java
import com.ra.model.dto.favoritecourse.FavoriteCourseRequestDTO;
import com.ra.model.dto.favoritecourse.FavoriteCourseResponseDTO;
import com.ra.service.favoritecourse.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/favorites")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    // Thêm khóa học vào danh sách yêu thích
    @PostMapping
    public ResponseEntity<FavoriteCourseResponseDTO> addFavorite(@RequestBody FavoriteCourseRequestDTO request) {
        FavoriteCourseResponseDTO favorite = favoriteCourseService.addFavorite(request.getUserId(), request.getCourseId());
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    // Xóa khóa học khỏi danh sách yêu thích theo ID
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long favoriteId) {
        boolean success = favoriteCourseService.removeFavoriteById(favoriteId);
        if (success) {
            return ResponseEntity.ok("Favorite removed successfully");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
    }

    // Lấy danh sách khóa học yêu thích của user
    @GetMapping
    public ResponseEntity<List<FavoriteCourseResponseDTO>> getFavoriteCourses(@RequestParam Long userId) {
        List<FavoriteCourseResponseDTO> favorites = favoriteCourseService.getFavoriteCourses(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
```

---

#### **2. Tạo DTO cho request**
Thêm `FavoriteCourseRequestDTO.java` để nhận dữ liệu JSON.

```java
package com.ra.model.dto.favoritecourse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteCourseRequestDTO {
    private Long userId;
    private Long courseId;
}
```

---

#### **3. Chỉnh sửa Service Layer**
- **Xóa `@RequestBody` khỏi `addFavorite()` trong `FavoriteCourseServiceImp`**
- **Nhận tham số từ DTO trong method của controller thay vì service**

```java
@Service
public class FavoriteCourseServiceImp implements FavoriteCourseService {

    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public FavoriteCourseResponseDTO addFavorite(Long userId, Long courseId) {
        // Kiểm tra user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        // Kiểm tra nếu đã có, trả về favorite hiện tại
        Optional<FavoriteCourse> existingFavorite = favoriteCourseRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
        if(existingFavorite.isPresent()){
            return convertToDTO(existingFavorite.get());
        }

        // Tạo FavoriteCourse mới
        FavoriteCourse favorite = FavoriteCourse.builder()
                .user(user)
                .course(course)
                .createdAt(LocalDateTime.now())
                .build();

        FavoriteCourse savedFavorite = favoriteCourseRepository.save(favorite);
        return convertToDTO(savedFavorite);
    }

    @Override
    public boolean removeFavoriteById(Long favoriteId) {
        Optional<FavoriteCourse> favoriteOpt = favoriteCourseRepository.findById(favoriteId);
        if (favoriteOpt.isEmpty()) {
            return false;
        }
        favoriteCourseRepository.delete(favoriteOpt.get());
        return true;
    }

    @Override
    public List<FavoriteCourseResponseDTO> getFavoriteCourses(Long userId) {
        List<FavoriteCourse> favorites = favoriteCourseRepository.findByUserUserId(userId);
        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FavoriteCourseResponseDTO convertToDTO(FavoriteCourse favorite) {
        return FavoriteCourseResponseDTO.builder()
                .favoriteId(favorite.getFavoriteId())
                .courseId(favorite.getCourse().getCourseId())
                .courseName(favorite.getCourse().getCourseName())
                .addedAt(favorite.getCreatedAt())
                .build();
    }
}
```

---

### 🔥 **Test API trên Postman**
#### **1. Thêm khóa học vào danh sách yêu thích**
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/v1/user/favorites`
- **Headers:** `Content-Type: application/json`
- **Body (raw, JSON):**
  ```json
  {
      "userId": 1,
      "courseId": 3
  }
  ```
- **Expected Response:**
  ```json
  {
      "favoriteId": 5,
      "courseId": 3,
      "courseName": "Java Spring Boot",
      "addedAt": "2025-02-26T19:30:00"
  }
  ```

---

#### **2. Lấy danh sách yêu thích của người dùng**
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/v1/user/favorites?userId=1`
- **Expected Response:**
  ```json
  [
      {
          "favoriteId": 5,
          "courseId": 3,
          "courseName": "Java Spring Boot",
          "addedAt": "2025-02-26T19:30:00"
      },
      {
          "favoriteId": 6,
          "courseId": 5,
          "courseName": "ReactJS Basics",
          "addedAt": "2025-02-26T19:35:00"
      }
  ]
  ```

---

#### **3. Xóa khóa học khỏi danh sách yêu thích**
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/api/v1/user/favorites/5`
- **Expected Response:**
  ```text
  Favorite removed successfully
  ```

---

### ✅ **Lợi ích của cách tiếp cận mới**
✔ **Hỗ trợ request JSON đúng cách**: Không còn lỗi `MissingServletRequestParameterException`.  
✔ **Code clean và dễ bảo trì hơn**: Tách biệt rõ ràng giữa DTO và Service.  
✔ **RESTful API chuẩn hơn**: Dùng `@RequestBody` cho `POST`, `@RequestParam` cho `GET`, và `@PathVariable` cho `DELETE`.

---

🚀 **Sau khi sửa, thử test lại trên Postman và báo mình biết nếu còn vấn đề nhé!**

Dưới đây là thiết kế **DTO, Service, Repository, Controller** cho **CourseReview** theo chuẩn **Spring Boot + DTO pattern**.

---

## **1️⃣ Tạo DTOs (Data Transfer Objects)**
Chúng ta cần tạo **RequestDTO** để nhận dữ liệu từ client và **ResponseDTO** để trả về dữ liệu.

### 📌 **CourseReviewRequestDTO** (Dùng khi tạo hoặc cập nhật review)
```java
package com.ra.model.dto.coursereview;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseReviewRequestDTO {
    private Long courseId;
    private Long userId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;
}
```
---

### 📌 **CourseReviewResponseDTO** (Dùng để trả về dữ liệu review)
```java
package com.ra.model.dto.coursereview;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseReviewResponseDTO {
    private Long reviewId;
    private Long courseId;
    private String courseName;
    private Long userId;
    private String userName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
```

---

## **2️⃣ Cập nhật Repository**
Thêm phương thức để lấy danh sách review theo `courseId` hoặc `userId`.

```java
package com.ra.repository;

import com.ra.model.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
    List<CourseReview> findByCourseCourseId(Long courseId);
    List<CourseReview> findByUserUserId(Long userId);
}
```

---

## **3️⃣ Tạo Service**
Dịch vụ chứa logic xử lý.

```java
package com.ra.service.coursereview;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import java.util.List;

public interface CourseReviewService {
    CourseReviewResponseDTO addReview(CourseReviewRequestDTO requestDTO);
    List<CourseReviewResponseDTO> getReviewsByCourse(Long courseId);
    List<CourseReviewResponseDTO> getReviewsByUser(Long userId);
    boolean deleteReview(Long reviewId);
}
```

---

## **4️⃣ Tạo Service Implementation**
```java
package com.ra.service.coursereview.impl;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.CourseReview;
import com.ra.model.entity.User;
import com.ra.repository.CourseRepository;
import com.ra.repository.CourseReviewRepository;
import com.ra.repository.UserRepository;
import com.ra.service.coursereview.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseReviewServiceImpl implements CourseReviewService {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseReviewResponseDTO addReview(CourseReviewRequestDTO requestDTO) {
        // Kiểm tra xem khóa học và user có tồn tại không
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Tạo đối tượng CourseReview
        CourseReview review = CourseReview.builder()
                .course(course)
                .user(user)
                .rating(requestDTO.getRating())
                .comment(requestDTO.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        // Lưu vào database
        CourseReview savedReview = courseReviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public List<CourseReviewResponseDTO> getReviewsByCourse(Long courseId) {
        List<CourseReview> reviews = courseReviewRepository.findByCourseCourseId(courseId);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseReviewResponseDTO> getReviewsByUser(Long userId) {
        List<CourseReview> reviews = courseReviewRepository.findByUserUserId(userId);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        if (!courseReviewRepository.existsById(reviewId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        courseReviewRepository.deleteById(reviewId);
        return true;
    }

    private CourseReviewResponseDTO convertToDTO(CourseReview review) {
        return CourseReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .courseId(review.getCourse().getCourseId())
                .courseName(review.getCourse().getCourseName())
                .userId(review.getUser().getUserId())
                .userName(review.getUser().getUserName())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
```

---

## **5️⃣ Tạo Controller**
```java
package com.ra.controller;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import com.ra.service.coursereview.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    // Thêm đánh giá
    @PostMapping
    public ResponseEntity<CourseReviewResponseDTO> addReview(@RequestBody CourseReviewRequestDTO requestDTO) {
        CourseReviewResponseDTO review = courseReviewService.addReview(requestDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // Lấy danh sách review theo courseId
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseReviewResponseDTO>> getReviewsByCourse(@PathVariable Long courseId) {
        List<CourseReviewResponseDTO> reviews = courseReviewService.getReviewsByCourse(courseId);
        return ResponseEntity.ok(reviews);
    }

    // Lấy danh sách review theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseReviewResponseDTO>> getReviewsByUser(@PathVariable Long userId) {
        List<CourseReviewResponseDTO> reviews = courseReviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    // Xóa review theo reviewId
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean success = courseReviewService.deleteReview(reviewId);
        if (success) {
            return ResponseEntity.ok("Review deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }
}
```

---

## **6️⃣ Cách gọi API**
- **Thêm review**
  ```
  POST http://localhost:8080/api/v1/reviews
  Content-Type: application/json
  {
    "courseId": 1,
    "userId": 2,
    "rating": 5,
    "comment": "Great course!"
  }
  ```

- **Lấy review theo course**
  ```
  GET http://localhost:8080/api/v1/reviews/course/1
  ```

- **Lấy review theo user**
  ```
  GET http://localhost:8080/api/v1/reviews/user/2
  ```

- **Xóa review**
  ```
  DELETE http://localhost:8080/api/v1/reviews/3
  ```

---

## 🎯 **Tóm lại**
- **DTOs**: Tạo `CourseReviewRequestDTO`, `CourseReviewResponseDTO`
- **Repository**: Thêm method tìm theo `courseId` và `userId`
- **Service**: Thêm logic xử lý
- **Controller**: Tạo API `add`, `getByCourse`, `getByUser`, `delete`

🔥 **Bây giờ bạn có thể quản lý review khóa học dễ dàng!** 🚀
**Submission**. Bạn có thể nhập các API này vào Postman để kiểm thử hệ thống.

---

## **I. Test API Assignment**
### **1. Lấy danh sách bài tập của khóa học**
**Request:**
```
GET http://localhost:8080/api/v1/courses/1/assignments
```
**Response mẫu:**
```json
[
  {
    "assignmentId": 10,
    "courseName": "Spring Boot Fundamentals",
    "title": "Bài tập Spring Boot 1",
    "description": "Hãy tạo một REST API đơn giản.",
    "dueDate": "2025-03-01T23:59:59",
    "createdAt": "2025-02-25T10:00:00"
  }
]
```

---

### **2. Lấy chi tiết bài tập**
**Request:**
```
GET http://localhost:8080/api/v1/courses/1/assignments/10
```
**Response mẫu:**
```json
{
  "assignmentId": 10,
  "courseName": "Spring Boot Fundamentals",
  "title": "Bài tập Spring Boot 1",
  "description": "Hãy tạo một REST API đơn giản.",
  "dueDate": "2025-03-01T23:59:59",
  "createdAt": "2025-02-25T10:00:00"
}
```

---

### **3. (Admin/Instructor) Tạo bài tập mới**
**Request:**
```
POST http://localhost:8080/api/v1/courses/1/assignments
```
**Body (JSON):**
```json
{
  "courseId": 1,
  "title": "Bài tập Java Spring",
  "description": "Hãy viết một ứng dụng Spring Boot cơ bản.",
  "dueDate": "2025-03-05T23:59:59"
}
```
**Response mẫu:**
```json
{
  "assignmentId": 11,
  "courseName": "Spring Boot Fundamentals",
  "title": "Bài tập Java Spring",
  "description": "Hãy viết một ứng dụng Spring Boot cơ bản.",
  "dueDate": "2025-03-05T23:59:59",
  "createdAt": "2025-02-26T12:00:00"
}
```

---

### **4. (Admin/Instructor) Cập nhật bài tập**
**Request:**
```
PUT http://localhost:8080/api/v1/courses/1/assignments/11
```
**Body (JSON):**
```json
{
  "courseId": 1,
  "title": "Bài tập Java Spring Update",
  "description": "Cập nhật bài tập Spring Boot.",
  "dueDate": "2025-03-10T23:59:59"
}
```
**Response mẫu:**
```json
{
  "assignmentId": 11,
  "courseName": "Spring Boot Fundamentals",
  "title": "Bài tập Java Spring Update",
  "description": "Cập nhật bài tập Spring Boot.",
  "dueDate": "2025-03-10T23:59:59",
  "createdAt": "2025-02-26T12:00:00"
}
```

---

### **5. (Admin/Instructor) Xóa bài tập**
**Request:**
```
DELETE http://localhost:8080/api/v1/courses/1/assignments/11
```
**Response mẫu:**
```json
{
  "message": "Bài tập đã được xóa thành công."
}
```

---

## **II. Test API Submission**
### **1. Lấy danh sách bài nộp của một bài tập**
**Request:**
```
GET http://localhost:8080/api/v1/courses/1/assignments/10/submissions
```
**Response mẫu:**
```json
[
  {
    "submissionId": 5,
    "studentName": "Nguyễn Văn A",
    "fileUrl": "https://example.com/file1.pdf",
    "grade": 9.0,
    "feedback": "Bài làm tốt!",
    "submittedAt": "2025-02-26T10:00:00"
  }
]
```

---

### **2. Sinh viên nộp bài tập**
**Request:**
```
POST http://localhost:8080/api/v1/courses/1/assignments/10/submissions?userId=3
```
**Body (JSON):**
```json
{
  "assignmentId": 10,
  "fileUrl": "https://example.com/file2.pdf"
}
```
**Response mẫu:**
```json
{
  "submissionId": 6,
  "studentName": "Trần Thị B",
  "fileUrl": "https://example.com/file2.pdf",
  "grade": null,
  "feedback": null,
  "submittedAt": "2025-02-26T11:00:00"
}
```

---

### **3. Sinh viên cập nhật bài nộp**
**Request:**
```
PUT http://localhost:8080/api/v1/courses/1/assignments/10/submissions/6?userId=3
```
**Body (JSON):**
```json
{
  "assignmentId": 10,
  "fileUrl": "https://example.com/updated-file.pdf"
}
```
**Response mẫu:**
```json
{
  "submissionId": 6,
  "studentName": "Trần Thị B",
  "fileUrl": "https://example.com/updated-file.pdf",
  "grade": null,
  "feedback": null,
  "submittedAt": "2025-02-26T11:00:00"
}
```

---

## **III. Postman Collection**
Bạn có thể nhập file JSON này vào Postman để test API nhanh chóng.

```json
{
  "info": {
    "_postman_id": "12345678-1234-1234-1234-1234567890ab",
    "name": "Assignment & Submission API Tests",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Get Assignments",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/v1/courses/1/assignments",
          "host": ["http://localhost:8080"],
          "path": ["api", "v1", "courses", "1", "assignments"]
        }
      }
    },
    {
      "name": "Submit Assignment",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "url": {
          "raw": "http://localhost:8080/api/v1/courses/1/assignments/10/submissions?userId=3",
          "host": ["http://localhost:8080"],
          "path": ["api", "v1", "courses", "1", "assignments", "10", "submissions"],
          "query": [
            {
              "key": "userId",
              "value": "3"
            }
          ]
        },
        "body": {
          "mode": "raw",
          "raw": "{\n  \"assignmentId\": 10,\n  \"fileUrl\": \"https://example.com/file2.pdf\"\n}"
        }
      }
    }
  ]
}
```

---

### **Kết luận**
✅ Đầy đủ các API test trên Postman cho **Assignment** và **Submission**.  
✅ Hỗ trợ kiểm thử trên Postman bằng file JSON Collection.  
✅ Có thể mở rộng thêm tính năng **chấm điểm, phản hồi bài nộp** nếu cần.

Có, để triển khai API **Quản lý Người Dùng của Admin**, ta cần thiết kế **DTO, Service, Controller, và Repository** để đảm bảo code rõ ràng, dễ mở rộng và bảo trì. Dưới đây là thiết kế chi tiết cho từng phần.

---

# **1. Thiết kế DTO (Data Transfer Object)**
DTO giúp kiểm soát dữ liệu vào/ra giữa client và server.

## **1.1. `UserResponseDTO`**
Trả về thông tin người dùng khi lấy danh sách hoặc chi tiết người dùng.
```java
package com.ra.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private boolean status;
    private String phone;
    private String address;
    private String avatar;
}
```

---

## **1.2. `UserStatusUpdateDTO`**
Dùng để cập nhật trạng thái tài khoản (`true` = Hoạt động, `false` = Bị khóa).
```java
package com.ra.model.dto.user;

import lombok.Data;

@Data
public class UserStatusUpdateDTO {
    private boolean status;
}
```

---

## **1.3. `UserRoleDTO`**
Dùng để thêm/xóa quyền của người dùng.
```java
package com.ra.model.dto.userrole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDTO {
    private Long userId;
    private Long roleId;
}
```

---

# **2. Thiết kế Repository**
Chứa các phương thức để truy vấn dữ liệu từ database.

## **2.1. `UserRepository`**
```java
package com.ra.repository;

import com.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

## **2.2. `UserRoleRepository`**
```java
package com.ra.repository;

import com.ra.model.entity.UserRole;
import com.ra.model.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
```

---

# **3. Thiết kế Service**
Chứa logic xử lý nghiệp vụ.

## **3.1. `UserService`**
```java
package com.ra.service.user;

import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.UserStatusUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService {
    Page<UserResponseDTO> findAll(Pageable pageable);
    Optional<UserResponseDTO> findById(Long userId);
    boolean updateUserStatus(Long userId, UserStatusUpdateDTO userStatusUpdateDTO);
}
```

---

## **3.2. `UserServiceImpl`**
```java
package com.ra.service.user.impl;

import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.UserStatusUpdateDTO;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserResponseDTO convertToDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .status(user.isStatus())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return new PageImpl<>(users.stream().map(this::convertToDTO).collect(Collectors.toList()), pageable, users.getTotalElements());
    }

    @Override
    public Optional<UserResponseDTO> findById(Long userId) {
        return userRepository.findById(userId).map(this::convertToDTO);
    }

    @Override
    public boolean updateUserStatus(Long userId, UserStatusUpdateDTO userStatusUpdateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(userStatusUpdateDTO.isStatus());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
```

---

# **4. Thiết kế Controller**
Dùng để xử lý các request từ client.

```java
package com.ra.controller.admin;

import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.UserStatusUpdateDTO;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        Page<UserResponseDTO> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        Optional<UserResponseDTO> user = userService.findById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long userId, @RequestBody UserStatusUpdateDTO userStatusUpdateDTO) {
        boolean updated = userService.updateUserStatus(userId, userStatusUpdateDTO);
        if (updated) {
            return ResponseEntity.ok("User account status updated successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}
```

---

# **5. Thiết kế Test Cases (Postman)**
### **1. Lấy danh sách người dùng**
- **Method:** `GET`
- **URL:** `/api/v1/admin/users?page=0&size=10&sortBy=username&sortDir=asc`
- **Expected Response:**
  ```json
  {
    "totalPages": 5,
    "totalUsers": 50,
    "currentPage": 1,
    "users": [...]
  }
  ```

---

### **2. Cập nhật trạng thái người dùng**
- **Method:** `PUT`
- **URL:** `/api/v1/admin/users/1`
- **Request Body:**
  ```json
  {
    "status": false
  }
  ```
- **Expected Response:**
  ```json
  {
    "message": "User account status updated successfully."
  }
  ```

---

### **3. Gán vai trò cho người dùng**
- **Method:** `POST`
- **URL:** `/api/v1/admin/users/1/role/2`
- **Expected Response:**
  ```json
  {
    "message": "Role assigned successfully."
  }
  ```

---

### **4. Xóa quyền của người dùng**
- **Method:** `DELETE`
- **URL:** `/api/v1/admin/users/1/role/2`
- **Expected Response:**
  ```json
  {
    "message": "Role removed successfully."
  }
  ```

---

Vậy là xong! 🎉 Bạn có muốn bổ sung tính năng nào khác không? 🚀
Bạn đã thiết kế DTO, service, controller cho **User**, nhưng để hoàn thiện chức năng **Quản lý Người Dùng của Admin**, bạn cần bổ sung một số phần sau:

### **📌 Những phần còn thiếu:**
1. **Cập nhật trạng thái người dùng (khóa/mở khóa)**
    - Bạn cần bổ sung endpoint xử lý cập nhật trạng thái tài khoản (`status`).
2. **Quản lý vai trò của người dùng (Gán/Xóa quyền)**
    - Bạn cần thiết kế **DTO, service, repository, và controller** để quản lý vai trò của người dùng (thêm/xóa role).

---

## **1️⃣ Cập nhật trạng thái người dùng**
Bạn đã có `UserService`, nhưng thiếu endpoint cập nhật trạng thái tài khoản trong controller.

**🛠 Cập nhật `AdminUserController` để xử lý khóa/mở khóa tài khoản**
```java
@PutMapping("/{userId}/status")
public ResponseEntity<String> updateUserStatus(@PathVariable Long userId, @RequestBody UserStatusUpdateDTO userStatusUpdateDTO) {
    boolean updated = userService.updateUserStatus(userId, userStatusUpdateDTO);
    if (updated) {
        return ResponseEntity.ok("User account status updated successfully.");
    }
    return ResponseEntity.notFound().build();
}
```
📌 **Bổ sung `UserStatusUpdateDTO` nếu chưa có:**
```java
package com.ra.model.dto.user;

import lombok.Data;

@Data
public class UserStatusUpdateDTO {
    private boolean status;
}
```

---

## **2️⃣ Quản lý vai trò của người dùng**
Bạn cần bổ sung các chức năng:
- **Gán vai trò cho người dùng**
- **Xóa quyền của người dùng**

📌 **Bổ sung `UserRoleService` để xử lý quyền của người dùng:**
### **`UserRoleService.java`**
```java
package com.ra.service.userrole;

import com.ra.model.dto.userrole.UserRoleDTO;

public interface UserRoleService {
    boolean assignRoleToUser(Long userId, Long roleId);
    boolean removeRoleFromUser(Long userId, Long roleId);
}
```

---

📌 **Bổ sung `UserRoleServiceImpl`**
### **`UserRoleServiceImpl.java`**
```java
package com.ra.service.userrole.imp;

import com.ra.model.dto.userrole.UserRoleDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.model.entity.UserRole;
import com.ra.model.entity.UserRoleId;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.repository.UserRoleRepository;
import com.ra.service.userrole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        if (user == null || role == null) {
            return false;
        }

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(userId, roleId))
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
        return true;
    }

    @Override
    public boolean removeRoleFromUser(Long userId, Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        if (!userRoleRepository.existsById(id)) {
            return false;
        }
        userRoleRepository.deleteById(id);
        return true;
    }
}
```

---

📌 **Bổ sung `AdminUserRoleController` để xử lý gán/xóa quyền**
### **`AdminUserRoleController.java`**
```java
package com.ra.controller.admin;

import com.ra.service.userrole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
        boolean assigned = userRoleService.assignRoleToUser(userId, roleId);
        if (assigned) {
            return ResponseEntity.ok("Role assigned successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to assign role.");
    }

    @DeleteMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> removeRole(@PathVariable Long userId, @PathVariable Long roleId) {
        boolean removed = userRoleService.removeRoleFromUser(userId, roleId);
        if (removed) {
            return ResponseEntity.ok("Role removed successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to remove role.");
    }
}
```

---

📌 **Bổ sung `UserRoleDTO` nếu chưa có:**
```java
package com.ra.model.dto.userrole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDTO {
    private Long userId;
    private Long roleId;
}
```

---

## **📌 Hoàn thiện API test cases (Postman)**
### **1️⃣ Lấy danh sách người dùng**
- **Method:** `GET`
- **URL:** `/api/v1/admin/users?page=0&size=10`
- **Response:**
  ```json
  {
    "totalPages": 5,
    "totalUsers": 50,
    "currentPage": 1,
    "users": [...]
  }
  ```

---

### **2️⃣ Cập nhật trạng thái người dùng (Khóa/Mở khóa)**
- **Method:** `PUT`
- **URL:** `/api/v1/admin/users/1/status`
- **Request Body:**
  ```json
  {
    "status": false
  }
  ```
- **Response:**
  ```json
  {
    "message": "User account status updated successfully."
  }
  ```

---

### **3️⃣ Gán quyền cho người dùng**
- **Method:** `POST`
- **URL:** `/api/v1/admin/users/1/role/2`
- **Response:**
  ```json
  {
    "message": "Role assigned successfully."
  }
  ```

---

### **4️⃣ Xóa quyền của người dùng**
- **Method:** `DELETE`
- **URL:** `/api/v1/admin/users/1/role/2`
- **Response:**
  ```json
  {
    "message": "Role removed successfully."
  }
  ```

---

## **🎯 Tổng kết**
🔹 **Bổ sung chức năng khóa/mở khóa tài khoản** ➝ `PUT /users/{userId}/status`  
🔹 **Bổ sung quản lý quyền người dùng** ➝ `POST /users/{userId}/role/{roleId}` & `DELETE /users/{userId}/role/{roleId}`  
🔹 **Bổ sung DTO, Service, Repository, Controller** để xử lý các chức năng trên.

🚀 **Bạn có muốn thêm tính năng nào nữa không?**