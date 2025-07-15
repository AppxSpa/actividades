package com.actividades.actividades.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipoDocProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDocumento;

    @OneToMany(mappedBy = "tipoDocumento")
    private List<DocumentosProfesor> documentos;

    public TipoDocProfesor() {
    }

    public TipoDocProfesor(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public List<DocumentosProfesor> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentosProfesor> documentos) {
        this.documentos = documentos;
    }

}
