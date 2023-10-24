package br.com.webtiete.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.webtiete.site.dto.PrisaoDTO;
import br.com.webtiete.site.services.PrisaoService;

@RestController
@RequestMapping("/api/prisao")
public class PrisaoController {

	@Autowired
	private PrisaoService prisaoService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PrisaoDTO create(@RequestBody PrisaoDTO prisao) {
		return prisaoService.create(prisao);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PrisaoDTO> findAll() {
		return prisaoService.findAll();
	}

    // endpoint pra atualizar dados
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public PrisaoDTO update(@PathVariable String id, @RequestBody PrisaoDTO prisao) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'dados'
		prisao.setId(id);

		// Chame o serviço 'dadosService' para atualizar os dados
		PrisaoDTO updatedDAdosd = prisaoService.update(prisao);
		return updatedDAdosd;
	}

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
    	prisaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
