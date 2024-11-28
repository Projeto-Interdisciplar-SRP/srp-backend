package api.srp.dto.response;

import java.time.LocalDateTime;
import api.srp.model.entity.Local;

public class PlaceResponseDTO {
    private String id;
    private Local idParoquia;
    private LocalDateTime ida;
    private LocalDateTime volta;

    // Construtor
    public PlaceResponseDTO(String id, Local idParoquia, LocalDateTime ida, LocalDateTime volta) {
        this.id = id;
        this.idParoquia = idParoquia;
        this.ida = ida;
        this.volta = volta;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Local getIdParoquia() {
        return idParoquia;
    }

    public void setIdParoquia(Local idParoquia) {
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

