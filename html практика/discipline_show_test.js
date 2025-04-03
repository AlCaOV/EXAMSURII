document.addEventListener("DOMContentLoaded", function () {
    // Отримуємо всі рядки таблиці
    const rows = document.querySelectorAll(".students-container tbody tr");

    rows.forEach(row => {
        const editBtn = row.querySelector(".edit-btn");
        const saveBtn = row.querySelector(".save-btn");

        // Спочатку ховаємо кнопку Save
        saveBtn.style.display = "none";

        // Додаємо обробник кліку на Edit
        editBtn.addEventListener("click", function () {
            saveBtn.style.display = "inline-block"; // Показуємо Save
        });

        // Додаємо обробник кліку на Save
        saveBtn.addEventListener("click", function () {
            saveBtn.style.display = "none"; // Знову ховаємо Save після збереження
        });
    });
});
