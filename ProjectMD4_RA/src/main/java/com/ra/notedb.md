Hệ thống quản lý khóa học trực tuyến được thiết kế với 20 bảng cơ sở dữ liệu, mỗi bảng đảm nhận một chức năng cụ thể để hỗ trợ quản lý hiệu quả các hoạt động liên quan đến người dùng, khóa học và tương tác giữa họ. Dưới đây là phân tích chức năng của từng bảng:

1. **Bảng `users` (người dùng): -S1**
    - **Chức năng:** Lưu trữ thông tin cá nhân của tất cả người dùng trong hệ thống, bao gồm tên đăng nhập, email, họ tên, mật khẩu (đã mã hóa), số điện thoại, địa chỉ, trạng thái hoạt động, ảnh đại diện và thời gian tạo/cập nhật tài khoản. Cột `is_deleted` cho biết tài khoản có bị xóa hay không.

2. **Bảng `roles` (quyền) -S:**
    - **Chức năng:** Xác định các vai trò khác nhau trong hệ thống, như 'ADMIN', 'STUDENT' và 'INSTRUCTOR'. Mỗi vai trò được gán một `role_id` duy nhất.

3. **Bảng `user_roles` (phân quyền người dùng):-S**
    - **Chức năng:** Liên kết người dùng với các vai trò tương ứng. Mỗi bản ghi xác định một người dùng cụ thể (`user_id`) có một vai trò nhất định (`role_id`), cho phép một người dùng có thể đảm nhận nhiều vai trò.

4. **Bảng `categories` (danh mục khóa học):-S**
    - **Chức năng:** Quản lý các danh mục hoặc chủ đề của khóa học, bao gồm tên danh mục, mô tả và trạng thái hoạt động.

5. **Bảng `courses` (khóa học):-S1**
    - **Chức năng:** Lưu trữ thông tin chi tiết về các khóa học, như mã khóa học (SKU), tên khóa học, mô tả, giá, số lượng chỗ còn trống, hình ảnh, danh mục liên quan, giảng viên phụ trách và thời gian tạo/cập nhật khóa học.

6. **Bảng `course_lessons` (bài giảng của khóa học):-S**
    - **Chức năng:** Chứa thông tin về các bài giảng trong mỗi khóa học, bao gồm tiêu đề bài giảng, nội dung, liên kết video, thứ tự sắp xếp và thời gian tạo/cập nhật.

7. **Bảng `enrollments` (đăng ký khóa học):-S1**
    - **Chức năng:** Quản lý thông tin về việc học viên đăng ký tham gia các khóa học, bao gồm mã đăng ký, người dùng, tổng giá, trạng thái đăng ký (chờ xác nhận, đã xác nhận, đang học, hoàn thành, hủy bỏ, từ chối), ghi chú và thời gian tạo.

8. **Bảng `enrollment_details` (chi tiết đăng ký khóa học):-S**
    - **Chức năng:** Cung cấp chi tiết về các khóa học mà học viên đã đăng ký, như mã đăng ký, mã khóa học, tên khóa học, giá mỗi đơn vị và số lượng.

9. **Bảng `course_cart` (giỏ khóa học):**
    - **Chức năng:** Lưu trữ thông tin về các khóa học mà người dùng thêm vào giỏ hàng trước khi hoàn tất đăng ký, bao gồm mã giỏ hàng, mã khóa học, người dùng, số lượng và thời gian tạo.

10. **Bảng `favorite_courses` (danh sách yêu thích khóa học):**
    - **Chức năng:** Quản lý danh sách các khóa học mà người dùng đánh dấu là yêu thích, bao gồm mã yêu thích, người dùng, khóa học và thời gian tạo.

11. **Bảng `course_reviews` (đánh giá khóa học):**
    - **Chức năng:** Lưu trữ các đánh giá và nhận xét của học viên về các khóa học, bao gồm mã đánh giá, mã khóa học, người dùng, xếp hạng (từ 1 đến 5 sao), bình luận và thời gian tạo.

12. **Bảng `payments` (giao dịch thanh toán):-S**
    - **Chức năng:** Quản lý thông tin về các giao dịch thanh toán cho việc đăng ký khóa học, bao gồm mã thanh toán, mã đăng ký, người dùng, số tiền, phương thức thanh toán (thẻ tín dụng, PayPal, chuyển khoản ngân hàng), trạng thái thanh toán (đang chờ, thành công, thất bại), thời gian thanh toán và thời gian tạo.

