toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

// Xử lý logout
function logout() {
    axios.post("/api/auth/logout")
        .then(function (response) {
            toastr.success('Đăng xuất thành công.')
            setTimeout(function () {
                window.location.href = "/dang-nhap";
            }, 1500);
        })
        .catch(function (error) {
            console.log(error);
            toastr.error(error.response.data.message)
        });
}

// Xử lý active menu
function activeMenu() {
    // Lây đường dẫn hiện tại
    let path = window.location.pathname;
    console.log(path)

    // Xử lý active menu
    const menuItems = document.querySelectorAll('#main-menu .navbar-nav .nav-link')
    menuItems.forEach(function (menu) {
        if (menu.getAttribute('href') === path) {
            menu.classList.add('active')
        }
    })
}

activeMenu()