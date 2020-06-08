/*
Hector abril 2020 
*/

import fft.Gestor;
import fft.NumeroComplejo;
import filtros.*;
import gui.JFrameImage;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrecuenciasMain {
    public static void main(String[] args) {

        Image imagenori = AbrirImagen.openImage();
        int size = 512;
        Image imagenO = imagenori.getScaledInstance(size,size,BufferedImage.TYPE_INT_RGB);
        JFrameImage frame1 = new JFrameImage(imagenO);
        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(imagenO));
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(true);
        JFrameImage frame2 = new JFrameImage(AbrirImagen.toImage(iFre));

        // creamos el filtro
        //FiltroIdealPasaAltas f = new FiltroIdealPasaAltas(50,new Dimension(512,512));
        //Butterworth f = new Butterworth(20,new Dimension(size,size),false,10);
        //Bandas f = new Bandas(30,50,true, new Dimension(512,512));
        //Gaussiano f = new Gaussiano(400, true, new Dimension(512,512));
        FiltroSelectivo f = new FiltroSelectivo(280,220,10,new Dimension(size,size),true);
        f.crearFiltro();

        NumeroComplejo[][] filtro = f.getFiltroEspacial();


        JFrameImage frameFil = new JFrameImage(f.getImagen());
        gestor.aplicarFiltro(filtro);


        BufferedImage imagenEspacial = gestor.obtenerImagenEspacial();
        JFrameImage frame = new JFrameImage(AbrirImagen.toImage(imagenEspacial));


        ///////////////////////
        Image imagenO2 = AbrirImagen.toImage(imagenEspacial);
        JFrameImage frame12 = new JFrameImage(imagenO2);
        Gestor gestor2 = new Gestor(AbrirImagen.toBufferedImage(imagenO2));
        BufferedImage iFre2 = gestor2.obtenerImagenFrecuencias(true);
        JFrameImage frame22 = new JFrameImage(AbrirImagen.toImage(iFre2));

        // creamos el filtro
        //FiltroIdealPasaAltas f = new FiltroIdealPasaAltas(50,new Dimension(512,512));
        //Butterworth f = new Butterworth(20,new Dimension(size,size),false,10);
        //Bandas f = new Bandas(30,50,true, new Dimension(512,512));
        //Gaussiano f = new Gaussiano(400, true, new Dimension(512,512));
        FiltroSelectivo f2 = new FiltroSelectivo(232,220,10,new Dimension(size,size),true);
        f2.crearFiltro();

        NumeroComplejo[][] filtro2 = f2.getFiltroEspacial();


        JFrameImage frameFil2 = new JFrameImage(f2.getImagen());
        gestor2.aplicarFiltro(filtro2);


        BufferedImage imagenEspacial2 = gestor2.obtenerImagenEspacial();
        JFrameImage frame222 = new JFrameImage(AbrirImagen.toImage(imagenEspacial2));
        //////////

        Image imagenO22 = AbrirImagen.toImage(imagenEspacial2);
        JFrameImage frame122 = new JFrameImage(imagenO22);
        Gestor gestor22 = new Gestor(AbrirImagen.toBufferedImage(imagenO22));
        BufferedImage iFre22 = gestor22.obtenerImagenFrecuencias(true);
        JFrameImage frame2222 = new JFrameImage(AbrirImagen.toImage(iFre22));


    }
}
