/*
Hector enero 2020 
*/

import analisisespacial.Convolucion;

import gui.JFrameImage;
import open.AbrirImagen;
import java.awt.Image;


public class Main {

    public static void main(String[] args) {
        Image imagen = AbrirImagen.openImage();
        JFrameImage original = new JFrameImage(imagen);

        int[][] mascara = new int[][]{
                {-2,-1,0},
                {-1,1,1},
                {0,1,2}
        };
        Image enfoque = Convolucion.aplicarConvolucion(imagen,mascara,1,0);
        JFrameImage s = new JFrameImage(enfoque);







    }
}