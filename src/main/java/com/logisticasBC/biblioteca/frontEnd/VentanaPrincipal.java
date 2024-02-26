package com.logisticasBC.biblioteca.frontEnd;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private static  JPanel panelCentral;
    private static  JPanel panelMenu;

    public VentanaPrincipal (){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle(" Biblioteca Cunoc");

        this.setBounds(300,300,900,750);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        panelCentral= new JPanel();
        panelMenu = new JPanel(); 

        panelMenu.setBounds(0,0,210,900);
        panelCentral.setBounds(210,0,690,900);

        panelMenu.setBackground(Color.BLACK);
        panelCentral.setBackground(Color.BLUE);

        this.add(panelMenu);
        this.add(panelCentral);
      

     
    }

    

}
