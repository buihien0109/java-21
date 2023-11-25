//1. Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const heading_1 = document.querySelector("#heading");
heading_1.style.color = "red";
heading_1.style.textTransform = "uppercase";

//2. Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const para = document.querySelectorAll(".para");
para.forEach(p => {
    p.style.color = "blue";
    p.style.fontSize = "20px";
})

// 3. Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const p3 = document.querySelector("p:nth-child(4)")
const link = document.createElement('a')
link.href="https://www.facebook.com/"
link.innerText="facebook"
p3.insertAdjacentElement('afterend', link); 

// 4. Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.getElementById('title'); 
title.innerText = 'Đây là thẻ tiêu đề';

// 5. Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
const description = document.querySelector('.content .description');
console.log(description)

description.classList.add("text-bold");

// 6. Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const  buttonElement = document.createElement("button");
buttonElement.textContent = "Click me";
p3.parentNode.replaceChild(buttonElement, p3);

// 7. Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
let para2 = document.querySelector('.para-2');
let para2Clone = para2.cloneNode(true);
para2.parentNode.insertBefore(para2Clone, para2.nextSibling);

// 8. Xóa thẻ có class=“para-1”
let para1 = document.querySelector('.para-1');
para1.parentNode.removeChild(para1);