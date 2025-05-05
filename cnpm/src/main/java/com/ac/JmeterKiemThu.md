

## **1. Thông tin kiểm thử**

### **API được kiểm thử**
- **URL**: `POST http://localhost:8080/api/v1/auth/login`
- **Request Body**:
  ```json
  {
    "email": "student${__threadNum}@example.com",
    "password": "123456"
  }
  ```

### **Cấu hình kiểm thử**
- **Load Testing** (LOAD TEST - LOGIN):
    - Số người dùng (Threads): 100
    - Ramp-Up Period: 10 giây
    - Loop Count: 5 (Tổng số yêu cầu: 100 x 5 = 500)
- **Stress Testing** (STRESS TEST - LOGIN):
    - Số người dùng (Threads): 500
    - Ramp-Up Period: 50 giây
    - Loop Count: 5 (Tổng số yêu cầu: 500 x 5 = 2500)

### **Kết quả kiểm thử (từ bảng Aggregate Report)**

#### **Load Testing (100 người dùng)**
| Label  | # Samples | Average (ms) | Median (ms) | 90% Line (ms) | 95% Line (ms) | 99% Line (ms) | Min (ms) | Max (ms) | Error % | Throughput (req/sec) | Received KB/sec | Sent KB/sec |
|--------|-----------|--------------|-------------|---------------|---------------|---------------|----------|----------|---------|----------------------|-----------------|-------------|
| Login  | 500       | 4            | 4           | 6             | 6             | 8             | 2        | 14       | 1.00%   | 50.4                 | 27.62           | 12.51       |

#### **Stress Testing (500 người dùng)**
| Label  | # Samples | Average (ms) | Median (ms) | 90% Line (ms) | 95% Line (ms) | 99% Line (ms) | Min (ms) | Max (ms) | Error % | Throughput (req/sec) | Received KB/sec | Sent KB/sec |
|--------|-----------|--------------|-------------|---------------|---------------|---------------|----------|----------|---------|----------------------|-----------------|-------------|
| Login  | 2500      | 4            | 4           | 6             | 6             | 7             | 2        | 29       | 2.60%   | 50.1                 | 27.26           | 12.31       |

---

## **2. Phân tích kết quả và ý nghĩa**

### **2.1. Load Testing (100 người dùng)**

#### **Kết quả chi tiết**
- **# Samples**: 500
    - **Ý nghĩa**: Tổng số yêu cầu được gửi (100 thread x 5 loop). Điều này khớp với cấu hình kiểm thử.
- **Average Response Time**: 4ms
    - **Ý nghĩa**: Thời gian trung bình để xử lý một yêu cầu là 4ms, rất nhanh và lý tưởng cho API đăng nhập. Hệ thống hoạt động hiệu quả với 100 người dùng.
- **Median Response Time**: 4ms
    - **Ý nghĩa**: 50% yêu cầu có thời gian phản hồi dưới 4ms, bằng với Average. Điều này cho thấy phân bố thời gian phản hồi rất đồng đều.
- **90% Line**: 6ms
    - **Ý nghĩa**: 90% yêu cầu được xử lý trong vòng 6ms hoặc nhanh hơn. Chỉ số này rất tốt, cho thấy phần lớn yêu cầu có hiệu năng ổn định.
- **95% Line**: 6ms
    - **Ý nghĩa**: 95% yêu cầu được xử lý trong vòng 6ms, cho thấy hệ thống duy trì hiệu năng tốt cho hầu hết các yêu cầu.
- **99% Line**: 8ms
    - **Ý nghĩa**: 99% yêu cầu được xử lý trong vòng 8ms. Chỉ 1% yêu cầu có thời gian phản hồi cao hơn, cho thấy hệ thống rất ổn định.
- **Min/Max Response Time**: 2ms / 14ms
    - **Ý nghĩa**: Thời gian ngắn nhất là 2ms (hiệu năng tối ưu), thời gian dài nhất là 14ms (vẫn rất nhanh). Khoảng cách giữa Min và Max nhỏ, cho thấy hệ thống không có dao động lớn.
