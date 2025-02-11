Dưới đây là hướng dẫn chi tiết cách test API upload file với các phương pháp khác nhau:

### 1. **Test bằng Postman**
**Bước 1:** Tạo request mới
- Method: **POST**
- URL: `http://localhost:8080/api/v1/upload`

**Bước 2:** Cấu hình Body
1. Chọn tab **Body**
2. Chọn **form-data**
3. Thêm 2 key:
    - Key 1:
        - Key: `file` (phải đúng tên param)
        - Type: **File**
        - Value: Chọn file từ máy
    - Key 2 (optional):
        - Key: `folder`
        - Type: **Text**
        - Value: Nhập tên folder (nếu muốn)

**Bước 3:** Send request và kiểm tra kết quả
- Thành công: Status 201 với URL file
- Lỗi: Sẽ trả về các status tương ứng (400, 413, 415...)

![Postman example](https://i.imgur.com/3v6XZ0T.png)

### 2. **Test bằng cURL**
```bash
curl -X POST \
  http://localhost:8080/api/v1/upload \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/your/file.jpg" \
  -F "folder=test_folder"
```

### 3. **Test bằng Swagger/OpenAPI**
Thêm annotation vào Controller:
```java
@Operation(summary = "Upload file")
@PostMapping("")
public ResponseEntity<String> upload(
    @Parameter(description = "File to upload") 
    @RequestParam("file") MultipartFile file,
    
    @Parameter(description = "Target folder") 
    @RequestParam(value = "folder", required = false) String folder
) {
    // ...
}
```

Truy cập Swagger UI tại: `http://localhost:8080/swagger-ui.html`

### 4. **Test bằng Unit Test**
```java
@SpringBootTest
@AutoConfigureMockMvc
class UploadFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUploadSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.jpg", 
                "image/jpeg", 
                "test content".getBytes()
        );

        mockMvc.perform(multipart("/api/v1/upload")
                .file(file)
                .param("folder", "unit_test"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("res.cloudinary.com")));
    }

    @Test
    void testUploadWithoutFile() throws Exception {
        mockMvc.perform(multipart("/api/v1/upload"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("File is empty")));
    }
}
```

### 5. **Test trực tiếp qua trình duyệt**
Tạo file HTML:
```html
<!DOCTYPE html>
<html>
<body>
    <form action="http://localhost:8080/api/v1/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="text" name="folder" placeholder="Folder name">
        <button type="submit">Upload</button>
    </form>
</body>
</html>
```

### **Các test case cần kiểm tra**
1. **Thành công:**
    - Upload file ảnh <2MB
    - Upload file PDF vào folder chỉ định
    - Upload không chỉ định folder

2. **Lỗi:**
    - Không chọn file → 400 Bad Request
    - File >10MB → 413 Payload Too Large
    - File type không hợp lệ (ví dụ .exe) → 415 Unsupported Media Type
    - File có tên đặc biệt (ví dụ: `test@123$.png`)

### **Kiểm tra Cloudinary**
- Đăng nhập Cloudinary Dashboard
- Kiểm tra trong section **Media Library**
- Verify:
    - File xuất hiện đúng folder
    - Public ID đúng định dạng
    - Resource type (image/video/raw)

### **Tips khi test:**
1. Dùng file test nhỏ (1-2KB)
2. Test với các loại file khác nhau (jpg, png, pdf, mp4)
3. Kiểm tra response header và body
4. Xem log server để debug chi tiết
5. Test đồng thời nhiều request để kiểm tra khả năng xử lý concurrent upload

Hãy bắt đầu với test case đơn giản nhất (upload file ảnh nhỏ không folder) trước khi test các trường hợp phức tạp hơn!