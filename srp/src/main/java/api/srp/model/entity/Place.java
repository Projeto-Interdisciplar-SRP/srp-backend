package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "place")
public class Place {

    @Id
    private String id;

    private String idParoquia;

    private LocalDateTime ida;

    private LocalDateTime volta;

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParoquia() {
        return idParoquia;
    }

    public void setIdParoquia(String idParoquia) {
        this.idParoquia = idParoquia;
    }

    public LocalDateTime getIda() {
        return ida;
    }

    public void setIda(LocalDateTime ida) {
        this.ida = ida;
    }

    public LocalDateTime getVolta() {
        return volta;
    }

    public void setVolta(LocalDateTime volta) {
        this.volta = volta;
    }
}
