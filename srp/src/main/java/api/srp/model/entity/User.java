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
    private String rua;
    private String bairro;
    private String cidade;
    private String cpf;
    private String rg;
    private String telefone;
    
    // Campo para definir se o usuário é comum (0) ou administrador (1)
    private Integer adm;
    
    // ID da paróquia para administradores
    private String id_paroquia;

    public User(String id, String nome, String email, String senha, String rua, String bairro, String cidade,
			String cpf, String rg, String telefone, Integer adm, String id_paroquia) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.adm = adm;
		this.id_paroquia = id_paroquia;
	}

	// Getters e Setters

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

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Integer getAdm() {
        return adm;
    }

    public void setAdm(Integer adm) {
        this.adm = adm;
    }

    public String getIdParoquia() {
        return id_paroquia;
    }

    public void setIdParoquia(String id_paroquia) {
        this.id_paroquia = id_paroquia;
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
