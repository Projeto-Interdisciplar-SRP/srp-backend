package api.srp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Viagem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataIda;
    private LocalDateTime dataVolta;
    private String horarioIda;
    private String horarioVolta;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "paroquia_id", nullable = false)
    private Paroquia paroquia;

    private int quantidadeIngressos;
    private double precoIngresso;

    @ManyToOne
    @JoinColumn(name = "coordenador_id", nullable = false)
    private Coordenador coordenador;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataIda() {
        return dataIda;
    }

    public void setDataIda(LocalDateTime dataIda) {
        this.dataIda = dataIda;
    }

    public LocalDateTime getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(LocalDateTime dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getHorarioIda() {
        return horarioIda;
    }

    public void setHorarioIda(String horarioIda) {
        this.horarioIda = horarioIda;
    }

    public String getHorarioVolta() {
        return horarioVolta;
    }

    public void setHorarioVolta(String horarioVolta) {
        this.horarioVolta = horarioVolta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Paroquia getParoquia() {
        return paroquia;
    }

    public void setParoquia(Paroquia paroquia) {
        this.paroquia = paroquia;
    }

    public int getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(int quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(double precoIngresso) {
        this.precoIngresso = precoIngresso;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
}
