package api.srp.model.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="travel")
public class Travel {
	
	@Id
	private String id;
	private String id_ingresso;
	private String	id_paroquia;
	private String id_paroquia_origem;
	private String id_onibus;
	private Date data_partida;
	private String id_place;
	
	public Travel(String id, String id_ingresso, String id_paroquia, Date data_partida, String id_onibus) {
		super();
		this.id = id;
		this.id_ingresso = id_ingresso;
		this.id_paroquia = id_paroquia;
		this.data_partida = data_partida;
		this.id_onibus = id_onibus;
	}

	public Travel(String id, String id_ingresso, String id_paroquia, String id_paroquia_origem, String id_onibus,
			Date data_partida, String id_place) {
		super();
		this.id = id;
		this.id_ingresso = id_ingresso;
		this.id_paroquia = id_paroquia;
		this.id_paroquia_origem = id_paroquia_origem;
		this.id_onibus = id_onibus;
		this.data_partida = data_partida;
		this.id_place = id_place;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_ingresso() {
		return id_ingresso;
	}

	public void setId_ingresso(String id_ingresso) {
		this.id_ingresso = id_ingresso;
	}

	public String getId_paroquia() {
		return id_paroquia;
	}

	public void setId_paroquia(String id_paroquia) {
		this.id_paroquia = id_paroquia;
	}

	public String getId_paroquia_origem() {
		return id_paroquia_origem;
	}

	public void setId_paroquia_origem(String id_paroquia_origem) {
		this.id_paroquia_origem = id_paroquia_origem;
	}

	public String getId_onibus() {
		return id_onibus;
	}

	public void setId_onibus(String id_onibus) {
		this.id_onibus = id_onibus;
	}

	public Date getData_partida() {
		return data_partida;
	}

	public void setData_partida(Date data_partida) {
		this.data_partida = data_partida;
	}

	public String getId_place() {
		return id_place;
	}

	public void setId_place(String id_place) {
		this.id_place = id_place;
	}

	
	
}
