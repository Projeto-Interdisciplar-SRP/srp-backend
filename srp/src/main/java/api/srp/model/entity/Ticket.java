package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ticket")
public class Ticket {

    @Id
    private String id;

    @Field("id_usuario") // Mapeia o campo no banco de dados como id_usuario
    private String idUsuario; // Nome da propriedade em camelCase

    private Integer quantidade;
    private float preco;
    private String status;
    private String type;

    public Ticket(String id, String idUsuario, Integer quantidade, float preco, String status, String type) {
        super();
        this.id = id;
        this.idUsuario = idUsuario;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