- **Error %**: 1.00% (5/500 yêu cầu thất bại)
    - **Ý nghĩa**: Tỷ lệ lỗi thấp, chỉ 1% yêu cầu thất bại. Nguyên nhân có thể là:
        - Một số email (`student${__threadNum}@example.com`) không tồn tại trong database.
        - Kết nối database tạm thời bị gián đoạn.
- **Throughput**: 50.4 yêu cầu/giây
    - **Ý nghĩa**: Hệ thống xử lý được 50.4 yêu cầu mỗi giây, rất tốt cho 100 người dùng. Điều này cho thấy hệ thống có khả năng xử lý tải ổn định.
- **Received KB/sec**: 27.62 KB/sec
    - **Ý nghĩa**: Lượng dữ liệu nhận về mỗi giây (phản hồi từ server). Con số này phù hợp với API đăng nhập (chỉ trả về token).
- **Sent KB/sec**: 12.51 KB/sec
    - **Ý nghĩa**: Lượng dữ liệu gửi đi mỗi giây (yêu cầu từ client). Giá trị này nhỏ, phù hợp với kích thước request body.

#### **Kết luận Load Testing**
- Hệ thống hoạt động rất tốt với 100 người dùng đồng thời. Response Time trung bình (4ms) và tối đa (14ms) đều rất nhanh, Throughput cao (50.4 req/sec), và Error Rate thấp (1.0%). Hệ thống ổn định, đáp ứng tốt tải bình thường.

---

### **2.2. Stress Testing (500 người dùng)**

#### **Kết quả chi tiết**
- **# Samples**: 2500
    - **Ý nghĩa**: Tổng số yêu cầu được gửi (500 thread x 5 loop). Điều này khớp với cấu hình kiểm thử.
- **Average Response Time**: 4ms
    - **Ý nghĩa**: Thời gian trung bình để xử lý một yêu cầu là 4ms, bằng với Load Testing. Điều này khá bất ngờ, vì thông thường Response Time sẽ tăng khi tải tăng. Có thể hệ thống đã được tối ưu hóa hoặc có caching hiệu quả.
- **Median Response Time**: 4ms
    - **Ý nghĩa**: 50% yêu cầu có thời gian phản hồi dưới 4ms, bằng với Average. Phân bố thời gian phản hồi vẫn đồng đều.
- **90% Line**: 6ms
    - **Ý nghĩa**: 90% yêu cầu được xử lý trong vòng 6ms, bằng với Load Testing. Hệ thống vẫn duy trì hiệu năng tốt cho phần lớn yêu cầu.
- **95% Line**: 6ms
    - **Ý nghĩa**: 95% yêu cầu được xử lý trong vòng 6ms, bằng với Load Testing. Hiệu năng ổn định.
- **99% Line**: 7ms
    - **Ý nghĩa**: 99% yêu cầu được xử lý trong vòng 7ms, thấp hơn Load Testing (8ms). Điều này cho thấy phần lớn yêu cầu vẫn nhanh, nhưng một số yêu cầu chậm hơn làm tăng Max Response Time.
- **Min/Max Response Time**: 2ms / 29ms
    - **Ý nghĩa**: Thời gian ngắn nhất là 2ms (bằng Load Testing), nhưng thời gian dài nhất tăng lên 29ms (gấp đôi Load Testing). Điều này cho thấy hệ thống bắt đầu gặp khó khăn khi tải tăng cao.
- **Error %**: 2.60% (65/2500 yêu cầu thất bại)
    - **Ý nghĩa**: Tỷ lệ lỗi tăng lên 2.60%, gấp hơn 2 lần so với Load Testing. Nguyên nhân có thể là:
        - Hết kết nối database (connection pool) khi xử lý 500 người dùng đồng thời.
        - Timeout hoặc server bị quá tải (CPU, RAM).
        - Một số email không tồn tại trong database.
