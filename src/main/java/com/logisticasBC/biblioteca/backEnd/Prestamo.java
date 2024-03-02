/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Personal
 */
public class Prestamo extends Archivo {
    public final static int MORA_A_COBRAR = 5;
    public final static int PRECIO_ALQUILER_LIBRO = 10;
    public final static int MAX_DIAS_SIN_MORA = 3;
    public final static int MAX_A_PAGAR = (MAX_DIAS_SIN_MORA * PRECIO_ALQUILER_LIBRO);
    
    private String codigoLibro;
    private int carnetEstudiante;
    private LocalDate fechaPrestamo;
    private boolean prestamoMoroso;

    //METODOS
    public int setAtributos(String[] textoLeido, int tipoArchivo){
        
        switch (textoLeido[0]) {
            
            case "PRESTAMO":
            return tipoArchivo;  
            
            case "CODIGOLIBRO": codigoLibro = textoLeido[1];
                               
            return tipoArchivo;
                
            case "CARNET": carnetEstudiante = Integer.parseInt(textoLeido[1]);
                           super.codigo = codigoLibro +"+"+ textoLeido[1];
                          
                return tipoArchivo;
                
            case "FECHA": String fecha = textoLeido[1];
                                 String[] separacionFecha = fecha.split("-");
                                 int anio = Integer.parseInt(separacionFecha[0]);
                                 int mes = Integer.parseInt(separacionFecha[1]);
                                 int dia = Integer.parseInt(separacionFecha[2]);
                                 fechaPrestamo = LocalDate.of(
                                         anio, mes, dia);
                return 0;
              
            default:
                return tipoArchivo;
        }
    }    
    
    public String getCodigoLibro() {
        return codigoLibro;
    }

    public int getCarneEstudiante() {
        return carnetEstudiante;
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

    @Override
    public String getPath() {
        return ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + super.codigo;
    }
}
