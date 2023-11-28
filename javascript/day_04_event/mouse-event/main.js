// document.addEventListener('click', function (event) {
//     console.log(event);
// })

// document.addEventListener('mousedown', function () {
//     console.log('mousedown');
// })

// document.addEventListener('mousemove', function () {
//     console.log('mousemove');
// })

// document.addEventListener('mouseup', function () {
//     console.log('mouseup');
// })

document.addEventListener('click', function (event) {
    // Xóa hình tròn trước đó (nếu có)
    const currentCircleEl = document.querySelector('.circle');
    console.log(currentCircleEl);

    // Nếu tồn tại circle thì xóa đi
    if(currentCircleEl) {
        currentCircleEl.parentElement.removeChild(currentCircleEl);
    }

    // Tạo hình tròn
    const circleEl = document.createElement('div');
    circleEl.classList.add('circle');

    // Gắn vị trí cho hình tròn (X - right, left, Y - top, bottom)
    circleEl.style.left = `${event.offsetX - 50}px`;
    circleEl.style.top = `${event.offsetY - 50}px`;

    // Thêm vào dom
    document.body.appendChild(circleEl);
})