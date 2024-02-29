/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Personal
 */
public class Prestamo {
    public final static int MORA_A_COBRAR = 5;
    public final static int PRECIO_ALQUILER_LIBRO = 10;
    public final static int MAX_DIAS_SIN_MORA = 3;
    public final static int MAX_A_PAGAR = (MAX_DIAS_SIN_MORA * PRECIO_ALQUILER_LIBRO);
    
    private String codigoLibro;
    private int carneEstudiante;
    private LocalDate fechaPrestamo;
    private boolean prestamoMoroso;

    //METODOS

    public Prestamo(String codigoLibro, int carneEstudiante) {
        this.codigoLibro = codigoLibro;
        this.carneEstudiante = carneEstudiante;
        this.fechaPrestamo = LocalDate.now();
    }
    
    
    
    public String getCodigoLibro() {
        return codigoLibro;
    }

    public int getCarneEstudiante() {
        return carneEstudiante;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public boolean isPrestamoMoroso() {
        return prestamoMoroso;
    }
    
    
    //se calcula el pago a pagar 
    public int calcularPago(){
        int cantidadPago = 0;
        
        LocalDate fechaActual = LocalDate.now();
        
        long diasDelPrestamo = ChronoUnit.DAYS.between(
                fechaActual, fechaPrestamo);
        
        int dias = (int)diasDelPrestamo;

        //se calcula la cantidad a pagar
        if (dias > MAX_DIAS_SIN_MORA) {
            
            cantidadPago = MAX_A_PAGAR + (dias * MORA_A_COBRAR);
        } else {
            
            cantidadPago = dias * PRECIO_ALQUILER_LIBRO;
        }
        
        return cantidadPago;
    }
}
