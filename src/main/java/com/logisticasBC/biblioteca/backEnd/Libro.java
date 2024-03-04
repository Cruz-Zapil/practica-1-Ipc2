/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import com.vaadin.shared.ui.datefield.LocalDateFieldState;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author Personal
 */
public class Libro extends Archivo {

    private static final long serialVersionUID = 16549498498410L;

    private String titulo;
    private String autor;
    private String codigoLibro;
    private int cantCopiasDisponibles;
    private LocalDate fechaPublicacion;
    private String editorial;

    public Libro (){
        
    }

    public Libro(String titulo, String autor, String codigoLibro, int cantCopiasDisponibles, LocalDate fechaPublicacion, String editorial) throws LibreriaException {
        this.titulo = titulo;
        this.autor = autor;
        this.codigoLibro = codigoLibro;
        super.codigo = codigoLibro;
        this.cantCopiasDisponibles = cantCopiasDisponibles;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;

  

    }

    
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
    
    //GETERS 
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
    
    //SETTERS

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public void setCantCopiasDisponibles(int cantCopiasDisponibles) {
        this.cantCopiasDisponibles = cantCopiasDisponibles;
    }

    public void setFechaPublicacion(int anio, int mes, int dia) {
        this.fechaPublicacion = LocalDate.of(anio, mes, dia);
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    //FILTRAR LISTA DE LIBROS
    public static ArrayList <Libro> filtrarPorCodigo (String filtro ) throws LibreriaException {

        ArrayList <Libro> listaLibros = ListarFiltrarArchivos.getLibros();
        ArrayList <Libro> listaFiltrada = new ArrayList<>();

        for (Libro libro : listaLibros) {
            if (libro.getCodigoLibro().startsWith(filtro)) {
                
                listaFiltrada.add(libro);
            }
        }
        ListarFiltrarArchivos.ordenarListaLibros(listaFiltrada);
        return listaFiltrada;
    }

    public static ArrayList<Libro> filtrarPorTitulo(String filtro) throws LibreriaException {

        ArrayList <Libro> listaLibros = ListarFiltrarArchivos.getLibros();
        ArrayList <Libro> listaFiltrada = new ArrayList<>();

        for (Libro libro : listaLibros) {
            if (libro.getTitulo().startsWith(filtro)) {
                listaFiltrada.add(libro);
            }
        }

        return listaFiltrada;
    }
    
}
