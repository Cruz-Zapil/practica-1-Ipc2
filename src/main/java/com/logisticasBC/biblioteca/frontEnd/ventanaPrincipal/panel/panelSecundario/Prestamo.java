package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Prestamo extends ConstrutorPanelS {

    // etiqueta
    private JLabel[] labels = new JLabel[5];
    private String[] etiquetas = { "Carné Estudiante", "Fecha Prestamo", "Info. Estudiante ", "Código Libro",
            "Info. Libro" };

    /// cuadro de texto
    private JTextField estudiante = new JTextField();
    private JTextField libro = new JTextField();

    public Prestamo() {
        super("Prestamo");
        this.setLayout(null);
        addEtiquetas();
        addJTextFiel();
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
        int y = 240;

        for (int i = 0; i < etiquetas.length; i++) {

            labels[i] = new JLabel(etiquetas[i]);
            labels[i].setLocation(x, y);
            labels[i].setSize(200, 30);
            y = y + 90;

            if (i == 2) {
                x = x + 290;
                y = 240;
            }
            this.add(labels[i]);
        }
    }

    public void addJTextFiel() {

        estudiante.setBounds(60,270,180,30);
        libro.setBounds(350,270,180,30);

        this.add(estudiante);
        this.add(libro);

    }

}