- **Throughput**: 50.1 yêu cầu/giây
    - **Ý nghĩa**: Throughput giảm nhẹ (50.1 req/sec so với 50.4 req/sec ở Load Testing). Điều này cho thấy hệ thống đã đạt giới hạn xử lý, không thể tăng Throughput dù tải tăng.
- **Received KB/sec**: 27.26 KB/sec
    - **Ý nghĩa**: Lượng dữ liệu nhận về giảm nhẹ so với Load Testing (27.62 KB/sec), do nhiều yêu cầu thất bại hơn.
- **Sent KB/sec**: 12.31 KB/sec
    - **Ý nghĩa**: Lượng dữ liệu gửi đi cũng giảm nhẹ, phù hợp với số lượng yêu cầu thất bại.

#### **Kết luận Stress Testing**
- Hệ thống vẫn duy trì Average Response Time tốt (4ms), nhưng Max Response Time tăng lên 29ms, cho thấy có một số yêu cầu bị chậm khi tải cao. Error Rate tăng lên 2.60%, và Throughput không tăng (50.1 req/sec), cho thấy hệ thống đã đạt giới hạn xử lý.

---

## **3. Lập bảng so sánh Load Testing và Stress Testing**

| **Chỉ số**              | **Load Testing (100 users)** | **Stress Testing (500 users)** | **Nhận xét**                                                                 |
|-------------------------|------------------------------|--------------------------------|------------------------------------------------------------------------------|
| **# Samples**           | 500                          | 2500                           | Stress Testing có số yêu cầu gấp 5 lần, phù hợp với cấu hình.               |
| **Average Response Time** | 4ms                         | 4ms                            | Average không đổi, cho thấy hệ thống duy trì hiệu năng tốt dù tải tăng.     |
| **Max Response Time**   | 14ms                         | 29ms                           | Max Response Time tăng gấp đôi, cho thấy hệ thống bị nghẽn khi tải cao.     |
| **90% Line**            | 6ms                          | 6ms                            | 90% yêu cầu vẫn nhanh, hệ thống duy trì hiệu năng tốt cho phần lớn yêu cầu.  |
| **95% Line**            | 6ms                          | 6ms                            | Tương tự 90% Line, hiệu năng ổn định.                                       |
| **99% Line**            | 8ms                          | 7ms                            | 99% Line giảm nhẹ, nhưng Max tăng, cho thấy một số yêu cầu chậm nghiêm trọng.|
| **Error %**             | 1.00%                        | 2.60%                          | Tỷ lệ lỗi tăng gấp hơn 2 lần, hệ thống không xử lý tốt khi tải tăng.        |
| **Throughput**          | 50.4 req/sec                 | 50.1 req/sec                   | Throughput không tăng, hệ thống đã đạt giới hạn xử lý.                      |
| **Received KB/sec**     | 27.62 KB/sec                 | 27.26 KB/sec                   | Giảm nhẹ do tỷ lệ lỗi cao hơn.                                              |
| **Sent KB/sec**         | 12.51 KB/sec                 | 12.31 KB/sec                   | Giảm nhẹ, phù hợp với số lượng yêu cầu thất bại.                            |

---

## **4. Hướng giải pháp nâng cấp**

### **Vấn đề cần giải quyết**
1. **Max Response Time tăng (29ms trong Stress Testing)**:
    - Một số yêu cầu bị chậm nghiêm trọng khi tải tăng, ảnh hưởng đến trải nghiệm người dùng.
2. **Error Rate cao (2.60% trong Stress Testing)**:
    - Tỷ lệ lỗi vượt ngưỡng mong đợi (≤2%), cho thấy hệ thống không ổn định khi tải cao.
3. **Throughput không tăng (50.1 req/sec)**:
    - Hệ thống đã đạt giới hạn xử lý, không thể xử lý thêm yêu cầu khi tải tăng.

### **Hướng giải pháp nâng cấp**

#### **1. Tối ưu hóa database**
- **Thêm index**:
    - Tối ưu truy vấn tìm kiếm user bằng cách thêm index cho cột `email`:
      ```sql
      CREATE INDEX idx_email ON users (email);
      ```
