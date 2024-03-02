/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;


/**
 *
 * @author Personal
 */
public class Libro extends Archivo {
    
    private String titulo;
    private String autor;
    private String codigoLibro;
    private int cantCopiasDisponibles;
    private LocalDate fechaPublicacion;
    private String editorial;
    
    public int setAtributos(String[] textoLeido, int tipoArchivo){
        
        switch (textoLeido[0]) {
            
            case "LIBRO":
            return tipoArchivo;  
            
            case "TITULO": titulo = textoLeido[1];
            return tipoArchivo;
                
            case "AUTOR": autor = textoLeido[1];
                return tipoArchivo;

            case "CODIGO": 
                codigoLibro = textoLeido[1];
                super.codigo = this.codigoLibro;
                if (codigoLibro == null) {
                    return ControladorAchivos.ERROR;
                }
                return tipoArchivo;

             case "CANTIDAD": cantCopiasDisponibles = Integer.parseInt(textoLeido[1]);
                return 0; 

            default:
                return ControladorAchivos.ERROR;
        }
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

    public String getCodigoLibro() {
        return codigoLibro;
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

    @Override
    public String getPath() {
        return ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + super.codigo;
    }
    
    
}
