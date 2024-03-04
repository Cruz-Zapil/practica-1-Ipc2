package com.logisticasBC.biblioteca.frontEnd.utilFrontEnd;

import java.nio.file.Paths;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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


    public String obtenerRutaArchivo() {
        JFileChooser miBuscador = new JFileChooser(".");
        miBuscador.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo seleccionar archivos
        // Opcional: Filtrar por tipo de archivo
        miBuscador.setFileFilter(new FileNameExtensionFilter("Archivos", "txt", "pdf", "doc"));

        int valor = miBuscador.showOpenDialog(null);
        if (valor == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = miBuscador.getSelectedFile().getAbsolutePath();
            System.out.println(rutaArchivo);
            return rutaArchivo;
        }

        return null; // Retorna null si la selección fue cancelada
    }


}