13. **Bảng `notifications` (thông báo):-S**
    - **Chức năng:** Lưu trữ các thông báo gửi đến người dùng, bao gồm mã thông báo, người dùng nhận, tiêu đề, nội dung, trạng thái đã đọc/chưa đọc và thời gian tạo.

14. **Bảng `assignments` (bài tập):-S**
    - **Chức năng:** Quản lý thông tin về các bài tập được giao trong khóa học, bao gồm mã bài tập, mã khóa học, tiêu đề, mô tả, hạn nộp và thời gian tạo.

15. **Bảng `submissions` (nộp bài):-S**
    - **Chức năng:** Lưu trữ thông tin về việc nộp bài tập của học viên, bao gồm mã nộp bài, mã bài tập, người dùng, liên kết tệp nộp, điểm số, phản hồi từ giảng viên và thời gian nộp.

16. **Bảng `discussions` (diễn đàn thảo luận):**
    - **Chức năng:** Quản lý các chủ đề thảo luận trong khóa học, bao gồm mã thảo luận, mã khóa học, người dùng tạo, tiêu đề, nội dung và thời gian tạo.

17. **Bảng `discussion_comments` (bình luận diễn đàn):**
    - **Chức năng:** Lưu trữ các bình luận trong các chủ đề thảo luận, bao gồm mã bình luận, mã thảo luận, người dùng, nội dung bình luận và thời gian tạo.

18. **Bảng `private_messages` (tin nhắn riêng):**
    - **Chức năng:** Quản lý các tin nhắn cá nhân giữa các người dùng, bao gồm mã tin nhắn, người gửi, người nhận, chủ đề, nội dung, trạng thái đã đọc/chưa đọc và thời gian tạo.

19. **Bảng `certificates` (chứng chỉ):**
    - **Chức năng:** Lưu trữ thông tin về các chứng chỉ được cấp cho học viên sau khi hoàn thành khóa học, bao gồm mã chứng chỉ, mã đăng ký, liên kết chứng chỉ và thời gian cấp.

20. **Bảng `live_sessions` (lịch học trực tuyến):-S**
    - **Chức năng:** Quản lý thông tin về các buổi học trực tuyến trong khóa học, bao gồm mã buổi học, mã khóa học, tiêu đề buổi học, mô tả, thời gian dự kiến, thời lượng và thời gian tạo.

Mỗi bảng trong hệ thống đóng vai trò quan trọng trong việc quản lý và vận hành các chức năng của nền tảng học trực tuyến, đảm bảo sự tương tác hiệu quả giữa người dùng và nội dung






Dưới đây là phân tích các module (khung API) và các entity tham gia vào từng module trong hệ thống quản lý khóa học trực tuyến của bạn:
###
---
Dưới đây là khung API đầy đủ cho hệ thống quản lý khóa học, được thiết kế dựa trên khung API mẫu của hệ thống CSDL bán hàng, nhưng đã được điều chỉnh để phù hợp với các module và nghiệp vụ của một hệ thống khóa học trực tuyến.

---

## 1. **Authentication – Xác thực người dùng**

- **Đăng ký tài khoản**
  **Endpoint:** `POST /api.myservice.com/v1/auth/sign-up`
  **Mô tả:** Đăng ký tài khoản mới cho người dùng (payload gồm: username, email, fullname, password, …).

- **Đăng nhập**
  **Endpoint:** `POST /api.myservice.com/v1/auth/sign-in`
  **Mô tả:** Xác thực đăng nhập bằng username và password.

---

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

- **Quản lý địa chỉ (nếu cần lưu thông tin địa chỉ cá nhân)**
  - Thêm địa chỉ mới:
    **Endpoint:** `POST /api.myservice.com/v1/user/account/addresses`
    **Mô tả:** Thêm mới 1 địa chỉ cho người dùng.

  - Lấy danh sách địa chỉ:
    **Endpoint:** `GET /api.myservice.com/v1/user/account/addresses`
    **Mô tả:** Lấy ra danh sách địa chỉ của người dùng.

  - Lấy thông tin 1 địa chỉ:
    **Endpoint:** `GET /api.myservice.com/v1/user/account/addresses/{addressId}`
    **Mô tả:** Lấy thông tin chi tiết của 1 địa chỉ theo mã addressId.

  - Xóa địa chỉ:
    **Endpoint:** `DELETE /api.myservice.com/v1/user/account/addresses/{addressId}`
    **Mô tả:** Xóa 1 địa chỉ theo mã addressId.

