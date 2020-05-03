/*
Hector abril 2020 
*/

import analisisespacial.FiltrosEspaciales;
import fft.Gestor;
import fft.NumeroComplejo;
import filtros.FiltroIdealPasaBajas;
import gui.JFrameImage;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrecuenciasMain {
    public static void main(String[] args) {
        Image imagenO = AbrirImagen.openImage();
        JFrameImage frame1 = new JFrameImage(imagenO);
        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(imagenO));
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(true);
        JFrameImage frame2 = new JFrameImage(AbrirImagen.toImage(iFre));

        // creamos el filtro
        FiltroIdealPasaBajas fipb = new FiltroIdealPasaBajas(35,new Dimension(512, 512));
        fipb.crearFiltro();
        NumeroComplejo[][] filtro = fipb.getFiltroEspacial();
        JFrameImage frameFil = new JFrameImage(fipb.getImagen());
        gestor.aplicarFiltro(filtro);


        BufferedImage imagenEspacial = gestor.obtenerImagenEspacial();
        JFrameImage frame = new JFrameImage(AbrirImagen.toImage(imagenEspacial));
    }
}
