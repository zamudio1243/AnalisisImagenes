package fft;

import java.awt.*;

/*
Hector abril 2020 
*/
public class HerramientasColor {

    public enum CanalColor {ROJO(2),VERDE(1),AZUL(0);

        private int index;

        private CanalColor(int index){
            this.index = index;
        }

        public int getColorIndex(){

            return index;
        }
    }
    public static int obtenerValorPorCanal(int rgb, CanalColor canal){

        Color color = new Color(rgb);
        int valor = 0;
        switch(canal){
            case ROJO:
                valor = color.getRed();
                break;
            case VERDE:
                valor = color.getGreen();
                break;
            case AZUL:
                valor = color.getBlue();
                break;
        }

        return valor;

    }

    public static int obtenerRGBPorCanal(int valor, CanalColor canal){

        int aux = 0;
        switch(canal){
            case ROJO:
                aux = new Color(valor,0, 0).getRGB();
                break;
            case VERDE:
                aux = new Color(0,valor, 0).getRGB();
                break;
            case AZUL:
                aux = new Color(0,0, valor).getRGB();
                break;
        }

        return aux;

    }

    public static int acumularColor(int color1, int color2 ){
        return color1 | color2;
    }
}