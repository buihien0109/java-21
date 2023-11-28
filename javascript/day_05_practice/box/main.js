const colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];
let currentColors = [...colors]; // let currentColors = colors.slice()

const btn = document.querySelector("#btn");
const pointsEl = document.querySelector(".points");
const boxesEl = document.querySelector(".boxes");

// render box;
const renderBox = (arr) => {
    let html = "";
    arr.forEach((color, index) => {
        html += `
            <div 
                class="box" 
                style="background-color: ${color}"
                onclick="removeBox(${index})"
            ></div>
        `;
    });
    boxesEl.innerHTML = html;

    // update total box
    pointsEl.innerHTML = arr.length;
}

// xóa box
const removeBox = (index) => {
    currentColors.splice(index, 1);
    renderBox(currentColors);
}

// thêm box
btn.addEventListener("click", () => {
    currentColors = [...currentColors, ...colors]; // currentColors = currentColors.concat(colors);
    renderBox(currentColors);
});

renderBox(currentColors);