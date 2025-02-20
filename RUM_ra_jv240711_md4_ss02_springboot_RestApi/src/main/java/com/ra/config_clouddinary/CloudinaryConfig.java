package com.ra.config_clouddinary; // Khai báo package chứa class cấu hình Cloudinary

import com.cloudinary.Cloudinary; // Import thư viện Cloudinary để thao tác với API lưu trữ ảnh
import com.cloudinary.utils.ObjectUtils; // Import ObjectUtils để tạo cấu hình Cloudinary dưới dạng Map
import org.springframework.beans.factory.annotation.Value; // Import @Value để lấy giá trị từ file cấu hình (application.properties hoặc application.yml)
import org.springframework.boot.context.properties.EnableConfigurationProperties; // Import annotation (không sử dụng trong đoạn code này)
import org.springframework.context.annotation.Bean; // Import @Bean để khai báo một Bean Spring
import org.springframework.context.annotation.Configuration; // Import @Configuration để đánh dấu class này là một cấu hình Spring
import org.springframework.context.annotation.Import; // Import annotation (không sử dụng trong đoạn code này)
import org.springframework.context.annotation.PropertySource; // Import annotation (không sử dụng trong đoạn code này)

@Configuration // Đánh dấu class này là một class cấu hình (Configuration Class) để Spring Boot tự động quét và quản lý
public class CloudinaryConfig {

    @Value("${cloudinary.cloud_name}") // Lấy giá trị "cloudinary.cloud_name" từ file application.properties
    private String cloudName;

    @Value("${cloudinary.api_key}") // Lấy giá trị "cloudinary.api_key" từ file application.properties
    private String apiKey;

    @Value("${cloudinary.api_secret}") // Lấy giá trị "cloudinary.api_secret" từ file application.properties
    private String apiSecret;

    @Bean // Đánh dấu phương thức này trả về một Bean để Spring Boot quản lý
    public Cloudinary cloudinary() {
        // Khởi tạo một đối tượng Cloudinary bằng cách truyền vào một Map chứa các thông tin cấu hình
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,  // Cấu hình Cloud Name
                "api_key", apiKey,        // Cấu hình API Key
                "api_secret", apiSecret   // Cấu hình API Secret
        ));
    }
}
