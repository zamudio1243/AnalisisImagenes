package filtros;
/*
Hector mayo 2020 
*/

import fft.NumeroComplejo;

import java.awt.*;

public class Bandas extends FiltroFrecuencia {
    private int r1;
    private int r2;
    private boolean inverso;
    private Dimension dim;
    private Image imagen;

    public Bandas(int r1, int r2,boolean inverso, Dimension dim){
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.r1=r1;
        this.r2=r2;
        this.inverso = inverso;
        this.dim = dim;
        this.imagen = null;
    }
    @Override
    public void crearFiltro() {

        int tamanoImagen = (int)dim.getWidth();
        for(int i=0; i < tamanoImagen;i++){
            for(int j=0; j < tamanoImagen;j++){
                int u = -1*(tamanoImagen/2)+i;
                int v = (tamanoImagen/2)-j;

                double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));
                // verificamos con respecto al  radio
                boolean anillo = (r >= r1) && ( r <= r2);
                if (inverso)
                    anillo = !(anillo);
                if(anillo){
                    // asignamos el valor al filtro
                    getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                    // asignamos el valor a la imagen
                    // bi.setRGB(i, j, new Color(255, 255, 255).getRGB());
                }  else {

                    // asignamos el valor al filtro
                    getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    // asignamos el valor a la imagen
                    // bi.setRGB(i, j, new Color(0, 0, 0).getRGB());

                }
            }
        }
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }

    public Image getImagen() {
        return imagen;
    }

}
