         document.addEventListener("DOMContentLoaded", function() {
            // Додаємо обробники подій для кнопок Edit
            document.querySelectorAll(".edit-btn").forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr");
                    let saveButton = row.querySelector(".save-btn");
                    saveButton.style.display = "inline-block"; // Показуємо Save
                    this.style.display = "none"; // Ховаємо Edit

                    // Робимо всі комірки редагованими
                    row.querySelectorAll("td:not(:last-child)").forEach(cell => {
                        let input = document.createElement("input");
                        input.type = "text";
                        input.value = cell.innerText;
                        cell.innerHTML = "";
                        cell.appendChild(input);
                    });
                });
            });

            // Додаємо обробники подій для кнопок Save
            document.querySelectorAll(".save-btn").forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr");
                    let editButton = row.querySelector(".edit-btn");
                    this.style.display = "none"; // Ховаємо Save
                    editButton.style.display = "inline-block"; // Повертаємо Edit

                    // Зберігаємо введені дані
                    row.querySelectorAll("td:not(:last-child)").forEach(cell => {
                        let input = cell.querySelector("input");
                        if (input) {
                            cell.innerText = input.value;
                        }
                    });
                });
            });

            // Додаємо обробники подій для кнопок Delete
            document.querySelectorAll(".delete-btn").forEach(button => {
                button.addEventListener("click", function() {
                    let row = this.closest("tr");
                    row.remove();
                });
            });
        });
    