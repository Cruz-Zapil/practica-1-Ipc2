package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.logisticasBC.biblioteca.backEnd.Estudiante;

public class MostrarReporteEstudiante extends JPanel {

    public static DefaultTableModel model;
    String[] columna = { "Cané", "Nombre", "Carrera", "Fecha Nacimiento" };

    public MostrarReporteEstudiante(ArrayList<Estudiante> datosFila) {

        this.setLayout(null);
        this.setBounds(0,0, 600, 420);
        this.setBackground(new Color(255, 228, 181));
        agregrarTabla(datosFila);

    }

    public void agregrarTabla(ArrayList<Estudiante> envio) {

        model = new DefaultTableModel();

        for (String columnas : columna) {
            model.addColumn(columnas);
        }

        // Agregar los datos de las filas al modelo de tabla
        for (Estudiante fila : envio) {

            model.addRow(
                    new Object[] { fila.getCarnet(), fila.getNombre(), fila.getCarrera(), fila.getFechaNacimiento() });

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
        scrollPane.setBounds(0, 0, 600, 320);

        this.add(scrollPane);
    }

}