- **Thông báo**
  - Lấy danh sách thông báo:
    **Endpoint:** `GET /api.myservice.com/v1/user/notifications`
    **Mô tả:** Truy xuất danh sách thông báo gửi tới người dùng.

  - Đánh dấu thông báo đã đọc:
    **Endpoint:** `PUT /api.myservice.com/v1/user/notifications/{notificationId}/read`
    **Mô tả:** Cập nhật trạng thái thông báo là đã đọc.

---

## 3. **Quản lý khóa học và danh mục**

- **Danh mục khóa học**
  - Lấy danh sách danh mục:
    **Endpoint:** `GET /api.myservice.com/v1/courses/categories`
    **Mô tả:** Lấy ra danh sách các danh mục khóa học.

  - Lấy khóa học theo danh mục:
    **Endpoint:** `GET /api.myservice.com/v1/courses/categories/{categoryId}`
    **Mô tả:** Lấy danh sách khóa học theo danh mục cụ thể.

- **Khóa học**
  - Lấy danh sách khóa học (có phân trang và sắp xếp):
    **Endpoint:** `GET /api.myservice.com/v1/courses`
    **Mô tả:** Truy xuất danh sách tất cả các khóa học.

  - Tìm kiếm khóa học theo tên hoặc mô tả:
    **Endpoint:** `GET /api.myservice.com/v1/courses/search`
    **Mô tả:** Tìm kiếm khóa học theo từ khóa.

  - Lấy danh sách khóa học nổi bật:
    **Endpoint:** `GET /api.myservice.com/v1/courses/featured`
    **Mô tả:** Danh sách khóa học được đánh giá nổi bật.

  - Lấy danh sách khóa học mới:
    **Endpoint:** `GET /api.myservice.com/v1/courses/new`
    **Mô tả:** Danh sách khóa học được cập nhật gần đây.

  - Lấy danh sách khóa học được ưa chuộng (ví dụ: có nhiều lượt đăng ký):
    **Endpoint:** `GET /api.myservice.com/v1/courses/popular`
    **Mô tả:** Danh sách khóa học có lượt đăng ký hoặc đánh giá cao.

  - Chi tiết khóa học
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}`
    **Mô tả:** Lấy ra thông tin chi tiết của 1 khóa học.

- **Bài giảng của khóa học**
  - Lấy danh sách bài giảng của 1 khóa học:
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/lessons`
    **Mô tả:** Truy xuất danh sách bài giảng thuộc một khóa học.

  - Chi tiết bài giảng:
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/lessons/{lessonId}`
    **Mô tả:** Lấy thông tin chi tiết của 1 bài giảng.

  - (Admin/Instructor) Tạo mới bài giảng:
    **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/lessons`
    **Mô tả:** Thêm bài giảng cho khóa học.

  - (Admin/Instructor) Cập nhật bài giảng:
    **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/lessons/{lessonId}`
    **Mô tả:** Chỉnh sửa nội dung bài giảng.

  - (Admin/Instructor) Xóa bài giảng:
    **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/lessons/{lessonId}`
    **Mô tả:** Xóa bài giảng khỏi khóa học.

---

## 4. **Đăng ký khóa học và giỏ hàng**

- **Giỏ khóa học**
  (Trong trường hợp hệ thống hỗ trợ mua khóa học theo mô hình giỏ hàng)
  - Lấy danh sách khóa học trong giỏ:
    **Endpoint:** `GET /api.myservice.com/v1/user/cart`
    **Mô tả:** Truy xuất danh sách khóa học đang có trong giỏ của người dùng.

  - Thêm khóa học vào giỏ:
    **Endpoint:** `POST /api.myservice.com/v1/user/cart`
    **Mô tả:** Thêm một khóa học vào giỏ (payload: courseId, [nếu cần quantity]).

  - Cập nhật thông tin trong giỏ (nếu cần điều chỉnh số lượng, ví dụ đối với gói khóa học có số lượng đăng ký):
    **Endpoint:** `PUT /api.myservice.com/v1/user/cart/{cartItemId}`
    **Mô tả:** Cập nhật số lượng hoặc thông tin của một mục trong giỏ.

  - Xóa 1 mục khỏi giỏ:
    **Endpoint:** `DELETE /api.myservice.com/v1/user/cart/{cartItemId}`
    **Mô tả:** Xóa khóa học khỏi giỏ.

  - Xóa toàn bộ giỏ hàng:
    **Endpoint:** `DELETE /api.myservice.com/v1/user/cart/clear`
    **Mô tả:** Xóa sạch các khóa học trong giỏ.

  - Thanh toán giỏ hàng – Đăng ký khóa học:
    **Endpoint:** `POST /api.myservice.com/v1/user/cart/checkout`
    **Mô tả:** Tiến hành đăng ký các khóa học có trong giỏ hàng và thực hiện thanh toán.

