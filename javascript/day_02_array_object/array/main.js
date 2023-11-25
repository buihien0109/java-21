// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];

for (let i = 0; i < myArr.length; i++) {
    console.log(myArr[i]);
}

myArr.forEach((e) => {
    console.log(e);
})

for (const value of myArr) {
    console.log(value);
}

myArr.push("Nguyễn Văn B", "Nguyễn Văn C");
console.log(myArr);

myArr.splice(4, 2, 10, 20, 30)
console.log(myArr);

let subArr = myArr.slice();
console.log(subArr);

// Java: class Student : id, name, email => sort student ASC name
// sort
let numbers = [2, 3, 5, 6, 7, 9, 1, 11, 21];
console.log(numbers.sort((a, b) => b - a));

let chars = "a-a-a-a-a-a-a-a-a-a-a-a-a";
let arrChars = chars.split("-");
console.log(arrChars);

let newChars = arrChars.join("_?");
console.log(newChars);

// So sánh 2 array
let arr1 = [1, 2, 3];
let arr2 = [1, 2, 3];
let arr3 = [3, 2, 1];
let str = "1,2,3";
console.log({ arr3, sort: arr3.sort() });

console.log(arr1 == arr2); // false - So sánh địa chỉ ô nhớ
console.log(arr1 == arr3); // false - So sánh địa chỉ ô nhớ
console.log(arr1 == str); // true
console.log(arr2 === str); // false

console.log(arr1 == arr3.sort()); // false
console.log(arr3 == arr3.sort()); // true
console.log(arr1 == arr1.reverse()); // true
console.log(arr2 == arr2.reverse().sort()); // true

// Object
let user = {
    name: "Nguyễn Văn A",
    age: 23,
    email: "abc@gmail.com",
    eat: function () {
        console.log("Eating...");
    },
    drink: function(name) {
        console.log("Drinking " + name);
    },
    address : {
        city: "Hà Nội",
        district: "Cầu Giấy",
        street: "Nguyễn Phong Sắc"
    },
    languages : ["Java", "PHP", "JavaScript"]
}

console.log(user.name);
user.eat();
user.drink("Coca Cola");
console.log(user.address.street);
console.log(user.languages[1]);

user.name = "Trần Văn B"

let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
];

const findByBrand = (products, brandName) => {
    return products.filter((product) => product.brand === brandName);
}

console.log(findByBrand(products, "Apple"));