package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.ReportRequest;
import br.com.fiap.seareport.dto.response.ReportResponse;
import br.com.fiap.seareport.entity.Report;
import br.com.fiap.seareport.service.ReportService;
import br.com.fiap.seareport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService service;
    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping
    public ResponseEntity<ReportResponse> save(@RequestBody ReportRequest report) {
        var saved = service.save(report);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @GetMapping("/user")
    public ResponseEntity<Page<ReportResponse>> getUserReport(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {

        var entity = service.getReportsByUserId(userId);
        if (Objects.isNull( entity ) || entity.isEmpty()) return ResponseEntity.notFound().build();

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "userId" );

        var response = entity.stream().map( service::toResponse ).toList();

        Page<ReportResponse> pagina = new PageImpl<>( response, pageable, response.size() );

        return ResponseEntity.ok( pagina );
    }

    @GetMapping("/disapproved")
    public ResponseEntity<List<ReportResponse>> getDisapprovedReports() {
        List<Report> reports = service.getUnprocessedReports();
        List<ReportResponse> reportResponses = reports.stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportResponses);
    }
}
