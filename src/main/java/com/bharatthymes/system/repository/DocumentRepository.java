package com.bharatthymes.system.repository;

import com.bharatthymes.system.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface DocumentRepository  extends JpaRepository<Document, Long> {

    List<Document> findByCategoryIgnoreCase(String category);
}
