package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bus")
public class Bus {
	
	@Id
	private String id;
	private String numero;
	private String placa_onibus;
	
	public Bus(String id, String numero, String placa_onibus) {
		super();
		this.id = id;
		this.numero = numero;
		this.placa_onibus = placa_onibus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPlaca_onibus() {
		return placa_onibus;
	}

	public void setPlaca_onibus(String placa_onibus) {
		this.placa_onibus = placa_onibus;
	}
	
	
	
	
	
}
