package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.PanelMenu;

public class VentanaPrincipal extends JFrame {

    private static  JPanel panelCentral;
    private static  JPanel panelIzquierdo;
    private PanelMenu panelMenu = new PanelMenu();

    public VentanaPrincipal (){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle(" Biblioteca Cunoc");

        this.setBounds(300,300,900,750);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        panelCentral= new JPanel();
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(null);

        panelIzquierdo.setBounds(0,0,210,900);
        panelCentral.setBounds(210,0,690,900);

        panelIzquierdo.setBackground(Color.BLACK);
        panelCentral.setBackground(new Color( 255,228,181));



        /// texto central



        /// agregando panel menu al panel izquierdo 
        

        panelIzquierdo.add(panelMenu);

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
