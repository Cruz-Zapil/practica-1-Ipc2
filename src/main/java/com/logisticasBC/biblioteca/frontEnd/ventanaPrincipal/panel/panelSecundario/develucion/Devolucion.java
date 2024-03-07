package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.develucion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.ConstructorPanelS;


public class Devolucion extends ConstructorPanelS implements ActionListener {

    /*
     * 3 botones
     * 4 etiquetas
     * 2 textfield
     */

    private String[] etiqueta = { "Carné estudiante", "Total: ", "Código libro", };

    private String[] textBoton = { "Buscar", "Aceptar", "Cancelar" };

    private JLabel[] label = new JLabel[3];
    private JTextField estudiante = new JTextField();
    private JTextField libro = new JTextField();
    private JTextPane mostrarInfo = new JTextPane();
    private Prestamo tmp  = new Prestamo();

    public Devolucion() {
        super(" Devolucion ");

        addEtiquetas();
        addJTextFiel();
        // addTabla();
        addButtons();
    }

    private void addEtiquetas() {

        int x = 60;
        int y = 200;

        for (int i = 0; i < etiqueta.length; i++) {

            label[i] = new JLabel(etiqueta[i]);
            label[i].setLocation(x, y);
            label[i].setSize(200, 30);
            y = y + 90;

            if (i == 1) {
                x = x + 290;
                y = 200;
            }
            this.add(label[i]);
        }
    }

    private void addJTextFiel() {

        estudiante.setBounds(60, 230, 180, 30);
        libro.setBounds(350, 230, 180, 30);

        mostrarInfo.setBounds(60, 350, 200, 250);
        mostrarInfo.setEditable(false);

        this.add(estudiante);
        this.add(libro);
        this.add(mostrarInfo);

    }

    private void addButtons() {

        JButton[] botones = new JButton[3];

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
            botones[i].addActionListener(this);
            botones[i].setText(textBoton[i]);
            this.add(botones[i]);
        }

        botones[0].setBounds(570, 230, 90, 30);
        botones[1].setBounds(450, 620, 90, 30);
        botones[2].setBounds(570, 620, 90, 30);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();

            if (sourceButton.getText().equals("Buscar")) {

                try {

                    tmp = Prestamo.cargarPrestamoActivo(estudiante.getText(), libro.getText());

                    int cant = tmp.calcularPago();

                    label[1].setText("Total a pagar: " + cant);

                    mostrarInfo.setText("Total a pagar sin mora: " + tmp.getDineroRecaudadoSinMora() + "\n"
                            + "Total a pagar con Mora: " + tmp.getDineroRecaudadoMora());

                } catch (LibreriaException e) {

                    e.printStackTrace();
                }

            } else if (sourceButton.getText().equals("Aceptar")) {

                
                try {

                    tmp.devolverLibro();

                    Message.mostrarConfirmacion("Devolucion realizada","Devolucion");

                } catch (LibreriaException e) {
                    
                    e.printStackTrace();
                }

              

            } else if (sourceButton.getText().equals("Cancelar")) {

                System.out.println(" cancelar y limpiar ");
            }
        }
    }

}
