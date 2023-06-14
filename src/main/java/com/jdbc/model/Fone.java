package com.jdbc.model;

import java.util.Objects;

public class Fone {
    private Long id;
    private String numero;
    private String tipo;
    private Long usuario;

    public Fone() {
    }

    public Fone(Long id, String numero, String tipo, Long usuario) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fone)) return false;
        Fone fone = (Fone) o;
        return Objects.equals(getId(), fone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Fone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
