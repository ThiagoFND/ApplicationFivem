package br.com.webtiete.site.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webtiete.site.dto.DadosDTO;
import br.com.webtiete.site.exception.exceptions.ResourceNotFoundException;
import br.com.webtiete.site.mapper.DozerMapper;
import br.com.webtiete.site.model.Dados;
import br.com.webtiete.site.repositories.DadosRepository;

@Service
public class DadosService {

	private Logger logger = Logger.getLogger(DadosService.class.getName());
	
	@Autowired
	private DadosRepository dadosRepository;
	public List<DadosDTO> findAll() {

		logger.info("Finding all dados!");

		return DozerMapper.parseListObjects(dadosRepository.findAll(), DadosDTO.class);
	}

	public DadosDTO findById(String id) {

		logger.info("Finding one dados!");

		var entity = dadosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, DadosDTO.class);
	}

	public DadosDTO create(DadosDTO dados) {

		logger.info("Creating one endereço!");
		var entity = DozerMapper.parseObject(dados, Dados.class);
		var vo = DozerMapper.parseObject(dadosRepository.save(entity), DadosDTO.class);
		return vo;
	}

	public DadosDTO update(DadosDTO dados) {

		logger.info("Updating one dados!");

		var entity = dadosRepository.findById(dados.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(dados.getId());
		entity.setArtigo(dados.getArtigo());
		entity.setCrime(dados.getCrime());
		entity.setServicos(dados.getServicos());
		entity.setValor(dados.getValor());

		var vo = DozerMapper.parseObject(dadosRepository.save(entity), DadosDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one person!");

		var entity = dadosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		dadosRepository.delete(entity);
	}
}
