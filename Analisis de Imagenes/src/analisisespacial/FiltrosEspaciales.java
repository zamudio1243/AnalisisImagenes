package analisisespacial;
/*
Hector febrero 2020 
*/

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class FiltrosEspaciales {
    public static Image generarImagenGrises(Image imagen){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < bi.getWidth(); i++) {

            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                color = new Color(prom,prom,prom);
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static Image generarImagenNegativa(Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                color = new Color(255-color.getRed(),255-color.getGreen(),255-color.getBlue());
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static Image iluminar(Image imagen,int alpha){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                //Validaciones
                int R = color.getRed() + alpha;
                int G = color.getGreen() + alpha;
                int B = color.getBlue() +alpha;
                color = new Color(FiltrosEspaciales.validadLimites(R),
                        FiltrosEspaciales.validadLimites(G),
                        FiltrosEspaciales.validadLimites(B));
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }

    public static Image temperatura(Image imagen,int alpha){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                //Validaciones
                int R = color.getRed()   + alpha;
                int B = color.getBlue()  - alpha;
                color = new Color(FiltrosEspaciales.validadLimites(R),
                        color.getGreen(),
                        FiltrosEspaciales.validadLimites(B));
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static Image segmentarImagen(Image imagen,int umbral){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color colorFondo = new Color(255,255,255);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                if(prom>umbral)
                    bi.setRGB(i,j,colorFondo.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static Image segmentarImagen(Image imagen,int u1, int u2){
        System.out.println("u1: "+u1+", u2: "+u2);
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color colorFondo = new Color(255,255,255);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                if(!(prom>= u1 && prom<=u2))
                    bi.setRGB(i,j,colorFondo.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }

    public static Image contrastarImagen(Image imagen,int max,int min){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);

        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int promR  =FiltrosEspaciales.validadLimites((255/(max-min))*(color.getRed()-min));
                int promG  =FiltrosEspaciales.validadLimites((255/(max-min))*(color.getGreen()-min));
                int promB  =FiltrosEspaciales.validadLimites((255/(max-min))*(color.getBlue()-min));
                color= new Color(promR,promG,promB);
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }

    public static int validadLimites(int i){
        if(i<0)   return 0;
        if(i>255) return 255;
        return i;
    }
}
