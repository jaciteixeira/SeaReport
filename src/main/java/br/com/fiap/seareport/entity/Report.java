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

    @Column(name = "DATE_REPORT")
    private LocalDateTime dateReport;

    @Column(name = "CATEGORY")
    private Category category;

    @Column(name = "APPROVED")
    private Boolean approved;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_LOCATION",
            referencedColumnName = "ID_LOCATION",
            foreignKey = @ForeignKey(name = "FK_REPORT_LOCATION")
    )
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_USER",
            referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_REPORT_USER")
    )
    private User user;
}
