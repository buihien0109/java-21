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