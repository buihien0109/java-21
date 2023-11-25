const heading = document.getElementById("heading");
console.log(heading);

// đổi màu chữ : red, background: black
heading.style.color = "red";
heading.style.backgroundColor = "black";

const paraList = document.querySelectorAll("p");
console.log(paraList);

paraList.forEach((para) => {
    para.style.color = "blue";
    para.style.fontSize = "24px";
});

const p1 = document.querySelector("p");
console.log(p1);
const p2 = document.querySelector("p:nth-child(3)");
console.log(p2);
const p3 = document.querySelector("p:nth-child(4)");
console.log(p3);

// get/set text content
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

heading.innerHTML = "<span>Heading 1</span>";

// insert
// document.body.insertBefore(p3, p1);
// heading.insertAdjacentElement("afterend", p3);
p1.insertAdjacentElement("beforebegin", p3);

// insert button "click me" vào giữa p1, p2 
// const btn = document.createElement("button");
// btn.innerText = "Click me";
// console.log(btn);

// document.body.insertBefore(btn, p2);

p1.insertAdjacentHTML("afterend", "<button>Click me</button>");

// xóa
// document.body.removeChild(p1);
p1.parentElement.removeChild(p1);

// thay thế
const input = document.createElement("input");
input.type = "text";
input.placeholder = "Enter your name";

p2.parentElement.replaceChild(input, p2);

// classList
p3.classList.add("text-red", "text-bold", "text-big");
p3.classList.remove("text-bold");
console.log(p3.classList.contains("text-red"));
console.log(p3.classList.contains("text-bold"));

// classList toggle
setInterval(() => {
    p3.classList.toggle("text-red");
}, 1000); // 1000ms = 1s