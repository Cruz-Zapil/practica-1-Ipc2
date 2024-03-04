package com.logisticasBC.biblioteca.frontEnd.utilFrontEnd;

import java.nio.file.Paths;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import java.io.File;


public class Directorio {


    public String obtenerRutaCarpeta() {
        JFileChooser miBuscador = new JFileChooser(".");
        miBuscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo seleccionar directorios

        int valor = miBuscador.showOpenDialog(miBuscador);
        if (valor == JFileChooser.APPROVE_OPTION) {
            String rutaCarpeta = miBuscador.getSelectedFile().getAbsolutePath();
            System.out.println(rutaCarpeta);
            return rutaCarpeta;
        }

        return null; // Retorna null si la selección fue cancelada
    }


    public String mobtenerRutaCarpeta() {
        JFileChooser miBuscador = new JFileChooser(".");
        miBuscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo seleccionar directorios

        int valor = miBuscador.showOpenDialog(miBuscador);
        if (valor == JFileChooser.APPROVE_OPTION) {
            File seleccionado = miBuscador.getSelectedFile();
            String rutaCarpetaAbsoluta = seleccionado.getAbsolutePath();

            // Obtener la ruta absoluta del directorio base (puede ser el directorio actual)
            String directorioBase = System.getProperty("user.dir");
            Path pathBase = Paths.get(directorioBase);

            // Obtener la ruta relativa
            Path pathAbsoluto = Paths.get(rutaCarpetaAbsoluta);
            Path pathRelativo = pathBase.relativize(pathAbsoluto);

            String rutaCarpetaRelativa = pathRelativo.toString();
            System.out.println(rutaCarpetaRelativa);

            return rutaCarpetaRelativa;
        }

        return null; // Retorna null si la selección fue cancelada
    }

}
