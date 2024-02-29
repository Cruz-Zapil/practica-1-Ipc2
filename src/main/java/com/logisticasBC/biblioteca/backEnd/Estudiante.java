/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class Estudiante {
    
    private int carne;
    private String nombre;
    private int codigoCarrera;
    private LocalDate fechaNacimiento;
    private String[] codigoLibrosPrestados = new String[3];
    private ArrayList<Prestamo> prestamosRealizados;
    private int contLibrosEnPosecion = 0;

    //constructor con solo los datos necesarios de un estudiante
    public Estudiante(int carne, String nombre, int codigoCarrera) {
        this.carne = carne;
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
    }
    // constructor con todos los datos que puede llegar a tener un estudiante
    public Estudiante(int carne, String nombre, int codigoCarrera, LocalDate fechaNacimiento) {
        this.carne = carne;
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.fechaNacimiento = fechaNacimiento;
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
    
}
