package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.vaadin.ui.ComboBox;

public class MostrarDatoReporte extends JPanel {

    public DefaultTableModel model;

    private String[] columna = { "Libro", "Estudiante", "Devolucion" };
    private ArrayList<ArrayList<String>> datosFila = new ArrayList<>();
    
    private ComboBox filtros = new ComboBox();

    public MostrarDatoReporte(ArrayList<ArrayList<String>> datosFila) {
        this.datosFila = datosFila;
        this.setLayout(null);
        this.setBounds(90, 300, 540, 320);
        this.setBackground(new Color(255, 228, 181));
        agregarTabla();
    }

    public void agregarTabla() {
        model = new DefaultTableModel();

        for (String columnas : columna) {
            model.addColumn(columnas);
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
        header.setPreferredSize(new Dimension(0, 0));
        table.setTableHeader(header);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(500, 400));
        scrollPane.setBounds(0, 0, 540, 320);

    

        this.add(scrollPane);
    }

   
}
