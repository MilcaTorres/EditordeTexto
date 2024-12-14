package com.example.InkBlue02.Controller;

import com.example.InkBlue02.Model.ArrayList;
import com.example.InkBlue02.Model.InkBlueService;
import org.springframework.web.bind.annotation.*;

import com.example.InkBlue02.Model.InkBlueService;

// InkBlue Controller
@RestController
@RequestMapping("/inkblue")
public class InkBlueController {

    private final InkBlueService service;

    public InkBlueController(InkBlueService service) {
        this.service = service;
    }

    // Endpoint to initialize a document
    @PostMapping("/new")
    public void createNewDocument(@RequestBody String name) {
        service.createDocument(name);
    }

    // Endpoint to save changes
    @PostMapping("/save")
    public void saveDocument(@RequestBody String content) {
        service.saveCurrentDocument(content);
    }

    // Endpoint to undo
    @GetMapping("/undo")
    public String undo() {
        return service.undo();
    }

    // Endpoint to redo
    @GetMapping("/redo")
    public String redo() {
        return service.redo();
    }

    // Endpoint to get recent files
    @GetMapping("/recent")
    public ArrayList<String> getRecentFiles() {
        return service.getRecentFiles();
    }

    // Endpoint to manage tabs
    @GetMapping("/tabs")
    public ArrayList<String> getTabs() {
        return service.getTabs();
    }

    @PostMapping("/tabs/add")
    public void addTab(@RequestBody String tabName) {
        service.addTab(tabName);
    }
}
