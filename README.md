#  Tiki Crawler
## 👋 Giới thiệu

Tiki Crawler là một ứng dụng hỗ trợ cào dữ liệu sản phẩm từ trang thương mại điện tử **Tiki**. Người dùng có thể tìm kiếm sản phẩm theo **từ khóa** hoặc **danh mục ngành hàng**, sau đó xuất dữ liệu ra các định dạng **JSON** hoặc **CSV** để dễ dàng import vào hệ thống **Odoo**.

## 👨‍💻 Tác giả

- **Name:** Nguyễn Huỳnh Thiện Nhân
- **Role:** Java Developer

## ⚙️ Tính năng
- **Cào dữ liệu sản phẩm từ Tiki:**
  - Theo từ khóa (ví dụ: "cát mèo").
  - Theo danh mục ngành hàng (ví dụ: "Điện gia dụng").
- **Xuất dữ liệu:**
  - File `.json`.
  - File `.csv`.
- **Tích hợp với Woocommerce:** Dữ liệu xuất ra được định dạng phù hợp để import vào Woocommerce.

## 📋 Hướng dẫn sử dụng
- **Nhập từ khóa hoặc chọn danh mục sản phẩm cần cào và số trang cần cào ( mỗi trang tương ứng 50 sản phẩm * số trang = tổng sản phẩm )**

![Cào theo từ khóa](demo/crawl-by-kw.png)

![Cào theo danh mục](demo/crawl-by-ct.png)

- **Xem trước các sản phẩm đã cào được.**

![Xem trước sản phẩm](demo/preview-products.png)

- **Chọn định dạng xuất file: JSON hoặc CSV.**

![Chọn định dạng xuất file](demo/export-options.png)

- **Tải file về và import vào .**

![Tải file](demo/files-downloaded.png)

- Cấu trúc file `.json`

![Cấu trúc file json](demo/jsonfile-structure.png)

- Cấu trúc file `.csv`

![Cấu trúc file csv](demo/csvfile-structure.png)

## License
This project is licensed under the ISC License.