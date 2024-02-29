package com.logisticasBC.biblioteca;

<<<<<<< HEAD
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;
=======
import com.logisticasBC.biblioteca.frontEnd.VentanaPrincipal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
>>>>>>> main

import java.time.LocalDate;

import java.time.LocalDate;

import java.time.LocalDate;

public class Main  {


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

       // VentanaPrincipal inicio = new VentanaPrincipal();
       
       LocalDate fecha = LocalDate.now();
        System.out.println("la fecha de hoy es "+fecha);
        // Definir las fechas
        LocalDate fecha1 = LocalDate.of(2024, 2, 20);
        LocalDate fecha2 = LocalDate.of(2024, 2, 25);
        
        // Calcular la diferencia en días
        long diferenciaEnDias = ChronoUnit.DAYS.between(fecha1, fecha2);
        
        // Imprimir la diferencia en días
        System.out.println("Diferencia en días: " + diferenciaEnDias);
    }
}