- **Đăng ký khóa học**
  - Lấy danh sách đăng ký của người dùng:
    **Endpoint:** `GET /api.myservice.com/v1/user/enrollments`
    **Mô tả:** Lấy danh sách các khóa học mà người dùng đã đăng ký.

  - Đăng ký khóa học mới (đối với hệ thống không dùng giỏ hàng):
    **Endpoint:** `POST /api.myservice.com/v1/user/enrollments`
    **Mô tả:** Đăng ký tham gia một khóa học (payload: courseId, phương thức thanh toán, …).

  - Lấy chi tiết đơn đăng ký:
    **Endpoint:** `GET /api.myservice.com/v1/user/enrollments/{enrollmentId}`
    **Mô tả:** Lấy chi tiết đăng ký của 1 khóa học.

  - Hủy đơn đăng ký (trong trường hợp đơn ở trạng thái “chờ xác nhận”):
    **Endpoint:** `PUT /api.myservice.com/v1/user/enrollments/{enrollmentId}/cancel`
    **Mô tả:** Hủy đăng ký khóa học.

- **Danh sách yêu thích**
  - Thêm khóa học vào danh sách yêu thích:
    **Endpoint:** `POST /api.myservice.com/v1/user/favorites`
    **Mô tả:** Lưu khóa học vào danh sách yêu thích (payload: courseId).

  - Lấy danh sách khóa học yêu thích:
    **Endpoint:** `GET /api.myservice.com/v1/user/favorites`
    **Mô tả:** Truy xuất danh sách các khóa học yêu thích của người dùng.

  - Xóa khóa học khỏi danh sách yêu thích:
    **Endpoint:** `DELETE /api.myservice.com/v1/user/favorites/{favoriteId}`
    **Mô tả:** Xóa một khóa học khỏi danh sách yêu thích.

---

## 5. **Đánh giá và phản hồi**

- **Đánh giá khóa học**
  - Lấy danh sách đánh giá của 1 khóa học:
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/reviews`
    **Mô tả:** Truy xuất các đánh giá và nhận xét về khóa học.

  - Thêm đánh giá cho khóa học:
    **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/reviews`
    **Mô tả:** Người dùng gửi đánh giá (payload: rating, comment).

  - Cập nhật đánh giá:
    **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/reviews/{reviewId}`
    **Mô tả:** Chỉnh sửa nội dung đánh giá của người dùng.

  - Xóa đánh giá:
    **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/reviews/{reviewId}`
    **Mô tả:** Xóa đánh giá khỏi khóa học.

---

## 6. **Bài tập và nộp bài**

- **Bài tập của khóa học**
  - Lấy danh sách bài tập:
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/assignments`
    **Mô tả:** Lấy danh sách bài tập được giao trong khóa học.

  - Chi tiết bài tập:
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}`
    **Mô tả:** Lấy thông tin chi tiết của bài tập.

  - (Admin/Instructor) Tạo bài tập mới:
    **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/assignments`
    **Mô tả:** Thêm bài tập cho khóa học.

  - (Admin/Instructor) Cập nhật bài tập:
    **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}`
    **Mô tả:** Chỉnh sửa bài tập.

  - (Admin/Instructor) Xóa bài tập:
    **Endpoint:** `DELETE /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}`
    **Mô tả:** Xóa bài tập khỏi khóa học.

