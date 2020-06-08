package filtros;
/*
Hector mayo 2020 
*/

import fft.NumeroComplejo;

import java.awt.*;

public class Gaussiano extends FiltroFrecuencia {
    private int radio;
    private Dimension dim;
    private  Image imagen;
    private boolean inverso;

    public Gaussiano(int radio,boolean inverso, Dimension dim ) {
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.imagen = null;
        this.inverso = inverso;
    }

    @Override
    public void crearFiltro() {
        int x,y;
        double v,r,m;

        if (inverso){
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
                double value = (v + m*(Math.exp((-0.5/radio)*(Math.pow(r,2)))));
                //double value = (v + m *(1/(1+Math.pow(r/radio,2*n))));

                value%=255;

                if(value<0)
                    getFiltroEspacial()[i][j]=new NumeroComplejo(0,0);
                else if (value>255)
                    getFiltroEspacial()[i][j]=new NumeroComplejo(1,1);
                getFiltroEspacial()[i][j]= new NumeroComplejo(value,value);

            }
        }
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    public Image getImagen() {
        return imagen;
    }
}
