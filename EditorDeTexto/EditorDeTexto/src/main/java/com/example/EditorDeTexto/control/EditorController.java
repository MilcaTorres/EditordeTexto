package com.example.EditorDeTexto.control;

import com.example.EditorDeTexto.control.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    // Endpoint para deshacer
    @PostMapping("/undo")
    public String undo() {
        return editorService.undo();
    }

    // Endpoint para rehacer
    @PostMapping("/redo")
    public String redo() {
        return editorService.redo();
    }

    // Endpoint para obtener los archivos recientes
    @GetMapping("/recent-files")
    public List<String> getRecentFiles() {
        return editorService.getRecentFiles();
    }

    // Endpoint para agregar un archivo reciente
    @PostMapping("/add-file")
    public void addRecentFile(@RequestParam String fileName) {
        editorService.addRecentFile(fileName);
    }
}
