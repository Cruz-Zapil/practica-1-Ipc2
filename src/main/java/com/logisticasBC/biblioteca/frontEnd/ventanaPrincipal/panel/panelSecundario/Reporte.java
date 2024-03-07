package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.logisticasBC.biblioteca.backEnd.Estudiante;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.Libro;
import com.logisticasBC.biblioteca.backEnd.ListarFiltrarArchivos;
import com.logisticasBC.biblioteca.backEnd.Prestamo;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReporteEstudiante;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReporteLibro;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarReportesPrestamo;
import com.vaadin.ui.ComboBox;

public class Reporte extends ConstructorPanelS implements ActionListener {

    private JButton[] botones = new JButton[4];
    private String[] etiqueta = { "Libro", "Estudiante", "Devolucion", "Buscar" };
    private JComboBox<String> comboBox;
    private JTextField busqueda;
    private JLabel etiquetaBusqueda;

    private String[] filttroLibro = { "Código", "Autor", "Titulo" };
    private String[] filtroEstudiante = { "Carné", "Nombre", "Código carrera" };
    private String[] filtroPrestamo = { "Por vencer", "En mora", "Dinero Recaudado", // intervalo de tiempo
            "Por un estudiante", "Por Estudiante Actualmente", "Por Carrera" };

    private String selecion;

    public Reporte() {
        super(" Reportes");

        addBottons();
        addItemCombo();

    }

    public void addBottons() {

        int tmp = 90;

        for (int i = 0; i < botones.length; i++) {

            botones[i] = new JButton(etiqueta[i]);
            botones[i].setFont(new Font("Arial", Font.BOLD, 14));
            botones[i].setBackground(new Color(255, 182, 193));
            botones[i].setForeground(Color.black);
            botones[i].setBounds(tmp, 270, 180, 30);
            botones[i].addActionListener(this);
            this.add(botones[i]);

            tmp = tmp + 180;

        }

        botones[3].setBounds(450, 200, 160, 30);
        this.add(botones[3]);

    }

    private void addItemCombo() {

        etiquetaBusqueda = new JLabel("Buscar");
        etiquetaBusqueda.setBounds(275, 170, 160, 30);

        busqueda = new JTextField();
        busqueda.setBounds(275, 200, 160, 30);

        comboBox = new JComboBox<>();
        comboBox.setBounds(90, 200, 180, 30);
        comboBox.addItem("Filtros");

        this.add(busqueda);
        this.add(comboBox);
        this.add(etiquetaBusqueda);

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
                    this.add(new MostrarReporteLibro(infoLibro));

                } catch (LibreriaException e) {

                    e.printStackTrace();
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

                    this.add(new MostrarReporteEstudiante(infoEstudiante));
                } catch (LibreriaException e) {

                    e.printStackTrace();
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
                    this.add(new MostrarReportesPrestamo(infoPrestamo));
                } catch (LibreriaException e) {

                    e.printStackTrace();
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

                                e.printStackTrace();
                            }

                            break;

                        case "Autor":

                            try {
                                filter = Libro.filtrarPorAutor(busqueda.getText());

                            } catch (LibreriaException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            break;

                        case "Titulo":
                            try {
                                filter = Libro.filtrarPorTitulo(busqueda.getText());
                            } catch (LibreriaException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            break;
                    }

                    this.add(new MostrarReporteLibro(filter));

                } else if (selecion.equals("Estudiante")) {
                    System.out.println(" tenemos Estudiante");

                    String tmp = (String) comboBox.getSelectedItem();

                    ArrayList<Estudiante> filter = new ArrayList<>();

                    switch (tmp) {
                        case "Carné":

                            try {
                                filter = Estudiante.filtrarPorCarnet(busqueda.getText());
                            } catch (LibreriaException e) {

                                e.printStackTrace();
                            }

                            break;

                        case "Nombre":

                            try {
                                filter = Estudiante.filtrarPorNombre(busqueda.getText());

                            } catch (LibreriaException e) {
                                
                                e.printStackTrace();
                            }

                            break;

                        case "Código carrera":
                            try {

                                if (esNumeroEntero(busqueda.getText())) {
                                    
                                 //   filter = Estudiante.filtrarPorCarrera(busqueda.getText());
                                }else {

                                }


                            } catch (LibreriaException e) {
                               
                                e.printStackTrace();
                            }

                            break;
                    }

                } else if (selecion.equals("Devolucion")) {

                    System.out.println(" tenemos devolucion");

                    String tmp = (String) comboBox.getSelectedItem();

                }
            }
        }
    }

    public  boolean esNumeroEntero(String str) {
        // Utilizar una expresión regular para verificar si el String es un número entero
        return str.matches("-?\\d+");
    }


}
