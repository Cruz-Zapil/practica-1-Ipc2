package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import javax.swing.JTextField;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario.actionPanelSecundario.ActionNewData;

import javax.swing.JLabel;
import javax.swing.JButton;

public class RegistrarLibro extends ConstrutorPanelS {

    private JLabel[] jLabel = new JLabel[6];
    private JTextField[] textField = new JTextField[6];
    private String[] etiqueta = { "Código", " Titulo", "Fecha Publicacion", "Autor", "Cant. Copias", "Editorial" };

    

    public RegistrarLibro() {

        super("Nuevo Libro");

        addComponents();
        addBotones();
    }

    private void addComponents() {

        int x_posLabel = 60;
        int y_posLabel = 200;

        int x_posField = 60;
        int y_posField = 230;

        for (int i = 0; i < etiqueta.length; i++) {

            // agregando Jlables

            jLabel[i] = new JLabel(etiqueta[i]);
            jLabel[i].setLocation(x_posLabel, y_posLabel);
            jLabel[i].setSize(200, 30);
            y_posLabel = y_posLabel + 90;

            if (i == 2) {
                x_posLabel = x_posLabel + 290;
                y_posLabel = 200;
            }

            /// agregando JTextField

            textField[i] = new JTextField();
            textField[i].setBounds(x_posField, y_posField, 180, 30);

            if (i == 2) {
                x_posField = x_posField + 290;
                y_posField = 200;
            }

            this.add(textField[i]);
            this.add(jLabel[i]);
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
