## 1. **Authentication – Xác thực người dùng**

- **Đăng ký tài khoản**  
  **Endpoint:** `POST /api.myservice.com/v1/auth/sign-up`  
  **Mô tả:** Đăng ký tài khoản mới cho người dùng (payload gồm: username, email, fullname, password, …).

- **Đăng nhập**  
  **Endpoint:** `POST /api.myservice.com/v1/auth/sign-in`  
  **Mô tả:** Xác thực đăng nhập bằng username và password.

## 2. **Thông tin người dùng và quản lý tài khoản**

- **Lấy thông tin tài khoản**  
  **Endpoint:** `GET /api.myservice.com/v1/user/account`  
  **Mô tả:** Truy xuất thông tin chi tiết tài khoản người dùng.

- **Cập nhật thông tin người dùng**  
  **Endpoint:** `PUT /api.myservice.com/v1/user/account`  
  **Mô tả:** Cập nhật thông tin cá nhân của người dùng.

- **Đổi mật khẩu**  
  **Endpoint:** `PUT /api.myservice.com/v1/user/account/change-password`  
  **Mô tả:** Thay đổi mật khẩu (payload: oldPass, newPass, confirmNewPass).
  **Xóa tài khoản người dùng (Delete User)
  DELETE http://localhost:8080/api/v1/users/delete/{id}

- **Thông báo**
    - Lấy danh sách thông báo:  
      **Endpoint:** `GET http://localhost:8080/api/v1/user/notifications`  
      **Mô tả:** Truy xuất danh sách thông báo gửi tới người dùng.

    - Đánh dấu thông báo đã đọc:  
      **Endpoint:** `PUT http://localhost:8080/api/v1/user/notifications/{notificationId}/read`  
      **Mô tả:** Cập nhật trạng thái thông báo là đã đọc.

---

## 3. **Quản lý khóa học và danh mục**

- **Danh mục khóa học**
    - Lấy danh sách danh mục:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/categories`  
      **Mô tả:** Lấy ra danh sách các danh mục khóa học.

    - Lấy khóa học theo danh mục:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/categories/{categoryId}`  
      **Mô tả:** Lấy danh sách khóa học theo danh mục cụ thể.

- **Khóa học**
    - Lấy danh sách khóa học (có phân trang và sắp xếp):  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses`  
      **Mô tả:** Truy xuất danh sách tất cả các khóa học.

    - Tìm kiếm khóa học theo tên hoặc mô tả:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/search`  
      **Mô tả:** Tìm kiếm khóa học theo từ khóa.

    - Lấy danh sách khóa học nổi bật:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/featured`  
      **Mô tả:** Danh sách khóa học được đánh giá nổi bật.

    - Lấy danh sách khóa học mới:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/new`  
      **Mô tả:** Danh sách khóa học được cập nhật gần đây.

    - Lấy danh sách khóa học được ưa chuộng (ví dụ: có nhiều lượt đăng ký):  
      **Endpoint:** GET http://localhost:8080/api/v1/courses/popular  
      **Mô tả:** Danh sách khóa học có lượt đăng ký hoặc đánh giá cao.

    - Chi tiết khóa học  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/{courseId}`
      **Mô tả:** Lấy ra thông tin chi tiết của 1 khóa học.

- **Bài giảng của khóa học**
    - Lấy danh sách bài giảng của 1 khóa học:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/{courseId}/lessons`  
      **Mô tả:** Truy xuất danh sách bài giảng thuộc một khóa học.

    - Chi tiết bài giảng:  
      **Endpoint:** `GET http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`  
      **Mô tả:** Lấy thông tin chi tiết của 1 bài giảng.

    - (Admin/Instructor) Tạo mới bài giảng:  
      **Endpoint:** `POST http://localhost:8080/api/v1/courses/{courseId}/lessons`  
      **Mô tả:** Thêm bài giảng cho khóa học.

    - (Admin/Instructor) Cập nhật bài giảng:  
      **Endpoint:** `PUT http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`  
      **Mô tả:** Chỉnh sửa nội dung bài giảng.

    - (Admin/Instructor) Xóa bài giảng:  
      **Endpoint:** `DELETE http://localhost:8080/api/v1/courses/{courseId}/lessons/{lessonId}`  
      **Mô tả:** Xóa bài giảng khỏi khóa học.

---

## 4. **Đăng ký khóa học và giỏ hàng**

