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
    private static final long serialVersionUID = 1698752310098498410L;
    private static final int MAX_LIBROS = 3;
    
    private String carnet;
    private String nombre;
    private int carrera;
    private LocalDate fechaNacimiento;
    
    private ArrayList<String> codigoLibrosPrestados = new ArrayList();
    private ArrayList<Prestamo> prestamosRealizados = new ArrayList<>();
       
    public int setAtributos(String[] textoLeido, int tipoArchivo){

         switch (textoLeido[0]) {

             case "ESTUDIANTE":
             return tipoArchivo;  

             case "CARNET": carnet = textoLeido[1];
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
    
    public void prestamosDisponible(String codigoLibro)throws LibreriaException{

        if (codigoLibrosPrestados.size() == MAX_LIBROS ) {
             throw  new LibreriaException("El esudiante ya no tiene m�s prestamos disponibles");
        } else {
            for (String codigoLibroPrestado : codigoLibrosPrestados) {
                if (codigoLibroPrestado.equals(codigoLibro)) {
                    
                    throw new LibreriaException("El estudiante ya cuenta con una copia de este libro");
                }
            }
        }
        
    }
    
    //Si el estudiante puede realilzar el prestamo el nuevo prestamo se guarda en el arrayList
    public void actualizarRegistroAlRealizarPrestamo  (Prestamo nuevoPrestamo)throws LibreriaException{
      
        prestamosRealizados.add(nuevoPrestamo);
        codigoLibrosPrestados.add(nuevoPrestamo.getCodigoLibro());
        super.actualizar();
        System.out.println("actualizando prestamo del estudiante");
    }
    
    /*El estudiante devuelve el libro se elimina el registro en el arreglo de codigos de libros prestados*/
    public void actualizarRegistroAlDevolverLibro(String codigoLibroADevolver){
        
        for (String codigoLibrosPrestado : codigoLibrosPrestados) {
            
            if (codigoLibrosPrestado.equals(codigoLibroADevolver)) {
                codigoLibrosPrestados.remove(codigoLibroADevolver);
            }
        }
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public int getCarrera() {
        return carrera;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public ArrayList<Prestamo> getPrestamosRealizados() {
        return prestamosRealizados;
    }
    
    public String getCarnet(){
        return carnet;
    }

    public Estudiante (){
        
    }

    public Estudiante(String carnet, String nombre, int carrera, LocalDate fechaNacimiento) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.carrera = carrera;
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String getPath() {

        return ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + super.codigo;
    }
    
    //SETTERS
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ArrayList<Libro> getLibrosPrestados () throws LibreriaException {

        ArrayList<Libro> librosPrestados = new ArrayList<>();
        
        for (String codigo : codigoLibrosPrestados) {
            System.out.println(codigo);
        }
        
        for (String codigoLibro : codigoLibrosPrestados) {
            
            Libro libroPrestado = (Libro)ControladorAchivos.cargarArchivo(
                ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + codigoLibro);
            librosPrestados.add(libroPrestado);
        }
        return librosPrestados;
    }
    
   //LISTAS FILTRADAS
   public static ArrayList<Estudiante> filtrarPorCarnet (String filtro) throws LibreriaException {

        ArrayList <Estudiante> listaEstudiantes = ListarFiltrarArchivos.getEstudiantes();
        ArrayList<Estudiante> listaFiltrada = new ArrayList<>();

        for (Estudiante estudiante : listaEstudiantes) {
            
            String carnet = String.valueOf(estudiante.getCarnet());
           
            if (carnet.startsWith(filtro)) {
                listaFiltrada.add(estudiante);
            }
        }
        //Ordenar la lista filtrada
        ListarFiltrarArchivos.ordenarListaEstudiantes(listaFiltrada);
        
        return listaFiltrada;
   } 

   public static ArrayList<Estudiante> filtrarPorNombre (String filtro) throws LibreriaException{
        ArrayList <Estudiante> listaEstudiantes = ListarFiltrarArchivos.getEstudiantes();
        ArrayList<Estudiante> listaFiltrada = new ArrayList<>();

        for (Estudiante estudiante : listaEstudiantes) {
            
            if (estudiante.getNombre().startsWith(filtro)) {
                listaFiltrada.add(estudiante);
            }
        }

        ListarFiltrarArchivos.ordenarListaEstudiantes(listaFiltrada);
        return listaFiltrada;
   }

   public static ArrayList<Estudiante> filtrarPorCarrera (int codigoCarrera) throws LibreriaException{
        ArrayList <Estudiante> lisEstudiantes = ListarFiltrarArchivos.getEstudiantes();
        ArrayList <Estudiante> listaFiltrada = new ArrayList<>();

        for (Estudiante estudiante : lisEstudiantes) {
           
            if (estudiante.getCarrera() == codigoCarrera) {
                listaFiltrada.add(estudiante);
            }
        }
        ListarFiltrarArchivos.ordenarListaEstudiantes(listaFiltrada);
        return listaFiltrada;
   }
}
