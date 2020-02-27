/*
Hector enero 2020 
*/
import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histrograma;
import gui.JFrameExpansion;
import gui.JFrameImage;
import gui.JFrameSegmentacion;
import gui.Segmentar;
import open.AbrirImagen;

import java.awt.Image;

public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        Histrograma h = new Histrograma(imagen);
        Image umbral = FiltrosEspaciales.segmentarImagen(imagen,20,70);
        JFrameImage jFrameImage = new JFrameImage(umbral);
    }
}