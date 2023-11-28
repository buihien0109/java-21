// Bài 1
document.addEventListener("keydown", function (event) {
    if(event.keyCode == 13){
        drawCircle();
    } else if(event.keyCode == 32){
        drawSquare();
    } else {
        changeBackground();
    }
});

const randomPosition = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

const drawCircle = () => {
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
    circleEl.style.left = `${randomPosition(0, 1300)}px`;
    circleEl.style.top = `${randomPosition(0, 600)}px`;

    // Thêm vào dom
    document.body.appendChild(circleEl);
}

const drawSquare = () => {}

const changeBackground = () => {}
