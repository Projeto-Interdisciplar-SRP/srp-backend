package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection="coordinator")
public class Coordinator {

	@Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String rua;
	private String bairro;
    private String cidade;
    private String cpf;
    private String rg;
    private String telefone;
    
    public Coordinator(String nome, String email, String senha) {
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
    
    public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getRg() {
		return rg;
	}



	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    //metodo de verificação da senha
    public boolean isPasswordMatching(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.senha);
    }

	//metodo para codificar a senha e setar
    public void setSenha(String senha, PasswordEncoder encoder) {
        this.senha = encoder.encode(senha);
    }
    
    
	
}
