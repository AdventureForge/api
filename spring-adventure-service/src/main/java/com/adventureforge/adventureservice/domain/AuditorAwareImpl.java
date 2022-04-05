package com.adventureforge.adventureservice.domain;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Toto"); //Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
