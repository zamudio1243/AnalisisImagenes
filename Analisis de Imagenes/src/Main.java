/*
Hector enero 2020 
*/
import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histrograma;
import gui.JFrameImage;
import gui.JFrameSegmentacion;
import gui.Segmentar;
import open.AbrirImagen;

import java.awt.Image;

public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        //Segmentar segmentar = new Segmentar(imagen);
        JFrameSegmentacion segmentar = new JFrameSegmentacion("segmentarcion",imagen);

    }
}