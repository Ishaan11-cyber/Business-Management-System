package com.bharatthymes.system.service;

import com.bharatthymes.system.dto.InvoiceRequest;
import com.bharatthymes.system.entity.Client;
import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.entity.Invoice;
import com.bharatthymes.system.repository.ClientRepository;
import com.bharatthymes.system.repository.DocumentRepository;
import com.bharatthymes.system.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final DocumentRepository documentRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ClientRepository clientRepository,
            DocumentRepository documentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.documentRepository = documentRepository;
    }

    public Invoice createInvoice(InvoiceRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(request.invoiceNumber());
        invoice.setInvoiceDate(request.invoiceDate());
        invoice.setDueDate(request.dueDate());
        invoice.setTotalAmount(request.totalAmount());
        invoice.setStatus(request.status());
        invoice.setClient(client);
        invoice.setDocument(document);

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found with ID: " + id));
    }

    public Invoice updateInvoice(Long id, InvoiceRequest request) {
        Invoice existingInvoice = getInvoiceById(id);

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        existingInvoice.setInvoiceNumber(request.invoiceNumber());
        existingInvoice.setInvoiceDate(request.invoiceDate());
        existingInvoice.setDueDate(request.dueDate());
        existingInvoice.setTotalAmount(request.totalAmount());
        existingInvoice.setStatus(request.status());
        existingInvoice.setClient(client);
        existingInvoice.setDocument(document);

        return invoiceRepository.save(existingInvoice);
    }

    public void deleteInvoice(Long id) {
        Invoice existingInvoice = getInvoiceById(id);
        invoiceRepository.delete(existingInvoice);
    }
}
