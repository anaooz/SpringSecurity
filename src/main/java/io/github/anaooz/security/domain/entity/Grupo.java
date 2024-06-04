package io.github.anaooz.security.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
}
