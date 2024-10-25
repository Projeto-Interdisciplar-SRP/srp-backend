package api.srp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.srp.dto.request.UserUpdateRequestDTO;
import api.srp.dto.response.LoginResponseDTO;
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
        	
        	UserRegisterResponseDTO userRegisterData = new UserRegisterResponseDTO(user.getId(), user.getNome(),user.getEmail(), user.getRua(), user.getBairro(), user.getCidade(), user.getCpf(), user.getRg(), user.getTelefone());
        	
        	WrapperResponseDTO<UserRegisterResponseDTO> response = new WrapperResponseDTO<UserRegisterResponseDTO>(true, "Usuário registrado com sucesso!", userRegisterData);
        	
        	return response;
        	
        }
        
    }
    
    @PutMapping("/edit")
    public WrapperResponseDTO<LoginResponseDTO> update(@RequestBody UserUpdateRequestDTO update){
    	
    	Optional<User> user = repository.findById(update.getId());
    	
    	if (user.isPresent()) {
    		
    		User foundUser = user.get();
    		
    		foundUser.setNome(update.getNome());
    		foundUser.setEmail(update.getEmail());
    		foundUser.setCpf(update.getCpf());
    		foundUser.setRg(update.getRg());
    		foundUser.setRua(update.getRua());
    		foundUser.setBairro(update.getBairro());
    		foundUser.setCidade(update.getCidade());
    		foundUser.setTelefone(update.getTelefone());
    		
    		repository.save(foundUser);
    		
    		LoginResponseDTO login = new LoginResponseDTO(foundUser.getId(), foundUser.getNome(), foundUser.getEmail(), foundUser.getRua(), foundUser.getBairro(), foundUser.getCidade(), foundUser.getCpf(), foundUser.getRg(), foundUser.getTelefone());
    		
    		WrapperResponseDTO<LoginResponseDTO> response = new WrapperResponseDTO<>(true ,"Usuário editado com sucesso!", login);
    		
    		return response;
    		
    	}else {
    		
    		WrapperResponseDTO<LoginResponseDTO> userNotFound = new WrapperResponseDTO<>(false,"Erro ao encontrar usuário.",null);
    		return userNotFound;
    		
    	}
    	
    }
    
    @DeleteMapping("/delete/{id}")
    public WrapperResponseDTO<Void> delete(@PathVariable("id") String id) {
    	
    	if (repository.findById(id).isPresent()) {
    		
            repository.deleteById(id);
            
            return new WrapperResponseDTO<>(true, "Usuário excluído com sucesso!", null);
    		
    	}else {

    		return new WrapperResponseDTO<>(false, "Erro ao encontrar usuário.", null);
    	}
    	
    }
    
    @GetMapping("/")
    public List<UserIndexResponse> index(){
    	
    	List<User> listUser = this.repository.findAll();
    	
    	List<UserIndexResponse> listResponse = new ArrayList<UserIndexResponse>();
    	
    	for (User itUser : listUser) {

    		UserIndexResponse currentUser = new UserIndexResponse(itUser.getId(), itUser.getNome(),itUser.getEmail(), itUser.getRua(), itUser.getBairro(), itUser.getCidade(), itUser.getCpf(), itUser.getRg(), itUser.getTelefone());
    		
    		listResponse.add(currentUser);
    		
		}
    	
    	return listResponse;
    	
    }

}
