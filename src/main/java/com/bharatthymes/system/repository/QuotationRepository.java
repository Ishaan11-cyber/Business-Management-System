package com.bharatthymes.system.repository;

import com.bharatthymes.system.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

}
