package com.ra.service; // Khai báo package chứa lớp này

import com.cloudinary.Cloudinary; // Import Cloudinary để upload file lên Cloudinary
import com.cloudinary.utils.ObjectUtils; // Import ObjectUtils để tạo tham số upload
import lombok.RequiredArgsConstructor; // Import annotation để tự động tạo constructor
import org.springframework.stereotype.Service; // Đánh dấu đây là một service trong Spring Boot
import org.springframework.web.multipart.MultipartFile; // Import MultipartFile để nhận file upload từ Client

import java.io.IOException; // Import IOException để xử lý lỗi trong quá trình upload
import java.util.Map; // Import Map để lưu các tham số upload
import java.util.Optional; // Import Optional để xử lý giá trị null một cách an toàn

/**
 * Lớp UploadService dùng để xử lý việc upload file lên Cloudinary.
 */
@Service // Đánh dấu đây là một Service trong Spring Boot
@RequiredArgsConstructor // Tự động tạo constructor cho các thuộc tính final
public class UploadService {
    private final Cloudinary cloudinary; // Inject Cloudinary vào service (được khởi tạo từ cấu hình Spring)

    /**
     * Phương thức upload file lên Cloudinary.
     *
     * @param file   File được upload từ client
     * @param folder Tên thư mục lưu trữ trên Cloudinary
     * @return URL của file sau khi upload
     * @throws IOException Nếu có lỗi trong quá trình upload
     */
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        // Kiểm tra kích thước file, nếu lớn hơn 10MB thì báo lỗi
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB
            throw new IOException("File size exceeds limit"); // Ném ra IOException nếu file quá lớn
        }

        // Tạo các tham số upload cho Cloudinary
        Map uploadParams = ObjectUtils.asMap(
                "public_id", extractFileName(file.getOriginalFilename()), // Đặt tên file (bỏ phần mở rộng)
                "folder", Optional.ofNullable(folder).orElse("default_folder"), // Nếu không có folder thì đặt mặc định
                "resource_type", "auto" // Cloudinary sẽ tự động nhận diện loại file (hình ảnh, video, tài liệu...)
        );

        // Upload file lên Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        // Lấy đường dẫn URL bảo mật (HTTPS) của file và trả về
        return uploadResult.get("secure_url").toString();
    }

    /**
     * Phương thức lấy tên file mà không bao gồm phần mở rộng.
     *
     * @param originalName Tên file gốc có phần mở rộng
     * @return Tên file đã bỏ phần mở rộng
     */
    private String extractFileName(String originalName) {
        if (originalName == null) return "unnamed_file"; // Nếu file không có tên, đặt tên mặc định

        int lastDotIndex = originalName.lastIndexOf("."); // Tìm vị trí dấu chấm cuối cùng trong tên file
        return (lastDotIndex == -1) // Nếu không có dấu chấm (tức là không có phần mở rộng)
                ? originalName // Trả về nguyên tên file
                : originalName.substring(0, lastDotIndex); // Cắt chuỗi trước dấu chấm để lấy tên file
    }
}
