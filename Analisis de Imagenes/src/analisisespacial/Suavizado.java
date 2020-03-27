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
    public static Image suaviar(Image imgOriginal,int[][] mascara){
        BufferedImage bufferOrginal = AbrirImagen.toBufferedImage(imgOriginal);
        int ancho = bufferOrginal.getWidth();
        int alto = bufferOrginal.getHeight();
        BufferedImage bufferSuavizado = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
        //Recorrer el buffer
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int rgb= calcularNuevoTono(i,j,bufferOrginal,mascara);
                bufferSuavizado.setRGB(i,j,rgb);
            }
        }


        return  AbrirImagen.toImage(bufferSuavizado);

    }

    private static int calcularNuevoTono(int x, int y, BufferedImage bufferOrginal, int[][] mascara) {
        //Recorrer la mascara

        Color color;
        int auxRed=0,auxBlue=0,auxGreen=0,k=0;
        for (int i = 0,r=x-1; i < mascara.length; i++,r++) {
            for (int j = 0,c=y-1; j < mascara[0].length; j++,c++) {
                if (mascara[i][j] != 0){
                    try {
                        int rgb = bufferOrginal.getRGB(r,c);
                        k++;
                        color = new Color(rgb);
                        auxRed+= color.getRed()*mascara[i][j];
                        auxBlue+= color.getBlue()*mascara[i][j];
                        auxGreen+= color.getGreen()*mascara[i][j];

                    }catch (ArrayIndexOutOfBoundsException e){
                        //e.printStackTrace();

                    }catch (ArithmeticException a){
                        k=1;
                    }
                }
            }
        }
        auxRed/=k;
        auxGreen/=k;
        auxBlue/=k;
        color = new Color(auxRed,auxGreen,auxBlue);

        return color.getRGB();
    }
}
