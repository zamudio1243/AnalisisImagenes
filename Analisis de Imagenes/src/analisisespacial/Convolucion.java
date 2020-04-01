package analisisespacial;
/*
Hector marzo 2020 
*/

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convolucion {
    public static Image aplicarConvolucion(Image imgOriginal, int[][] mascara, int div, int offset){
        BufferedImage bufferOrginal = AbrirImagen.toBufferedImage(imgOriginal);
        int ancho = bufferOrginal.getWidth();
        int alto = bufferOrginal.getHeight();
        BufferedImage bufferSuavizado = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
        //Recorrer el buffer
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int rgb= calcularNuevoTono(i,j,bufferOrginal,mascara,div,offset);
                bufferSuavizado.setRGB(i,j,rgb);
            }
        }

        return  AbrirImagen.toImage(bufferSuavizado);
    }


    private static int calcularNuevoTono(int x, int y, BufferedImage bufferOriginal, int[][] mascara, int div, int offset) {
        //Recorrer la mascara

        Color color;
        int auxRed=0,auxBlue=0,auxGreen=0,k=0;
        for (int i = 0,r=x-1; i < mascara.length; i++,r++) {
            for (int j = 0,c=y-1; j < mascara[0].length; j++,c++) {
                if (mascara[i][j] != 0){
                    try {
                        int rgb = bufferOriginal.getRGB(r,c);
                        k++;
                        color = new Color(rgb);
                        auxRed+= color.getRed()*mascara[i][j];
                        auxBlue+= color.getBlue()*mascara[i][j];
                        auxGreen+= color.getGreen()*mascara[i][j];

                    }catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }
        auxRed/=div;
        auxGreen/=div;
        auxBlue/=div;
        color = new Color(validadLimites(auxRed+offset),
                          validadLimites(auxGreen+offset),
                          validadLimites(auxBlue+offset));
        return  color.getRGB();
    }
    public static int validadLimites(int i){
        if(i<0)   return 0;
        if(i>255) return 255;
        return i;
    }
    
}
