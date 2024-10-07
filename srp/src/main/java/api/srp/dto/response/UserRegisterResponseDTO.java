package api.srp.dto.response;

public class UserRegisterResponseDTO {

	private String id;
	private String name;
	private String email;
	
	public UserRegisterResponseDTO(String id, String name, String email) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		
		
	}
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
}
