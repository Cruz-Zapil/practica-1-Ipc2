package com.logisticasBC.biblioteca;

import java.io.File;
import java.util.ArrayList;

import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;

public class Main  {


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       

       // VentanaPrincipal inicio = new VentanaPrincipal();
        try {
            String ruta = "C:"+File.separatorChar+"Users"+File.separatorChar+"Personal"+File.separatorChar+"Documents"+File.separatorChar+"data.txt";
            /////////
            ArrayList<String> lineasMalLeidas = ControladorAchivos.leerArchivoTxt(ruta);
            ///////////////
            System.out.println("<<LINEAS EN MAL ESTADO>>");
            for (String linea : lineasMalLeidas) {
                System.out.println(linea);
            }       
        } catch (LibreriaException e) {
            System.out.println("tira un error en el metodo leer archivo");
            e.getMessage();
            e.printStackTrace();
        }
       
       
    }
}