- **Giỏ khóa học**  
  (Trong trường hợp hệ thống hỗ trợ mua khóa học theo mô hình giỏ hàng)
    - Lấy danh sách khóa học trong giỏ:  
      **Endpoint:** `GET http://localhost:8080/api/v1/user/cart/{userId}`  
      **Mô tả:** Truy xuất danh sách khóa học đang có trong giỏ của người dùng.

    - Thêm khóa học vào giỏ:  
      **Endpoint:** `POST http://localhost:8080/api/v1/user/cart?userId={userId}`  
      **Mô tả:** Thêm một khóa học vào giỏ (payload: courseId, [nếu cần quantity]).

    - Cập nhật thông tin trong giỏ (nếu cần điều chỉnh số lượng, ví dụ đối với gói khóa học có số lượng đăng ký):  
      **Endpoint:** `PUT http://localhost:8080/api/v1/user/cart/{cartId}?userId={userId}&quantity={quantity}`  
      **Mô tả:** Cập nhật số lượng hoặc thông tin của một mục trong giỏ.

    - Xóa 1 mục khỏi giỏ:  
      **Endpoint:** `DELETE http://localhost:8080/api/v1/user/cart/{cartId}?userId={userId}`  
      **Mô tả:** Xóa khóa học khỏi giỏ.

    - Xóa toàn bộ giỏ hàng:  
      **Endpoint:** DELETE http://localhost:8080/api/v1/user/cart/clear?userId={userId}
      **Mô tả:** Xóa sạch các khóa học trong giỏ.

    - Thanh toán giỏ hàng – Đăng ký khóa học:  
      **Endpoint:** POST http://localhost:8080/api/v1/user/cart/checkout?userId={userId}
      **Mô tả:** Tiến hành đăng ký các khóa học có trong giỏ hàng và thực hiện thanh toán.

- **Đăng ký khóa học**

    - Đăng ký khóa học mới (đối với hệ thống không dùng giỏ hàng):  
      **Endpoint:** POST http://localhost:8080/api/v1/user/cart/checkoutEnrollment
      **Mô tả:** Đăng ký tham gia một khóa học (payload: courseId, phương thức thanh toán, …).

    - Lấy chi tiết đơn đăng ký:  
      **Endpoint:** GET http://localhost:8080/api/v1/user/enrollments/{enrollmentId}
      **Mô tả:** Lấy chi tiết đăng ký của 1 khóa học.

    - Hủy đơn đăng ký (trong trường hợp đơn ở trạng thái “chờ xác nhận”):  
      **Endpoint:** PUT http://localhost:8080/api/v1/user/enrollments/{enrollmentId}/cancel
      **Mô tả:** Hủy đăng ký khóa học.

- **Danh sách yêu thích**
    - Thêm khóa học vào danh sách yêu thích:  
      **Endpoint:** `POST http://localhost:8080/api/v1/user/favorites
      **Mô tả:** Lưu khóa học vào danh sách yêu thích (payload: courseId).

    - Lấy danh sách khóa học yêu thích:  
      **Endpoint:** `GET http://localhost:8080/api/v1/user/favorites?userId=1
      **Mô tả:** Truy xuất danh sách các khóa học yêu thích của người dùng.

    - Xóa khóa học khỏi danh sách yêu thích:  
      **Endpoint:** `DELETE http://localhost:8080/api/v1/user/favorites/5
      **Mô tả:** Xóa một khóa học khỏi danh sách yêu thích.

---

## 5. **Đánh giá và phản hồi**

- **Đánh giá khóa học**
    - Lấy danh sách đánh giá của 1 khóa học:  
      **Endpoint:** GET http://localhost:8080/api/v1/reviews/course/1
      **Mô tả:** Truy xuất các đánh giá và nhận xét về khóa học.
      **Lấy review theo user**
      GET http://localhost:8080/api/v1/reviews/user/2
    - Thêm đánh giá cho khóa học:  
      **Endpoint:** POST http://localhost:8080/api/v1/reviews
      **Mô tả:** Người dùng gửi đánh giá (payload: rating, comment).

    - Cập nhật đánh giá:  
      **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/reviews/{reviewId}`  
      **Mô tả:** Chỉnh sửa nội dung đánh giá của người dùng.

    - Xóa đánh giá:  
      **Endpoint:** `DELETE  DELETE http://localhost:8080/api/v1/reviews/3

---

## 6. **Bài tập và nộp bài**

- **Bài tập của khóa học**
    - Lấy danh sách bài tập:  
      **Endpoint:** GET http://localhost:8080/api/v1/courses/1/assignments
      **Mô tả:** Lấy danh sách bài tập được giao trong khóa học.

    - Chi tiết bài tập:  
      **Endpoint:** GET http://localhost:8080/api/v1/courses/1/assignments/10
      **Mô tả:** Lấy thông tin chi tiết của bài tập.

    - (Admin/Instructor) Tạo bài tập mới:  
      **Endpoint:** POST http://localhost:8080/api/v1/courses/1/assignments
      **Mô tả:** Thêm bài tập cho khóa học.

    - (Admin/Instructor) Cập nhật bài tập:  
      **Endpoint:** PUT http://localhost:8080/api/v1/courses/1/assignments/11
      **Mô tả:** Chỉnh sửa bài tập.

    - (Admin/Instructor) Xóa bài tập:  
      **Endpoint:** DELETE http://localhost:8080/api/v1/courses/1/assignments/11
      **Mô tả:** Xóa bài tập khỏi khóa học.

- **Nộp bài tập**
    - Lấy danh sách bài nộp (cho giảng viên kiểm tra):  
      **Endpoint:** GET http://localhost:8080/api/v1/courses/1/assignments/10/submissions
      **Mô tả:** Danh sách bài nộp của học viên cho bài tập.

    - Nộp bài tập của học viên:  
      **Endpoint:** POST http://localhost:8080/api/v1/courses/1/assignments/10/submissions?userId=3
      **Mô tả:** Học viên nộp bài (payload: file_url, …).

    - (Nếu cần) Cập nhật bài nộp:  
      **Endpoint:** PUT http://localhost:8080/api/v1/courses/1/assignments/10/submissions/6?userId=3
      **Mô tả:** Học viên cập nhật bài nộp.

---

## 7. **Diễn đàn thảo luận và bình luận - chưa xong **

- **Diễn đàn thảo luận của khóa học**
    - Lấy danh sách chủ đề thảo luận:  
      **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/discussions`  
      **Mô tả:** Lấy danh sách các chủ đề thảo luận trong khóa học.

    - Tạo chủ đề thảo luận mới:  
      **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/discussions`  
      **Mô tả:** Tạo chủ đề (payload: title, content).

    - Chi tiết chủ đề thảo luận:  
      **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}`  
      **Mô tả:** Xem chi tiết nội dung và các phản hồi của chủ đề.

    - Cập nhật chủ đề (chỉ chủ sở hữu hoặc admin):  
      **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}`  
      **Mô tả:** Cập nhật tiêu đề/nội dung chủ đề.

    - Xóa chủ đề:  
      **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}`  
      **Mô tả:** Xóa chủ đề thảo luận.

