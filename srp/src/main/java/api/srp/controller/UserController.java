package api.srp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.srp.model.entity.User;
import api.srp.model.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	private UserRepository repository;//var para manipular a dependencia MongoRepositoryORM
	
    private PasswordEncoder password;//var para dar hash na senha usando a dependencia passwordEncoder..
	
    //dando a injeção de dependencia nas depencias necessarias..
	@Autowired
	public UserController(UserRepository repositoryParam, PasswordEncoder passwordParam) {
		 this.repository = repositoryParam;
		 this.password = passwordParam;
	}
	
	@GetMapping("/")
	public List<User> listAll() {
		return repository.findAll();
	}
	
	@PostMapping("/register")
	public User insert(@RequestBody User user) {
		
		user.setSenha(password.encode( user.getSenha() ));
		
		return repository.save(user);
		
	}
	
}