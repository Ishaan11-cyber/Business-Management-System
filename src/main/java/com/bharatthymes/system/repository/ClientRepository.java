package com.bharatthymes.system.repository;

import com.bharatthymes.system.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
