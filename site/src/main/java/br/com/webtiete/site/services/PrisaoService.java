package br.com.webtiete.site.services;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.webtiete.site.dto.PrisaoDTO;
import br.com.webtiete.site.exception.exceptions.ResourceNotFoundException;
import br.com.webtiete.site.mapper.DozerMapper;
import br.com.webtiete.site.model.Prisao;
import br.com.webtiete.site.repositories.PrisaoRepository;

@Service
public class PrisaoService {


	private Logger logger = Logger.getLogger(PrisaoService.class.getName());
	
	@Autowired
	private PrisaoRepository prisaoRepository;
    
	public List<PrisaoDTO> findAll() {

		logger.info("Finding all dados!");

		return DozerMapper.parseListObjects(prisaoRepository.findAll(), PrisaoDTO.class);
	}

	public PrisaoDTO findById(String id) {

		logger.info("Finding one dados!");

		var entity = prisaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PrisaoDTO.class);
	}

	public PrisaoDTO create(PrisaoDTO prisao) {

		logger.info("Creating one endereço!");
		var entity = DozerMapper.parseObject(prisao, Prisao.class);
		var vo = DozerMapper.parseObject(prisaoRepository.save(entity), PrisaoDTO.class);
		return vo;
	}

	public PrisaoDTO update(PrisaoDTO dados) {

		logger.info("Updating one dados!");

		var entity = prisaoRepository.findById(dados.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(dados.getId());
		
		var vo = DozerMapper.parseObject(prisaoRepository.save(entity), PrisaoDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one person!");

		var entity = prisaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		prisaoRepository.delete(entity);
	}

}
