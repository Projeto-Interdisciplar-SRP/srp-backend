package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "place")
public class Place {
	
    @Id
    private String id;
    private String destino;
    private float preco_unitario;
    private Date ida;
    private Date volta;
    
	public Place(String id, String destino, float preco_unitario, Date ida, Date volta) {
		super();
		this.id = id;
		this.destino = destino;
		this.preco_unitario = preco_unitario;
		this.ida = ida;
		this.volta = volta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public float getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(float preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public Date getIda() {
		return ida;
	}

	public void setIda(Date ida) {
		this.ida = ida;
	}

	public Date getVolta() {
		return volta;
	}

	public void setVolta(Date volta) {
		this.volta = volta;
	}
    
}