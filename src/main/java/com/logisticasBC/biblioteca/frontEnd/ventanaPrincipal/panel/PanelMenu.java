package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel;

import javax.swing.JPanel;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu.AccionMenu;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu.ContruccionBoton;


import java.awt.Color;
/*
 * atributos del panel menu:
 * contiene botones,
 * color de fondo, 
 * pos_x y pos_y
 * dim_x y dim_y 
 * 
 */

public class PanelMenu extends JPanel {

    private String [] etiquetas= {"Prestamo", "Devolucion", "Consulta", "Nuevo Registro", "Importar Datos","Acerca de:"};
    private ContruccionBoton[] botonMenu= new ContruccionBoton[6];
    private AccionMenu accionBoton = new AccionMenu();

    public PanelMenu() {

        this.setLayout(null);
        this.setBounds(0, 0, 210, 750);
        this.setBackground(new Color(255,222,173));

        setComponents();

    }

    private void setComponents(){
         int tmp =0;

        for (int i = 0; i < botonMenu.length; i++) {
            botonMenu[i]= new ContruccionBoton(etiquetas[i], accionBoton);
            botonMenu[i].setBounds(0, tmp, 210, 60);

            tmp= tmp +60;
            this.add(botonMenu[i]);
            System.out.println("crear boton "+ etiquetas[i]+ i);
            
        }



    }

}
