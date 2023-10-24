package br.com.webtiete.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.webtiete.site.dto.DadosDTO;
import br.com.webtiete.site.services.DadosService;

@RestController
@RequestMapping("/api/dados")
public class DadosController {
	@Autowired
	private DadosService dadosService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DadosDTO create(@RequestBody DadosDTO dados) {
		return dadosService.create(dados);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DadosDTO> findAll() {
		return dadosService.findAll();
	}

    // endpoint pra atualizar dados
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public DadosDTO update(@PathVariable String id, @RequestBody DadosDTO dados) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'dados'
		dados.setId(id);

		// Chame o serviço 'dadosService' para atualizar os dados
		DadosDTO updatedDAdosd = dadosService.update(dados);
		return updatedDAdosd;
	}

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		dadosService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
