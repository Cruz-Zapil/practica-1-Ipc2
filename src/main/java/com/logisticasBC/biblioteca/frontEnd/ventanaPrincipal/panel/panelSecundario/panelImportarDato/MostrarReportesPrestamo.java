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

import com.logisticasBC.biblioteca.backEnd.Prestamo;

public class MostrarReportesPrestamo extends JPanel {

    public static DefaultTableModel model;
    String[] columna = { "Código Libro", "Carné Estudiante", "Fecha Prestamo", "Sin cancelar" };

    public MostrarReportesPrestamo(ArrayList<Prestamo> datosFila) {

        this.setLayout(null);
        this.setBounds(0,0, 600, 420);
        this.setBackground(new Color(255, 228, 181));
        agregrarTabla(datosFila);

    }

    public void agregrarTabla(ArrayList<Prestamo> envio) {

        model = new DefaultTableModel();
        // Limpiar el modelo existente
        model.setRowCount(0);


        for (String columnas : columna) {
            model.addColumn(columnas);
        }

        // Agregar los datos de las filas al modelo de tabla
        for (Prestamo fila : envio) {
            model.addRow(new Object[] { fila.getCodigoLibro(), fila.getCarneEstudiante(), fila.getFechaPrestamo(),
                    fila.isPrestamoActivo() });
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
