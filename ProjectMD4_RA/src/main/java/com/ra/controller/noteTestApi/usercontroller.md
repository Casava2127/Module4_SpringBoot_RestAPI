Dưới đây là khung API mẫu dựa trên controller của bạn, kèm theo ví dụ payload và cURL để test:

---

## **1. Lấy danh sách tất cả người dùng**
**Endpoint:**  
`GET http://localhost:8080/api/v1/users`

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/v1/users
```

**Mô tả:**  
Trả về danh sách các user dưới dạng JSON. Ví dụ kết quả:
```json
[
  {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullname": "John Doe",
    "status": true,
    "avatar": "https://example.com/avatar1.jpg",
    "phone": "0123456789",
    "address": "123 Main St"
  },
  {
    "userId": 2,
    "username": "jane_doe",
    "email": "jane@example.com",
    "fullname": "Jane Doe",
    "status": true,
    "avatar": "https://example.com/avatar2.jpg",
    "phone": "0987654321",
    "address": "456 Elm St"
  }
]
```

---

## **2. Lấy thông tin người dùng theo ID**
**Endpoint:**  
`GET http://localhost:8080/api/v1/users/{id}`  
Ví dụ: `GET http://localhost:8080/api/v1/users/1`

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/v1/users/1
```

**Mô tả:**  
Trả về thông tin chi tiết của một user dưới dạng JSON, hoặc trả về 404 nếu không tìm thấy.

---

## **3. Tạo mới người dùng**
**Endpoint:**  
`POST http://localhost:8080/api/v1/users/create`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON mẫu):**
```json
{
  "username": "newuser123",
  "email": "newuser123@example.com",
  "fullname": "New User",
  "password": "pass123",
  "phone": "0123456789",
  "address": "789 New Street",
  "avatar": "https://example.com/avatar-new.jpg"
}
```

**Ví dụ cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/users/create \
  -H "Content-Type: application/json" \
  -d '{
        "username": "newuser123",
        "email": "newuser123@example.com",
        "fullname": "New User",
        "password": "pass123",
        "phone": "0123456789",
        "address": "789 New Street",
        "avatar": "https://example.com/avatar-new.jpg"
      }'
```

**Mô tả:**  
Endpoint này sẽ tạo mới một user. Nếu thành công, trả về thông tin user vừa tạo và HTTP status 201 (Created).

---

## **4. Cập nhật thông tin người dùng**
**Endpoint:**  
`PUT http://localhost:8080/api/v1/users/update/{id}`  
Ví dụ: `PUT http://localhost:8080/api/v1/users/update/1`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON mẫu):**
```json
{
  "username": "updateduser",
  "email": "updateduser@example.com",
  "fullname": "Updated User",
  "password": "newpass123",
  "phone": "0987654321",
  "address": "987 Updated Street",
  "avatar": "https://example.com/avatar-updated.jpg"
}
```

**Ví dụ cURL:**
```bash
curl -X PUT http://localhost:8080/api/v1/users/update/1 \
  -H "Content-Type: application/json" \
  -d '{
        "username": "updateduser",
        "email": "updateduser@example.com",
        "fullname": "Updated User",
        "password": "newpass123",
        "phone": "0987654321",
        "address": "987 Updated Street",
        "avatar": "https://example.com/avatar-updated.jpg"
      }'
```

**Mô tả:**  
Endpoint này cập nhật thông tin của user có ID tương ứng. Nếu user không tồn tại, trả về 404.

---

## **5. Xóa người dùng**
**Endpoint:**  
`DELETE http://localhost:8080/api/v1/users/delete/{id}`  
Ví dụ: `DELETE http://localhost:8080/api/v1/users/delete/1`

**Ví dụ cURL:**
```bash
curl -X DELETE http://localhost:8080/api/v1/users/delete/1
```

**Mô tả:**  
Endpoint này xóa user có ID tương ứng. Nếu thành công, trả về HTTP status 204 (No Content); nếu không tìm thấy, trả về 404.

---

### **Lưu ý khi test trên Postman:**

- Đảm bảo rằng URL endpoint và method đúng với mapping trong controller.
- Sử dụng Header `Content-Type: application/json` với body dạng JSON.
- Kiểm tra lại dữ liệu trong cơ sở dữ liệu để xác nhận các thao tác CRUD.

Với khung API mẫu trên, bạn có thể dễ dàng test các chức năng của `UserController` trên Postman.