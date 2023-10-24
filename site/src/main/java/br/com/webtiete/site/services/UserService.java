package br.com.webtiete.site.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.webtiete.site.dto.UserDTO;
import br.com.webtiete.site.exception.exceptions.ResourceNotFoundException;
import br.com.webtiete.site.mapper.DozerMapper;
import br.com.webtiete.site.model.User;
import br.com.webtiete.site.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userReposiory;
	
	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	public List<UserDTO> findAll() {

		logger.info("Finding all users!");

		return DozerMapper.parseListObjects(userReposiory.findAll(), UserDTO.class);
	}

	public UserDTO findById(String id) {

		logger.info("Finding one users!");

		var entity = userReposiory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, UserDTO.class);
	}

	public UserDTO create(UserDTO user) {

		logger.info("Creating one endereço!");
		var entity = DozerMapper.parseObject(user, User.class);
		var vo = DozerMapper.parseObject(userReposiory.save(entity), UserDTO.class);
		return vo;
	}

	public UserDTO update(UserDTO user) {

		logger.info("Updating one users!");

		var entity = userReposiory.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(user.getId());
		entity.setEmail(user.getEmail());
		entity.setLogin(user.getLogin());
		entity.setNome(user.getNome());
		entity.setPassword(user.getPassword());
		entity.setRole(user.getRole());

		var vo = DozerMapper.parseObject(userReposiory.save(entity), UserDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one user!");

		var entity = userReposiory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		userReposiory.delete(entity);
	}
	
}
