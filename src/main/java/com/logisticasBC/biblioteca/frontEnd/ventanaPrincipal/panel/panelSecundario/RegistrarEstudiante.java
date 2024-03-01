package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;


import javax.swing.JTextField;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario.actionPanelSecundario.ActionNewData;

import javax.swing.JLabel;
import javax.swing.JButton;

public class RegistrarEstudiante extends ConstrutorPanelS {

    private JLabel[] labels = new JLabel[4];
    private String[] etiqueta = {"Carnè ","Código Carrera", "Nombre","Fecha Nacimiento"};
    private JTextField [] cajaTexto = new JTextField[4];

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

            if (i==1) {
                x_posField = x_posField + 290;
                y_posField =200;                
            }

            this.add(cajaTexto[i]);
            this.add(labels[i]);
        }

    }


    
    
    private void addBotones() {

        JButton aceptar = new JButton();
        aceptar.setText("Aceptar");
        aceptar.setBounds(450,620,90,30);
        aceptar.addActionListener(new ActionNewData());
        
        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(570,620,90,30);
        cancelar.addActionListener(new ActionNewData());

        this.add(aceptar);
        this.add(cancelar);

    }
    
}
