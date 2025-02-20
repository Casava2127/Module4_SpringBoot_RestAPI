package com.ra.controller; // Khai báo package chứa controller này

import com.ra.service.UploadService; // Import UploadService để xử lý upload file
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired để inject service vào controller
import org.springframework.http.HttpStatus; // Import HttpStatus để sử dụng mã phản hồi HTTP
import org.springframework.http.ResponseEntity; // Import ResponseEntity để trả về phản hồi HTTP
import org.springframework.web.bind.annotation.*; // Import các annotation của Spring Boot
import org.springframework.web.multipart.MultipartFile; // Import MultipartFile để nhận file upload từ Client

/**
 * Controller để xử lý API upload file.
 */
@RestController // Đánh dấu đây là REST API Controller trong Spring Boot
@RequestMapping("/api/v1/upload") // Định nghĩa đường dẫn chung cho tất cả API trong Controller này
public class UploadFileController {

    @Autowired // Inject UploadService vào Controller để sử dụng
    private UploadService uploadService;

    /**
     * API upload file lên Cloudinary.
     *
     * @param file   File được upload từ client (tham số bắt buộc)
     * @param folder Thư mục lưu file trên Cloudinary (tham số tùy chọn)
     * @return Đường dẫn URL của file sau khi upload
     */
    @PostMapping("") // Xử lý HTTP POST tại endpoint "/api/v1/upload"
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file, // Nhận file từ client với key "file" trong request
            @RequestParam(value = "folder", required = false) String folder // Nhận tên thư mục (tùy chọn), nếu không có sẽ để mặc định
    ) {
        try {
            // Kiểm tra nếu file rỗng
            if (file.isEmpty()) {
                return new ResponseEntity<>("File is empty!", HttpStatus.BAD_REQUEST); // Trả về lỗi 400 BAD REQUEST
            }

            // Kiểm tra định dạng file hợp lệ
            String contentType = file.getContentType(); // Lấy loại MIME của file
            if (!isValidFileType(contentType)) { // Nếu không hợp lệ
                return new ResponseEntity<>("Invalid file type!", HttpStatus.UNSUPPORTED_MEDIA_TYPE); // Trả về lỗi 415 UNSUPPORTED MEDIA TYPE
            }

            // Upload file lên Cloudinary thông qua service
            String fileUrl = uploadService.uploadFile(file, folder); // Gọi service để upload file

            // Trả về URL file nếu upload thành công với mã 201 CREATED
            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);

        } catch (Exception e) { // Bắt lỗi nếu có lỗi trong quá trình upload
            // Trả về thông báo lỗi với mã 500 INTERNAL SERVER ERROR
            return new ResponseEntity<>("Upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Kiểm tra định dạng file có hợp lệ hay không.
     *
     * @param contentType Loại MIME của file
     * @return true nếu file hợp lệ, false nếu không hợp lệ
     */
    private boolean isValidFileType(String contentType) {
        return contentType != null && ( // Đảm bảo contentType không null trước khi kiểm tra
                contentType.startsWith("image/") || // Cho phép file ảnh (jpg, png, etc.)
                        contentType.startsWith("video/") || // Cho phép file video (mp4, avi, etc.)
                        contentType.equals("application/pdf") // Cho phép file PDF
        );
    }
}
