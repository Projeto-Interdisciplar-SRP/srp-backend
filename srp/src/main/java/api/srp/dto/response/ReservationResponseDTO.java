package api.srp.dto.response;

import java.util.Date;

import api.srp.model.entity.Bus;
import api.srp.model.entity.Local;
import api.srp.model.entity.User;

public class ReservationResponseDTO {
	
    private String id;
    private String userId;
    private String userNome;
    private String userEmail;
    private String userRua;
    private String userBairro;
    private String userCidade;
    private String userCpf;
    private String userRg;
    private String userTelefone;
    private int userAdm;
    private String userIdParoquia;
    private String localId;
    private String localNome;
    private String localRua;
    private String localBairro;
    private String localCidade;
    private String busId;
    private String busNumero;
    private String busPlacaOnibus;
    private int quantidade;
    private double preco;
    private Date dataPartida;
	public ReservationResponseDTO(String id, String userId, String userNome, String userEmail, String userRua,
			String userBairro, String userCidade, String userCpf, String userRg, String userTelefone, int userAdm,
			String userIdParoquia, String localId, String localNome, String localRua, String localBairro,
			String localCidade, String busId, String busNumero, String busPlacaOnibus, int quantidade, double preco,
			Date dataPartida) {
		super();
		this.id = id;
		this.userId = userId;
		this.userNome = userNome;
		this.userEmail = userEmail;
		this.userRua = userRua;
		this.userBairro = userBairro;
		this.userCidade = userCidade;
		this.userCpf = userCpf;
		this.userRg = userRg;
		this.userTelefone = userTelefone;
		this.userAdm = userAdm;
		this.userIdParoquia = userIdParoquia;
		this.localId = localId;
		this.localNome = localNome;
		this.localRua = localRua;
		this.localBairro = localBairro;
		this.localCidade = localCidade;
		this.busId = busId;
		this.busNumero = busNumero;
		this.busPlacaOnibus = busPlacaOnibus;
		this.quantidade = quantidade;
		this.preco = preco;
		this.dataPartida = dataPartida;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNome() {
		return userNome;
	}
	public void setUserNome(String userNome) {
		this.userNome = userNome;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRua() {
		return userRua;
	}
	public void setUserRua(String userRua) {
		this.userRua = userRua;
	}
	public String getUserBairro() {
		return userBairro;
	}
	public void setUserBairro(String userBairro) {
		this.userBairro = userBairro;
	}
	public String getUserCidade() {
		return userCidade;
	}
	public void setUserCidade(String userCidade) {
		this.userCidade = userCidade;
	}
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public String getUserRg() {
		return userRg;
	}
	public void setUserRg(String userRg) {
		this.userRg = userRg;
	}
	public String getUserTelefone() {
		return userTelefone;
	}
	public void setUserTelefone(String userTelefone) {
		this.userTelefone = userTelefone;
	}
	public int getUserAdm() {
		return userAdm;
	}
	public void setUserAdm(int userAdm) {
		this.userAdm = userAdm;
	}
	public String getUserIdParoquia() {
		return userIdParoquia;
	}
	public void setUserIdParoquia(String userIdParoquia) {
		this.userIdParoquia = userIdParoquia;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public String getLocalNome() {
		return localNome;
	}
	public void setLocalNome(String localNome) {
		this.localNome = localNome;
	}
	public String getLocalRua() {
		return localRua;
	}
	public void setLocalRua(String localRua) {
		this.localRua = localRua;
	}
	public String getLocalBairro() {
		return localBairro;
	}
	public void setLocalBairro(String localBairro) {
		this.localBairro = localBairro;
	}
	public String getLocalCidade() {
		return localCidade;
	}
	public void setLocalCidade(String localCidade) {
		this.localCidade = localCidade;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getBusNumero() {
		return busNumero;
	}
	public void setBusNumero(String busNumero) {
		this.busNumero = busNumero;
	}
	public String getBusPlacaOnibus() {
		return busPlacaOnibus;
	}
	public void setBusPlacaOnibus(String busPlacaOnibus) {
		this.busPlacaOnibus = busPlacaOnibus;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Date getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}
    
	
    
    
	
	
	
	
	
	
}
