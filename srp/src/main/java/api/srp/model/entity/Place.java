package api.srp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "place")
public class Place {
    @Id
    private String id;
    private String destino;
    private Date ida;
    private Date volta;

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getIda() {
        return ida;
    }

    public void setIda(Date ida) {
        this.ida = ida;
    }

    public Date getVolta() {
        return volta;
    }

    public void setVolta(Date volta) {
        this.volta = volta;
    }

    // Construtores
    public Place() {}

    public Place(String id, String destino, Date ida, Date volta) {
        this.id = id;
        this.destino = destino;
        this.ida = ida;
        this.volta = volta;
    }
}