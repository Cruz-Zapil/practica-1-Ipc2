package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario.actionPanelSecundario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu.ContruccionBoton;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.RegistrarEstudiante;

public class ActionNewData implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

           Object source = event.getSource();

        if(source instanceof RegistrarEstudiante ){
            
            JButton botonAceptarE = (JButton) event.getSource();

            if (botonAceptarE.getText().equals("Aceptar")) {
                
            }else if (botonAceptarE.getText().equals("Canelar")) {
                

            }


        }
        

    }
    
}
