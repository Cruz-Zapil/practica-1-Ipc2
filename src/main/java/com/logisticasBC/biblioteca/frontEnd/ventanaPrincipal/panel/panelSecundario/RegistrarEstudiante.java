package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;


import javax.swing.JTextField;

import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario.actionPanelSecundario.ActionNewData;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

public class RegistrarEstudiante extends ConstructorPanelS implements ActionListener {

    private JLabel[] labels = new JLabel[4];
    private String[] etiqueta = {"Carné ","Código Carrera", "Nombre","Fecha Nacimiento"};
    private   JTextField [] cajaTexto = new JTextField[4];

    public RegistrarEstudiante(){
        super("Nuevo Estudiante");

        addComponents();
        addBotones();
    }

    private void addComponents(){
        int x_posLabel = 60;
        int y_posLabel = 200;

        int x_posField = 60;
        int y_posField = 230;

        for (int i = 0; i < etiqueta.length; i++) {
            
            // agregando Jlables

            labels[i] = new JLabel(etiqueta[i]);
            labels[i].setLocation(x_posLabel, y_posLabel);
            labels[i].setSize(200, 30);
            y_posLabel = y_posLabel + 90;

            if (i == 1) {
                x_posLabel = x_posLabel + 290;
                y_posLabel = 200;
            }


            /// agregando JTextField 
            cajaTexto[i] = new JTextField();
            cajaTexto[i].setBounds(x_posField, y_posField, 180,30);
            y_posField = y_posField + 90;

            if (i==1) {
                x_posField = x_posField + 290;
                y_posField =230;                
            }

            this.add(cajaTexto[i]);
            this.add(labels[i]);
        }

    }


    
    
    private void addBotones() {

        JButton aceptar = new JButton();
        aceptar.setText("Aceptar");
        aceptar.setBounds(450,620,90,30);
        aceptar.addActionListener(this);
        
        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(570,620,90,30);
        cancelar.addActionListener(this);

        this.add(aceptar);
        this.add(cancelar);

    }

   
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();
    
            if (sourceButton.getText().equals("Aceptar")) {
                if (!cajaTexto[0].getText().isEmpty() && !cajaTexto[1].getText().isEmpty() && !cajaTexto[2].getText().isEmpty()) {
                
                    Estudiante nuevotmp = new Estudiante(cajaTexto[0].getText(),cajaTexto[2].getText(), 2, LocalDate.of(2024,12,25) );

                 

                    Message.mostrarConfirmacion("Registro Guardado", "Nuevo Estudiante");
                } else {

                    Message.mostrarMensajeError("Rellene bien los datos", "Error de Datos");
                }

            } else if (sourceButton.getText().equals("Cancelar")) {
               
                
                cajaTexto[0].setText("");
                cajaTexto[1].setText("");
                cajaTexto[2].setText("");
            }
        }
    }
    
}
