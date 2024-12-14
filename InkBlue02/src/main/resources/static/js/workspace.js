const editor = document.getElementById('editor');

async function save() {
    await fetch('/inkblue/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(editor.value)
    });
}

async function undo() {
    const response = await fetch('/inkblue/undo');
    const content = await response.text();
    editor.value = content;
}

async function redo() {
    const response = await fetch('/inkblue/redo');
    const content = await response.text();
    editor.value = content;
}

async function loadTabs() {
    const response = await fetch('/inkblue/tabs');
    const tabs = await response.json();
    const list = document.getElementById('tabs');
    list.innerHTML = tabs.map(tab => `<li>${tab}</li>`).join('');
}

loadTabs();