package com.example.InkBlue02.Model;

// InkBlue Model
public class InkBlue {

    private String name; // Document name
    private String content; // Current content of the document
    private Stack<String> undoStack; // Undo stack
    private Stack<String> redoStack; // Redo stack

    // Constructor
    public InkBlue(String name) {
        this.name = name;
        this.content = "";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    // Update content and save previous state
    public void updateContent(String newContent) {
        undoStack.push(content); // Save current state
        content = newContent; // Update content
        redoStack.clear(); // Clear redo stack
    }

    // Undo last change
    public String undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(content); // Save current state to redo stack
            content = undoStack.pop(); // Retrieve previous state
        }
        return content;
    }

    // Redo last undone change
    public String redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(content); // Save current state to undo stack
            content = redoStack.pop(); // Retrieve future state
        }
        return content;
    }
}
