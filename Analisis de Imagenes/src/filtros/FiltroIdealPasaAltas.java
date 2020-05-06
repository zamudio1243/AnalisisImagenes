package filtros;
/*
Hector mayo 2020 
*/

import fft.NumeroComplejo;

import java.awt.*;

public class FiltroIdealPasaAltas extends FiltroFrecuencia {

    private int radio;
    private Dimension dim;
    private Image imagen;

    public FiltroIdealPasaAltas(int radio, Dimension dim) {
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
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
                if(r>=this.radio){
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

    public void modificarFiltro(int radio){
        this.radio = radio;
        crearFiltro();
    }

    public Image getImagen() {
        return imagen;
    }

}
