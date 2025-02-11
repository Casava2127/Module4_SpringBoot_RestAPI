package com.ra.service; // Khai báo package chứa lớp này

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
    public String uploadFile(@RequestParam("file") MultipartFile file, String folder) throws IOException {
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
