package io.github.anaooz.security.domain.security;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificacaoUsuario {

    private String id;
    private String nome;
    private String login;
    private List<String> permissoes;

    public List<String> getPermissoes() {
        if(permissoes == null) {
            permissoes = new ArrayList<>();
        }

        return permissoes;
    }
}
