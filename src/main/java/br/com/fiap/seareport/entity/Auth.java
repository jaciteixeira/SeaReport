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
@Table(name = "T_OP_SR_AUTH", uniqueConstraints = {
        @UniqueConstraint(name = "UK_OP_SR_EMAIL", columnNames = "EMAIL")
})
public class Auth {
    @Id
    @Column(name = "ID_AUTH")
    private String id;
    @Column(name = "EMAIL")
    private String email;

}
