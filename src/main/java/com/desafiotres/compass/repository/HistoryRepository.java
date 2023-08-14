package com.desafiotres.compass.repository;

import com.desafiotres.compass.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
