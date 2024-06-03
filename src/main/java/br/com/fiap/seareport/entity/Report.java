package br.com.fiap.seareport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_OP_SR_REPORT")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_REPORT")
    @SequenceGenerator(name = "SQ_OP_SR_REPORT", sequenceName = "SQ_OP_SR_REPORT", allocationSize = 1)
    @Column(name = "ID_REPORT")
    private Long id;

    @Column(name = "DESC_REPORT")
    private String description;

//    @Column(name = "LAT")
//    private Long latitude;
//
//    @Column(name = "LON")
//    private Long longitude;

    @Column(name = "DATE_REPORT")
    private LocalDateTime dateReport;

    @Column(name = "PROCESSED")
    private Boolean isProcessed;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "LOCATION",
            referencedColumnName = "ID_LOCATION",
            foreignKey = @ForeignKey(name = "FK_REPORT_LOCATION")
    )
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "USER",
            referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_REPORT_USER")
    )
    private User user;
}
