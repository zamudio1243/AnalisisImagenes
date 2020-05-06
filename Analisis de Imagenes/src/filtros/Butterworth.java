package filtros;
/*
Hector mayo 2020 
*/

import fft.NumeroComplejo;

import java.awt.*;

public class Butterworth extends FiltroFrecuencia {
    private int radio;
    private Dimension dim;
    private Image image;
    private int n;
    private boolean pasa_altas;

    public Butterworth(int radio, Dimension dim,boolean pasa_altas, int orden){
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.image = null;
        this.pasa_altas =pasa_altas;
        this.n=orden;
    }
    @Override
    public void crearFiltro() {
        int x,y;
        double v,r,m;

        if (pasa_altas){
            v=1;
            m= -1;
        }
        else{ //Pasa bajas
            v=0;
            m=1;
        }
        int size = dim.height;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                x= -1 *(size/2)+i;
                y= (size/2)-j;
                r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                double value = (v + m *(1/(1+Math.pow(r/radio,2*n))));
                         
                getFiltroEspacial()[i][j]= new NumeroComplejo(value,value);

            }
        }
        this.image = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());

    }
    public Image getImagen() {
        return image;
    }
}
