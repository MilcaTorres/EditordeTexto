async function loadRecentFiles() {
    const response = await fetch('/inkblue/recent');
    const files = await response.json();
    const list = document.getElementById('recent-files');
    list.innerHTML = files.map(file => `<li>${file}</li>`).join('');
}

function createNewDocument() {
    const name = prompt("Enter document name:");
    if (name) {
        fetch('/inkblue/new', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(name)
        }).then(() => window.location.href = '/workspace.html');
    }
}

loadRecentFiles();