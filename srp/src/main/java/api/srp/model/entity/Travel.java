package api.srp.model.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "travel")
public class Travel {

    @Id
    private String id;

    @Field("id_ingresso") // Mapeia o campo no banco de dados para id_ingresso
    private String idIngresso; // Nome da propriedade em camelCase

    @Field("id_paroquia")
    private String idParoquia;

    @Field("id_paroquia_origem")
    private String idParoquiaOrigem;

    @Field("id_onibus")
    private String idOnibus;

    @Field("data_partida")
    private Date dataPartida;

    @Field("id_place")
    private String idPlace;

    public Travel(String id, String idIngresso, String idParoquia, String idParoquiaOrigem, String idOnibus,
                  Date dataPartida, String idPlace) {
        super();
        this.id = id;
        this.idIngresso = idIngresso;
        this.idParoquia = idParoquia;
        this.idParoquiaOrigem = idParoquiaOrigem;
        this.idOnibus = idOnibus;
        this.dataPartida = dataPartida;
        this.idPlace = idPlace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(String idIngresso) {
        this.idIngresso = idIngresso;
    }

    public String getIdParoquia() {
        return idParoquia;
    }

    public void setIdParoquia(String idParoquia) {
        this.idParoquia = idParoquia;
    }

    public String getIdParoquiaOrigem() {
        return idParoquiaOrigem;
    }

    public void setIdParoquiaOrigem(String idParoquiaOrigem) {
        this.idParoquiaOrigem = idParoquiaOrigem;
    }

    public String getIdOnibus() {
        return idOnibus;
    }

    public void setIdOnibus(String idOnibus) {
        this.idOnibus = idOnibus;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }
}
