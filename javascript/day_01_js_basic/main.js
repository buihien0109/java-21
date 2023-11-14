console.log("Hello world");
// alert("Hello world");
// document.write("Hello world");

// Java : int age = 10;
// String email = "abc@gmail";

// JavaScript : Kiểu dữ liệu của biến là động
// Khai báo biến và không gán giá trị cho biến

// Java : int age; int point, String name;
let age;
console.log(age); // undefined
age = 35; // number

age = "abc"; // string

// Khai báo biến và gán giá trị cho biến
let email = "hien@techmaster.vn"

const PI = 3.14; // final double PI = 3.14;
// PI = 3.15; // Không được phép gán lại giá trị cho hằng số

let firstName = "Hien";
let lastName = "Nguyen";

let first_name = "Hien";

let $age = 35;

// Template string - es6
let name = "Bùi Hiên"
let year = 1997

console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2023 - year} tuổi`);
console.log("Xin chào các bạn, mình tên là " + name + ". Năm nay " + (2023 - year) + " tuổi");

// function
// c1 : regular function
function sum(a, b) {
    return a + b;
}
console.log(sum(4, 5));

// c2 : function expression
const sum2 = function (a, b) {
    return a + b;
}
console.log(sum2(4, 5));

// c3 : arrow function - es6 // lambda expression - java8
const sum3 = (a = 10, b = 20) => { // default parameter - es6
    console.log(a, b);
    return a + b;
}
// const sum3 = (a, b) => a + b;
console.log(sum3(4, 5));
console.log(sum3(4));
console.log(sum3());

console.log(2 == 2);
console.log(2 == "2"); // Number("2")
console.log(2 === "2"); // Kiểu dữ liệu + giá trị
console.log(1 + "0"); // 1.toString() -> "1" + "0" -> "10"
console.log("9" / 3); // "9" -> Number("9") -> 9 // 9 / 3 -> 3