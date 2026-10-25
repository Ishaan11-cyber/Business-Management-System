package com.bharatthymes.system.service;

import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import com.bharatthymes.system.repository.ClientRepository;
import java.util.List;
@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ClientRepository clientRepository;

    public DocumentService(DocumentRepository documentRepository, ClientRepository clientRepository) {
        this.clientRepository=clientRepository;
        this.documentRepository = documentRepository;
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found with ID: " + id));
    }

    public Document updateDocument(Long id, Document updatedDocument) {
        Document existingDocument = getDocumentById(id);

        existingDocument.setTitle(updatedDocument.getTitle());
        existingDocument.setCategory(updatedDocument.getCategory());
        existingDocument.setDescription(updatedDocument.getDescription());
        existingDocument.setDocumentDate(updatedDocument.getDocumentDate());
        existingDocument.setGoogleDriveFileId(updatedDocument.getGoogleDriveFileId());
        existingDocument.setGoogleDriveUrl(updatedDocument.getGoogleDriveUrl());
        existingDocument.setClient(updatedDocument.getClient());

        return documentRepository.save(existingDocument);
    }

    public void deleteDocument(Long id) {
        Document existingDocument = getDocumentById(id);
        documentRepository.delete(existingDocument);
    }

    public List<Document> getDocumentsByCategory(String category) {
        return documentRepository.findByCategoryIgnoreCase(category);
    }

    public Document linkDocumentToClient(Long documentId, Long clientId) {

        Document document = getDocumentById(documentId);

        var client = clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Client not found with ID: " + clientId));

        document.setClient(client);

        return documentRepository.save(document);
    }


}
