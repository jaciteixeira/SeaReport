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
@Table(name = "T_OP_SR_POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_POST")
    @SequenceGenerator(name = "SQ_OP_SR_POST", sequenceName = "SQ_OP_SR_POST", allocationSize = 1)
    @Column(name = "ID_POST")
    private Long id;

    @Column(name = "CONTENT_POST")
    private String content;

    @Column(name = "DATE_POST")
    private LocalDateTime date;

}
