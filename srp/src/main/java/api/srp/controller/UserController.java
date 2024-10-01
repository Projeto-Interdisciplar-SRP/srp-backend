package api.srp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.srp.model.entity.User;
import api.srp.model.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	private UserRepository repository;
	
	@Autowired
	public UserController(UserRepository repository) {
		 this.repository = repository;
	}
	
	@GetMapping("/")
	public List<User> listAll() {
		return repository.findAll();
	}
	
}