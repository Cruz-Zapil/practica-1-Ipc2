package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.backEnd.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;

public class PanelPrestamo extends ConstructorPanelS implements ActionListener {

    // etiqueta
    private JLabel[] labels = new JLabel[7];
    private String[] etiqueta = { "Carné Estudiante", "Fecha Prestamo", "Info. Estudiante ", "Código Libro",
            "Info. Libro" };
    private String[] textBoton = { "Buscar", "Aceptar", "Cancelar" };
    private JTextPane[] textAreaImput = new JTextPane[2];
    private Estudiante nomEstudiante;
    private Libro nomLibro;
    JButton[] botones = new JButton[3];

    /// cuadro de texto
    private JTextField estudiante = new JTextField();
    private JTextField libro = new JTextField();

    public PanelPrestamo() {
        super("Nuevo Prestamo");
        this.setLayout(null);
        addEtiquetas();
        addJTextFiel();
        addButtons();
        addFecha();
        addInfo();
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

            Font font = labels[i].getFont();
            labels[i].setFont(new Font(font.getName(), font.getStyle(), 16));
            y = y + 90;

            if (i == 2) {
                x = x + 290;
                y = 200;
            }
            this.add(labels[i]);
        }
    }

    private void addFecha() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha como una cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        // Mostrar la fecha en el JLabel
        labels[5] = new JLabel();
        labels[5].setBounds(60, 320, 200, 30);
        labels[5].setText(fechaFormateada);

        Font font = labels[5].getFont();
        labels[5].setFont(new Font(font.getName(), font.getStyle(), 18));

        this.add(labels[5]);

    }

    private void addInfo() {

        textAreaImput[0] = new JTextPane();
        textAreaImput[0].setBounds(60, 410, 180, 200);
        textAreaImput[0].setEditable(false);

        this.add(textAreaImput[0]);

        textAreaImput[1] = new JTextPane();
        textAreaImput[1].setBounds(350, 320, 180, 200);
        textAreaImput[1].setEditable(false);
        this.add(textAreaImput[1]);
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
        botones[1].setEnabled(false);

        botones[0].setBounds(570, 230, 90, 30);
        botones[1].setBounds(450, 620, 90, 30);
        botones[2].setBounds(570, 620, 90, 30);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();

            if (sourceButton.getText().equals("Buscar")) {

                System.out.println("buscar los archivos ");

                try {

                    nomEstudiante = (Estudiante) ControladorAchivos.cargarArchivo(
                            ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES + File.separatorChar + estudiante.getText());
                    textAreaImput[0].setText(nomEstudiante.getNombre());

                    try {

                        nomLibro = (Libro) ControladorAchivos.cargarArchivo(
                                ControladorAchivos.PATH_DIRECTORIO_LIBROS + File.separatorChar + libro.getText());
                        textAreaImput[1].setText(nomLibro.getTitulo());
                        botones[1].setEnabled(true);
                    } catch (LibreriaException e) {

                        Message.mostrarMensajeError(e.getMessage() + "al no encontrar el libro ", "Error ");
                    }

                } catch (LibreriaException e) {

                    Message.mostrarMensajeError(e.getMessage() + "al no encontrar el estudiante ", "Error ");
                }

            } else if (sourceButton.getText().equals("Aceptar")) {

                try {

                    nomEstudiante.prestamosDisponible(libro.getText());

                    if (nomLibro.getCantCopiasDisponibles() > 0) {

                        Prestamo nuevoPrestamo = new Prestamo(libro.getText(), estudiante.getText(), LocalDate.now());
                        ControladorAchivos.guardarArchivo(nuevoPrestamo);

                    } else {
                        Message.mostrarMensajeError("No tenemos Copias Disponibles del libro ", "Error");
                    }
                } catch (LibreriaException e) {

                    Message.mostrarMensajeError(e.getMessage(), "Error");
                }

            } else if (sourceButton.getText().equals("Cancelar")) {

                System.out.println(" lipiar  ");

            }

        }

    }

}
