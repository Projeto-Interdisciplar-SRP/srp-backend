package api.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.srp.dto.request.LoginRequestDTO;
import api.srp.dto.response.LoginResponseDTO;
import api.srp.model.entity.User;
import api.srp.model.repository.UserRepository;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
	
	private UserRepository repo;//var para manipular a dependencia MongoRepositoryORM
	private PasswordEncoder passwordEncoder;
	
    //dando a injeção de dependencia nas depencias necessarias..
	@Autowired
	public AuthController(UserRepository repositoryParam, PasswordEncoder passwordEncoder) {
		 this.repo = repositoryParam;
		 this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO login){
		
		User userFound = this.repo.findByEmail(login.getEmail());
		
		if(userFound != null) {
			
			if(userFound.isPasswordMatching(login.getPassword(), passwordEncoder)) {
				
				LoginResponseDTO response = new LoginResponseDTO(userFound.getId(), userFound.getNome(),userFound.getEmail());
				
				return response;
				
			}else {
				
				return null;
				
			}
			
		}else {
			
			return null;
			
		}
		
	}
	
}
