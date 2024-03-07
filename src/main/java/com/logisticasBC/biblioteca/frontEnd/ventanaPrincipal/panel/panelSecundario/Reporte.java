package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.MostrarDatoReporte;

public class Reporte extends ConstructorPanelS implements ActionListener {

    private JButton[] botones = new JButton[4];
    private String[] etiqueta = { "Libro", "Estudiante", "Devolucion","Buscar" };
    private JComboBox<String> comboBox ;
    private JTextField busqueda ;
    private JLabel etiquetaBusqueda;

    private String[] filttroLibro = { "Código", "Autor", "Titulo" };
    private String[] filtroEstudiante = { "Carné", "Nombre", "Codigo" };
    private String[] filtroPrestamo = { "Libros que se entregan hoy", "Libros que estan en mora", "Dinero Recaudado",
            "Listado hechos por un estudiante", "Prestamos hechos a una carrera", "Libros prestados actualmente" };

    public Reporte() {
        super(" Reportes");

        addComponents();
        addBottons();
        addItemCombo();
    }

    private void addComponents() {

        ArrayList<ArrayList<String>> filas = new ArrayList<>();
        filas.add(new ArrayList<>(List.of("Dato 1-1", "Dato 1-2", "datos")));

        this.add(new MostrarDatoReporte(filas));

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
        
        botones[3].setBounds(450,200,160,30);
        this.add(botones[3]);

    }

    private void addItemCombo() {

        etiquetaBusqueda = new JLabel("Buscar");
        etiquetaBusqueda.setBounds(275,170,160,30);

        busqueda = new JTextField();
        busqueda.setBounds(275,200,160,30);


        comboBox = new JComboBox<>();
        comboBox.setBounds(90,200,180,30);
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

                for (int i = 0; i <filttroLibro.length; i++) {
                    comboBox.addItem(filttroLibro[i]);                    
                }

                 

            } else if (sourceButton.getText().equals("Estudiante")) {
                comboBox.removeAllItems();

                for (int i = 0; i <filtroEstudiante.length; i++) {
                    comboBox.addItem(filtroEstudiante[i]);                    
                }


            } else if (sourceButton.getText().equals("Devolucion")) {
          

            }else if (sourceButton.getText().equals("Buscar")){
                System.out.println("buscar");

            }
        }

    }

}
