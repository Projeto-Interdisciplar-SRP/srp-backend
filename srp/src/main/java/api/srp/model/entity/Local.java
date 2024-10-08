package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="local")
public class Local {

	@Id
	private String id;
	private String nome;
	private String rua;
	private String bairro;
    private String cidade;
    private String id_coordenador;
    
	public Local(String id, String nome, String rua, String bairro, String cidade, String id_coordenador) {
		super();
		this.id = id;
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.id_coordenador = id_coordenador;
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

	public String getId_coordenador() {
		return id_coordenador;
	}

	public void setId_coordenador(String id_coordenador) {
		this.id_coordenador = id_coordenador;
	}
    
    
	
}