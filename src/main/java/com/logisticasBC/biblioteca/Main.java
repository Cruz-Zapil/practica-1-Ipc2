package com.logisticasBC.biblioteca;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import com.logisticasBC.biblioteca.backEnd.Archivo;
import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.backEnd.ListarFiltrarArchivos;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;

public class Main  {


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       

        //VentanaPrincipal inicio = new VentanaPrincipal();
        
        try {
           String ruta = "C:"+File.separatorChar+"Users"+File.separatorChar+"Personal"+File.separatorChar+"Documents"+File.separatorChar+"data.txt";
        //    /////////
           ControladorAchivos.leerArchivoTxt(ruta);
    //        System.out.println("archivo de texto leido");

    //      ArrayList<Libro> libros = Libro.filtrarPorTitulo("L");
    //    
    //      for (Libro libro : libros) {  
    //           System.out.println(libro.getTitulo() + " " + libro.getCodigoLibro());
    //      }
    //      System.out.println(" ");
    //       System.out.println("listado de estudiantes filtrados por carrera");
            
                ArrayList<Estudiante> estudiantes = ListarFiltrarArchivos.getEstudiantes();
    //            for (Estudiante estudiante : estudiantes) {
    //                System.out.println("");
    //                System.out.println(estudiante.getNombre());
    //                System.out.println("LIBROS QUE HA PRESTADO EL ESTUDIANTE");
    //                ArrayList<Libro> librosDelEstudiante = estudiante.getLibrosPrestados();
    //                System.out.println(librosDelEstudiante.size());
    //                for (Libro libroDelEstudiante : librosDelEstudiante) {
    //                    System.out.println(libroDelEstudiante.getTitulo() );
    //                }
    //            }


        } catch (LibreriaException e) {
            e.getMessage();
            e.printStackTrace();
        }
       
       
    }

   
}