package com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.panelImportarDato;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.logisticasBC.biblioteca.backEnd.ControladorAchivos;
import com.logisticasBC.biblioteca.backEnd.LibreriaException;
import com.logisticasBC.biblioteca.backEnd.ListarFiltrarArchivos;
import com.logisticasBC.biblioteca.frontEnd.utilFrontEnd.Directorio;
import com.logisticasBC.biblioteca.frontEnd.ventanaPrincipal.panel.panelSecundario.ConstructorPanelS;
import com.vaadin.ui.ComboBox;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImportarDato extends ConstructorPanelS implements ActionListener {

    private JLabel[] jlabel = new JLabel[3];

    private static JTextArea erorres = new JTextArea();
    // private static JTable info = new JTable

    private static ComboBox datosRegistrados = new ComboBox<>();

    /*
     * componenetes:
     * 3 etiquetas ,
     * 1 text area para mostrar errores
     * 1 combobox
     * 3 botones: buscar archivo, aceptar y cancelar
     * 1 textfile para buscar
     * 
     */

    public ImportarDato() {
        super("Importar Datos");

        addBotonsEtiqueta();
        addComponents();
        addAreaTexto();
    }

    private void addBotonsEtiqueta() {

        JButton buscar = new JButton();
        buscar.setText("Abrir Archivo");
        buscar.setBounds(60, 125, 120, 30);
        buscar.addActionListener(this);

        JButton aceptar = new JButton();
        aceptar.setText("Guardar");
        aceptar.setBounds(450, 620, 90, 30);
        aceptar.addActionListener(this);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(570, 620, 90, 30);
        cancelar.addActionListener(this);

        this.add(aceptar);
        this.add(cancelar);
        this.add(buscar);

        // agregando etiquetas

        jlabel[0] = new JLabel("Datos Registrados: ");
        jlabel[0].setBounds(60, 160, 200, 30);

        jlabel[1] = new JLabel("Error de Registro");
        jlabel[1].setBounds(60, 420, 200, 30);

        this.add(jlabel[0]);
        this.add(jlabel[1]);

    }

    private void addComponents() {

        JLabel cuadroText = new JLabel();
        cuadroText.setBounds(200, 125, 200, 30);
        cuadroText.setText(" : ");
        this.add(cuadroText);

    }

    private void addAreaTexto() {

        erorres.setBounds(60, 460, 600, 150);
        erorres.setEditable(false);

        // Crear JScrollPane para manejar el desplazamiento del texto
        JScrollPane scrollPane = new JScrollPane(erorres);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(60, 460, 600, 150);

        // Agregar el JScrollPane a la ventana
        this.add(scrollPane);


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

        this.add(new MostradorDatos(holi, filas, 185));

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) event.getSource();

            if (sourceButton.getText().equals("Abrir Archivo")) {

                String tmp = new Directorio().obtenerRutaArchivo();
            
                try {
                    
                    ArrayList<String> datos = ControladorAchivos.leerArchivoTxt(tmp);

                    for (String linea : datos) {
                        erorres.append(linea + "\n"); // Agregar cada línea al JTextArea
                    }
                    
                    ListarFiltrarArchivos.getEstudiantes();

                } catch (LibreriaException e) {
                    
                    e.printStackTrace();

                }

            }

        }
    }

}
