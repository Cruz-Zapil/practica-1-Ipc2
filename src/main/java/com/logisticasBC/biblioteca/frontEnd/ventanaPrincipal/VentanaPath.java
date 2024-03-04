package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JLabel;

import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Message;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Directorio;

public class VentanaPath extends JFrame implements ActionListener {

    private JTextField directorio = new JTextField();
    private JButton buscador = new JButton();
    private JButton aceptar = new JButton();
    private JLabel texto1 ,texto2;
    private String pathRecuperado = new String();
    private JPanel panel = new JPanel();

    public VentanaPath() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Direcctorio");

        this.setBounds(300, 300, 460, 290);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        addComponents();

    }

    private void addComponents() {

        panel.setBounds(0, 0, 460, 290);
        panel.setBackground(new Color(255, 228, 181));
        panel.setLayout(null);

        directorio.setBounds(30, 120, 300, 30);

        buscador.setBounds(330, 120, 90, 30);
        buscador.setText("Buscar");
        buscador.addActionListener(this);

        aceptar.setBounds(330, 180, 90, 30);
        aceptar.setText("Aceptar");
        aceptar.addActionListener(this);

        texto1 = new JLabel("Seleccione un directorio donde se");
        texto1.setBounds(30, 30, 390, 60);
        texto1.setBackground(new Color(255, 228, 181));
        texto2 = new JLabel("almacenan los datos");
        texto2.setBounds(30,52,390,60);

        // Modificar el tamaño de la fuente del JLabel
        Font fuenteOriginal = texto1.getFont();
        texto1.setFont(new Font(fuenteOriginal.getName(), Font.TYPE1_FONT, fuenteOriginal.getSize() + 8));

        Font tmp = texto2.getFont();
        texto2.setFont(new Font(tmp.getName(), Font.TYPE1_FONT, tmp.getSize() + 8));

        panel.add(texto1);
        panel.add(texto2);
        panel.add(directorio);
        panel.add(buscador);
        panel.add(aceptar);

        this.add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == buscador) {
            /// abre ventana de buscador
            directorio.setText(new Directorio().obtenerRutaCarpeta());

        } else if (event.getSource() == aceptar) {

            if (!directorio.getText().equals("")) {

                this.dispose();

                new VentanaPrincipal();

            } else {

               Message.mostrarMensajeError("Seleccione una carpeta valida ","Error de selecion ");
            }

        }

    }

}
