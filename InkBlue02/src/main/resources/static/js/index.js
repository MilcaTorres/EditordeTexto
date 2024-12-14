// Función para cargar los archivos recientes
async function loadRecentFiles() {
    const response = await fetch('http://localhost:8080/inkblue/recent');
    const files = await response.json();
    const list = document.getElementById('recent-files');
    list.innerHTML = files.map(file => `<li>${file}</li>`).join('');
}

// Función para crear un nuevo documento
async function createNewDocument() {
    const name = document.getElementById('documentName').value;
    if (name) {
        fetch('http://localhost:8080/inkblue/new', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(name)
        }).then(() => window.location.href = 'workspace.html');
        try {
            const response = await fetch('http://localhost:8080/inkblue/new', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(name)
            });

            if (response.ok) {
                // Cierra el modal después de crear el documento
                const modal = bootstrap.Modal.getInstance(document.getElementById('createDocumentModal'));
                modal.hide();

                // Redirige al usuario
                window.location.href = 'workspace.html';
            } else {
                alert('Error al crear el documento. Por favor, inténtelo de nuevo.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un problema al conectar con el servidor.');
        }
    } else {
        alert("Por favor, ingrese un nombre para el documento.");
    }
}

// Asignar evento al botón del modal
document.getElementById('createDocumentButton').addEventListener('click', createNewDocument);

// Llamar a la función para cargar los archivos recientes
loadRecentFiles();
