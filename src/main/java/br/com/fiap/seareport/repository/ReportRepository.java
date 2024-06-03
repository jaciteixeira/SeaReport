package br.com.fiap.seareport.repository;

import br.com.fiap.seareport.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);
    List<Report> findByIsProcessedFalse();
}
