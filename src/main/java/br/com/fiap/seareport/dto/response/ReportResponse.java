package br.com.fiap.seareport.dto.response;

import br.com.fiap.seareport.entity.Location;
import jakarta.persistence.Column;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReportResponse(
        Long id,
        String description,
        Location location,
        LocalDateTime dateReport,
        Boolean isProcessed
) {
}
