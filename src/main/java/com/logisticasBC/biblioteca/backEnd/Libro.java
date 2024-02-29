/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.time.LocalDate;


/**
 *
 * @author Personal
 */
public class Libro {
    
    private String titulo;
    private String autor;
    private String codigo;
    private int cantCopiasDisponibles;
    private LocalDate fechaPublicacion;
    private String editorial;

    public Libro(String titulo, String autor, String codigo, int cantCopiasDisponibles, LocalDate fechaPublicacion, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.cantCopiasDisponibles = cantCopiasDisponibles;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }
    
    public Libro(String titulo, String autor, String codigo, int cantCopiasDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.cantCopiasDisponibles = cantCopiasDisponibles;
    } 
    
    public boolean copiasDisponibles(){
        
        return cantCopiasDisponibles != 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantCopiasDisponibles() {
        return cantCopiasDisponibles;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }
    
    
}
