package io.github.anaooz.security.domain.repository;

import io.github.anaooz.security.domain.entity.Usuario;
import io.github.anaooz.security.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {

    @Query("SELECT DISTINCT g.nome from UsuarioGrupo ug JOIN ug.grupo g JOIN ug.usuario u WHERE u = ?1")
    List<String> findPermissoesByUsuario(Usuario usuario);
}
