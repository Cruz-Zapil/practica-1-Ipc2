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
           //ArrayList<Prestamo> prestamosMorosos = Prestamo.prestamosMorosos();
           //System.out.println("PRESTAMOS MOROSOS");

           /*for (Prestamo prestamo : prestamosMorosos) {
                System.out.println(prestamo.getCarneEstudiante() +" "+ prestamo.getCodigoLibro() + " Cant a pagar: " + prestamo.calcularPago());
           }*/

           ArrayList<Estudiante> estudiantes = ListarFiltrarArchivos.getEstudiantes();
           
           for (Estudiante estudiante : estudiantes) {

                System.out.println(estudiante.getNombre() + " " + estudiante.getCarnet());
                ArrayList<Libro> libros = estudiante.getLibrosPrestados();
                System.out.println(libros.size());
           }
           
           Prestamo prestamo = 
                (Prestamo)ControladorAchivos.cargarArchivo(
                    ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + "040-DEF+00198763");
           
           System.out.println(prestamo.getCodigo());
           
           prestamo.devolverLibro();

            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-");
            System.out.println("   PRESTAMO ELIMINADO");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-");
            
            for (Estudiante estudiante : estudiantes) {

                System.out.println(estudiante.getNombre() + " " + estudiante.getCarnet());
                ArrayList<Libro> libros = estudiante.getLibrosPrestados();
                System.out.println(libros.size());
           }
   
        } catch (LibreriaException e) {
            e.getMessage();
            e.printStackTrace();
        }
       
       
        

    }

   
}