package br.com.fiap.seareport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_OP_SR_LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_LOCATION")
    @SequenceGenerator(name = "SQ_OP_SR_LOCATION", sequenceName = "SQ_OP_SR_LOCATION", allocationSize = 1)
    @Column(name = "ID_LOCATION")
    private Long id;

    @Column(name = "LAT")
    private Double latitude;

    @Column(name = "LON")
    private Double longitude;

}
