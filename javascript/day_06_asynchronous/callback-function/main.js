function doTask1(name, callback) {
    console.log("Bắt đầu công việc");
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 3000);
}

function doTask2(name, callback) {
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 4000);
}

function doTask3(name, callback) {
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 5000);
}

function finish() {
    console.log("Kết thúc công việc");
}

// Nhặt rau - rửa rau - luộc rau
// doTask1("Nhặt rau", () => {
//     doTask2("Rửa rau", () => {
//         doTask3("Luộc rau", finish);
//     });
// });


// Ăn cơm - xem tivi - lướt facebook
doTask1("Ăn cơm", () => {
    console.log("Ăn cơm xong");
})

doTask2("Xem tivi", () => {
    console.log("Xem tivi xong");
});

doTask3("Lướt facebook", () => {
    console.log("Lướt facebook xong");
});