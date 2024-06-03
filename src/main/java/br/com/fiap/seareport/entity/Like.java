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
@Table(name = "T_OP_SR_LIKE")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_LIKE")
    @SequenceGenerator(name = "SQ_OP_SR_LIKE", sequenceName = "SQ_OP_SR_LIKE", allocationSize = 1)
    @Column(name = "ID_LIKE")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_USER",
            referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_LIKE_USER")
    )
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_POST",
            referencedColumnName = "ID_POST",
            foreignKey = @ForeignKey(name = "FK_LIKE_POST")
    )
    private Post post;
}
