package br.com.webtiete.site.dto;

import br.com.webtiete.site.model.UserRole;

public record RegisterDTO(String nome, String email, String login, String password, UserRole role) {
}
