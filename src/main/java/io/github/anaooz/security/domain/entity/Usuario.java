package io.github.anaooz.security.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String login;
    private String senha;
    private String nome;

    @Transient //ignorar mapeamento jpa
    private List<String> permissoes;
}
