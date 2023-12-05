const doTask1 = (name) => {
    return new Promise((resolve, reject) => {
        console.log("Bắt đầu công việc");
        console.log(`Thực hiện công việc ${name}`);
        setTimeout(() => {
            resolve(`Hoàn thành công việc ${name}`);
            // reject(`Không thể hoàn thành công việc ${name}`);
        }, 4000);
    });
};

const doTask2 = (name) => {
    return new Promise((resolve, reject) => {
        console.log(`Thực hiện công việc ${name}`);
        setTimeout(() => {
            // resolve(`Hoàn thành công việc ${name}`);
            reject("Vẩy rau lỗi kỹ thuật")
        }, 3000);
    });
};

const doTask3 = (name) => {
    return new Promise((resolve, reject) => {
        console.log(`Thực hiện công việc ${name}`);
        setTimeout(() => {
            resolve(`Hoàn thành công việc ${name}`);
        }, 5000);
    });
};

const doTask = async () => {
    try {
        let rs = await doTask1("Nhặt rau");
        console.log(rs);

        let rs2 = await doTask2("Rửa rau");
        console.log(rs2);

        let rs3 = await doTask3("Luộc rau");
        console.log(rs3);
        console.log("Kết thúc công việc");
    } catch (error) {
        console.log(error);
    }
}

doTask();