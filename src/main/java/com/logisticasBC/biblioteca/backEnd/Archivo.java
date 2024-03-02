/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logisticasBC.biblioteca.backEnd;

import java.io.Serializable;

/**
 *
 * @author Personal
 */

public abstract class Archivo implements Serializable{
    
    protected String codigo;
    
    public abstract String getPath();
}
