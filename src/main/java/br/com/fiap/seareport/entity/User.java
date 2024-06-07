package br.com.fiap.seareport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_OP_SR_USER", uniqueConstraints = {
        @UniqueConstraint(name = "UK_OP_SR_USER_ID_AUTH", columnNames = "ID_AUTH")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_USER")
    @SequenceGenerator(name = "SQ_OP_SR_USER", sequenceName = "SQ_OP_SR_USER", allocationSize = 1)
    @Column(name = "ID_USER")
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "XP")
    private Integer xp;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_AUTH",
            referencedColumnName = "ID_AUTH",
            foreignKey = @ForeignKey(name = "FK_USER_AUTH")
    )
    private Auth auth;
}
