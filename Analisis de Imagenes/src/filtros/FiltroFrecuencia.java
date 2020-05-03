package filtros;
/*
Hector mayo 2020 
*/

import fft.NumeroComplejo;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class FiltroFrecuencia {

    private NumeroComplejo[][] filtroEspacial;

    public FiltroFrecuencia(int ancho, int alto) {
        this.filtroEspacial = new NumeroComplejo[ancho][alto];
    }

    public abstract void crearFiltro();


    public NumeroComplejo[][] getFiltroEspacial() {
        return filtroEspacial;
    }

    public void setFiltroEspacial(NumeroComplejo[][] filtroEspacial) {
        this.filtroEspacial = filtroEspacial;
    }


    public static Image toImageDeComplejo(NumeroComplejo[][]filtro){

        // incializar el buffer
        BufferedImage bi = new BufferedImage((int)filtro.length,
                (int)filtro.length, BufferedImage.TYPE_INT_ARGB);

        // recorrer el filtro
        for (int x=0;x<filtro.length;x++){
            for (int y=0;y<filtro.length;y++){
                double valor = filtro[x][y].getParteReal()*255;
                bi.setRGB(x, y, new Color((int)valor,(int)valor,(int)valor).getRGB());

            }
        }

        return AbrirImagen.toImage(bi);
    }

}