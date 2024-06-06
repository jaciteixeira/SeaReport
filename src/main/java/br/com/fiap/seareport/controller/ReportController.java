package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.ReportRequest;
import br.com.fiap.seareport.dto.response.CategoryResponse;
import br.com.fiap.seareport.dto.response.ReportResponse;
import br.com.fiap.seareport.entity.Category;
import br.com.fiap.seareport.exception.ResourceNotFoundException;
import br.com.fiap.seareport.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService service;

    @Transactional
    @PostMapping
    public ResponseEntity<ReportResponse> save(@RequestBody @Valid ReportRequest report) {
        var saved = service.save(report);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReportResponse>> findReportByUserId(
            @PathVariable Long userId,
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
        if (Objects.isNull( entity ) || entity.isEmpty()) throw new ResourceNotFoundException("Report not found!");

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
    public ResponseEntity<Page<ReportResponse>> getDisapprovedReports(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {
        var reports = service.getUnprocessedReports().stream()
                .map(service::toResponse)
                .toList();

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "dateReport");

        Page<ReportResponse> pagina = new PageImpl<>( reports, pageable, reports.size() );

        return ResponseEntity.ok( pagina );
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponse>> findCategorias() {
        List<CategoryResponse> categorias = Arrays.stream(Category.values())
                .map(categoria -> CategoryResponse.builder()
                        .id(categoria.getId())
                        .name(categoria.getName())
                        .description(categoria.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/approve-report/{id}")
    public ResponseEntity<ReportResponse> toApproveReport(@PathVariable Long id) {
        var approved = service.approve(id);
        return ResponseEntity.ok(service.toResponse(approved));
    }
}
