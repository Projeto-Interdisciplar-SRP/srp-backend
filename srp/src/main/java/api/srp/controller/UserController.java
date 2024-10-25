package api.srp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import api.srp.dto.request.UserUpdateRequestDTO;
import api.srp.dto.response.LoginResponseDTO;
import api.srp.dto.response.UserIndexResponse;
import api.srp.dto.response.UserRegisterResponseDTO;
import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Bus;
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
        	
            // Se o usuário não for admin (adm == 0), seta o idParoquia como null
            if (user.getAdm() == 0) {
                user.setIdParoquia(null);
            }
        	
        	repository.save(user);
        	
        	UserRegisterResponseDTO userRegisterData = new UserRegisterResponseDTO(user.getId(), user.getNome(),user.getEmail(), user.getRua(), user.getBairro(), user.getCidade(), user.getCpf(), user.getRg(), user.getTelefone(), user.getAdm());
        	
        	WrapperResponseDTO<UserRegisterResponseDTO> response = new WrapperResponseDTO<UserRegisterResponseDTO>(true, "Usuário registrado com sucesso!", userRegisterData);
        	
        	return response;
        	
        }
        
    }
    
    @PatchMapping("/change-password")
    public WrapperResponseDTO<Void> changePassword(@RequestPart("id_user") String id_user, @RequestPart("current_password") String currentPassword, @RequestPart("new_password") String newPassword) {

        
        User userFound = this.repository.findById(id_user).orElse(null);

        if (userFound != null) {
            
            if (userFound.isPasswordMatching(currentPassword, passwordEncoder)) {
                
                userFound.setSenha(passwordEncoder.encode(newPassword));
                this.repository.save(userFound);

                WrapperResponseDTO<Void> response = new WrapperResponseDTO<>(true, "Senha alterada com sucesso.", null);
                return response;
            } else {
                WrapperResponseDTO<Void> response = new WrapperResponseDTO<>(false, "Senha atual inválida.", null);
                return response;
            }
        } else {
            WrapperResponseDTO<Void> response = new WrapperResponseDTO<>(false, "Usuário não encontrado.", null);
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
    		
    		LoginResponseDTO login = new LoginResponseDTO(foundUser.getId(), foundUser.getNome(),foundUser.getEmail(), foundUser.getRua(), foundUser.getBairro(), foundUser.getCidade(), foundUser.getCpf(), foundUser.getRg(), foundUser.getTelefone(), foundUser.getAdm());
    		
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
    
    @GetMapping("/{id}")
    public WrapperResponseDTO<User> getBusById(@PathVariable String id) {
        Optional<User> user = repository.findById(id);
        
        if (user.isPresent()) {
            return new WrapperResponseDTO<User>(true, "Usuário encontrado com sucesso!", user.get());
        } else {
            return new WrapperResponseDTO<>(false, "Usuário não encontrado!", null);
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