- **Bình luận trong diễn đàn**
    - Lấy danh sách bình luận của 1 chủ đề:  
      **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}/comments`  
      **Mô tả:** Truy xuất bình luận của chủ đề.

    - Thêm bình luận:  
      **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}/comments`  
      **Mô tả:** Gửi bình luận (payload: comment).

    - Xóa bình luận:  
      **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/discussions/{discussionId}/comments/{commentId}`  
      **Mô tả:** Xóa bình luận khỏi chủ đề.

---

## 8. **Tin nhắn riêng tư - chưa xong **

- **Quản lý tin nhắn**
    - Lấy danh sách tin nhắn (gửi/nhận) của người dùng:  
      **Endpoint:** `GET /api.myservice.com/v1/user/messages`  
      **Mô tả:** Truy xuất danh sách tin nhắn của người dùng.

    - Gửi tin nhắn riêng:  
      **Endpoint:** `POST /api.myservice.com/v1/user/messages`  
      **Mô tả:** Gửi tin nhắn tới người dùng khác (payload: receiver_id, subject, content).

    - Xem chi tiết tin nhắn:  
      **Endpoint:** `GET /api.myservice.com/v1/user/messages/{messageId}`  
      **Mô tả:** Xem nội dung tin nhắn.

    - Đánh dấu tin nhắn đã đọc:  
      **Endpoint:** `PUT /api.myservice.com/v1/user/messages/{messageId}/read`  
      **Mô tả:** Cập nhật trạng thái tin nhắn là đã đọc.

    - Xóa tin nhắn:  
      **Endpoint:** `DELETE /api.myservice.com/v1/user/messages/{messageId}`  
      **Mô tả:** Xóa tin nhắn khỏi hộp thư.

---

## 9. **Chứng chỉ và học trực tuyến - chưa xong **

- **Chứng chỉ**
    - Lấy danh sách chứng chỉ của người dùng:  
      **Endpoint:** `GET /api.myservice.com/v1/user/certificates`  
      **Mô tả:** Lấy danh sách các chứng chỉ đạt được sau khi hoàn thành khóa học.

    - Chi tiết chứng chỉ:  
      **Endpoint:** `GET /api.myservice.com/v1/user/certificates/{certificateId}`  
      **Mô tả:** Xem thông tin chi tiết chứng chỉ.

- **Lịch học trực tuyến (Live Sessions)**
    - Lấy danh sách buổi học trực tuyến của khóa học:  
      **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/live-sessions`  
      **Mô tả:** Lấy danh sách các buổi học trực tuyến (live session) của khóa học.

    - Chi tiết buổi học:  
      **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/live-sessions/{liveSessionId}`  
      **Mô tả:** Xem thông tin chi tiết của buổi học.

    - (Admin/Instructor) Tạo mới buổi học:  
      **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/live-sessions`  
      **Mô tả:** Tạo buổi học trực tuyến mới.

    - (Admin/Instructor) Cập nhật buổi học:  
      **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/live-sessions/{liveSessionId}`  
      **Mô tả:** Cập nhật thông tin buổi học.

    - (Admin/Instructor) Xóa buổi học:  
      **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/live-sessions/{liveSessionId}`  
      **Mô tả:** Xóa buổi học khỏi lịch học.

