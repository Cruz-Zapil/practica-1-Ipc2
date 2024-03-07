package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.VentanaPrincipal;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu.ContruccionBoton;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.PanelPrestamo;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.RegistrarEstudiante;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.RegistrarLibro;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.Reporte;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.develucion.Devolucion;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.ImportarDato;

public class AccionMenu implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();

        if(source instanceof ContruccionBoton){

            ContruccionBoton botonMenu = (ContruccionBoton) event.getSource();

            if(botonMenu.getText().equals("Prestamo") ){

                VentanaPrincipal.addPanelCentral(new PanelPrestamo());

            }else if(botonMenu.getText().equals("Devolucion")) {

                VentanaPrincipal.addPanelCentral(new Devolucion());

            }else if (botonMenu.getText().equals("Consulta")){

                VentanaPrincipal.addPanelCentral(new Reporte());

            }else if (botonMenu.getText().equals("Nuevo Registro")){

                int tmp = Message.seleccioneOpcion("Seleccione una opcion:", "Nuevo Registro");

                if (tmp ==1){

                    VentanaPrincipal.addPanelCentral(new RegistrarEstudiante());

                }else if (tmp == 2){

                    VentanaPrincipal.addPanelCentral(new RegistrarLibro());

                }

            }else if (botonMenu.getText().equals("Importar Datos")){

                VentanaPrincipal.addPanelCentral(new ImportarDato());

            }else if (botonMenu.getText().equals("Acerca de:")){
                

            }

        }
    }
    
}
