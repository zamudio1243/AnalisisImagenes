/*
Hector abril 2020 
*/

import analisisespacial.FiltrosEspaciales;
import fft.Gestor;
import gui.JFrameImage;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrecuenciasMain {
    public static void main(String[] args) {
        int size = 512;
        Image imagen = AbrirImagen.openImage();
        Image imagenO = imagen.getScaledInstance(size,size,BufferedImage.TYPE_INT_RGB);

        JFrameImage frame1 = new JFrameImage(imagenO);
        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(imagenO));
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(true);
        JFrameImage frame2 = new JFrameImage(AbrirImagen.toImage(iFre));

    }
}