---

## 10. **Admin – Quản trị hệ thống**

- **Quản lý người dùng**
    - Lấy danh sách người dùng (phân trang, sắp xếp):  
      **Endpoint:** `GET /api/v1/admin/users?page=0&size=10&sortBy=username&sortDir=asc
      **Mô tả:** Truy xuất danh sách tài khoản người dùng.

    - Cập nhật trạng thái người dùng (khóa/mở khóa):  
      **Endpoint:** `PUT /api/v1/admin/users/{userId}`  
      **Mô tả:** Cập nhật trạng thái hoạt động của người dùng.

    - Phân quyền cho người dùng:  
      **Endpoint:** `POST /api/v1/admin/users/{userId}/role/{roleId}`  
      **Mô tả:** Gán thêm vai trò cho người dùng.

    - Xóa quyền của người dùng:  
      **Endpoint:** `DELETE /api/v1/admin/users/{userId}/role/{roleId}`  
      **Mô tả:** Bỏ vai trò đã gán cho người dùng.

- **Quản lý khóa học & danh mục**
    - Lấy danh sách khóa học (phân trang, sắp xếp):  
      **Endpoint:** `GET /api.myservice.com/v1/admin/courses`  
      **Mô tả:** Truy xuất danh sách tất cả các khóa học.

    - Chi tiết khóa học:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/courses/{courseId}`  
      **Mô tả:** Xem thông tin chi tiết của một khóa học.

    - Tạo mới khóa học:  
      **Endpoint:** `POST /api.myservice.com/v1/admin/courses`  
      **Mô tả:** Thêm mới một khóa học.

    - Cập nhật khóa học:  
      **Endpoint:** `PUT /api.myservice.com/v1/admin/courses/{courseId}`  
      **Mô tả:** Chỉnh sửa thông tin khóa học.

    - Xóa khóa học:  
      **Endpoint:** `DELETE /api.myservice.com/v1/admin/courses/{courseId}`  
      **Mô tả:** Xóa khóa học khỏi hệ thống.

    - Lấy danh sách danh mục:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/categories`  
      **Mô tả:** Truy xuất danh sách danh mục khóa học.

    - Chi tiết danh mục:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/categories/{categoryId}`  
      **Mô tả:** Xem thông tin của 1 danh mục.

    - Tạo mới danh mục:  
      **Endpoint:** `POST /api.myservice.com/v1/admin/categories`  
      **Mô tả:** Thêm danh mục khóa học mới.

    - Cập nhật danh mục:  
      **Endpoint:** `PUT /api.myservice.com/v1/admin/categories/{categoryId}`  
      **Mô tả:** Chỉnh sửa thông tin danh mục.

    - Xóa danh mục:  
      **Endpoint:** `DELETE /api.myservice.com/v1/admin/categories/{categoryId}`  
      **Mô tả:** Xóa danh mục khỏi hệ thống.

- **Quản lý đăng ký khóa học - chưa xong**
    - Lấy danh sách đơn đăng ký của tất cả người dùng:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/enrollments`  
      **Mô tả:** Truy xuất danh sách đơn đăng ký khóa học.

    - Chi tiết đơn đăng ký:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/enrollments/{enrollmentId}`  
      **Mô tả:** Xem chi tiết đăng ký.

    - Cập nhật trạng thái đăng ký:  
      **Endpoint:** `PUT /api.myservice.com/v1/admin/enrollments/{enrollmentId}/status`  
      **Mô tả:** Cập nhật trạng thái của đơn đăng ký (payload: status).

- **Báo cáo & Thống kê - chưa xong**
    - Thống kê số lượng đăng ký theo thời gian:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/reports/enrollments-over-time`  
      **Mô tả:** Thống kê số đăng ký theo khoảng thời gian (payload: from, to).

    - Thống kê khóa học được đăng ký nhiều nhất:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/reports/courses-popularity`  
      **Mô tả:** Danh sách khóa học có lượt đăng ký cao (payload: from, to).

    - Thống kê doanh thu từ đăng ký khóa học:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/reports/revenue-over-time`  
      **Mô tả:** Doanh thu từ đơn đăng ký theo khoảng thời gian (payload: from, to).

    - Báo cáo hiệu suất của giảng viên:  
      **Endpoint:** `GET /api.myservice.com/v1/admin/reports/instructor-performance`  
      **Mô tả:** Thống kê hiệu suất và phản hồi của giảng viên (payload: from, to).
