Để tạo một dự án **ReactJS** với **Vite** trong **IntelliJ IDEA**, bạn có thể làm theo các bước sau:

---

## **Bước 1: Cài đặt Node.js**
Trước tiên, bạn cần cài đặt **Node.js** (nếu chưa có).
- Tải và cài đặt từ trang chính thức: [https://nodejs.org/](https://nodejs.org/)
- Kiểm tra phiên bản sau khi cài đặt bằng lệnh:
  ```sh
  node -v
  npm -v
  ```

---

## **Bước 2: Tạo dự án React với Vite**
Mở **Terminal** trong **IntelliJ IDEA** hoặc sử dụng **Command Prompt** và chạy lệnh:
```sh
npm create vite@latest my-react-app --template react
```
- `my-react-app`: Tên thư mục dự án của bạn.
- `--template react`: Chỉ định dùng React.

---

## **Bước 3: Cài đặt dependencies**
Di chuyển vào thư mục dự án:
```sh
cd my-react-app
```
Cài đặt các dependencies:
```sh
npm install
```

---

## **Bước 4: Chạy dự án React**
Chạy lệnh sau để khởi động server:
```sh
npm run dev
```
Mặc định, dự án sẽ chạy trên `http://localhost:5173/`.

---

## **Bước 5: Mở dự án trong IntelliJ IDEA**
1. Mở **IntelliJ IDEA**.
2. Chọn **Open** và duyệt đến thư mục `my-react-app`.
3. Chờ IntelliJ tự động nhận diện cấu trúc dự án. Nếu chưa có Node.js Plugin, bạn có thể cài đặt qua **Settings > Plugins**.

---

## **Bước 6: Cấu hình Run/Debug trong IntelliJ IDEA (Tùy chọn)**
Nếu muốn chạy **ReactJS Vite** trực tiếp từ IntelliJ IDEA:
1. Vào **Run > Edit Configurations**.
2. Nhấn **+ (Add New Configuration)**, chọn **npm**.
3. Đặt tên là `Vite Dev Server`.
4. Ở phần **Command**, nhập `run dev`.
5. Nhấn **Apply > OK**.

Bây giờ, bạn có thể **chạy ReactJS trực tiếp từ IntelliJ** bằng nút **Run** (`Shift + F10`).

---

## **Bước 7: Bắt đầu phát triển ứng dụng**
Bạn có thể chỉnh sửa file `src/App.jsx` để thay đổi nội dung hiển thị.

---

## **Tóm tắt các lệnh chính**
| Lệnh | Chức năng |
|------|----------|
| `npm create vite@latest my-app --template react` | Tạo dự án React với Vite |
| `cd my-app` | Di chuyển vào thư mục dự án |
| `npm install` | Cài đặt dependencies |
| `npm run dev` | Chạy dự án ReactJS |
| `npm run build` | Build dự án |
| `npm run preview` | Xem trước sau khi build |

---

### ✅ **Bây giờ bạn đã sẵn sàng để code React với Vite trong IntelliJ IDEA! 🚀**
Nếu gặp lỗi, cứ hỏi mình nhé! 😃