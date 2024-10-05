package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "user")
public class User {
    
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    
    public User(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    

    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
		return senha;
	}
    
    // Método de verificação da senha
    public boolean isPasswordMatching(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.senha);
    }

	// Método para codificar a senha e setar
    public void setSenha(String senha, PasswordEncoder encoder) {
        this.senha = encoder.encode(senha);
    }
}
