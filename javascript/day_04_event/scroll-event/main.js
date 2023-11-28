// 1. Ẩn hiện button "TOP"
const btn = document.getElementById("btn")
window.addEventListener('scroll', function() {
    if(document.documentElement.scrollTop > 300) {
        btn.classList.add('show')
    } else {
        btn.classList.remove('show')
    }
});

// 2. Ấn vào button "TOP" thì trở về đầu trang
btn.addEventListener('click', function() {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
});