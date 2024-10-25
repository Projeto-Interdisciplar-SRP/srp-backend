package api.srp.dto.request;

public class ChangePasswordDTO {
    private String id_usuario;
    private String senha_atual;
    private String nova_senha;

    // Getters and Setters
    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSenha_atual() {
        return senha_atual;
    }

    public void setSenha_atual(String senha_atual) {
        this.senha_atual = senha_atual;
    }

    public String getNova_senha() {
        return nova_senha;
    }

    public void setNova_senha(String nova_senha) {
        this.nova_senha = nova_senha;
    }
}