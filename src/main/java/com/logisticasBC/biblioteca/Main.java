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
import com.logisticasBC.biblioteca.backEnd.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;

public class Main  {


    public static void main( String[] args )
    {
       System.out.println( "Hello World!" );
 
        //VentanaPrincipal inicio = new VentanaPrincipal();
        
        try {
           String ruta = "C:"+File.separatorChar+"Users"+File.separatorChar+"Personal"+File.separatorChar+"Documents"+File.separatorChar+"data.txt";
           //ControladorAchivos.leerArchivoTxt(ruta);
           
           System.out.println("DEVOLVER LIBRO");
           Prestamo prestamo = (Prestamo) ControladorAchivos.cargarArchivo(
            ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + "040-DEF+00198763"
           );
           System.out.println("DATOS DEL PRESTAMO");
           System.out.println(prestamo.getCodigo());
           System.out.println("DATOS DEL ESTUDIANTE");
           Estudiante estudiante = (Estudiante)ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
           System.out.println(estudiante.getNombre() + " " + estudiante.getCarnet());
           System.out.println(estudiante.getLibrosPrestados().size());
           System.out.println("DATOS LIBRO");
           Libro libro = (Libro) ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + prestamo.getCodigoLibro());
           System.out.println(libro.getTitulo() + " " + libro.getCodigoLibro());
           System.out.println("copias disponibles: "+libro.getCantCopiasDisponibles());
           
           prestamo.devolverLibro();

           System.out.println("");
           System.out.println("DATOS DEL PRESTAMO ACTUALIZADO");
           System.out.println(prestamo.getCodigo());

           System.out.println("DATOS DEL ESTUDIANTE");

           Estudiante estudiante1 = (Estudiante)ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
           System.out.println(estudiante1.getNombre() + " " + estudiante1.getCarnet());
           System.out.println(estudiante1.getLibrosPrestados().size());
           System.out.println("DATOS LIBRO");
           Libro libro1 = (Libro) ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + prestamo.getCodigoLibro());
           System.out.println(libro1.getTitulo() + " " + libro1.getCodigoLibro());
           System.out.println("copias disponibles: "+libro1.getCantCopiasDisponibles());
           estudiante1.actualizar();
           libro1.actualizar();

        } catch (LibreriaException e) {
            e.getMessage();
            e.printStackTrace();
        }
       
    }

   
}