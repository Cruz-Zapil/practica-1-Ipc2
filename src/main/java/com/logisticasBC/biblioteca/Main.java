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
           String ruta = "C:"+File.separatorChar+"Users"+File.separatorChar+"Personal"+File.separatorChar+"Documents"+File.separatorChar+"data.txt";
           ArrayList<String> lineasMalLeidas = ControladorAchivos.leerArchivoTxt(ruta);
           System.out.println();
           for (String string : lineasMalLeidas) {
                System.out.println(string);
           }
           
           //System.out.println("DEVOLVER LIBRO");
           //Prestamo prestamo = (Prestamo) ControladorAchivos.cargarArchivo(
           //ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + "040-DEF+00198763);
           //Estudiante estudiante1 = (Estudiante)ControladorAchivos.cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
          
        } catch (LibreriaException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

   
}