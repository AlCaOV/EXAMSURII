document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const submitButton = document.querySelector("button[type='submit']");
    const inputs = form.querySelectorAll("input");

    // Спочатку ховаємо кнопку відправки
    submitButton.style.display = "none";

    function checkFormCompletion() {
        let allFilled = true;
        
        inputs.forEach(input => {
            if (!input.value.trim()) {
                allFilled = false;
            }
        });
        
        // Показуємо кнопку тільки якщо всі поля заповнені
        submitButton.style.display = allFilled ? "block" : "none";
    }

    // Перевіряємо заповненість форми при зміні кожного поля
    inputs.forEach(input => {
        input.addEventListener("input", checkFormCompletion);
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(" .submit-btn, form button");

    buttons.forEach(button => {
        button.style.position = "relative"; // Зберігаємо розміщення відносно батьківського контейнера
        button.style.transition = "transform 0.1s ease-out"; // Плавний ефект втечі

        button.addEventListener("mouseover", function () {
            const offsetX = (Math.random() - 0.5) * 400; // Більше зміщення по X
            const offsetY = (Math.random() - 0.5) * 400; // Більше зміщення по Y
            
            button.style.transform = `translate(${offsetX}px, ${offsetY}px)`;
        });
    });
});
