/*
Hector enero 2020 
*/
import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histrograma;
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
        Histrograma h = new Histrograma(imagen);
        h.graficarHistogramas();

        JFrameImage imagenOriginal = new JFrameImage(imagen);

        Image contraste = Expansion.expansionPropia(imagen);
        Histrograma hContraste = new Histrograma(contraste);
        hContraste.graficarHistogramas();
        JFrameImage imagenContradada = new JFrameImage(contraste);


    }
}