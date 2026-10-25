package com.bharatthymes.system.controller;

import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Create a document record
    @PostMapping
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    // Get all documents
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    // Get a document by ID
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }

    // Update a document record
    @PutMapping("/{id}")
    public Document updateDocument(
            @PathVariable Long id,
            @RequestBody Document document) {
        return documentService.updateDocument(id, document);
    }

    // Delete a document record
    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }
    @GetMapping("/category/{category}")
    public List<Document> getDocumentsByCategory(
            @PathVariable String category) {
        return documentService.getDocumentsByCategory(category);
    }

    @PatchMapping("/{documentId}/client/{clientId}")
    public Document linkDocumentToClient(
            @PathVariable Long documentId,
            @PathVariable Long clientId) {

        return documentService.linkDocumentToClient(documentId, clientId);
    }

}
