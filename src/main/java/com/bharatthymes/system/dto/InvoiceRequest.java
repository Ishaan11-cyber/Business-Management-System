package com.bharatthymes.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceRequest(String invoiceNumber,
                             LocalDate invoiceDate,
                             LocalDate dueDate,
                             BigDecimal totalAmount,
                             String status,
                             Long clientId,
                             Long documentId) {
}
