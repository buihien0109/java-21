// 1. Xóa review
const deleteReview = (reviewId) => {
    console.log(reviewId)
    // Hỏi người dùng có chắc chắn xóa không?
    const isConfirmed = window.confirm('Bạn có chắc chắn muốn xóa review này?')
    if (!isConfirmed) return

    // Gọi API để xóa review (fetch, axios, ajax, ...)
    const url = `/api/reviews/${reviewId}`
    axios.delete(url)
        .then(function (response) {
            alert('Xóa review thành công')

            // Xóa trên giao diện (recommended) / reload lại trang
            window.location.reload()
        })
        .catch(function (error) {
            console.log(error);
        });
}

// 2. Tạo mới review

// 3. Cập nhật review