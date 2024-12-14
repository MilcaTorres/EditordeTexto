const editor = document.getElementById('editor');

async function save() {
    try {
        const response = await fetch('/inkblue/save', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editor.value)
        });

        if (!response.ok) {
            throw new Error(`Error al guardar: ${response.status} ${response.statusText}`);
        }

        console.log('Contenido guardado exitosamente');
    } catch (error) {
        console.error('Error en la función save:', error);
    }
}

async function undo() {
    const url = `http://localhost:8080/inkblue/undo`;

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        });

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        console.log('Operación deshecha exitosamente:', data);
        return data;
    } catch (error) {
        console.error('Ocurrió un error en undo:', error);
    }
}

async function redo() {
    try {
        const response = await fetch('/inkblue/redo');
        if (!response.ok) {
            throw new Error(`Error en redo: ${response.status} ${response.statusText}`);
        }

        const content = await response.text();
        editor.value = content;
        console.log('Operación redo realizada');
    } catch (error) {
        console.error('Ocurrió un error en redo:', error);
    }
}

