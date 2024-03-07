/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Brigido Alvarado
 */
public class Prestamo extends Archivo {

    private static final long serialVersionUID = 1654741963498410L;
    public final static int MORA_A_COBRAR = 10;
    public final static int PRECIO_ALQUILER_LIBRO = 5;
    public final static int MAX_DIAS_SIN_MORA = 3;
    public final static int MAX_A_PAGAR = (MAX_DIAS_SIN_MORA * PRECIO_ALQUILER_LIBRO);
    
    private String codigoLibro;
    private String carnetEstudiante;
    private LocalDate fechaPrestamo;
    private boolean prestamoActivo = true;
    private int dineroRecaudadoSinMora = 0;
    private int dineroRecaudadoMora = 0;
    
    //METODOS
    public Prestamo(){}


    public Prestamo(String codigoLibro, String carnetEstudiante, LocalDate fechaPrestamo) throws LibreriaException {
        this.codigoLibro = codigoLibro;
        this.carnetEstudiante = carnetEstudiante;
        this.codigo = this.codigoLibro + "+" + this.carnetEstudiante + "+" + random();
        this.fechaPrestamo = fechaPrestamo;
        actualizarDatosEstudianteYLibro();
    }

    public int setAtributos(String[] textoLeido, int tipoArchivo){
        
        try {
           
            switch (textoLeido[0]) {
                
                case "PRESTAMO":
                return tipoArchivo;  
                
                case "CODIGOLIBRO": codigoLibro = textoLeido[1];
                                   
                return tipoArchivo;
                    
                case "CARNET": carnetEstudiante = textoLeido[1];
                               super.codigo = codigoLibro +"+"+ textoLeido[1] + "+" + random();
                              
                    return tipoArchivo;
                    
                case "FECHA": String fecha = textoLeido[1];
                                     String[] separacionFecha = fecha.split("-");
                                     int anio = Integer.parseInt(separacionFecha[0]);
                                     int mes = Integer.parseInt(separacionFecha[1]);
                                     int dia = Integer.parseInt(separacionFecha[2]);
                                     fechaPrestamo = LocalDate.of(anio, mes, dia);
                                     
                    return 0;
                  
                default:
                    return ControladorAchivos.ERROR;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ControladorAchivos.ERROR;

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
    
    //se calcula el monto a pagar 
    public int calcularPago(){
                
        LocalDate fechaActual = LocalDate.now();
        
        int dias = (int) ChronoUnit.DAYS.between(fechaPrestamo, fechaActual);
        //Convertir a positivo
        if (dias < 0) {
            dias *= -1;
        }

        //se calcula la cantidad a pagar
        System.out.println("fecha prestamo: "+fechaPrestamo+" fecha hoy: " + fechaActual + "dias entre fechas: "+dias);
        
        if (dias > MAX_DIAS_SIN_MORA) {

            dineroRecaudadoSinMora = MAX_A_PAGAR;
            dineroRecaudadoMora = (dias - MAX_A_PAGAR) * MORA_A_COBRAR;

        } else {
            
            dineroRecaudadoSinMora = dias * PRECIO_ALQUILER_LIBRO;
        }
        
        return dineroRecaudadoMora + dineroRecaudadoSinMora;
    }
    
    //Actualizar los registros del estudiante y del libro cuando se devuelve un libro
    public void devolverLibro() throws LibreriaException{

        Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo(
            ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + carnetEstudiante);

        Libro libro = (Libro) ControladorAchivos.cargarArchivo(
            ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + codigoLibro);

        
        estudiante.actualizarRegistroAlDevolverLibro(codigoLibro);
        libro.devolverLibro();
        prestamoActivo = false;
        super.actualizar();
    }

    //Actualizar Datos del estudiante que realizo el prestamo
    public void aniadirLibroPrestamoAEstudiante () throws LibreriaException {
        
        ArrayList<Estudiante> estudiantes = ListarFiltrarArchivos.getEstudiantes();

        for (Estudiante estudiante : estudiantes) {
            
            if (estudiante.getCarnet().equals(carnetEstudiante)) {
                
                estudiante.aniadirPrestamo(this);
            }
        }
    }

    private void actualizarDatosEstudianteYLibro() throws LibreriaException{
        
        Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo( ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + carnetEstudiante);
        estudiante.aniadirPrestamo(this);
        Libro libro = (Libro) ControladorAchivos.cargarArchivo( ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + codigoLibro);
        libro.prestarLibro();
        
        
    }
    
    private int random (){
        Random random = new Random();
        return random.nextInt(90000) + 10000;
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
    
    public boolean isPrestamoActivo (){
        return prestamoActivo;
    }

     @Override
    public String getPath() {
        return ControladorAchivos.PATH_DIRECTORIO_PRESTAMOS + File.separatorChar + super.codigo;
    }

    public int getDineroRecaudadoSinMora() {
        return dineroRecaudadoSinMora;
    }

    public int getDineroRecaudadoMora() {
        return dineroRecaudadoMora;
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
    

    
    
    //FILTROS (PARTE DE LOS REPORTES)
    public static ArrayList<Prestamo> prestamosQueVencenHoy() throws LibreriaException {
       
        ArrayList<Prestamo> prestamos = ListarFiltrarArchivos.getPrestamos();
        ArrayList<Prestamo> listaFiltrada = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            
            LocalDate fechaPrestamo = prestamo.getFechaPrestamo();
            LocalDate fechaHoy = LocalDate.now();
            int diasDelPrestamo = (int) ChronoUnit.DAYS.between(fechaHoy, fechaPrestamo);

            if (diasDelPrestamo == MAX_DIAS_SIN_MORA) {
                listaFiltrada.add(prestamo);
            }


        }
        return prestamos;
    }

    public static ArrayList<Prestamo> prestamosMorosos() throws LibreriaException {
       
        ArrayList<Prestamo> prestamos = ListarFiltrarArchivos.getPrestamos();
        ArrayList<Prestamo> listaFiltrada = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {

            if (prestamo.prestamoMoroso()) {
                listaFiltrada.add(prestamo);
            }

        }
        return prestamos;
    }

    public static ArrayList<Prestamo> prestamosCanceladosIntervaloTiempo(LocalDate limInferior, LocalDate limSuperior) throws LibreriaException {
        
        ArrayList<Prestamo> prestamos = ListarFiltrarArchivos.getPrestamos();
        ArrayList<Prestamo> listaFiltrada = new ArrayList<>();

       //caso 1 no se indica un intervalo de tiempo
        if (limInferior == null && limSuperior == null) {
            for (Prestamo prestamo : prestamos) {

                if (!prestamo.isPrestamoActivo()) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 2 solo se indica la fecha superior
        else if (limInferior == null) {
            for (Prestamo prestamo : prestamos) {

                if (!prestamo.isPrestamoActivo() && prestamo.getFechaPrestamo().isBefore(limSuperior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 3 solo se indica la fecha inferior
        else if (limSuperior == null) {
            for (Prestamo prestamo : prestamos) {

                if (!prestamo.isPrestamoActivo() && prestamo.getFechaPrestamo().isAfter(limInferior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 4 se indica el intervalo de tiempo
        else {
            for (Prestamo prestamo : prestamos) {

                if (!prestamo.isPrestamoActivo() && prestamo.getFechaPrestamo().isAfter(limInferior)  && prestamo.getFechaPrestamo().isBefore(limSuperior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
       
        return prestamos;
    }

    public static ArrayList <Prestamo> prestamosHechosPorCarreraIntervalotTiempo (LocalDate fechaInferior, LocalDate fechaSuperior, int carrera) throws LibreriaException{
        
        ArrayList<Prestamo> prestamos = ListarFiltrarArchivos.getPrestamos();
        ArrayList<Prestamo> listaFiltrada = new ArrayList<>();

       //caso 1 no se indica un intervalo de tiempo
        if (fechaInferior == null && fechaSuperior == null) {
            for (Prestamo prestamo : prestamos) {

                Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo(
                     ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());

                if (estudiante.getCarrera() == carrera) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 2 solo se indica la fecha superior
        else if (fechaInferior == null) {
            for (Prestamo prestamo : prestamos) {

                Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo(
                    ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
                
                if ( estudiante.getCarrera() == carrera && prestamo.getFechaPrestamo().isBefore(fechaSuperior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 3 solo se indica la fecha inferior
        else if (fechaSuperior == null) {
            for (Prestamo prestamo : prestamos) {

                Estudiante estudiante = (Estudiante) ControladorAchivos.cargarArchivo(
                    ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + prestamo.getCarneEstudiante());
                
                if (estudiante.getCarrera() == carrera && prestamo.getFechaPrestamo().isAfter(fechaInferior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
        //caso 4 se indica el intervalo de tiempo
        else {
            for (Prestamo prestamo : prestamos) {

                if (!prestamo.isPrestamoActivo() && prestamo.getFechaPrestamo().isAfter(fechaInferior)  && prestamo.getFechaPrestamo().isBefore(fechaSuperior)) {
                    listaFiltrada.add(prestamo);
                }
    
            }
        }
       
        return prestamos;
    }

    //CARGAR UN PRESTAMO
    public static Prestamo cargarPrestamoActivo (String carnet, String codigoLibro) throws LibreriaException {

        ArrayList<Prestamo> prestamos = ListarFiltrarArchivos.getPrestamos();
        Prestamo prestamoBuscado = null;

        for (Prestamo prestamo : prestamos) {
            
            if ( prestamo.getCodigo().startsWith(codigoLibro + "+" + carnet) && prestamo.isPrestamoActivo() ) {
                
                System.out.println(prestamo.isPrestamoActivo());
                prestamoBuscado = prestamo;
                
            } 
        }

        if(prestamoBuscado == null) {
            throw new LibreriaException("No se ecuentran concidencias de prestamos activos con este numero de carnet y este codigo de libro.");
        }

        return prestamoBuscado;
    }
    
}