- **Nộp bài tập**
  - Lấy danh sách bài nộp (cho giảng viên kiểm tra):
    **Endpoint:** `GET /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}/submissions`
    **Mô tả:** Danh sách bài nộp của học viên cho bài tập.

  - Nộp bài tập của học viên:
    **Endpoint:** `POST /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}/submissions`
    **Mô tả:** Học viên nộp bài (payload: file_url, …).

  - (Nếu cần) Cập nhật bài nộp:
    **Endpoint:** `PUT /api.myservice.com/v1/courses/{courseId}/assignments/{assignmentId}/submissions/{submissionId}`
    **Mô tả:** Học viên cập nhật bài nộp.

---

## 7. **Diễn đàn thảo luận và bình luận**

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

## 8. **Tin nhắn riêng tư**

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

## 9. **Chứng chỉ và học trực tuyến**

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
    **Endpoint:** `GET /api.myservice.com/v1/admin/users`
    **Mô tả:** Truy xuất danh sách tài khoản người dùng.

  - Cập nhật trạng thái người dùng (khóa/mở khóa):
    **Endpoint:** `PUT /api.myservice.com/v1/admin/users/{userId}`
    **Mô tả:** Cập nhật trạng thái hoạt động của người dùng.

  - Phân quyền cho người dùng:
    **Endpoint:** `POST /api.myservice.com/v1/admin/users/{userId}/role/{roleId}`
    **Mô tả:** Gán thêm vai trò cho người dùng.

  - Xóa quyền của người dùng:
    **Endpoint:** `DELETE /api.myservice.com/v1/admin/users/{userId}/role/{roleId}`
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

- **Quản lý đăng ký khóa học**
  - Lấy danh sách đơn đăng ký của tất cả người dùng:
    **Endpoint:** `GET /api.myservice.com/v1/admin/enrollments`
    **Mô tả:** Truy xuất danh sách đơn đăng ký khóa học.

  - Chi tiết đơn đăng ký:
    **Endpoint:** `GET /api.myservice.com/v1/admin/enrollments/{enrollmentId}`
    **Mô tả:** Xem chi tiết đăng ký.

  - Cập nhật trạng thái đăng ký:
    **Endpoint:** `PUT /api.myservice.com/v1/admin/enrollments/{enrollmentId}/status`
    **Mô tả:** Cập nhật trạng thái của đơn đăng ký (payload: status).

- **Báo cáo & Thống kê**
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

---

## Tổng kết

Khung API trên được thiết kế nhằm bao quát đầy đủ các chức năng của hệ thống quản lý khóa học trực tuyến, từ việc xác thực người dùng, quản lý thông tin cá nhân, duyệt và đăng ký các khóa học, tham gia các buổi học trực tuyến, nộp bài tập, trao đổi thông tin qua diễn đàn – cho tới việc quản trị và báo cáo tổng hợp ở phía admin.

Mỗi endpoint cần có xác thực (token JWT hoặc các phương thức xác thực khác) và các quy định về phân quyền để đảm bảo chỉ người dùng được phép (student, instructor, admin) mới có thể truy cập hoặc thao tác trên các dữ liệu tương ứng.

Hy vọng khung API này sẽ đáp ứng được các yêu cầu của hệ thống quản lý khóa học của bạn!
###
## 1. **Authentication – Xác thực người dùng**

- **Các chức năng chính:**
    - Đăng ký tài khoản
    - Đăng nhập

- **Entity tham gia:**
    - **User**: Lưu thông tin người dùng (username, email, fullname, password, …).
    - **Role**: Định nghĩa các vai trò (ADMIN, STUDENT, INSTRUCTOR).
    - **UserRole**: Bảng trung gian liên kết User với Role (phân quyền cho người dùng).

---

## 2. **Thông tin người dùng và quản lý tài khoản**

- **Các chức năng chính:**
    - Lấy, cập nhật thông tin tài khoản
    - Đổi mật khẩu
    - Quản lý địa chỉ (nếu tách riêng hoặc nếu tích hợp trong bảng users)
    - Thông báo (lấy danh sách và cập nhật trạng thái đã đọc)

- **Entity tham gia:**
    - **User**: Thông tin cơ bản của người dùng.
    - **Notification**: Lưu các thông báo gửi tới người dùng.
    - (Nếu có riêng) **Address**: Nếu bạn tách thông tin địa chỉ ra thành bảng riêng (ở đây bạn hợp nhất thông tin địa chỉ trong bảng `users`).

---

## 3. **Quản lý khóa học và danh mục**

