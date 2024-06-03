package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.ReportRequest;
import br.com.fiap.seareport.dto.response.ReportResponse;
import br.com.fiap.seareport.entity.Report;
import br.com.fiap.seareport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService service;

    @Transactional
    @PostMapping
    public ResponseEntity<ReportResponse> submitReport(@RequestBody ReportRequest report) {
        var saved = service.save(report);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReportResponse>> getUserReports(@PathVariable Long userId) {
        List<Report> reports = service.getReportsByUserId(userId);
        List<ReportResponse> reportResponses = reports.stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportResponses);
    }


    @GetMapping("/unprocessed")
    public ResponseEntity<List<ReportResponse>> getUnprocessedReports() {
        List<Report> reports = service.getUnprocessedReports();
        List<ReportResponse> reportResponses = reports.stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportResponses);
    }
}
