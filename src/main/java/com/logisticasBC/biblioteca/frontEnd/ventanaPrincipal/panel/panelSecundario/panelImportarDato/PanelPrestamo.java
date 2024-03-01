package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class PanelPrestamo extends JPanel {

    public static DefaultTableModel model;

    private ArrayList<String> datosColumna = new ArrayList<>();
    private ArrayList<ArrayList< String>> datosFila= new ArrayList<>();

    public PanelPrestamo(ArrayList<String> datosColumna, ArrayList<ArrayList<String>> datosFila, int y_pos) {

        this.datosColumna = datosColumna;
        this.datosFila = datosFila;
        this.setLayout(null);
        this.setBounds(60, y_pos, 600, 220);
        agregrarTabla();

    }

    public void agregrarTabla() {

        model = new DefaultTableModel();

        for (String columna : datosColumna) {
            model.addColumn(columna);
        }

        // Agregar los datos de las filas al modelo de tabla
        for (ArrayList<String> fila : datosFila) {
            model.addRow(fila.toArray());
        }
   

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13)); 
        table.setRowHeight(30); 
        table.setIntercellSpacing(new Dimension(0, 0)); 
        table.setLocation(0, 0);

        // Personaliza el encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14)); 
        header.setBackground(new Color(255, 182, 193));  
        header.setForeground(Color.black);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 
        scrollPane.setPreferredSize(new Dimension(500, 400)); 
        scrollPane.setBounds(0, 0, 600, 220);

        this.add(scrollPane);
    }

}
