package api.srp.dto.request;

import java.sql.Date;

public class ReservationUpdateRequestDTO {
	
	private String id_onibus;
	private String id_paroquia;
	private Date data_partida;
	private String id_usuario;
	private float preco;
	private Integer quantidade;
	
	public ReservationUpdateRequestDTO(String id_onibus, String id_paroquia, Date data_partida, String id_usuario,
			float preco, Integer quantidade) {
		super();
		this.id_onibus = id_onibus;
		this.id_paroquia = id_paroquia;
		this.data_partida = data_partida;
		this.id_usuario = id_usuario;
		this.preco = preco;
		this.quantidade = quantidade;
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
	
	
	
}
