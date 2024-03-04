/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class Prestamo extends Archivo {

    private static final long serialVersionUID = 1654741963498410L;
    public final static int MORA_A_COBRAR = 5;
    public final static int PRECIO_ALQUILER_LIBRO = 10;
    public final static int MAX_DIAS_SIN_MORA = 3;
    public final static int MAX_A_PAGAR = (MAX_DIAS_SIN_MORA * PRECIO_ALQUILER_LIBRO);
    
    private String codigoLibro;
    private String carnetEstudiante;
    private LocalDate fechaPrestamo;
    
    //METODOS
    public Prestamo(){}

    public Prestamo(String codigoLibro, String carnetEstudiante, LocalDate fechaPrestamo) throws LibreriaException {
        this.codigoLibro = codigoLibro;
        this.carnetEstudiante = carnetEstudiante;
        this.fechaPrestamo = fechaPrestamo;
        actualizarPrestamo();
    }

    public int setAtributos(String[] textoLeido, int tipoArchivo){
        
        switch (textoLeido[0]) {
            
            case "PRESTAMO":
            return tipoArchivo;  
            
            case "CODIGOLIBRO": codigoLibro = textoLeido[1];
                               
            return tipoArchivo;
                
            case "CARNET": carnetEstudiante = textoLeido[1];
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
    
    //Se calcula si el prestamo es moroso
    public boolean prestamoMoroso (){
        
        boolean prestamoMoroso = false;
        LocalDate fechaHoy = LocalDate.now();
        int diasDelPrestamo = (int) ChronoUnit.DAYS.between(fechaHoy, fechaPrestamo);
        
        if (diasDelPrestamo > MAX_DIAS_SIN_MORA) {
            return true;
        }
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
    
    //Actualizar Datos del estudiante que realizo el prestamo
    public void actualizarPrestamo () throws LibreriaException {
        
        ArrayList<Estudiante> estudiantes = ListarFiltrarArchivos.getEstudiantes();

        for (Estudiante estudiante : estudiantes) {
            
            if (estudiante.getCarnet().equals(carnetEstudiante)) {
                
                estudiante.actualizarRegistroAlRealizarPrestamo(this);
            }
        }
    }

    //GETTERS
    public String getCodigoLibro() {
        return codigoLibro;
    }

    public String getCarneEstudiante() {
        return carnetEstudiante;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    
     @Override
    public String getPath() {
        return ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + super.codigo;
    }
    
    //SETTERS
    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public void setCarnetEstudiante(String carnetEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
    }

    
    public void setFechaPrestamo( int anio, int mes, int dia) {
        this.fechaPrestamo = LocalDate.of(anio, mes, dia);
    }
    

    
    /* 
    //FILTROS (PARTE DE LOS REPORTES)
<<<<<<< HEAD
    public static ArrayList<Prestamo>() {

=======
    public static ArrayList<Prestamo> filtarPrestamosPor(){
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        
        return prestamos;
        
>>>>>>> 33e756ef5da274fb2ea50115dcf83ca51d9b4a44
    }
    */
}
