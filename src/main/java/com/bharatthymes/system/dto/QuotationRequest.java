package com.bharatthymes.system.dto;

import java.time.LocalDate;

public record QuotationRequest(String quotationNumber,
                               LocalDate quotationDate,
                               String status,
                               Long clientId,
                               Long documentId) {
}
