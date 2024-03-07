package com.logisticasBC.biblioteca;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import com.logisticasBC.biblioteca.backEnd.Archivo;
import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.backEnd.ListarFiltrarArchivos;
import com.logisticasBC.biblioteca.backEnd.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;

public class Main  {


    public static void main( String[] args )
    {
       System.out.println( "Hello World!" );
 
        //VentanaPrincipal inicio = new VentanaPrincipal();
        
         try {
           //String ruta = "C:"+File.separatorChar+"Users"+File.separatorChar+"Personal"+File.separatorChar+"Documents"+File.separatorChar+"data.txt";
           //ArrayList<String> lineasMalLeidas = ControladorAchivos.leerArchivoTxt(ruta);
           
           System.out.println("DEVOLVER LIBRO");
           Prestamo prestamo = Prestamo.cargarPrestamoActivo("00543208", "031-ABC");
           System.out.println(prestamo);

           Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
           System.out.println(estudiante.getNombre() + " " + estudiante.getLibrosPrestados().size());
           
           Libro libro = (Libro) ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + prestamo.getCodigoLibro());
           System.out.println(libro.getTitulo() + libro.getCantCopiasDisponibles());
           
           prestamo.devolverLibro();
           System.out.println();

           Libro libro1 = (Libro) ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + prestamo.getCodigoLibro());
           System.out.println(libro1.getTitulo() + libro1.getCantCopiasDisponibles());
           
           Estudiante estudiante1 = (Estudiante)ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
           System.out.println(estudiante1.getNombre() + " " + estudiante1.getLibrosPrestados().size()); 

        } catch (LibreriaException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

   
}