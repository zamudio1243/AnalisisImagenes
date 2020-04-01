/*
Hector enero 2020 
*/

import analisisespacial.Convolucion;

import analisisespacial.Mascaras;
import gui.JFrameImage;
import open.AbrirImagen;
import java.awt.Image;


public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        JFrameImage original = new JFrameImage(imagen);

        Image GX = Convolucion.aplicarConvolucion(imagen, Mascaras.PrewittGX,1,0);
        Image GY = Convolucion.aplicarConvolucion(imagen, Mascaras.PrewittGY,1,0);

        JFrameImage s = new JFrameImage(GY);







    }
}