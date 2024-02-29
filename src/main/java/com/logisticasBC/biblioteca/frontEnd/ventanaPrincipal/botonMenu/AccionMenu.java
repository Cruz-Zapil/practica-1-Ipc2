package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.Devolucion;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.ImportarDato;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.Reporte;

public class AccionMenu implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();

        if(source instanceof ContruccionBoton){

            ContruccionBoton botonMenu = (ContruccionBoton) event.getSource();

            if(botonMenu.getText().equals("Prestamo") ){

                VentanaPrincipal.addPanelCentral(new Prestamo());

            }else if(botonMenu.getText().equals("Devolucion")) {

                VentanaPrincipal.addPanelCentral(new Devolucion());

            }else if (botonMenu.getText().equals("Consulta")){

                VentanaPrincipal.addPanelCentral(new Reporte());

            }else if (botonMenu.getText().equals("Nuevo Registro")){

                

            }else if (botonMenu.getText().equals("Importar Datos")){

                VentanaPrincipal.addPanelCentral(new ImportarDato());

            }else if (botonMenu.getText().equals("Acerca de:")){
                

            }

        }

        
        
        

    }
    
}
