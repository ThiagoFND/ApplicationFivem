package br.com.webtiete.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webtiete.site.model.Edital;

public interface EditalRepository extends JpaRepository<Edital, String> {

}
