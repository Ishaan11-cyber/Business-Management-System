package com.bharatthymes.system.service;

import com.bharatthymes.system.dto.SalesOrderRequest;
import com.bharatthymes.system.entity.Client;
import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.entity.SalesOrder;
import com.bharatthymes.system.repository.ClientRepository;
import com.bharatthymes.system.repository.DocumentRepository;
import com.bharatthymes.system.repository.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final ClientRepository clientRepository;
    private final DocumentRepository documentRepository;
    public SalesOrderService(
            SalesOrderRepository salesOrderRepository,
            ClientRepository clientRepository,
            DocumentRepository documentRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.clientRepository = clientRepository;
        this.documentRepository = documentRepository;
    }
    public SalesOrder createSalesOrder(SalesOrderRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setSalesOrderNumber(request.salesOrderNumber());
        salesOrder.setOrderDate(request.orderDate());
        salesOrder.setExpectedDeliveryDate(request.expectedDeliveryDate());
        salesOrder.setTotalAmount(request.totalAmount());
        salesOrder.setStatus(request.status());
        salesOrder.setClient(client);
        salesOrder.setDocument(document);

        return salesOrderRepository.save(salesOrder);
    }
    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public SalesOrder getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sales order not found: " + id));
    }
    public SalesOrder updateSalesOrder(Long id, SalesOrderRequest request) {
        SalesOrder existing = getSalesOrderById(id);

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Client not found: " + request.clientId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        existing.setSalesOrderNumber(request.salesOrderNumber());
        existing.setOrderDate(request.orderDate());
        existing.setExpectedDeliveryDate(request.expectedDeliveryDate());
        existing.setTotalAmount(request.totalAmount());
        existing.setStatus(request.status());
        existing.setClient(client);
        existing.setDocument(document);

        return salesOrderRepository.save(existing);
    }
    public void deleteSalesOrder(Long id) {
        SalesOrder existing = getSalesOrderById(id);
        salesOrderRepository.delete(existing);
    }
}
