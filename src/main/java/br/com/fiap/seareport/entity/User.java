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
@Table(name = "T_OP_SR_USER")
public class User extends RepresentationModel<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OP_SR_USER")
    @SequenceGenerator(name = "SQ_OP_SR_USER", sequenceName = "SQ_OP_SR_USER", allocationSize = 1)
    @Column(name = "ID_USER")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "XP")
    private Long xp;
}
