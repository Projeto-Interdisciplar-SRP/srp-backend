package api.srp.dto.request;

public class LoginRequestDTO {

	private String email;
	private String senha;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String password) {
		this.senha = password;
	}
	
}
