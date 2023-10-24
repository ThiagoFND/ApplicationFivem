package br.com.webtiete.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webtiete.site.model.Prisao;

public interface PrisaoRepository extends JpaRepository<Prisao, String> {

}