- **Các chức năng chính:**
    - Quản lý danh mục khóa học (lấy danh sách, chi tiết danh mục)
    - Quản lý khóa học (lấy danh sách, tìm kiếm, khóa học nổi bật, mới, phổ biến, chi tiết)

- **Entity tham gia:**
    - **Category**: Lưu thông tin danh mục (category_name, description, status).
    - **Course**: Lưu thông tin khóa học (sku, course_name, description, price, available_slots, image, created_at, updated_at).
    - **User**: Đóng vai trò là giảng viên cho khóa học (instructor_id).

---

## 4. **Bài giảng của khóa học**

- **Các chức năng chính:**
    - Lấy danh sách bài giảng của 1 khóa học
    - Xem chi tiết bài giảng
    - (Admin/Instructor) Tạo, cập nhật, xóa bài giảng

- **Entity tham gia:**
    - **CourseLesson**: Lưu thông tin bài giảng (lesson_title, content, video_url, sort_order, created_at, updated_at).
    - **Course**: Liên kết khóa học mà bài giảng thuộc về.

---

## 5. **Đăng ký khóa học và giỏ hàng**

### A. **Giỏ khóa học**

- **Các chức năng chính:**
    - Lấy danh sách khóa học trong giỏ
    - Thêm khóa học vào giỏ
    - Cập nhật số lượng trong giỏ
    - Xóa mục khỏi giỏ
    - Xóa toàn bộ giỏ hàng
    - Thanh toán giỏ hàng (checkout) để đăng ký khóa học

- **Entity tham gia:**
    - **CourseCart**: Lưu các mục (cart item) chứa khóa học và số lượng.
    - **Course**: Thông tin của khóa học.
    - **User**: Xác định giỏ hàng của người dùng.
    - **Enrollment**: Khi checkout, các mục giỏ hàng chuyển thành đơn đăng ký khóa học.
    - **EnrollmentDetail**: Chi tiết từng khóa học trong đơn đăng ký.
    - **Payment**: Ghi nhận thông tin giao dịch thanh toán.

### B. **Đăng ký khóa học (Enrollment Management)**

- **Các chức năng chính:**
    - Lấy danh sách đơn đăng ký của người dùng
    - Đăng ký khóa học mới (nếu không dùng giỏ hàng)
    - Lấy chi tiết đơn đăng ký
    - Hủy đơn đăng ký (trong trạng thái “chờ xác nhận”)

- **Entity tham gia:**
    - **Enrollment**: Đại diện cho đơn đăng ký khóa học của người dùng.
    - **EnrollmentDetail**: Chi tiết các khóa học trong đơn đăng ký.
    - **User**: Người đăng ký.
    - **Course**: Khóa học được đăng ký.
    - **Payment**: Nếu tích hợp thanh toán trực tiếp trong đăng ký.

---

## 6. **Danh sách yêu thích**

- **Các chức năng chính:**
    - Thêm khóa học vào danh sách yêu thích
    - Lấy danh sách khóa học yêu thích của người dùng
    - Xóa khóa học khỏi danh sách yêu thích

- **Entity tham gia:**
    - **FavoriteCourse**: Lưu thông tin khóa học yêu thích của người dùng.
    - **Course**: Thông tin khóa học yêu thích.
    - **User**: Người dùng sở hữu danh sách yêu thích.

---

## 7. **Đánh giá và phản hồi**

- **Các chức năng chính:**
    - Lấy danh sách đánh giá của 1 khóa học
    - Thêm, cập nhật, xóa đánh giá cho khóa học

- **Entity tham gia:**
    - **CourseReview**: Lưu đánh giá và nhận xét của người dùng đối với khóa học.
    - **Course**: Khóa học được đánh giá.
    - **User**: Người dùng gửi đánh giá.

---

## 8. **Bài tập và nộp bài**

- **Các chức năng chính:**
    - Lấy danh sách bài tập của khóa học
    - Lấy chi tiết bài tập
    - Tạo, cập nhật, xóa bài tập (Admin/Instructor)
    - Nộp bài tập (cho học viên), lấy danh sách bài nộp, cập nhật bài nộp

- **Entity tham gia:**
    - **Assignment**: Lưu thông tin bài tập.
    - **Submission**: Lưu bài nộp của học viên.
    - **Course**: Khóa học liên quan.
    - **User**: Giảng viên và học viên.

