package com.example.InkBlue02.Model;

import org.springframework.stereotype.Service;

// InkBlue Service
@Service
public class InkBlueService {

    private LinkedList<String> recentFiles = new LinkedList<>(); // Usando tu LinkedList personalizada
    private ArrayList<InkBlue> tabs = new ArrayList<>(); // Usando tu ArrayList personalizada
    private InkBlue currentDocument;

    // Crear un nuevo documento
    public void createDocument(String name) {
        InkBlue newDocument = new InkBlue(name);
        tabs.add(newDocument);  // Añadir el nuevo documento a la lista de pestañas
        currentDocument = newDocument;  // Establecer el documento actual
        addToRecentFiles(name);  // Añadir el archivo reciente
    }

    // Guardar cambios en el documento actual
    public void saveCurrentDocument(String content) {
        if (currentDocument != null) {
            currentDocument.updateContent(content);  // Actualizar contenido del documento
        }
    }

    // Deshacer en el documento actual
    public String undo() {
        return currentDocument != null ? currentDocument.undo() : "";  // Realizar deshacer
    }

    // Rehacer en el documento actual
    public String redo() {
        return currentDocument != null ? currentDocument.redo() : "";  // Realizar rehacer
    }

    // Obtener los archivos recientes
    public ArrayList<String> getRecentFiles() {
        ArrayList<String> recentFilesList = new ArrayList<>();
        for (int i = 0; i < recentFiles.size(); i++) {
            recentFilesList.add(recentFiles.get(i));  // Recorrer la lista de archivos recientes
        }
        return recentFilesList;  // Retornar la lista de archivos recientes
    }

    // Añadir un archivo a los recientes
    private void addToRecentFiles(String fileName) {
        if (!recentFiles.contains(fileName)) {  // Verificar si el archivo no está ya en la lista
            recentFiles.add(fileName);  // Añadir archivo
            if (recentFiles.size() > 5) {  // Mantener solo los 5 archivos más recientes
                recentFiles.remove(0);  // Eliminar el más antiguo si hay más de 5 archivos
            }
        }
    }

    // Obtener las pestañas abiertas
    public ArrayList<String> getTabs() {
        ArrayList<String> tabsList = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            tabsList.add(tabs.get(i).getName());  // Añadir el nombre de cada pestaña
        }
        return tabsList;  // Retornar la lista de pestañas
    }

    // Añadir una nueva pestaña
    public void addTab(String tabName) {
        boolean exists = false;
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).getName().equals(tabName)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            tabs.add(new InkBlue(tabName));  // Añadir una nueva pestaña si no existe
        }
    }
}