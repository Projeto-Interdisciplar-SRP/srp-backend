package api.srp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.srp.dto.response.UserIndexResponse;
import api.srp.dto.response.UserRegisterResponseDTO;
import api.srp.dto.response.WrapperResponseDTO;
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
    public WrapperResponseDTO<UserRegisterResponseDTO> register(@RequestBody User user) {
        
        
        if(repository.findByEmail(user.getEmail()) != null) {
        	
        	WrapperResponseDTO<UserRegisterResponseDTO> response = new WrapperResponseDTO<UserRegisterResponseDTO>(false, "Email já utilizado. Tente novamente com outro email.", null);
        			
        	return response;
        	
        }else {
        	
        	user.setSenha(user.getSenha(), passwordEncoder);
        	
        	repository.save(user);
        	
        	UserRegisterResponseDTO userRegisterData = new UserRegisterResponseDTO(user.getId(), user.getNome(), user.getEmail());
        	
        	WrapperResponseDTO<UserRegisterResponseDTO> response = new WrapperResponseDTO<UserRegisterResponseDTO>(false, "Usuário registrado com sucesso!", userRegisterData);
        	
        	return response;
        	
        }
        
    }
    
    @GetMapping("/")
    public List<UserIndexResponse> index(){
    	
    	List<User> listUser = this.repository.findAll();
    	
    	List<UserIndexResponse> listResponse = new ArrayList<UserIndexResponse>();
    	
    	for (User itUser : listUser) {
			
    		String currentName = itUser.getNome();
    		String currentId = itUser.getId();
    		String currentEmail = itUser.getEmail();
    		
    		UserIndexResponse currentUser = new UserIndexResponse(currentId, currentName, currentEmail);
    		
    		listResponse.add(currentUser);
    		
		}
    	
    	return listResponse;
    	
    }

}
