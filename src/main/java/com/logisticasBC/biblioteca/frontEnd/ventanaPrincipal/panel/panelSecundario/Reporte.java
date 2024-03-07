package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.backEnd.ListarFiltrarArchivos;
import com.logisticasBC.biblioteca.backEnd.Prestamo;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReporteEstudiante;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReporteLibro;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReportesPrestamo;

public class Reporte extends ConstructorPanelS implements ActionListener {

    private JButton[] botones = new JButton[4];
    private String[] etiqueta = { "Libro", "Estudiante", "Devolucion", "Buscar" };
    private JComboBox<String> comboBox;
    private JComboBox<Integer> comboCodigo;
    private JTextField busqueda;
    private JLabel etiquetaBusqueda;
    private JPanel visualConsulta;
    private int dineroSinMora = 0;
    private int dineroConMora = 0;

    private JLabel max = new JLabel("Fecha limite");

    private JLabel ini = new JLabel("Fecha inicio");

    private String[] filttroLibro = { "Código", "Autor", "Titulo" };
    private String[] filtroEstudiante = { "Carné", "Nombre", "Código carrera" };

    private String[] filtroPrestamo = { "Por vencer", "En mora", "Dinero Recaudado en x tiempo", // intervalo de tiempo
            "Por un estudiante", "Por Carrera en x tiempo" };

    private String selecion;
    private JXDatePicker iniPicker;
    private JXDatePicker maxPicher;

    public Reporte() {
        super(" Reportes");

        visualConsulta = new JPanel();
        visualConsulta.setLayout(null);
        visualConsulta.setBounds(60, 300, 600, 420);
        visualConsulta.setBackground(new Color(255, 228, 181));

        this.add(visualConsulta);

        addBottons();
        addItemCombo();
        addCalendario();
    }

    public void addBottons() {

        int tmp = 90;

        for (int i = 0; i < botones.length; i++) {

            botones[i] = new JButton(etiqueta[i]);
            botones[i].setFont(new Font("Arial", Font.BOLD, 14));
            botones[i].setBackground(new Color(173, 216, 230));
            botones[i].setForeground(Color.black);
            botones[i].setBounds(tmp, 270, 180, 30);
            botones[i].addActionListener(this);
            this.add(botones[i]);

            tmp = tmp + 180;

        }

        botones[3].setBounds(450, 150, 160, 30);
        this.add(botones[3]);

    }

    private void addItemCombo() {

        etiquetaBusqueda = new JLabel("Buscar");
        etiquetaBusqueda.setBounds(275, 120, 160, 30);

        busqueda = new JTextField();
        busqueda.setBounds(275, 150, 160, 30);

        comboBox = new JComboBox<>();
        Font font = new Font("Arial", Font.PLAIN, 15); // Puedes ajustar el tamaño según tus preferencias
        comboBox.setFont(font);
        comboBox.setBounds(60, 150, 210, 30);
        comboBox.addActionListener(this);
        comboBox.addItem("Filtros");

        comboCodigo = new JComboBox<>();
        comboCodigo.setBounds(275, 150, 160, 30);
        for (int i = 1; i <= 5; i++) {
            comboCodigo.addItem(i);
        }
        comboCodigo.setVisible(false);

        this.add(busqueda);
        this.add(comboBox);
        this.add(etiquetaBusqueda);
        this.add(comboCodigo);

    }

