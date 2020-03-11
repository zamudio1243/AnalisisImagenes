/*
Hector enero 2020 
*/
import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histrograma;
import analisisespacial.Suavizado;
import expansiones.Expansion;
import gui.JFrameExpansion;
import gui.JFrameImage;
import gui.JFrameSegmentacion;
import gui.Segmentar;
import open.AbrirImagen;

import java.awt.Image;

public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        Image imgRuido = Suavizado.agrgegarRuidoAditivo(imagen,1);
        Image imgSus = Suavizado.agrgegarRuidoSustractivo(imagen,1);
        Histrograma hOriginal= new Histrograma(imagen);
        Histrograma hRuido = new Histrograma(imgRuido);
        Histrograma hSust = new Histrograma(imgSus);
        JFrameImage  jFrameImage = new JFrameImage(imagen);
        JFrameImage jFrameImage1 = new JFrameImage(imgRuido);
        JFrameImage jFrameImage2 = new JFrameImage(imgSus);
        hOriginal.graficarHistogramas();
        hRuido.graficarHistogramas();
        hSust.graficarHistogramas();






    }
}