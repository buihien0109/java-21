/*
Mô hình client - server
Client -> request -> Server : Xử lý -> response -> Client

request : CURD (Create, Update, Read, Delete)
response : JSON (JavaScript Object Notation), XML, HTML, ...

API (Application Programming Interface) : Giao diện lập trình ứng dụng

Khách hàng : Client
Nhân viên : Lấy menu, gọi món, hủy món, cập nhât, thanh toán, ... (API)
Nhà bếp : Server

- Tài nguyên hệ thống : Món ăn
- Hành động tương tác với tài nguyên : Lấy menu, gọi món, hủy món, cập nhật, thanh toán, ...
- Phương thức : GET - Read, POST - Create, PUT - Update, DELETE - Delete, ...

// Response : Thành công - Thất bại (Http status code)

API example
- GET /api/v1/posts : Lấy danh sách bài viết
- GET /api/v1/posts?limit=10&page=1 : Lấy danh sách bài viết, mỗi trang 10 bài viết, trang 1
- GET /api/v1/posts/1 : Lấy bài viết có id = 1
- DELETE /api/v1/posts/1 : Xóa bài viết có id = 1
- POST /api/v1/posts : Tạo bài viết mới
    request body - {title: 'abc', content: 'xyz'}
- PUT /api/v1/posts/1 : Cập nhật bài viết có id = 1
    request body - {title: 'xyz', content: 'xyz'}

Gọi API trong JS:
- fetch API : build-in method của JS
- axios : thư viện bên ngoài
*/

const btn = document.getElementById("btn");
const image = document.getElementById("image");

// Cách 1 : fetch API
// Bấm nút -> gọi API -> lấy dữ liệu từ API -> hiển thị lên giao diện
// btn.addEventListener("click", () => {
//     fetch("https://dog.ceo/api/breeds/image/random")
//         .then(rs => {
//             console.log(rs);
//             return rs.json();
//         })
//         .then(rs => {
//             console.log(rs); // { message, status}
//             image.src = rs.message;
//         })
//         .catch(err => {
//             console.log(err);
//         });
// });

// Cách 2 : async/await
// btn.addEventListener("click", async () => {
//     try {
//         const rs = await fetch("https://dog.ceo/api/breeds/image/random");
//         const data = await rs.json();
//         image.src = data.message;
//     } catch (err) {
//         console.log(err);
//     }
// });

// Cách 3 : axios
// btn.addEventListener("click", async () => {
//     axios.get('https://dog.ceo/api/breeds/image/random')
//         .then(function (response) {
//             console.log(response);
//             image.src = response.data.message;
//         })
//         .catch(function (error) {
//             console.log(error);
//         })
// });

// Cách 4 : axios + async/await
btn.addEventListener("click", async () => {
    try {
        const rs = await axios.get("https://dog.ceo/api/breeds/image/random");
        image.src = rs.data.message;
    } catch (err) {
        console.log(err);
    }
});