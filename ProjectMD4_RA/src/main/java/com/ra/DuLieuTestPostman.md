**1. Đăng ký tài khoản (Sign-Up) - OK**

**Endpoint1:**  
`POST http://localhost:8080/api/v1/auth/sign-up`

**Headers:**
```
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "username": "new user1234",
  "email": "newuser1234@example.com",
  "fullname": "New User 1234",
  "password": "password1234",
  "phone": "0123456784",
  "address": "1234 Test Street",
  "avatar": "https://example4.com/avatar.jpg"
}
```

**Ví dụ cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/sign-up \
  -H "Content-Type: application/json" \
  -d '{
        "username": "new user1234",
        "email": "newuser1234@example.com",
        "fullname": "New User 1234",
        "password": "password1234",
        "phone": "0123456784",
        "address": "1234 Test Street",
        "avatar": "https://example4.com/avatar.jpg"
      }'
```

**Mô tả:**  
Endpoint này nhận dữ liệu đăng ký từ client. Nếu đăng ký thành công, API trả về thông báo "User registered successfully!" và HTTP status 201 (Created).

---

**2. Đăng nhập (Sign-In)**

**Endpoint1:**  
`POST http://localhost:8080/api/v1/auth/sign-in`

**Headers:**
```
Content-Type: application/json
```

**Payload JSON mẫu:**
```json
{
  "username": "new user1234",
  "password": "password1234"
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


---
**3. Quan ly tài khoản**

### **1. Lấy thông tin tài khoản (Get Account Info)**

**Endpoint1:**  
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

**4. Cập nhật thông tin tài khoản (Update Account Info)**

**Endpoint1:**  
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

**5. Đổi mật khẩu (Change Password)**

**Endpoint1:**  
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

**Endpoint1:**  
`DELETE http://localhost:8080/api/v1/users/delete/{id}`
// kiem tra lai
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
## **1. Lấy danh sách thông báo (Get Notifications)**

**Endpoint1:**  
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

**Endpoint1:**  
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


## **1. Lấy danh sách danh mục khóa học (Get Course Categories)**

**Endpoint1:**  
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

**Endpoint1:**  
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

---

## **1. Lấy danh sách khóa học (Có phân trang và sắp xếp)**
📌 **Endpoint1:**  
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
📌 **Endpoint1:**  
`GET http://localhost:8080/api/v1/courses/search`

📌 **Query Parameters:**  
| Tham số   | Kiểu dữ liệu | Mô tả                 | Giá trị mẫu |
|----------|------------|--------------------|------------|
| `keyword` | `string`   | Từ khóa tìm kiếm    | `java` |

📌 **Ví dụ cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/courses/search?keyword=python" \
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
📌 **Endpoint1:**  
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
📌 **Endpoint1:**  
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
📌 **Endpoint1:**  
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
📌 **Endpoint1:**  
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

# **API Bài Giảng Của Khóa Học**

## **1. Lấy danh sách bài giảng của khóa học**
📌 **Endpoint1:**  
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
## **2. Lấy chi tiết bài giảng**
📌 **Endpoint1:**  
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
📌 **Endpoint1:**  
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
## **4. Cập nhật bài giảng (Admin/Instructor)**
📌 **Endpoint1:**  
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
## **5. Xóa bài giảng (Admin/Instructor)**
📌 **Endpoint1:**  
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
# **Tóm tắt API Postman**

| Chức năng                            | HTTP Method | Endpoint                                        | Mô tả                                  |
|--------------------------------------|------------|------------------------------------------------|---------------------------------------|
| **Lấy danh sách bài giảng**          | `GET`      | `/api/v1/courses/{courseId}/lessons`          | Truy xuất danh sách bài giảng của khóa học |
| **Lấy chi tiết bài giảng**           | `GET`      | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Lấy thông tin bài giảng cụ thể         |
| **Tạo mới bài giảng**                | `POST`     | `/api/v1/courses/{courseId}/lessons`          | Thêm bài giảng mới vào khóa học        |
| **Cập nhật bài giảng**               | `PUT`      | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Chỉnh sửa nội dung bài giảng            |
| **Xóa bài giảng**                    | `DELETE`   | `/api/v1/courses/{courseId}/lessons/{lessonId}` | Xóa bài giảng khỏi khóa học            |

---

# 📌 **Giỏ khóa học (Course Cart) API**
## **1. Lấy danh sách khóa học trong giỏ**
**📍 Endpoint1:**  
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
**📍 Endpoint1:**  
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
**📍 Endpoint1:**  
`PUT http://localhost:8080/api/v1/user/cart/{cartId}?userId={userId}&quantity={quantity}`

**📍 Ví dụ cURL:**
```bash
curl -X PUT "http://localhost:8080/api/v1/user/cart/1?userId=1&quantity=2" \
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
**📍 Endpoint1:**  
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
**📍 Endpoint1:**  
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
**📍 Endpoint1:**  
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

## **1. Đăng ký khóa học mới**

**Endpoint1:**  
`POST http://localhost:8080/api/v1/user/cart/checkoutEnrollment`

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
curl -X POST "http://localhost:8080/api/v1/user/cart/checkoutEnrollment" \
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

**Endpoint1:**  
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

**Endpoint1:**  
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
### 🔥 **Test API trên Postman**
#### **1. Thêm khóa học vào danh sách yêu thích**
- **Method:** `POST`
- **Endpoint1:** `http://localhost:8080/api/v1/user/favorites`
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
- **Endpoint1:** `http://localhost:8080/api/v1/user/favorites?userId=1`
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
#### **3. Xóa khóa học khỏi danh sách yêu thích**
- **Method:** `DELETE`
- **Endpoint1:** `http://localhost:8080/api/v1/user/favorites/5`
- **Expected Response:**
  ```text
  Favorite removed successfully
  ```

## **6️⃣ Cách gọi API**
Endpoint1
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
- Endpoint1
  ```
  GET http://localhost:8080/api/v1/reviews/course/1
  ```

- **Lấy review theo user**
- Endpoint1
  ```
  GET http://localhost:8080/api/v1/reviews/user/2
  ```

- **Xóa review**
- Endpoint1
  ```
  DELETE http://localhost:8080/api/v1/reviews/3
  ```
## **I. Test API Assignment**
### **1. Lấy danh sách bài tập của khóa học**
**Request:**
```
Endpoint1 GET http://localhost:8080/api/v1/courses/1/assignments
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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
**Request:** Endpoint1
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

# **5. Thiết kế Test Cases (Postman)**
### **1. Lấy danh sách người dùng**
- **Method:** `GET` Endpoint1
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
- **Method:** `PUT` Endpoint1
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
- **Method:** `POST` Endpoint1
- **URL:** `/api/v1/admin/users/1/role/2`
- **Expected Response:**
  ```json
  {
    "message": "Role assigned successfully."
  }
  ```

---

### **4. Xóa quyền của người dùng**
- **Method:** `DELETE` Endpoint1
- **URL:** `/api/v1/admin/users/1/role/2`
- **Expected Response:**
  ```json
  {
    "message": "Role removed successfully."
  }
  ```

## **📌 Hoàn thiện API test cases (Postman)**
### **1️⃣ Lấy danh sách người dùng**
- **Method:** `GET` Endpoint1
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

### **2️⃣ Cập nhật trạng thái người dùng (Khóa/Mở khóa)** Endpoint1
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
