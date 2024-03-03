/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class ControladorAchivos {

    public static final String PATH_DIRECTORIO_PRINCIPAL = "archivos";
    public static final String PATH_DIRECTORIO_LIBROS = PATH_DIRECTORIO_PRINCIPAL + File.separatorChar + "libros";
    public static final String PATH_DIRECTORIO_ESTUDIANTES = PATH_DIRECTORIO_PRINCIPAL + File.separatorChar + "estudiantes";
    public static final String PATH_DIRECTORIO_PRESTAMOS = PATH_DIRECTORIO_PRINCIPAL + File.separatorChar + "prestamos";
    //Para detectar que objeto debe contruir
    public static final int LIBRO = 1;
    public static final int ESTUDIANTE = 2;
    public static final int PRESTAMO = 3;
    public static final int ERROR = 4;

    //VARIABLES
    private static int tipoArchivo;
    
    //METODOS
    public static void crearDirectorios(){

        File directorio = new File (PATH_DIRECTORIO_PRINCIPAL);
        File directorioEstudiantes = new File (PATH_DIRECTORIO_ESTUDIANTES);
        File directorioLibros = new File (PATH_DIRECTORIO_LIBROS);
        File directorioPrestamos = new File (PATH_DIRECTORIO_PRESTAMOS);
        //ARCHIVOS
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        //ESTUDIANTES
        if (!directorioEstudiantes.exists()) {
            directorioEstudiantes.mkdir();
        }
        //LIBROS
        if (!directorioLibros.exists()) {
            directorioLibros.mkdir();
        }
        //PRESTAMOS
        
        if (!directorioPrestamos.exists()) {
            directorioPrestamos.mkdir();
        }
        

    }
    
    public  static ArrayList<String> leerArchivoTxt(String pathArchivoTxt) throws LibreriaException{
        
        crearDirectorios();
        tipoArchivo = 0;
        ArrayList<String> lineasMalLeidas = new ArrayList<String>();
        File archivoTexto = new File(pathArchivoTxt);
       
            Libro libro = null;
            Estudiante estudiante = null;
            Prestamo prestamo = null;
        
        try ( FileReader fileReader = new FileReader(archivoTexto);
              BufferedReader bufferedReader = new BufferedReader(fileReader);) 
        {
            String textoLeido;
            
            while ((textoLeido = bufferedReader.readLine()) != null){
                                                
                switch (textoLeido) {
                    
                    case ("LIBRO"):
                            tipoArchivo = LIBRO;
                            libro = new Libro();
                        break;

                    case ("ESTUDIANTE"):
                            tipoArchivo = ESTUDIANTE;
                            estudiante = new Estudiante();
                        break;
                       
                    case ("PRESTAMO"): 
                            tipoArchivo = PRESTAMO;
                            prestamo = new Prestamo();
                        break;
                }
                switch (tipoArchivo) {
                    case LIBRO:
                        
                        String[] infLibro = textoLeido.split(":");
                        tipoArchivo = libro.setAtributos(infLibro, tipoArchivo);
                        
                        if (tipoArchivo == 0) {
                            guardarArchivo(libro);
                            libro = null;
                        } else if (tipoArchivo == ERROR && !textoLeido.trim().isEmpty()) {
                            tipoArchivo = 0;
                            libro = null;
                            lineasMalLeidas.add(textoLeido);
                        }
                        break;
                        
                    case ESTUDIANTE:
                        String[] infEstudiante = textoLeido.split(":");
                        tipoArchivo = estudiante.setAtributos(infEstudiante, tipoArchivo);
                        if (tipoArchivo == 0) {
                            guardarArchivo(estudiante);
                            estudiante = null;
                        } else if (tipoArchivo == ERROR && !textoLeido.trim().isEmpty()) {
                            tipoArchivo = 0;
                            estudiante = null;
                            lineasMalLeidas.add(textoLeido);
                        }
                        
                        break;
                    
                    case PRESTAMO:
                        String[] infPrestamo = textoLeido.split(":");
                        tipoArchivo = prestamo.setAtributos(infPrestamo, tipoArchivo);
                        if (tipoArchivo == 0) {
                            guardarArchivo(prestamo);
                            prestamo = null;
                        }
                        
                        break;
                    default:
                        
                    if (!textoLeido.trim().isEmpty()) {
                        lineasMalLeidas.add(textoLeido);
                    }
                    
                        
                }


            }

            return lineasMalLeidas; 
            
        } catch (Exception e) {
            throw new LibreriaException("Error al cargar los datos del archivo de texto");
        }

    }
    
    public static void guardarArchivo( Archivo archivo) throws LibreriaException{
        
        File archivoJugador = new File(archivo.getPath());
        
        try ( FileOutputStream fileOutputStream = new FileOutputStream(archivoJugador);
              ObjectOutputStream binarioJugador = new ObjectOutputStream(fileOutputStream);
        ){
            binarioJugador.writeObject(archivo);
            binarioJugador.close();
        } catch (IOException e) {
            throw new LibreriaException("Error al guardar el archivo");
            
        }
    }
             
}
