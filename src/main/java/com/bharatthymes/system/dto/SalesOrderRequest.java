package com.bharatthymes.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SalesOrderRequest(
        String salesOrderNumber,
        LocalDate orderDate,
        LocalDate expectedDeliveryDate,
        BigDecimal totalAmount,
        String status,
        Long clientId,
        Long documentId
) {
}
