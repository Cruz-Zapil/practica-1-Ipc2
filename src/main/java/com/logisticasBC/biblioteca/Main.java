package com.logisticasBC.biblioteca;


import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;

public class Main  {


    public static void main( String[] args )
    {
        
        ControladorAchivos.crearDirectorios();        

        new VentanaPrincipal();
   
    }

   
}