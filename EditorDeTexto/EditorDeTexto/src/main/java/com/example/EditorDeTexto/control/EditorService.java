package com.example.EditorDeTexto.control;

import com.example.EditorDeTexto.model.LinkedList;
import com.example.EditorDeTexto.model.Stack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditorService {

    private final Stack<String> undoStack = new Stack<>();
    private final Stack<String> redoStack = new Stack<>();
    private final LinkedList<String> recentFiles = new LinkedList<>();

    public String undo() {
        if (undoStack.isEmpty()) {
            return "No hay acciones para deshacer.";
        }
        String action = undoStack.pop();
        redoStack.push(action);
        return "Deshacer: " + action;
    }

    public String redo() {
        if (redoStack.isEmpty()) {
            return "No hay acciones para rehacer.";
        }
        String action = redoStack.pop();
        undoStack.push(action);
        return "Rehacer: " + action;
    }

    public void addRecentFile(String fileName) {
        recentFiles.add(fileName);
    }

    public List<String> getRecentFiles() {
        List<String> files = new ArrayList<>();
        for (int i = 0; i < recentFiles.size(); i++) {
            files.add(recentFiles.get(i));
        }
        return files;
    }
}
