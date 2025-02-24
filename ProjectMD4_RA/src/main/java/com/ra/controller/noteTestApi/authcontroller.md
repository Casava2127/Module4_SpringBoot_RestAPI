Dưới đây là khung API mẫu để test chức năng đăng ký (sign-up) và đăng nhập (sign-in) của AuthController. Các endpoint này sử dụng DTO như đã định nghĩa và bạn có thể test chúng bằng Postman hoặc cURL.

---

## **1. Đăng ký tài khoản (Sign-Up)**

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
  "password": "password123"
  // Nếu bạn dùng phiên bản đầy đủ có các trường khác, có thể bổ sung: "phone", "address", "avatar"
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
        "password": "password123"
      }'
```

**Mô tả:**  
Endpoint này nhận dữ liệu đăng ký từ client. Nếu đăng ký thành công, API trả về thông báo "User registered successfully!" và HTTP status 201 (Created).

---

## **2. Đăng nhập (Sign-In)**

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
Endpoint này nhận dữ liệu đăng nhập. Nếu thông tin hợp lệ, API sẽ trả về một đối tượng JSON chứa token (ví dụ token giả lập) và kiểu token (thường là "Bearer"), với HTTP status 200 (OK).

Ví dụ phản hồi thành công:
```json
{
  "accessToken": "fake-jwt-token-1740395288274",
  "tokenType": "Bearer"
}
```

---

## **Hướng dẫn test với Postman:**

1. **Đăng ký (Sign-Up):**
    - Tạo request mới, chọn phương thức **POST** và URL:  
      `http://localhost:8080/api/v1/auth/sign-up`
    - Trong tab **Headers**, thêm:  
      `Content-Type: application/json`
    - Trong tab **Body** chọn **raw** và nhập JSON payload mẫu như trên.
    - Nhấn **Send** và kiểm tra phản hồi.

2. **Đăng nhập (Sign-In):**
    - Tạo request mới, chọn phương thức **POST** và URL:  
      `http://localhost:8080/api/v1/auth/sign-in`
    - Thiết lập header và body tương tự như ở Sign-Up, nhưng với payload đăng nhập.
    - Nhấn **Send** và kiểm tra token trả về.

Với khung API mẫu này, bạn có thể dễ dàng kiểm tra chức năng xác thực người dùng qua AuthController của dự án.