---

## 9. **Diễn đàn thảo luận và bình luận**

- **Các chức năng chính:**
    - Lấy danh sách chủ đề thảo luận của khóa học
    - Tạo, cập nhật, xóa chủ đề (Admin/Instructor/Chủ sở hữu)
    - Lấy danh sách bình luận của một chủ đề, thêm và xóa bình luận

- **Entity tham gia:**
    - **Discussion**: Lưu thông tin chủ đề thảo luận.
    - **DiscussionComment**: Lưu bình luận trong chủ đề.
    - **Course**: Khóa học liên quan.
    - **User**: Người dùng tham gia thảo luận.

---

## 10. **Tin nhắn riêng tư**

- **Các chức năng chính:**
    - Lấy danh sách tin nhắn của người dùng
    - Gửi tin nhắn, xem chi tiết, đánh dấu đã đọc, xóa tin nhắn

- **Entity tham gia:**
    - **PrivateMessage**: Lưu thông tin tin nhắn riêng giữa người dùng.
    - **User**: Người gửi và người nhận.

---

## 11. **Chứng chỉ và học trực tuyến**

- **Chức năng Chứng chỉ:**
    - Lấy danh sách chứng chỉ của người dùng
    - Lấy chi tiết chứng chỉ

- **Entity tham gia:**
    - **Certificate**: Lưu thông tin chứng chỉ.
    - **Enrollment**: Liên kết với đơn đăng ký để tạo chứng chỉ.

- **Chức năng Live Sessions:**
    - Lấy danh sách buổi học trực tuyến của khóa học
    - Lấy chi tiết, tạo, cập nhật, xóa buổi học

- **Entity tham gia:**
    - **LiveSession**: Lưu thông tin buổi học trực tuyến.
    - **Course**: Khóa học liên quan.

---

## 12. **Admin – Quản trị hệ thống**

- **Quản lý người dùng:**
    - Xem danh sách, cập nhật trạng thái, phân quyền người dùng

- **Entity tham gia:**
    - **User**, **Role**, **UserRole**

- **Quản lý khóa học & danh mục:**
    - Xem danh sách, chi tiết, tạo, cập nhật, xóa khóa học; xem danh sách danh mục, chi tiết, tạo, cập nhật, xóa danh mục

- **Entity tham gia:**
    - **Course**, **Category**

- **Quản lý đăng ký khóa học:**
    - Xem danh sách đơn đăng ký của người dùng, chi tiết, cập nhật trạng thái

- **Entity tham gia:**
    - **Enrollment**, **EnrollmentDetail**

- **Báo cáo & Thống kê:**
    - Sử dụng các entity **Enrollment, Payment, Course, User** (Instructor), v.v.

---

## **Tóm tắt**
- **Authentication**: User, Role, UserRole
- **User Account & Management**: User, Notification
- **Course & Category Management**: Category, Course, User (as instructor)
- **Course Lessons**: CourseLesson, Course
- **Course Cart & Enrollment**: CourseCart, Course, User, Enrollment, EnrollmentDetail, Payment
- **Favorite Courses**: FavoriteCourse, Course, User
- **Reviews**: CourseReview, Course, User
- **Assignments & Submissions**: Assignment, Submission, Course, User
- **Discussions & Comments**: Discussion, DiscussionComment, Course, User
- **Private Messages**: PrivateMessage, User
- **Certificates & Live Sessions**: Certificate, Enrollment, LiveSession, Course
- **Admin Management**: User, Role, UserRole, Course, Category, Enrollment, Payment, v.v.

---

Đây là bảng phân tích chi tiết các entity tham gia cho từng khung API của hệ thống quản lý khóa học trực tuyến của bạn. Nếu cần triển khai cụ thể cho từng module (DTO, Service, Repository, Controller) bạn có thể xây dựng dựa trên cấu trúc này.st/response và service cho các bảng **Users, Courses, Enrollments (và chi tiết đăng ký), Categories,** và **Course Lessons** vì đây là các bảng cốt lõi định hình chức năng chính của hệ thống quản lý khóa học. Các bảng khác có thể được tích hợp trong service hoặc xây dựng riêng khi nghiệp vụ mở rộng.


Dưới đây là các API test mẫu trên Postman cho **Assignment** vàBạn muốn thêm chức năng nào nữa không? 🚀