- **Tăng connection pool**:
    - Tăng số lượng kết nối database để giảm thời gian chờ:
      ```properties
      spring.datasource.hikari.minimumIdle=5
      spring.datasource.hikari.maximumPoolSize=100
      ```
- **Sử dụng caching**:
    - Dùng Redis để cache thông tin user, giảm truy vấn database:
      ```java
      @Cacheable(value = "users", key = "#email")
      public User findByEmail(String email) {
          return userRepository.findByEmail(email);
      }
      ```

#### **2. Giảm Error Rate**
- **Kiểm tra dữ liệu**:
    - Đảm bảo tất cả email (`student1@example.com` đến `student500@example.com`) tồn tại trong database. Nếu không, thêm logic xử lý lỗi mềm:
      ```java
      if (!userRepository.existsByEmail(email)) {
          return ResponseEntity.status(400).body("Email not found");
      }
      ```
- **Tăng timeout**:
    - Đặt timeout cao hơn để tránh lỗi timeout:
      ```properties
      spring.datasource.hikari.connectionTimeout=30000
      ```
- **Cơ chế retry**:
    - Thêm cơ chế retry cho các yêu cầu thất bại do timeout:
      ```java
      @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
      public User findByEmail(String email) {
          return userRepository.findByEmail(email);
      }
      ```

#### **3. Tăng Throughput**
- **Tăng thread pool**:
    - Tăng số lượng thread xử lý của server (Tomcat/Spring Boot):
      ```properties
      server.tomcat.threads.max=500
      ```
- **Dùng load balancer**:
    - Sử dụng Nginx hoặc AWS Load Balancer để phân phối tải, tăng khả năng xử lý đồng thời:
      ```nginx
      upstream backend {
          server localhost:8080;
      }
      server {
          location / {
              proxy_pass http://backend;
          }
      }
      ```
- **Tăng tài nguyên server**:
    - Kiểm tra tài nguyên (CPU, RAM). Nếu CPU usage cao (>80%), cần tăng tài nguyên hoặc dùng server mạnh hơn.

#### **4. Tối ưu logic đăng nhập**
- **Mã hóa mật khẩu**:
    - Nếu mật khẩu được mã hóa (như BCrypt), hãy tối ưu thuật toán để giảm thời gian xử lý:
      ```java
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); // Giảm số vòng lặp để tăng tốc
      ```
- **Kiểm tra trùng lặp**:
    - Đảm bảo logic đăng nhập không gây lỗi khi có nhiều người dùng cùng email (dù đã dùng `${__threadNum}`).

#### **5. Giám sát và phân tích**
- **Sử dụng monitoring tools**:
    - Dùng các công cụ như Prometheus và Grafana để giám sát hiệu năng server (CPU, RAM, database connections) trong thời gian thực.
- **Log chi tiết**:
    - Thêm log để xác định nguyên nhân lỗi (timeout, hết kết nối database):
      ```java
      log.error("Failed to login for email: {}", email, e);
      ```

---

## **5. Kết luận**
- **Load Testing**: Hệ thống hoạt động rất tốt với 100 người dùng, với Response Time trung bình (4ms), Max Response Time (14ms), và Throughput cao (50.4 req/sec). Error Rate (1.0%) nằm trong ngưỡng chấp nhận được.
- **Stress Testing**: Hệ thống vẫn duy trì Average Response Time tốt (4ms), nhưng Max Response Time tăng lên 29ms, Error Rate tăng lên 2.60%, và Throughput không tăng (50.1 req/sec). Điều này cho thấy hệ thống đã đạt giới hạn xử lý với 500 người dùng.
- **Hành động tiếp theo**: Thực hiện các giải pháp tối ưu (tăng connection pool, thêm caching, dùng load balancer) và chạy lại Stress Testing để kiểm tra cải thiện. Nếu cần, tăng số người dùng (ví dụ: 1000 người dùng) để tìm điểm giới hạn thực sự của hệ thống.
