
package com.bharatthymes.system.controller;

import com.bharatthymes.system.dto.QuotationRequest;
import com.bharatthymes.system.entity.Quotation;
import com.bharatthymes.system.service.QuotationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping
    public Quotation createQuotation(@RequestBody QuotationRequest request) {
        return quotationService.createQuotation(request);
    }

    @GetMapping
    public List<Quotation> getAllQuotations() {
        return quotationService.getAllQuotations();
    }

    @GetMapping("/{id}")
    public Quotation getQuotationById(@PathVariable Long id) {
        return quotationService.getQuotationById(id);
    }

    @PutMapping("/{id}")
    public Quotation updateQuotation(
            @PathVariable Long id,
            @RequestBody QuotationRequest request) {
        return quotationService.updateQuotation(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteQuotation(@PathVariable Long id) {
        quotationService.deleteQuotation(id);
    }
}
