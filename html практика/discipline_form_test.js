function submitForm() {
    // Отримуємо форму
    let form = document.getElementById("disciplineForm");

    // Отримуємо значення з полів форми
    let facultyName = form.elements["facultyName"].value.trim();
    let Lgroup = form.elements["Lgroup"].value.trim();
    let facultyNumber = form.elements["facultyNumber"].value.trim();
    let gradeInDiscipline = form.elements["gradeInDiscipline"].value.trim();
    let course = form.elements["course"].value.trim();

    // Виводимо введені користувачем дані у консоль
    console.log(Faculty Name: ${facultyName});
    console.log(Group Number: ${Lgroup});
    console.log(Faculty Number: ${facultyNumber});
    console.log(Grade in Discipline: ${gradeInDiscipline});
    console.log(Course: ${course});

    // Додатково можна показати повідомлення про успіх
    alert("Form submitted! Check the console for details.");
}

// Додаємо обробник події на кнопку Submit
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("submitBtn").addEventListener("click", submitForm);
});