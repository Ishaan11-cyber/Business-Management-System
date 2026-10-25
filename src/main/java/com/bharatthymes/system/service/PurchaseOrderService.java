package com.bharatthymes.system.service;

import com.bharatthymes.system.dto.PurchaseOrderRequest;
import com.bharatthymes.system.entity.Document;
import com.bharatthymes.system.entity.PurchaseOrder;
import com.bharatthymes.system.entity.Supplier;
import com.bharatthymes.system.repository.DocumentRepository;
import com.bharatthymes.system.repository.PurchaseOrderRepository;
import com.bharatthymes.system.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final DocumentRepository documentRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            SupplierRepository supplierRepository,
            DocumentRepository documentRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.documentRepository = documentRepository;
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest request) {
        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found: " + request.supplierId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderNumber(request.purchaseOrderNumber());
        purchaseOrder.setOrderDate(request.orderDate());
        purchaseOrder.setExpectedDeliveryDate(request.expectedDeliveryDate());
        purchaseOrder.setTotalAmount(request.totalAmount());
        purchaseOrder.setStatus(request.status());
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setDocument(document);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Purchase order not found: " + id));
    }


    public PurchaseOrder updatePurchaseOrder(
            Long id, PurchaseOrderRequest request) {

        PurchaseOrder existing = getPurchaseOrderById(id);

        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found: " + request.supplierId()));

        Document document = null;

        if (request.documentId() != null) {
            document = documentRepository.findById(request.documentId())
                    .orElseThrow(() ->
                            new RuntimeException("Document not found: " + request.documentId()));
        }

        existing.setPurchaseOrderNumber(request.purchaseOrderNumber());
        existing.setOrderDate(request.orderDate());
        existing.setExpectedDeliveryDate(request.expectedDeliveryDate());
        existing.setTotalAmount(request.totalAmount());
        existing.setStatus(request.status());
        existing.setSupplier(supplier);
        existing.setDocument(document);

        return purchaseOrderRepository.save(existing);
    }

    public void deletePurchaseOrder(Long id) {
        PurchaseOrder existing = getPurchaseOrderById(id);
        purchaseOrderRepository.delete(existing);
    }
}
