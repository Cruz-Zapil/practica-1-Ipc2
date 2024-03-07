package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import javax.swing.JLabel;
import javax.swing.JTextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Prestamo extends ConstructorPanelS implements ActionListener {

    // etiqueta
    private JLabel[] labels = new JLabel[5];
    private String[] etiqueta = { "Carné Estudiante", "Fecha Prestamo", "Info. Estudiante ", "Código Libro",
            "Info. Libro" };
    private String[] textBoton = { "Buscar", "Aceptar", "Cancelar" };

    /// cuadro de texto
    private JTextField estudiante = new JTextField();
    private JTextField libro = new JTextField();

    public Prestamo() {
        super("Nuevo Prestamo");
        this.setLayout(null);
        addEtiquetas();
        addJTextFiel();
        addButtons();
    }

    // componentes a agregar:
    /*
     * cinco lebel: titulo de carne, titulo Libro, fecha de prestamo, info libro,
     * info estudiante
     * dos cuadros de texto
     * fecha de prestamo
     * 3 botones
     * info del libro
     */

    public void addEtiquetas() {

        int x = 60;
        int y = 200;

        for (int i = 0; i < etiqueta.length; i++) {

            labels[i] = new JLabel(etiqueta[i]);
            labels[i].setLocation(x, y);
            labels[i].setSize(200, 30);
            y = y + 90;

            if (i == 2) {
                x = x + 290;
                y = 200;
            }
            this.add(labels[i]);
        }
    }

    private void addJTextFiel() {

        estudiante.setBounds(60, 230, 180, 30);
        libro.setBounds(350, 230, 180, 30);

        this.add(estudiante);
        this.add(libro);

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

    private void addInfoLabels() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        

    }

}
