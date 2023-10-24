package br.com.webtiete.site.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webtiete.site.dto.EditalDTO;
import br.com.webtiete.site.exception.exceptions.ResourceNotFoundException;
import br.com.webtiete.site.mapper.DozerMapper;
import br.com.webtiete.site.model.Edital;
import br.com.webtiete.site.repositories.EditalRepository;

@Service
public class EditalService {

	private Logger logger = Logger.getLogger(EditalService.class.getName());

	@Autowired
	private EditalRepository editalRepository;

	public List<EditalDTO> findAll() {

		logger.info("Finding all edital!");

		return DozerMapper.parseListObjects(editalRepository.findAll(), EditalDTO.class);
	}

	public EditalDTO findById(String id) {

		logger.info("Finding one edital!");

		var entity = editalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, EditalDTO.class);
	}

	public EditalDTO create(EditalDTO edital) {

		logger.info("Creating one edital!");
		var entity = DozerMapper.parseObject(edital, Edital.class);
		var vo = DozerMapper.parseObject(editalRepository.save(entity), EditalDTO.class);
		return vo;
	}

	public EditalDTO update(EditalDTO edital) {

		logger.info("Updating one edital!");

		var entity = editalRepository.findById(edital.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(edital.getId());
		entity.setCargoo(edital.getCargoo());
		entity.setData(edital.getData());
		entity.setDiaNome(edital.getDiaNome());
		entity.setEstudo(edital.getEstudo());
		entity.setHora(edital.getHora());
		entity.setInstituicao(edital.getInstituicao());
		entity.setOrganizacao(edital.getOrganizacao());
		entity.setPolicialCadastrou(edital.getPolicialCadastrou());

		var vo = DozerMapper.parseObject(editalRepository.save(entity), EditalDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one edital!");

		var entity = editalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		editalRepository.delete(entity);
	}
}
