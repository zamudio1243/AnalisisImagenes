package analisisespacial;
/*
Hector marzo 2020 
*/

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Suavizado {
    public static Image agrgegarRuidoAditivo(Image imgOriginal, int porcentaje){
        int dim = imgOriginal.getHeight(null)*imgOriginal.getWidth(null);
        int cantPixeles= dim*porcentaje/100;
        BufferedImage bi = AbrirImagen.toBufferedImage(imgOriginal);
        Random random = new Random();
        for (int i = 0; i < cantPixeles; i++) {
            int r = random.nextInt(bi.getWidth());
            int c = random.nextInt(bi.getHeight());
            bi.setRGB(r,c, Color.white.getRGB());
        }
        return  AbrirImagen.toImage(bi);
    }
    public static Image agrgegarRuidoSustractivo(Image imgOriginal, int porcentaje){
        int dim = imgOriginal.getHeight(null)*imgOriginal.getWidth(null);
        int cantPixeles= dim*porcentaje/100;
        BufferedImage bi = AbrirImagen.toBufferedImage(imgOriginal);
        Random random = new Random();
        for (int i = 0; i < cantPixeles; i++) {
            int r = random.nextInt(bi.getWidth());
            int c = random.nextInt(bi.getHeight());
            bi.setRGB(r,c, Color.black.getRGB());
        }
        return  AbrirImagen.toImage(bi);
    }
}
