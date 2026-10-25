
package com.bharatthymes.system.service;

import com.bharatthymes.system.dto.QuotationRequest;
import com.bharatthymes.system.entity.Client;
import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.entity.Quotation;
import com.bharatthymes.system.repository.ClientRepository;
import com.bharatthymes.system.repository.DocumentRepository;
import com.bharatthymes.system.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final ClientRepository clientRepository;
    private final DocumentRepository documentRepository;

    public QuotationService(
            QuotationRepository quotationRepository,
            ClientRepository clientRepository,
            DocumentRepository documentRepository) {
        this.quotationRepository = quotationRepository;
        this.clientRepository = clientRepository;
        this.documentRepository = documentRepository;
    }

    public Quotation createQuotation(QuotationRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        Quotation quotation = new Quotation();
        quotation.setQuotationNumber(request.quotationNumber());
        quotation.setQuotationDate(request.quotationDate());
        quotation.setStatus(request.status());
        quotation.setClient(client);
        quotation.setDocument(document);

        return quotationRepository.save(quotation);
    }

    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }

    public Quotation getQuotationById(Long id) {
        return quotationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Quotation not found with ID: " + id));
    }

    public Quotation updateQuotation(Long id, QuotationRequest request) {
        Quotation existingQuotation = getQuotationById(id);

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        existingQuotation.setQuotationNumber(request.quotationNumber());
        existingQuotation.setQuotationDate(request.quotationDate());
        existingQuotation.setStatus(request.status());
        existingQuotation.setClient(client);
        existingQuotation.setDocument(document);

        return quotationRepository.save(existingQuotation);
    }

    public void deleteQuotation(Long id) {
        Quotation existingQuotation = getQuotationById(id);
        quotationRepository.delete(existingQuotation);
    }
}
