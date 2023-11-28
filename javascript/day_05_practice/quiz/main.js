// question : title, choices, answer
const questions = [
    {
        title: "What is 10 + 4?",
        choices: ["12", "14", "16", "18"],
        answer: "14"
    },
    {
        title: "What is 20 - 9?",
        choices: ["7", "13", "11", "15"],
        answer: "11"
    },
    {
        title: "What is 7 x 3?",
        choices: ["21", "24", "25", "27"],
        answer: "21"
    },
    {
        title: "What is 8 / 2?",
        choices: ["10", "2", "4", "6"],
        answer: "4"
    },
    {
        title: "What is 8 + 2?",
        choices: ["10", "2", "4", "6"],
        answer: "10"
    }
];

let currentQuestionIndex = 0;
let score = 0;
let yourAnswers = [];
let questionsStatus = questions.map((question, index) => {
    if(index <= currentQuestionIndex) {
        return true;
    }
    return false;
});
console.log(questionsStatus);


const questionTitleEl = document.querySelector("#question p"); // hiển thị tiêu đề câu hỏi
const choicesEl = document.querySelector(".choices"); // hiển thị ds các lựa chọn
const btnNext = document.getElementById("btn-next"); // nút next
const btnFinish = document.getElementById("btn-finish"); // nút kết thúc
const progressBarEl = document.querySelector(".progress-bar"); // thanh progress bar
const questionNumberEl = document.querySelector(".question-number"); // hiển thị số câu hỏi

const renderQuestion = () => {
    const currentQuestion = questions[currentQuestionIndex];

    // hiển thị tiêu đề câu hỏi
    questionTitleEl.innerHTML = `Câu ${currentQuestionIndex + 1} : ${currentQuestion.title}`

    // hiển thị ds các lựa chọn
    let choicesHtml = "";
    currentQuestion.choices.forEach((choice, index) => {
        choicesHtml += `
            <div class="choice-item">
                <input type="radio" name="choice" id="choice-${index}" value="${choice}">
                <label for="choice-${index}">${choice}</label>
            </div>
        `;
    });
    choicesEl.innerHTML = choicesHtml;

    // Cập nhật progress bar
    const answerd = questionsStatus.filter(status => status === true).length;
    console.log(answerd);
    const percent = answerd / questions.length * 100;
    progressBarEl.style.width = `${percent}%`;
    progressBarEl.innerHTML = `${percent}%`;

    // Cập nhật số câu hỏi
    renderQuestionNumber();
};

const renderQuestionNumber = () => {
    let html = "";
    questions.forEach((question, index) => {
       html += `
        <div 
            class="rounded border py-2 px-3 me-2 ${index === currentQuestionIndex ? 'border-primary' : ''}"
            onclick="renderQuestionWithQuestionNumber(${index})";
        >
            ${index + 1}
        </div>`;
    });
    questionNumberEl.innerHTML = html;
}

const renderQuestionWithQuestionNumber = (questionNumber) => {
    currentQuestionIndex = questionNumber;
    renderQuestion();
};

btnNext.addEventListener("click", () => {
    // Kiểm tra xem người dùng đã chọn đáp án chưa?
    // Nếu chọn rồi -> next
    // Nếu chưa chọn -> thông báo cho người dùng chọn đáp án
    const checkedChoice = document.querySelector("input[type=radio]:checked");
    if (!checkedChoice) {
        alert("Bạn chưa chọn đáp án!");
        return;
    }

    // Lưu đáp án của người dùng vào mảng yourAnswers
    yourAnswers.push(checkedChoice.value);
    console.log(yourAnswers);

    currentQuestionIndex++; // chuyển sang câu hỏi tiếp theo
    questionsStatus[currentQuestionIndex] = true;
    console.log(questionsStatus);
    renderQuestion(); // render câu hỏi tiếp theo trên giao diện

    // ẩn nút next khi đến câu hỏi cuối cùng
    if (currentQuestionIndex === questions.length - 1) {
        btnNext.classList.add("hide");
        btnFinish.classList.remove("hide");
    }
});

btnFinish.addEventListener("click", () => {
    const checkedChoice = document.querySelector("input[type=radio]:checked");
    if (!checkedChoice) {
        alert("Bạn chưa chọn đáp án!");
        return;
    }

    // Lưu đáp án của người dùng vào mảng yourAnswers
    yourAnswers.push(checkedChoice.value);
    questionsStatus[currentQuestionIndex] = true;
    console.log(yourAnswers);
    console.log(questionsStatus);

    // Tính điểm
    questions.forEach((question, index) => {
        if (question.answer === yourAnswers[index]) {
            score++;
        }
    });

    // Thông báo
    alert(`Bạn đã trả lời đúng ${score} câu hỏi!`);
});

renderQuestion();