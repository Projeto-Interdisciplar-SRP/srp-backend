package api.srp.dto.response;

public class UserIndexResponse {
	
	private String id;
    private String nome;
    private String email;
    private String rua;
	private String bairro;
    private String cidade;
    private String cpf;
    private String rg;
    private String telefone;
    
	public UserIndexResponse(String id, String nome, String email, String rua, String bairro, String cidade, String cpf, String rg, String telefone) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
	}
	
	
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getRua() {
		return rua;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public String getCpf() {
		return cpf;
	}
	public String getRg() {
		return rg;
	}
	public String getTelefone() {
		return telefone;
	}


	
	
}
