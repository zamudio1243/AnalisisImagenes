package filtros;
/*
Hector junio 2020 
*/

import fft.NumeroComplejo;

import java.awt.*;


public class FiltroSelectivo extends FiltroFrecuencia {
    private int radio;
    private int px;
    private  int py;
    private Dimension dim;
    private Image imagen;
    private boolean pasaAltas;

    public FiltroSelectivo(int px,int py,int radio, Dimension dim,boolean pasaAltas){
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.px=px;
        this.py=py;
        this.imagen = null;
        this.pasaAltas=pasaAltas;
    }
    public void crearFiltro() {

        int tamanoImagen = (int)dim.getWidth();
        for(int i=0; i < tamanoImagen;i++){
            for(int j=0; j < tamanoImagen;j++){
                int u = -1*(px)+i;
                int v = (py)-j;
                double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));

                //Calculamos reglejo de filtro dependiendo su cuadrante original
                int cx=0,cy=0;
                switch (cuadrante(px,py)){
                    case 1:
                        cx= tamanoImagen-px;
                        cy= (tamanoImagen/2)+((tamanoImagen/2)-py) ;
                        break;
                    case 2:
                        cx= tamanoImagen-px;
                        cy= tamanoImagen-py;
                }
                //System.out.println("Nuevo centro. x="+cx+" y="+cy);
                int u2= -1*(cx)+i;
                int v2= cy-j;
                double r2= Math.sqrt(Math.pow(u2,2)+Math.pow(v2, 2));
                boolean menor_a_radios=r<=radio ||r2<=radio;
                if(pasaAltas){
                    menor_a_radios = !(r<=radio ||r2<=radio);
                }

                getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                // verificamos con respecto al  radio
                if(menor_a_radios){ // asignamos el valor al filtro
                    getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                }
            }
        }
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());

    }
    public int cuadrante(int x, int y){
        int middle = dim.width/2;
        if(x> middle && y<middle)
            return 1;
        else if (x<middle && y< middle)
            return 2;
        return 1;
    }
    public void modificarFiltro(int radio){
        this.radio = radio;
        crearFiltro();
    }

    public Image getImagen() {
        return imagen;
    }
}
