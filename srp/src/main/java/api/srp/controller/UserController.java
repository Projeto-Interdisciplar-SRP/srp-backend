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
@RequestMapping(path = "/user")
public class UserController {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        
	        
	        if(repository.findByEmail(user.getEmail()) != null) {
	        	
	        	return "Email já utilizado. Tente novamente com outro email.";
	        	
	        }else {
	        	
	        	user.setSenha(user.getSenha(), passwordEncoder);
	        	
	        	repository.save(user);
	        	
	        	return "Usuário registrado com sucesso!";
	        	
	        }
	        
	    }

}
