package api.srp.dto.response;

import java.util.Date;

public class ReservationResponseDTO {
	
	private String id_viajante;
	private String rua_local;
	private String bairro_local;
	private String cidade_local;
	private String coordinator_local;
	private Date data_partida;
	private String preco;
	private String quantidade;
	
	public ReservationResponseDTO(String id_viajante, String rua_local, String bairro_local, String cidade_local,
			String coordinator_local, Date data_partida, String preco, String quantidade) {
		super();
		this.id_viajante = id_viajante;
		this.rua_local = rua_local;
		this.bairro_local = bairro_local;
		this.cidade_local = cidade_local;
		this.coordinator_local = coordinator_local;
		this.data_partida = data_partida;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getId_viajante() {
		return id_viajante;
	}

	public void setId_viajante(String id_viajante) {
		this.id_viajante = id_viajante;
	}

	public String getRua_local() {
		return rua_local;
	}

	public void setRua_local(String rua_local) {
		this.rua_local = rua_local;
	}

	public String getBairro_local() {
		return bairro_local;
	}

	public void setBairro_local(String bairro_local) {
		this.bairro_local = bairro_local;
	}

	public String getCidade_local() {
		return cidade_local;
	}

	public void setCidade_local(String cidade_local) {
		this.cidade_local = cidade_local;
	}

	public String getCoordinator_local() {
		return coordinator_local;
	}

	public void setCoordinator_local(String coordinator_local) {
		this.coordinator_local = coordinator_local;
	}

	public Date getData_partida() {
		return data_partida;
	}

	public void setData_partida(Date data_partida) {
		this.data_partida = data_partida;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
