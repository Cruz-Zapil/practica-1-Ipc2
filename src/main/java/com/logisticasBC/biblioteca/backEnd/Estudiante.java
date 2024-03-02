/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class Estudiante extends Archivo {
    
    private int carnet = 0;
    private String nombre;
    private int carrera;
    
    private LocalDate fechaNacimiento;
    
    private String[] codigoLibrosPrestados = new String[3];
    private ArrayList<Prestamo> prestamosRealizados;
    
    private int contLibrosEnPosecion = 0;
   
    public int setAtributos(String[] textoLeido, int tipoArchivo){

         switch (textoLeido[0]) {

             case "ESTUDIANTE":
             return tipoArchivo;  

             case "CARNET": carnet = Integer.parseInt(textoLeido[1]);
                            super.codigo = textoLeido[1];
                            
             return tipoArchivo;

             case "NOMBRE": nombre = textoLeido[1];
                 return tipoArchivo;
                 
             case "CARRERA": carrera = Integer.parseInt(textoLeido[1]);
                 return 0;
                 
            default:
                return ControladorAchivos.ERROR;
         }
    }
    
    public void prestamosDisponibles(String codigoLibro)throws LibreriaException{

        int contadorRegistros = 0;
        
        for (int i = 0; i < codigoLibrosPrestados.length; i++) {
            
            
            if (
                    codigoLibrosPrestados[i].equals(codigoLibro)
                    ) {
                throw new LibreriaException("El estudiante ya cuenta con una copia del libro");
                
            }
            
           if (
                    codigoLibrosPrestados[i] != null
                    ) {
                contadorRegistros++;
                
            }
        }
        
        if (contadorRegistros == codigoLibrosPrestados.length) {
            throw new LibreriaException("El estudiante ya no puede realizar mas prestamos");
        }
    }
    
    //Si el estudiante puede realilzar el prestamo el nuevo prestamo se guarda en el arrayList
    public void actualizarRegistroAlRealizarPrestamo(Prestamo nuevoPrestamo){
        prestamosRealizados.add(nuevoPrestamo);
    }
    
    /*El estudiante devuelve el libro
         se elimina el registro en el arreglo de codigos de libros prestados
        */
    public void actualizarRegistroAlDevolverLibro(String codigoLibroADevolver){
        
        for (int i = 0; i < codigoLibrosPrestados.length; i++) {
            
            if (
                    codigoLibrosPrestados[i].equals(codigoLibroADevolver)
                    ) {
                codigoLibrosPrestados[i] = null;
            }
        }
    }

    public int getCarnet(){
        return carnet;
    }

    @Override
    public String getPath() {

        return ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + super.codigo;
    }
    
}
