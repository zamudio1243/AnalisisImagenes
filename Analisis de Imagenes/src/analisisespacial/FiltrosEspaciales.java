package analisisespacial;
/*
Hector febrero 2020 
*/

import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;


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

    public static Image binarizar(Image image, int umbral){
        Image gris = FiltrosEspaciales.generarImagenGrises(image);
        Color fondo = new Color(255,255,255);
        Color objeto = new Color(0,0,0);
        BufferedImage bi = AbrirImagen.toBufferedImage(gris);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                if(prom>umbral)
                    bi.setRGB(i,j,fondo.getRGB());
                else
                    bi.setRGB(i,j,objeto.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static Image binarizar(Image image, int u1,int u2){
        Image gris = FiltrosEspaciales.generarImagenGrises(image);
        Color fondo = new Color(255,255,255);
        Color objeto = new Color(0,0,0);
        BufferedImage bi = AbrirImagen.toBufferedImage(gris);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                if(!(prom>= u1 && prom<=u2))
                    bi.setRGB(i,j,fondo.getRGB());
                else
                    bi.setRGB(i,j,objeto.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    public static  Image binzarizacionAutomatica(Image imagen){
        Image gris = FiltrosEspaciales.generarImagenGrises(imagen);
        Histrograma h = new Histrograma(gris);
        int umbral = Umbral.metodoIterativo(h.getGrises());
        System.out.println("Umbral final: "+umbral);
        Color fondo = new Color(255,255,255);
        Color objeto = new Color(0,0,0);
        BufferedImage bi = AbrirImagen.toBufferedImage(gris);

        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                if(prom>umbral)
                    bi.setRGB(i,j,fondo.getRGB());
                else
                    bi.setRGB(i,j,objeto.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);

    }
    public static  Image binzarizacionAutomaticaOtsu(Image imagen){
        Image gris = FiltrosEspaciales.generarImagenGrises(imagen);
        Histrograma h = new Histrograma(gris);
        int umbral = 0;//Umbral.metodoOtsu(h.getGrises());
        double[] mean = Umbral.metodoOtsu(h.getGrises());

        System.out.println("Umbral final: "+umbral);
        Color fondo = new Color(255,255,255);
        Color objeto = new Color(0,0,0);
        BufferedImage bi = AbrirImagen.toBufferedImage(gris);
        int pxp =bi.getHeight()*bi.getWidth();
        System.out.println("pxp:"+pxp);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                int t = color.getRed();
                int t2 = (int)mean[t];
                color = new Color(t2,t2,t2);
                bi.setRGB(i,j,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);

    }
    public static Image ecualizarImagen(Image imagen){

        int nxm = imagen.getWidth(null)*imagen.getHeight(null);
        Histrograma h = new Histrograma(imagen);
        //h.graficarHistogramas();
        double[] ho = h.getR();
        int[] daf = new int[256];
        int[] nt = new int[256];
        daf[0] = (int)ho[0];
        nt[0] = Math.round((daf[0]/nxm)*255);
        // recorremos el histograma para acumular
        for(int x=1; x<ho.length;x++){
            daf[x] = (int)(ho[x]+daf[x-1]);

            double tmp = Math.round((daf[x]*255/nxm));
            nt[x] =(int) tmp;
        }

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int t = color.getRed();
                int t2 =nt[t];
                color = new Color(t2,t2,t2);
                bi.setRGB(x,y,color.getRGB());
            }


        return AbrirImagen.toImage(bi);

    }
    public static int validadLimites(int i){
        if(i<0)   return 0;
        if(i>255) return 255;
        return i;
    }
}
