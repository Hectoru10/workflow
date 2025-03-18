// Función para activar el modo oscuro
const darkTheme = () => {
    const body = document.querySelector("body");
    body.setAttribute("data-bs-theme", "dark");
    document.querySelector("#dl-icon").setAttribute("class", "btn btn-secondary bi bi-sun-fill");

    // Cambiar todos los elementos con clases bg-primary a bg-body-secondary
    document.querySelectorAll(".bg-primary").forEach((elemento) => {
        elemento.classList.remove("bg-body-secondary");
        elemento.classList.add("bg-body-secondary");
    });

    // Guardar el estado en localStorage
    localStorage.setItem("theme", "dark");
};

// Función para activar el modo claro
const lightTheme = () => {
    const body = document.querySelector("body");
    body.setAttribute("data-bs-theme", "light");
    document.querySelector("#dl-icon").setAttribute("class", "btn btn-secondary bi bi-moon-fill");

    // Cambiar todos los elementos con clases bg-body-secondary a bg-primary
    document.querySelectorAll(".bg-body-secondary").forEach((elemento) => {
        elemento.classList.remove("bg-body-secondary");
        elemento.classList.add("bg-primary");
    });

    // Guardar el estado en localStorage
    localStorage.setItem("theme", "light");
};

// Función para alternar entre temas
const changeTheme = () => {
    const body = document.querySelector("body");
    body.getAttribute("data-bs-theme") === "light" ? darkTheme() : lightTheme();
};

// Aplicar el tema al cargar la página
window.addEventListener("load", () => {
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme === "dark") {
        darkTheme();
    } else {
        lightTheme();
    }
});

// Asignar evento al botón
document.querySelector("#dl-icon").addEventListener("click", changeTheme);
