package analisisespacial;
/*
Hector enero 2020 
*/

import java.awt.*;
import java.awt.image.BufferedImage;

public class DibujarCirculo {

    public static int dibujarX( double cx, double angulo,int radio){
        angulo=angulo*Math.PI/180;
        cx= radio*Math.cos(angulo) +cx;
        return (int)cx;
    }
    public static int dibujarY( double cy, double angulo,int radio){
        angulo=angulo*Math.PI/180;
        cy= radio*Math.sin(angulo) +cy;
        return (int)cy;
    }
    public static BufferedImage dibujarCirculo(BufferedImage Bimage, Color color, int radio ){
        int centroX= Bimage.getWidth()/2;
        int centroY= Bimage.getHeight()/2;
        for (int i = 0; i < 360; i++) {
            Bimage.setRGB(dibujarX(centroX, i, radio), dibujarY(centroY, i, radio), color.getRGB());
            Bimage.setRGB(dibujarX(centroX, i, radio+1), dibujarY(centroY, i, radio+1), color.getRGB());
            Bimage.setRGB(dibujarX(centroX, i, radio-1), dibujarY(centroY, i, radio-1), color.getRGB());
        }
        return Bimage;

    }
}
