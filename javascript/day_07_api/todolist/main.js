const API_URL = "http://localhost:8080/todos";

const getAllTodos = async () => {
    try {
        let res = await axios.get(API_URL);
        console.log(res);

        renderTodo(res.data);
    } catch (error) {
        console.log(error);
    }
};

// Hiển thị ds công việc
const todoListEl = document.getElementById("todolist");
const renderTodo = (todos) => {
    let html = "";
    todos.forEach((todo) => {
        html += `
            <li>
                <input type="checkbox" ${todo.completed ? 'checked' : ''} 
                    onchange="toggleCompleted(${todo.id})">
                <span class="${todo.completed ? 'active' : ''}">${todo.title}</span>
                <button onclick="editTodo(${todo.id})">Sửa</button>
                <button onclick="deleteTodo(${todo.id})">Xóa</button>
            </li>
        `;
    });
    todoListEl.innerHTML = html;
};

// Thêm công việc
const todoInputEl = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click", async () => {
    // Lấy ra nd trong input
    const title = todoInputEl.value.trim();

    // Kiểm tra nd có rỗng hay không
    if (title === "") {
        alert("Vui lòng nhập nội dung công việc");
        return;
    }

    // Gọi API
    try {
        const newTodo = {
            title: title,
            completed: false
        }

        let res = await axios.post(API_URL, newTodo);
        console.log(res);

        // Hiển thị lại ds công việc
        // C1: Gọi lại API lấy ds công việc
        // C2: Reload lại trang
        // C3: Tạo thêm 1 li mới và append vào ul
    } catch (error) {
        console.log(error);
    }
});

// Xóa công việc
const deleteTodo = async (id) => {
    try {
        // Gọi API để xóa trên server
        await axios.delete(`${API_URL}/${id}`);

        // Xóa cv trên giao diện
        // C1: Gọi lại API lấy ds công việc
        // C2: Reload lại trang
        // C3: Xóa li chưa todo tương ứng trong ul
    } catch (error) {
        console.log(error);
    }
}

// Sửa tiêu đề công việc
const editTodo = async (id) => {
    try {
        // Tìm kiếm cv cần sửa
        let res = await axios.get(`${API_URL}/${id}`);
        let currentTodo = res.data;

        // Sử dụng window.prompt để hiển thị popup nhập nội dung mới
        let newTitle = window.prompt("Nhập nội dung mới", currentTodo.title);

        // Kiểm tra nội dung mới có rỗng hay không
        if (newTitle === null) return;
        if (newTitle === "") {
            alert("Vui lòng nhập nội dung công việc");
            return;
        }

        // Sửa tiêu đề
        currentTodo.title = newTitle;

        // Gọi API để sửa trên server
        await axios.put(`${API_URL}/${id}`, currentTodo);

        // Update cv trên giao diện
        // C1: Gọi lại API lấy ds công việc
        // C2: Reload lại trang
        // C3: Tìm li chứa todo tương ứng trong ul và update lại tiêu đề
    } catch (error) {
        console.log(error);
    }
    
}

// Sửa trạng thái công việc
const toggleCompleted = async (id) => {
    try {
        // Tìm kiếm cv cần sửa
        let res = await axios.get(`${API_URL}/${id}`);
        let currentTodo = res.data;

        // Sửa trạng thái
        currentTodo.completed = !currentTodo.completed;

        // Gọi API để sửa trên server
        await axios.put(`${API_URL}/${id}`, currentTodo);

        // Update cv trên giao diện
        // C1: Gọi lại API lấy ds công việc
        // C2: Reload lại trang
        // C3: Tìm li chứa todo tương ứng trong ul và update lại trạng thái
    } catch (error) {
        console.log(error);
    }
}

getAllTodos();
