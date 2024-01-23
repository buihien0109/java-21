// Rating ---------------------------------------------------------------------
const stars = document.querySelectorAll(".rating-container .rating-item");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;
stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.dataset.rating);
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.dataset.rating);
        ratingValue.innerHTML = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.dataset.rating);
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// Render reviews --------------------------------------------------------------
const formatDate = (dateString) => {
    let date = new Date(dateString);
    let year = date.getFullYear();
    let month = `0${date.getMonth() + 1}`.slice(-2); // 01 -> 01, 012 -> 12
    let day = `0${date.getDate()}`.slice(-2);
    return `${day}/${month}/${year}`;
}

const renderReviews = (reviews) => {
    console.log(reviews)
    const htmlReviews = reviews.map((review) => {
        return `
            <div class="rating-item d-flex align-items-center mb-3 pb-3">
                    <div class="rating-avatar">
                        <img src="${review.user.avatar}" alt="${review.user.name}">
                    </div>
                    <div class="rating-info ms-3">
                        <div class="d-flex align-items-center">
                            <p class="rating-name mb-0">
                                <strong>${review.user.name}</strong>
                            </p>
                            <p class="rating-time mb-0 ms-2">${formatDate(review.updatedAt)}</p>
                        </div>
                        <div class="rating-star">
                            <p class="mb-0 fw-bold">
                                <span class="rating-icon"><i class="fa fa-star"></i></span>
                                <span>${review.rating}/10 ${review.ratingText}</span>
                            </p>
                        </div>
                        <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p>

                        ${currentUser && currentUser.id === review.user.id 
                            ? `<div class="rating-action mt-2">
                                    <a href="javascript:void(0)" class="text-primary text-decoration-underline me-2" onclick="openModalUpdate(${review.id})">Sửa</a>
                                    <a href="javascript:void(0)" class="text-danger text-decoration-underline" onclick="deleteReview(${review.id})">Xóa</a>
                                </div>`
                            : ''
                        }
                    </div>
                </div>
        `
    }).join('')

    document.querySelector('.review-list').innerHTML = htmlReviews
}

// Phần xử lý liên quan đến reivew
let reviewIdUpdated = null;

// 1. Xóa review
const deleteReview = (reviewId) => {
    // Hỏi người dùng có chắc chắn xóa không?
    const isConfirmed = window.confirm('Bạn có chắc chắn muốn xóa review này?')
    if (!isConfirmed) return

    // Gọi API để xóa review (fetch, axios, ajax, ...)
    const url = `/api/reviews/${reviewId}`
    axios.delete(url)
        .then(function (response) {
            toastr.success('Xóa review thành công.')

            // Xóa review có id = reviewId khỏi danh sách reviews
            reviews = reviews.filter(review => review.id !== reviewId)
            renderReviews(reviews)
        })
        .catch(function (error) {
            console.log(error);
            toastr.error(error.response.data.message)
        });
}

const btnOpenModalReview = document.getElementById('btn-open-modal-review')
const ratingContentEl = document.getElementById('rating-content')
const modalReview = new bootstrap.Modal(document.getElementById('modal-review'), {
    keyboard: false
})
btnOpenModalReview.addEventListener('click', function () {
    // Mở modal
    modalReview.show();

    // Thay đổi title của modal
    document.getElementById("modal-title").innerHTML = "Viết đánh giá";
})


document.getElementById('modal-review').addEventListener('hidden.bs.modal', function (event) {
    resetStars();
    ratingValue.innerHTML = "";
    ratingContentEl.value = "";
    reviewIdUpdated = null;
    currentRating = 0;
})

const btnHandleReview = document.getElementById('btn-handle-review')
btnHandleReview.addEventListener('click', function () {
    if(reviewIdUpdated) {
        // Cập nhật review
        updateReview()
    } else {
        createReview()
    }
});
// 2. Tạo mới review
const createReview = () => {
    console.log('create review')
    // Kiểm tra xem người dùng đã đánh giá chưa?
    if(!currentRating) {
        toastr.error('Bạn chưa đánh giá.')
        return;
    }

    // Kiểm tra ndung review có hợp lệ không?
    if(!ratingContentEl.value.trim()) {
        toastr.error('Bạn chưa nhập nội dung đánh giá.')
        return;
    }

    const data = {
        rating: currentRating,
        content: ratingContentEl.value,
        movieId: movie.id
    }

    // Gọi API để tạo mới review sử dụng axios
    axios.post('/api/reviews', data)
        .then(function (response) {
            toastr.success('Tạo mới review thành công.')
            // Đóng modal
            modalReview.hide()

            // Thêm review vào danh sách reviews
            reviews.unshift(response.data)
            renderReviews(reviews)
        })
        .catch(function (error) {
            console.log(error);
            toastr.error(error.response.data.message)
        });
}

// 3. Cập nhật review
const openModalUpdate = id => {
    // Tìm kiếm review có id = id
    const review = reviews.find(review => review.id === id)

    // Thay đổi title của modal
    document.getElementById("modal-title").innerHTML = "Cập nhật đánh giá";

    // Cập nhật dữ liệu cho modal
    resetStars();
    currentRating = review.rating;
    ratingValue.innerHTML = `Bạn đã đánh giá ${review.rating} sao.`;
    highlightStars(currentRating);

    ratingContentEl.value = review.content
    reviewIdUpdated = review.id

    // Mở modal
    modalReview.show();
}
const updateReview = () => {
// Kiểm tra xem người dùng đã đánh giá chưa?
    if(!currentRating) {
        toastr.error('Bạn chưa đánh giá.')
        return;
    }

    // Kiểm tra ndung review có hợp lệ không?
    if(!ratingContentEl.value.trim()) {
        toastr.error('Bạn chưa nhập nội dung đánh giá.')
        return;
    }

    const data = {
        rating: currentRating,
        content: ratingContentEl.value,
        movieId: movie.id
    }

    // Gọi API để tạo mới review sử dụng axios
    axios.put(`/api/reviews/${reviewIdUpdated}`, data)
        .then(function (response) {
            toastr.success('Cập nhật review thành công.')
            // Đóng modal
            modalReview.hide()

            // Cập nhật review trong danh sách reviews
            const index = reviews.findIndex(review => review.id === reviewIdUpdated)
            reviews[index] = response.data

            // Render lại danh sách reviews
            renderReviews(reviews)
        })
        .catch(function (error) {
            console.log(error);
            toastr.error(error.response.data.message)
        });
}
