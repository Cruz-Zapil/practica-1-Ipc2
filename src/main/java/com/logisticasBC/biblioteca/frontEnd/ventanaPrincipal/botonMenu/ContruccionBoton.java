package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.botonMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ContruccionBoton extends JButton {

    // Atributos de los botones de menu:
    // color de fondo
    // color de border
    // texto
    // accion

    public ContruccionBoton( String texto, ActionListener accion) {
      
        Color colorFondo = new Color(205, 133, 63);
        Border border = BorderFactory.createLineBorder(colorFondo.darker()); 
        Border margin = new EmptyBorder(15, 30, 15, 30);
        this.setBorder(new CompoundBorder(border, margin));
        this.setBackground(colorFondo);

        this.setForeground(new Color(0,0,0));
        
        this.setText(texto);
        Font fuente = this.getFont();
        this.setFont(new Font(fuente.getName(), Font.BOLD, fuente.getSize() + 4)); // Aumentar el tamaño de la fuente


        this.addActionListener(accion);

    }

}
