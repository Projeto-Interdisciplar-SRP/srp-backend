package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ticket")
public class Ticket {

	@Id
	private String id;
	private String id_usuario;
	private Integer quantidade;
	private float preco;
	
	public Ticket(String id, String id_usuario, Integer quantidade, float preco) {
		
		this.id = id;
		this.id_usuario = id_usuario;
		this.quantidade = quantidade;
		this.preco = preco;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
}
