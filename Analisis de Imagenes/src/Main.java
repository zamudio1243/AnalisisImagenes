/*
Hector enero 2020 
*/
import gui.Segmentar;
import open.AbrirImagen;

import java.awt.Image;

public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        Segmentar segmentar = new Segmentar(imagen);



        /*
        Image imagen = AbrirImagen.openImage();
        JFrameImage original = new JFrameImage(imagen);
        Histrograma hisOriginal = new Histrograma(imagen);
        hisOriginal.graficarHistogramas();
        // Aumentar temperatura

        Image filtro = FiltrosEspaciales.temperatura(imagen,30);
        Histrograma hisFiltro = new Histrograma(filtro);
        hisFiltro.graficarHistogramas();
        JFrameImage resultado = new JFrameImage(filtro);
        //Reducir temperatura

        Image filtr2 = FiltrosEspaciales.temperatura(imagen,-30);
        Histrograma hisFiltro2 = new Histrograma(filtr2);
        hisFiltro2.graficarHistogramas();
        JFrameImage resultado2 = new JFrameImage(filtr2);

         */
    }
}