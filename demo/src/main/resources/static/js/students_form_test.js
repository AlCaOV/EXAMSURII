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