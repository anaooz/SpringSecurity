package io.github.anaooz.security.config;

import io.github.anaooz.security.domain.security.CustomAuthentication;
import io.github.anaooz.security.domain.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();
        var senha = (String) authentication.getCredentials();

        String loginMaster = "master";
        String senhaMaster = "@321";

        if(loginMaster.equals(login) && senhaMaster.equals(senha)) {
            IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
                "Sou master",
                    "Master",
                    loginMaster,
                    List.of("ADMIN")
            );
            return new CustomAuthentication(identificacaoUsuario);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; //suporta esse tipo de autenticação? t/f
    }
}
