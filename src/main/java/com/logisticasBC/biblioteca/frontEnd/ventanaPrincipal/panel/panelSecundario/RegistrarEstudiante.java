package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import org.jdesktop.swingx.JXDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import com.ibm.icu.util.Calendar;
import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;

import javax.swing.JLabel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RegistrarEstudiante extends ConstructorPanelS implements ActionListener {

    private JLabel[] labels = new JLabel[4];
    private String[] etiqueta = { "Carné ", "Código Carrera", "Nombre", "Fecha Nacimiento" };
    private JTextField[] cajaTexto = new JTextField[2];
    private JXDatePicker datePicker; 

    public RegistrarEstudiante() {
        super("Nuevo Estudiante");

        addComponents();
        addBotones();
    }

    private void addComponents() {
        int x_posLabel = 60;
        int y_posLabel = 200;

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

            this.add(labels[i]);
        }

        /// agregando JTextField
        cajaTexto[0] = new JTextField();
        cajaTexto[0].setBounds(60, 230, 200, 30);

        cajaTexto[1] = new JTextField();
        cajaTexto[1].setBounds(350, 230, 180, 30);

        this.add(cajaTexto[0]);
        this.add(cajaTexto[1]);

        datePicker = new JXDatePicker();
        datePicker.setDate(Calendar.getInstance().getTime());
        datePicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker.setBounds(350, 320, 180, 30);
        this.add(datePicker);

    }

    private void addBotones() {

        JButton aceptar = new JButton();
        aceptar.setText("Aceptar");
        aceptar.setBounds(450, 620, 90, 30);
        aceptar.addActionListener(this);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(570, 620, 90, 30);
        cancelar.addActionListener(this);

        this.add(aceptar);
        this.add(cancelar);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();

            if (sourceButton.getText().equals("Aceptar")) {
                if (!cajaTexto[0].getText().isEmpty() && !cajaTexto[1].getText().isEmpty()) {

                    try {

                        Date selectedDate = datePicker.getDate();
                        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        new Estudiante(cajaTexto[0].getText(), cajaTexto[1].getText(), 2, localDate);

                        Message.mostrarMensajeInfo("Registro Guardado", "Nuevo Estudiante");

                    } catch (LibreriaException e) {
                        Message.mostrarMensajeError("Error de almacenado", "Error de Registro");
                        e.printStackTrace();
                    }
                    
                } else {

                    Message.mostrarMensajeError("Rellene bien los datos", "Error de Datos");
                }

            } else if (sourceButton.getText().equals("Cancelar")) {
                cajaTexto[0].setText("");
                cajaTexto[1].setText("");
            }
        }
    }
}
