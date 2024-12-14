// Referencias al DOM
const undoButton = document.getElementById("undo-button");
const redoButton = document.getElementById("redo-button");
const recentFilesList = document.getElementById("recent-files");

// Función para actualizar la lista de archivos recientes
async function loadRecentFiles() {
    const response = await fetch("/api/editor/recent-files");
    const files = await response.json();
    recentFilesList.innerHTML = ""; // Limpiar lista
    files.forEach(file => {
        const li = document.createElement("li");
        li.textContent = file;
        recentFilesList.appendChild(li);
    });
}

// Función para deshacer
undoButton.addEventListener("click", async () => {
    const response = await fetch("/api/editor/undo", { method: "POST" });
    const message = await response.text();
    alert(message);
    loadRecentFiles(); // Actualizar lista de archivos recientes
});

// Función para rehacer
redoButton.addEventListener("click", async () => {
    const response = await fetch("/api/editor/redo", { method: "POST" });
    const message = await response.text();
    alert(message);
    loadRecentFiles();
});

// Cargar archivos recientes al iniciar
document.addEventListener("DOMContentLoaded", loadRecentFiles);
