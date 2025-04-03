         document.addEventListener("DOMContentLoaded", function() {
            const editButtons = document.querySelectorAll(".edit-btn");
            editButtons.forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr"); // Отримуємо поточний рядок
                    let saveButton = row.querySelector(".save-btn"); // Знаходимо кнопку Save
                    saveButton.style.display = "inline-block"; // Робимо кнопку Save видимою
                    this.style.display = "none"; // Ховаємо кнопку Edit
                });
            });

            const deleteButtons = document.querySelectorAll(".delete-btn");
            deleteButtons.forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr");
                    row.remove()
                });
            });
        });
            
let inactiveTime = 10; // Час бездіяльності (10 секунд)
let timerElement = document.getElementById("timer");
let inactiveOverlay = document.getElementById("inactive-overlay");
let interval;

function updateTimer() {
    let minutes = Math.floor(inactiveTime / 60);
    let seconds = inactiveTime % 60;
    timerElement.textContent = `${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
}

function startTimer() {
    interval = setInterval(() => {
        inactiveTime--;
        updateTimer();

        if (inactiveTime <= 0) {
            clearInterval(interval);
            inactiveOverlay.style.display = "block"; // Показуємо затемнення
            document.body.style.pointerEvents = "none"; // Блокуємо всі дії
        }
    }, 1000);
}

function resetTimer() {
    inactiveTime = 10
    updateTimer()
}

// Запускаємо таймер при завантаженні сторінки
updateTimer();
startTimer();


document.addEventListener("mousemove", resetTimer);
document.addEventListener("keydown", resetTimer);
document.addEventListener("scroll", resetTimer);

document.addEventListener("DOMContentLoaded", function () {
    let isDisabled = false;

    document.addEventListener("keydown", function (event) {
       if (event.key === "f" || event.key === "F") {
            isDisabled = !isDisabled; // Перемикаємо стан при натисканні F5
        }
    });

    const buttons = document.querySelectorAll(".edit-btn, .save-btn, .delete-btn, form button");

    buttons.forEach(button => {
        button.style.position = "relative"; // Зберігаємо розміщення відносно батьківського контейнера
        button.style.transition = "transform 0.1s ease-out"; // Плавний ефект втечі

        button.addEventListener("mouseover", function () {
            if (isDisabled) return;
            
            const offsetX = (Math.random() - 0.5) * 400; // Більше зміщення по X
            const offsetY = (Math.random() - 0.5) * 400; // Більше зміщення по Y
            
            button.style.transform = `translate(${offsetX}px, ${offsetY}px)`;
        });
    });
});
       