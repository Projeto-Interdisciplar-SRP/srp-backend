package api.srp.dto.request;

import java.sql.Date;

public class ReservationUpdateRequestDTO {
	
	private String id_onibus;
	private String id_paroquia;
	private Date data_partida;
	private String id_usuario;
	private String id_place;
	private float preco;
	private Integer quantidade;
	private String type;
	
	public ReservationUpdateRequestDTO(String id_onibus, String id_paroquia, Date data_partida, String id_usuario,
			String id_place, float preco, Integer quantidade, String type) {
		super();
		this.id_onibus = id_onibus;
		this.id_paroquia = id_paroquia;
		this.data_partida = data_partida;
		this.id_usuario = id_usuario;
		this.id_place = id_place;
		this.preco = preco;
		this.quantidade = quantidade;
		this.type = type;
	}

	public String getId_onibus() {
		return id_onibus;
	}

	public void setId_onibus(String id_onibus) {
		this.id_onibus = id_onibus;
	}

	public String getId_paroquia() {
		return id_paroquia;
	}

	public void setId_paroquia(String id_paroquia) {
		this.id_paroquia = id_paroquia;
	}

	public Date getData_partida() {
		return data_partida;
	}

	public void setData_partida(Date data_partida) {
		this.data_partida = data_partida;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_place() {
		return id_place;
	}

	public void setId_place(String id_place) {
		this.id_place = id_place;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
