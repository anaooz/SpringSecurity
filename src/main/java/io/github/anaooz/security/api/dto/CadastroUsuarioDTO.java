package io.github.anaooz.security.api.dto;

import io.github.anaooz.security.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;

}
