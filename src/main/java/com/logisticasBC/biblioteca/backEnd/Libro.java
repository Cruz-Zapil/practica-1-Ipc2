/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author BrigidoAlvarado
 */
public class Libro extends Archivo {

    private static final long serialVersionUID = 16549498498410L;
    
    private String titulo;
    private String autor;
    private String codigoLibro;
    private int cantCopiasDisponibles;
    private LocalDate fechaPublicacion;
    private String editorial;

    public Libro (){    }

    public Libro(String titulo, String autor, String codigoLibro, int cantCopiasDisponibles, LocalDate fechaPublicacion, String editorial) throws LibreriaException {
        this.titulo = titulo;
        this.autor = autor;
        this.codigoLibro = codigoLibro;
        super.codigo = codigoLibro;
        this.cantCopiasDisponibles = cantCopiasDisponibles;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
        
        if (super.archivoExite(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + this.codigoLibro)) {
            Libro libroExistente = (Libro)ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + this.codigoLibro);
            libroExistente.setCantCopiasDisponibles(cantCopiasDisponibles);
            throw new LibreriaException("El libro ya esta registrado en la biblioteca se actualizo el registro de libros disponibles en la biblioteca");

<<<<<<< HEAD
        } else {
            super.actualizar();
        }
=======
  
>>>>>>> 9db60078edf64b6a84ed577575797766d8acd093

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
    
    public void devolverLibro() throws LibreriaException{
        cantCopiasDisponibles++;
        super.actualizar();
    }
    
    public void prestarLibro() throws LibreriaException {
        cantCopiasDisponibles--;
        super.actualizar();
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
    public void setTitulo(String titulo) throws LibreriaException {
        this.titulo = titulo;
        super.actualizar();
    }

    public void setAutor(String autor) throws LibreriaException {
        this.autor = autor;
        super.actualizar();
    }

    public void setCodigoLibro(String codigoLibro) throws LibreriaException {
        this.codigoLibro = codigoLibro;
        super.actualizar();
    }

    public void setCantCopiasDisponibles(int cantCopiasDisponibles) throws LibreriaException {
        this.cantCopiasDisponibles += cantCopiasDisponibles;
        super.actualizar();
    }

    public void setFechaPublicacion(int anio, int mes, int dia) throws LibreriaException {
        this.fechaPublicacion = LocalDate.of(anio, mes, dia);
        super.actualizar();
    }

    public void setEditorial(String editorial) throws LibreriaException {
        this.editorial = editorial;
        super.actualizar();
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

    public static ArrayList<Libro> filtrarPorAutor (String filtro) throws LibreriaException {

        ArrayList <Libro> listaLibros = ListarFiltrarArchivos.getLibros();
        ArrayList <Libro> listaFiltrada = new ArrayList<>();

        for (Libro libro : listaFiltrada) {
            if (libro.getAutor().startsWith(filtro)) {
                listaFiltrada.add(libro);
            }
        }
        return listaFiltrada;
    }
}
