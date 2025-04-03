document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".edit-btn").forEach(button => {
        button.addEventListener("click", function () {
            const row = this.closest("tr");
            const inputs = row.querySelectorAll(".edit-input");
            const spans = row.querySelectorAll("span");

            if (this.textContent === "Edit") {
                // Ховаємо всі <span> і показуємо input
                spans.forEach(span => span.style.display = "none");
                inputs.forEach(input => input.style.display = "inline-block");
                this.textContent = "Save";
            } else {
                const id = row.dataset.id;
                const updatedData = {
                    faculty_name: inputs[1].value,
                    lgroup: inputs[2].value,
                    faculty_number: inputs[3].value,
                    grade_in_discipline: inputs[4].value,
                    course: inputs[5].value // Додано кому!
                };

                console.log("Оновлення запису:", updatedData);
            fetch(`/discipline/update/${id}`, {
                 method: "PUT",
                 headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedData)
                }).then(response => {
                    console.log("Передано на сервер: ", updatedData); // Лог для перевірки
                       if (response.ok) {
                         spans.forEach((span, i) => {
                          span.textContent = inputs[i].value;
                         span.style.display = "inline";
                         inputs[i].style.display = "none";
                         });
                         this.textContent = "Edit";
                     } else {
                         alert("Помилка оновлення.");
                        }

                }).catch(error => {
                    console.error("Error:", error);
                    alert("Помилка оновлення.");
                });
            }
        });
    });

    // Обробник для кнопки Delete
    document.querySelectorAll(".delete-btn").forEach(button => {
        button.addEventListener("click", function () {
            const row = this.closest("tr");
            const id = row.dataset.id;

            fetch(`/discipline/delete/${id}`, { method: "DELETE" })
                .then(response => {
                    if (response.ok) {
                        row.remove();
                    } else {
                        alert("Помилка видалення.");
                    }
                }).catch(error => {
                console.error("Error:", error);
                alert("Помилка видалення.");
            });
        });
    });
});