    private void addCalendario() {

        iniPicker = new JXDatePicker();
        iniPicker.setDate(Calendar.getInstance().getTime());
        iniPicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        iniPicker.setBounds(90, 220, 180, 30);
        iniPicker.setVisible(false);
        ini.setVisible(false);
        ini.setBounds(90, 190, 180, 30);

        this.add(ini);
        this.add(iniPicker);

        maxPicher = new JXDatePicker();
        maxPicher.setDate(Calendar.getInstance().getTime());
        maxPicher.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        maxPicher.setBounds(300, 220, 180, 30);
        max.setVisible(false);
        maxPicher.setVisible(false);

        max.setBounds(300, 190, 180, 30);

        this.add(max);
        this.add(maxPicher);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();

            if (sourceButton.getText().equals("Libro")) {

                comboBox.removeAllItems();
                selecion = "Libro";

                for (int i = 0; i < filttroLibro.length; i++) {
                    comboBox.addItem(filttroLibro[i]);
                }

                try {
                    ArrayList<Libro> infoLibro = new ArrayList<>();
                    infoLibro = ListarFiltrarArchivos.getLibros();
                    actualizar(new MostrarReporteLibro(infoLibro));

                } catch (LibreriaException e) {

                    Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                }

            } else if (sourceButton.getText().equals("Estudiante")) {

                comboBox.removeAllItems();
                selecion = "Estudiante";

                for (int i = 0; i < filtroEstudiante.length; i++) {
                    comboBox.addItem(filtroEstudiante[i]);
                }

                try {
                    ArrayList<Estudiante> infoEstudiante = new ArrayList<>();
                    infoEstudiante = ListarFiltrarArchivos.getEstudiantes();

                    actualizar(new MostrarReporteEstudiante(infoEstudiante));
                } catch (LibreriaException e) {

                    Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");

                }

            } else if (sourceButton.getText().equals("Devolucion")) {

                comboBox.removeAllItems();
                selecion = "Devolucion";

                for (int i = 0; i < filtroPrestamo.length; i++) {
                    comboBox.addItem(filtroPrestamo[i]);
                }

                try {
                    ArrayList<Prestamo> infoPrestamo = new ArrayList<>();
                    infoPrestamo = ListarFiltrarArchivos.getPrestamos();
                    actualizar(new MostrarReportesPrestamo(infoPrestamo));
                } catch (LibreriaException e) {

                    Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                }

            } else if (sourceButton.getText().equals("Buscar")) {

                if (selecion.equals("Libro")) {

                    String tmp = (String) comboBox.getSelectedItem();

                    ArrayList<Libro> filter = new ArrayList<>();
                    switch (tmp) {
                        case "Código":

                            try {
                                filter = Libro.filtrarPorCodigo(busqueda.getText());

                                this.add(new MostrarReporteLibro(filter));

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        case "Autor":

                            try {
                                System.out.println(" filtrando por autor ");
                                filter = Libro.filtrarPorAutor(busqueda.getText());

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        case "Titulo":
                            try {
                                filter = Libro.filtrarPorTitulo(busqueda.getText());

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;
                    }

                    actualizar(new MostrarReporteLibro(filter));

                } else if (selecion.equals("Estudiante")) {

                    String tmp = (String) comboBox.getSelectedItem();

                    ArrayList<Estudiante> filter = new ArrayList<>();

                    switch (tmp) {
                        case "Carné":

                            try {
                                filter = Estudiante.filtrarPorCarnet(busqueda.getText());
                            } catch (LibreriaException e) {
                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        case "Nombre":

                            System.out.println(" filtrando estudiante por nombre");

                            try {
                                filter = Estudiante.filtrarPorNombre(busqueda.getText());

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        case "Código carrera":

                            try {
                                filter = Estudiante.filtrarPorCarrera((int) comboCodigo.getSelectedItem());

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;
                    }
                    actualizar(new MostrarReporteEstudiante(filter));

                } else if (selecion.equals("Devolucion")) {

                    String tmp = (String) comboBox.getSelectedItem();

                    ArrayList<Prestamo> filter = new ArrayList<>();

                    switch (tmp) {
                        case "Por vencer":

                            System.out.println(" por vencer ");
                            try {
                                filter = Prestamo.prestamosQueVencenHoy();

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        case "En mora":
                            System.out.println(" en mora");
                            try {
                                filter = Prestamo.prestamosMorosos();

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage() + "No se encontraron datos", "Error");
                            }

                            break;

                        /// dinero recaudado en un intevalo de tiempo
                        case "Dinero Recaudado en x tiempo":

                            System.out.println(" abriendo dinero ");

                            try {

                                // Obtener la fecha seleccionada como java.util.Date
                                Date selectedDateIni = iniPicker.getDate();
                                // Convertir a LocalDate utilizando la zona horaria predeterminada del sistema
                                LocalDate timeIni = Instant.ofEpochMilli(selectedDateIni.getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();

                                Date selectedDateMax = maxPicher.getDate();

                                LocalDate timeMax = Instant.ofEpochMilli(selectedDateMax.getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();

                                // Hacer lo que necesitas con localDate
                                System.out.println("Fecha seleccionada: " + timeIni);

                                System.out.println("Fecha seleccionada: " + timeMax);

                                filter = Prestamo.prestamosCanceladosIntervaloTiempo(timeIni, timeMax);

                                for (Prestamo prestamo : filter) {

                                    dineroSinMora = dineroSinMora + prestamo.getDineroRecaudadoSinMora();
                                    dineroConMora = dineroConMora + prestamo.getDineroRecaudadoMora();

                                }
                                Message.mostrarMensajeInfo("con mora: " + dineroSinMora, "Dinero Recaudado:");
                                Message.mostrarMensajeInfo("sin mora: " + dineroSinMora, "Dinero recudado");

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage()
                                        + "No se encontraron datos", "Error");
                            }

                            break;

                        case "Por un estudiante":
                        try {

                            Estudiante prestaEstudiante = (Estudiante) ControladorAchivos
                                    .cargarArchivo(ControladorAchivos.PATH_DIRECTORIO_ESTUDIANTES
                                            + File.separatorChar + busqueda.getText());

                            filter = prestaEstudiante.getPrestamosRealizados();

                        } catch (LibreriaException e) {

                            Message.mostrarMensajeInfo(e.getMessage()
                                    + "No se encontraron datos", "Error");
                        }
                            break;

                      //005-STU
                        case "Por Carrera en x tiempo":
                            try {

                                //filter = Prestamo.prestamosHechosPorCarreraIntervalotTiempo(null, null, ABORT);

                                // Obtener la fecha seleccionada como java.util.Date
                                Date selectedDateIni = iniPicker.getDate();
                                // Convertir a LocalDate utilizando la zona horaria predeterminada del sistema
                                LocalDate timeIni = Instant.ofEpochMilli(selectedDateIni.getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();

                                Date selectedDateMax = maxPicher.getDate();

                                LocalDate timeMax = Instant.ofEpochMilli(selectedDateMax.getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();

                                // Hacer lo que necesitas con localDate
                                System.out.println("Fecha seleccionada: " + timeIni);

                                System.out.println("Fecha seleccionada: " + timeMax);

                                filter = Prestamo.prestamosHechosPorCarreraIntervalotTiempo(timeIni, timeMax, (int) comboCodigo.getSelectedItem());

                            } catch (LibreriaException e) {

                                Message.mostrarMensajeInfo(e.getMessage()
                                        + "No se encontraron datos", "Error");
                            }

                            break;

                    }

                    actualizar(new MostrarReportesPrestamo(filter));

                }
            }
        } else if (event.getSource() instanceof JComboBox) {

            @SuppressWarnings("rawtypes")
            JComboBox sourceComboBox = (JComboBox) event.getSource();

            if ("Código carrera".equals(sourceComboBox.getSelectedItem())) {

                busqueda.setVisible(false);
                comboCodigo.setVisible(true);

            } else if ("Por Carrera en x tiempo".equals(sourceComboBox.getSelectedItem())) {

                busqueda.setVisible(false);
                comboCodigo.setVisible(true);
                ini.setVisible(true);
                iniPicker.setVisible(true);
                max.setVisible(true);
                maxPicher.setVisible(true);


            } else if ("Dinero Recaudado en x tiempo".equals(sourceComboBox.getSelectedItem())) {

                // mostar dos calendario
                ini.setVisible(true);
                iniPicker.setVisible(true);
                max.setVisible(true);
                maxPicher.setVisible(true);

            }
        }
    }

    public static boolean esNumeroEntero(String str) {
        try {
            // Intentar convertir el String a int
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            // Si ocurre una excepción, el String no es un número entero válido
            return false;
        }
    }

    private void actualizar(JPanel nuevo) {

        comboCodigo.setVisible(false);
        busqueda.setVisible(true);

        ini.setVisible(false);
        iniPicker.setVisible(false);
        max.setVisible(false);
        maxPicher.setVisible(false);

        System.out.println(" actualizando ");
        // Eliminar componentes existentes en el JPanel
        visualConsulta.removeAll();
        visualConsulta.add(nuevo);

        // Actualizar la interfaz gráfica
        visualConsulta.revalidate();

        visualConsulta.repaint();

    }

}
