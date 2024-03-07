/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Brigido Alvarado
 */
public class ListarFiltrarArchivos {
  
    public static ArrayList<Archivo> getListadoOf (String rutaDirectorio) throws LibreriaException{
        
        ArrayList<Archivo> archivos = new ArrayList<>();

        File directorio = new File(rutaDirectorio);
        
        String[] rutas = directorio.list();
        for (int i = 0; i < rutas.length; i++) {
            archivos.add(
                ControladorAchivos.cargarArchivo(rutaDirectorio + File.separatorChar + rutas[i])
            );
        }
       
        return archivos;
        
    }
    


        public static ArrayList<Estudiante> getEstudiantes() throws LibreriaException {
        
        ArrayList<Archivo> listado = ListarFiltrarArchivos.getListadoOf(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
            
        for (Archivo estudiante : listado) {
                
                estudiantes.add((Estudiante)estudiante);
            }
        return estudiantes;
    }
    
    public static ArrayList<Libro> getLibros() throws LibreriaException {
        
        ArrayList<Archivo> listado = ListarFiltrarArchivos.getListadoOf(ControladorAchivos.PATH_DIRECTORIO_LIBROS);
       
        ArrayList<Libro> libros = new ArrayList<>();
            
        for (Archivo libro : listado) {
                
                libros.add((Libro)libro);
            }
        return libros;
    }
    
    public static ArrayList<Prestamo> getPrestamos() throws LibreriaException {
        
        ArrayList<Archivo> listado = ListarFiltrarArchivos.getListadoOf(ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS);
        ArrayList<Prestamo> prestamos = new ArrayList<>();
            
        for (Archivo prestamo : listado) {
                
                prestamos.add((Prestamo)prestamo);
            }
        return prestamos;
    }

    public static void ordenarListaEstudiantes (ArrayList<Estudiante> listaFiltrada){
       
        Collections.sort(listaFiltrada, new Comparator<Estudiante>() {

            @Override
            public int compare(Estudiante a1, Estudiante a2){
                int codigoA1 = Integer.parseInt(a1.getCodigo()), codigoA2 = Integer.parseInt(a2.getCodigo());
                
                return Integer.compare(codigoA1, codigoA2);
            }
            
        });
       
    }

    public static void ordenarListaLibros (ArrayList<Libro> listaFiltrada){

        Collections.sort(listaFiltrada, new Comparator <Libro> (){

            @Override
            public int compare(Libro l1, Libro l2){
                
                int noCodigoL1 = Integer.parseInt(l1.getCodigoLibro().split("-")[0]);
                int noCodigoL2 = Integer.parseInt(l2.getCodigoLibro().split("-")[0]);

                return Integer.compare(noCodigoL1, noCodigoL2);
            }
        });
    }

}
