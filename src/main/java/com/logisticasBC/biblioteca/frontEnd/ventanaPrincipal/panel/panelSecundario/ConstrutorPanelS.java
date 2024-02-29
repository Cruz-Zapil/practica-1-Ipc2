package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.JLabel;


public class ConstrutorPanelS extends JPanel {

    public ConstrutorPanelS( String texto  ){

        this.setLayout(null);
        this.setBounds(0,0,690,750);
        this.setBackground(new Color( 255,228,181));

 
        JLabel label = new JLabel(texto);
        label.setBounds(190, 30, 600, 75);// Ajusta la posición y tamaño según tus necesidades
        
        Font fuente = label.getFont();
        label.setFont(new Font(fuente.getName(), Font.BOLD, fuente.getSize() + 50));

        

        this.add(label);

    }


     
}
