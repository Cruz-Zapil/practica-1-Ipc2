/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.File;
import java.io.Serializable;

//import com.gargoylesoftware.htmlunit.javascript.host.file.File;

/**
 *
 * @author Personal
 */

public abstract class Archivo implements Serializable {

    
    protected String codigo;
    
    public abstract String getPath();

    public String getCodigo (){
        return codigo;
    }
    
    public void actualizar () throws LibreriaException{
            
        ControladorAchivos.guardarArchivo(this);
        
    }


    protected boolean archivoExite (String ruta){
        
        File archivo = new File(ruta);
        
        return archivo.exists();
    }

}
