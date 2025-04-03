         document.addEventListener("DOMContentLoaded", function() {
            // Отримуємо всі кнопки "Edit"
            const editButtons = document.querySelectorAll(".edit-btn");
            editButtons.forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr"); // Отримуємо поточний рядок
                    let saveButton = row.querySelector(".save-btn"); // Знаходимо кнопку Save
                    saveButton.style.display = "inline-block"; // Робимо кнопку Save видимою
                    this.style.display = "none"; // Ховаємо кнопку Edit
                });
            });

            // Отримуємо всі кнопки "Delete"
            const deleteButtons = document.querySelectorAll(".delete-btn");
            deleteButtons.forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr");
                    row.remove(); // Видаляємо рядок
                });
            });
        });