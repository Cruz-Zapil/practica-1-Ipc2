package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.accionesUsuario.actionPanelSecundario.ActionDevolution;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato.PanelPrestamo;

public class Devolucion extends ConstrutorPanelS {

    /*
     * 3 botones
     * 4 etiquetas
     * 2 textfield
     */

    private String[] etiqueta = { "Carné estudiante", "Total: ", "Código libro", };

    private String[] textBoton = { "Buscar", "Aceptar", "Cancelar" };

    private JLabel[] label = new JLabel[3];
    private JTextField estudiante = new JTextField();
    private JTextField libro = new JTextField();

    // agregnado accion
    ActionDevolution ActionButtons = new ActionDevolution(); 

    public Devolucion() {
        super(" Devolucion ");

        addEtiquetas();
        addJTextFiel();
        addTabla();
        addButtons();
    }

    private void addEtiquetas() {

        int x = 60;
        int y = 200;

        for (int i = 0; i < etiqueta.length; i++) {

            label[i] = new JLabel(etiqueta[i]);
            label[i].setLocation(x, y);
            label[i].setSize(200, 30);
            y = y + 90;

            if (i == 1) {
                x = x + 290;
                y = 200;
            }
            this.add(label[i]);
        }
    }

    private void addJTextFiel() {

        estudiante.setBounds(60, 230, 180, 30);
        libro.setBounds(350, 230, 180, 30);

        this.add(estudiante);
        this.add(libro);

    }

    private void addButtons() {

        JButton[] botones = new JButton[3];

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
            botones[i].addActionListener(ActionButtons);
            botones[i].setText(textBoton[i]);
            this.add(botones[i]);
        }

        botones[0].setBounds(570, 230, 90, 30);

        botones[1].setBounds(450, 620, 90, 30);

        botones[2].setBounds(570, 620, 90, 30);

    }

    private void addTabla() {

        ArrayList<String> holi = new ArrayList<>();
        ArrayList<ArrayList<String>> filas = new ArrayList<>();
        filas.add(new ArrayList<>(List.of("Dato 1-1", "Dato 1-2", "datos")));
        filas.add(new ArrayList<>(List.of("Dato 2-1", "Dato 2-2")));
        filas.add(new ArrayList<>(List.of("Dato 1-1", "Dato 1-2", "datos")));
        filas.add(new ArrayList<>(List.of("Dato 2-1", "Dato 2-2")));
        filas.add(new ArrayList<>(List.of("Dato 1-1", "Dato 1-2", "datos")));
        filas.add(new ArrayList<>(List.of("Dato 2-1", "Dato 2-2")));
        filas.add(new ArrayList<>(List.of("Dato 1-1", "Dato 1-2", "datos")));
        filas.add(new ArrayList<>(List.of("Dato 2-1", "Dato 2-2")));

        holi.add(" uno");
        holi.add(" dos ");
        holi.add(" tres");
        holi.add(" cuatro");
        holi.add(" cinco");

        this.add(new PanelPrestamo(holi, filas, 310));
    }

}
