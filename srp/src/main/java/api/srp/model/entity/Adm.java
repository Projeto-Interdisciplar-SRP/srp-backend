package api.srp.model.entity;

import org.springframework.data.annotation.Id;

public class Adm {
	
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
    private String id_paroquia;
    
	public Adm(String id, String nome, String email, String senha, String rua, String bairro, String cidade, String cpf,
			String rg, String telefone, String id_paroquia) {
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
		this.id_paroquia = id_paroquia;
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

	public String getId_paroquia() {
		return id_paroquia;
	}

	public void setId_paroquia(String id_paroquia) {
		this.id_paroquia = id_paroquia;
	}
    
    
	
}
