/*
Hector abril 2020 
*/

import analisisespacial.FiltrosEspaciales;
import fft.Gestor;
import fft.NumeroComplejo;
import filtros.Butterworth;
import filtros.FiltroIdealPasaAltas;
import filtros.FiltroIdealPasaBajas;
import gui.JFrameImage;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrecuenciasMain {
    public static void main(String[] args) {

        Image imagenori = AbrirImagen.openImage();
        Image imagenO = imagenori.getScaledInstance(512,512,BufferedImage.TYPE_INT_RGB);
        JFrameImage frame1 = new JFrameImage(imagenO);
        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(imagenO));
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(true);
        JFrameImage frame2 = new JFrameImage(AbrirImagen.toImage(iFre));

        // creamos el filtro
        Butterworth bw = new Butterworth(20,new Dimension(512,512),false,2);
        bw.crearFiltro();


        NumeroComplejo[][] filtro = bw.getFiltroEspacial();
        System.out.println();

        JFrameImage frameFil = new JFrameImage(bw.getImagen());
        gestor.aplicarFiltro(filtro);


        BufferedImage imagenEspacial = gestor.obtenerImagenEspacial();
        JFrameImage frame = new JFrameImage(AbrirImagen.toImage(imagenEspacial));


    }
}
