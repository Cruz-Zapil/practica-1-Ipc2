package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Constructor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.PanelMenu;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.ConstructorPanelS;

public class VentanaPrincipal extends JFrame {

    private static JPanel panelCentral;
    private static JPanel panelIzquierdo;
    private PanelMenu panelMenu = new PanelMenu();

    public VentanaPrincipal() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle(" Biblioteca Cunoc");

        this.setBounds(300, 300, 930, 750);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        panelCentral = new JPanel();
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(null);

        panelIzquierdo.setBounds(0, 0, 210, 750);
        panelCentral.setBounds(210, 0, 720, 750);

        panelIzquierdo.setBackground(Color.BLACK);
        panelCentral.setBackground(new Color(255, 228, 181));

        /// texto central

        JLabel label = new JLabel("BIBLIOTECA");
        label.setBounds(165, 320, 380, 50);// Ajusta la posición y tamaño según tus necesidades
        panelCentral.add(label);

        Font fuente = label.getFont();
        label.setFont(new Font(fuente.getName(), Font.BOLD, fuente.getSize() + 50));

        /// agregando panel menu al panel izquierdo

        panelIzquierdo.add(panelMenu);
        panelCentral.setLayout(null);
        this.add(panelIzquierdo);
        this.add(panelCentral);

    }

    public static void addPanelCentral(JPanel panelCentralx) {
        panelCentral.removeAll();
        panelCentral.add(panelCentralx);
        panelCentral.repaint();
        panelCentral.revalidate();
    }

}
