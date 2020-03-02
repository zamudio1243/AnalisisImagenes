package expansiones;
/*
Hector marzo 2020 
*/

import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histrograma;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Expansion {


    public static Image expansionLineal(int r1, int r2, Image imagen){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = ((color.getRed()-r2)*255)/(r2-r1);
                int g = ((color.getGreen()-r2)*255)/(r2-r1);
                int b = ((color.getBlue()-r2)*255)/(r2-r1);
                color = new Color(
                        Expansion.validadLimites(r),
                        Expansion.validadLimites(g),
                        Expansion.validadLimites(b)
                );
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }

    public static Image expansionLineal(Histrograma h, Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = ((color.getRed()-h.getMinR())*255)/(h.getMaxR()-h.getMinR());
                int g = ((color.getGreen()-h.getMinG())*255)/(h.getMaxG()-h.getMinG());
                int b = ((color.getBlue()-h.getMinB())*255)/(h.getMaxB()-h.getMinB());

                color = new Color(
                        Expansion.validadLimites(r),
                        Expansion.validadLimites(g),
                        Expansion.validadLimites(b)
                );
                bi.setRGB(x,y,color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    public static Image expansionLogaritmica(Image imagen) {
        BufferedImage buffer = AbrirImagen.toBufferedImage(imagen);

        for (int i = 0; i < buffer.getWidth(); i++) {
            for (int j = 0; j < buffer.getHeight(); j++) {
                Color color = new Color(buffer.getRGB(i, j));
                //formula: (255*ln(1+color_original(i,j)))/(ln(1+255))
                double r= 255 * Math.log(1 + color.getRed()) / Math.log(256);
                double g =255 * Math.log(1 + color.getGreen()) / Math.log(256);
                double b = 255 * Math.log(1 + color.getBlue()) / Math.log(256);

                color = new Color(validadLimites((int)r),validadLimites((int)g),validadLimites((int)b));

                buffer.setRGB(i, j, color.getRGB());
            }
        }
        return AbrirImagen.toImage(buffer);
    }
    public static Image expansionExponencial(Image imagen, double z) {
        BufferedImage buffer = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < buffer.getWidth(); i++) {
            for (int j = 0; j < buffer.getHeight(); j++) {
                Color color = new Color(buffer.getRGB(i, j));

                //formula: (1+(1/z))^(color_original(i,j)
                double r = Math.pow((1+(1/z)), color.getRed());
                double g = Math.pow((1+(1/z)), color.getGreen());
                double b = Math.pow((1+(1/z)), color.getBlue());

                color = new Color(validadLimites((int)r),validadLimites((int)g),validadLimites((int)b));

                buffer.setRGB(i, j, color.getRGB());
            }
        }
        return AbrirImagen.toImage(buffer);
    }


    public static int validadLimites(int i){
        if(i<0)   return 0;
        if(i>255) return 255;
        return i;
    }
}
