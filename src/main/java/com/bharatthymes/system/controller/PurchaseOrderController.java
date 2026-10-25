package com.bharatthymes.system.controller;

import com.bharatthymes.system.dto.PurchaseOrderRequest;
import com.bharatthymes.system.entity.PurchaseOrder;
import com.bharatthymes.system.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(
            @RequestBody PurchaseOrderRequest request) {
        return purchaseOrderService.createPurchaseOrder(request);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PutMapping("/{id}")
    public PurchaseOrder updatePurchaseOrder(
            @PathVariable Long id,
            @RequestBody PurchaseOrderRequest request) {
        return purchaseOrderService.updatePurchaseOrder(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
    }
}
