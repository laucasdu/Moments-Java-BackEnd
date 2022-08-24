package com.factoria.moments.auth.facade;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade { // és un component que és reutilitzable a qualsevol servei, s'injectarà directament en l'autowire, contructor, inclus dintre d'una funció, etc.
    @Autowired
    AuthRepository authRepository;

    public Optional<User> getAuthUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByUsername(userName); //get();
    }
}
