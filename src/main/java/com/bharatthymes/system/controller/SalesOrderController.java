package com.bharatthymes.system.controller;

import com.bharatthymes.system.dto.SalesOrderRequest;
import com.bharatthymes.system.entity.SalesOrder;
import org.springframework.web.bind.annotation.*;
import com.bharatthymes.system.service.SalesOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {
    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }
    @PostMapping
    public SalesOrder createSalesOrder(
            @RequestBody SalesOrderRequest request) {
        return salesOrderService.createSalesOrder(request);
    }
    @GetMapping
    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping("/{id}")
    public SalesOrder getSalesOrderById(@PathVariable Long id) {
        return salesOrderService.getSalesOrderById(id);
    }

    @PutMapping("/{id}")
    public SalesOrder updateSalesOrder(
            @PathVariable Long id,
            @RequestBody SalesOrderRequest request) {
        return salesOrderService.updateSalesOrder(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteSalesOrder(@PathVariable Long id) {
        salesOrderService.deleteSalesOrder(id);
    }


}
