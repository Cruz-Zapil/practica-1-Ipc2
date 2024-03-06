package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;
import java.time.LocalDate;
import java.time.ZoneId;

import com.ibm.icu.util.Calendar;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarLibro extends ConstructorPanelS implements ActionListener {

    private JLabel[] jLabel = new JLabel[6];
    private JTextField[] textField = new JTextField[6];
    private String[] etiqueta = { "Código", " Titulo", "Autor", "Fecha Publicacion", "Cant. Copias", "Editorial" };
    private JXDatePicker datePicker;

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
            y_posField = y_posField + 90;

            if (i == 2) {
                x_posField = x_posField + 290;
                y_posField = 230;
            }

            if (i == 2) {
                System.out.println(" reando calendario ");

                // agregando calendario
                datePicker = new JXDatePicker();
                datePicker.setDate(Calendar.getInstance().getTime());
                datePicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
                datePicker.setBounds(x_posField, y_posField, 180, 30);

                this.add(datePicker);

                System.out.println(" creando ");
            }

            if (i != 3) {

                this.add(textField[i]);
            }

            this.add(jLabel[i]);
        }
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
                if (!textField[0].getText().isEmpty() && !textField[1].getText().isEmpty()) {

                    try {

                        Date selectedDate = datePicker.getDate();
                        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        new Libro(textField[1].getText(), textField[3].getText(), textField[0].getText(), 2, localDate,
                                textField[5].getText());

                        Message.mostrarMensajeInfo("Registro Guardado", "Nuevo Estudiante");

                    } catch (LibreriaException e) {
                        Message.mostrarMensajeError("Error de almacenado", "Error de Registro");
                        e.printStackTrace();
                    }

                } else {

                    Message.mostrarMensajeError("Rellene bien los datos", "Error de Datos");
                }

            } else if (sourceButton.getText().equals("Cancelar")) {
                for (int i = 0; i < textField.length; i++) {

                    textField[i].setText("");

                }
            }
        }

    }

}
