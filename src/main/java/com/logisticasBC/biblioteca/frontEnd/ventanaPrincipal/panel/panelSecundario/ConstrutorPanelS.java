package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.JLabel;

public class ConstrutorPanelS extends JPanel {

    private JPanel panelTitulo = new JPanel();

    public ConstrutorPanelS(String texto) {

        this.setLayout(null);
        this.setBounds(0, 0, 690, 750);
        this.setBackground(new Color(255, 228, 181));

        tituloPanel(texto);

    }

    public void tituloPanel(String titulo) {

        panelTitulo.setBounds(0, 15, 690, 90);
        panelTitulo.setBackground(new Color(25, 228, 181));

        JLabel label = new JLabel(titulo);

        Font fuente = label.getFont();
        label.setFont(new Font(fuente.getName(), Font.BOLD, fuente.getSize() + 50));

        panelTitulo.add(label);

        this.add(panelTitulo);

    }

}
