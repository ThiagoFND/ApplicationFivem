package br.com.webtiete.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webtiete.site.model.Dados;

public interface DadosRepository extends JpaRepository<Dados, String>{

}
