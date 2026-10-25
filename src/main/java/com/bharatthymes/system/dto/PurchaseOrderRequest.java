package com.bharatthymes.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseOrderRequest(String purchaseOrderNumber,
                                   LocalDate orderDate,
                                   LocalDate expectedDeliveryDate,
                                   BigDecimal totalAmount,
                                   String status,
                                   Long supplierId,
                                   Long documentId) {
}
