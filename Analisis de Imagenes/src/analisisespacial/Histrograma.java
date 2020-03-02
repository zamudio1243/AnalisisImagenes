package analisisespacial;
/*
Hector febrero 2020 
*/

import open.AbrirImagen;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Histrograma {
    private double r[],g[],b[],grises[];
    private int minR,maxR;
    private int minG,maxG;
    private int minB,maxB;

    public Histrograma(Image imagen){
        r = new double[256];
        g = new double[256];
        b = new double[256];
        grises = new double[256];
        calcularHistogramas(imagen);

    }
    private void calcularHistogramas(Image imagen){
        BufferedImage bImage = AbrirImagen.toBufferedImage(imagen);
        for (int i = 0; i < bImage.getWidth(); i++) {
            for (int j = 0; j < bImage.getHeight(); j++) {
                int rgb = bImage.getRGB(i,j);
                Color colorRGB = new Color(rgb);
                r[colorRGB.getRed()]++;
                g[colorRGB.getGreen()]++;
                b[colorRGB.getBlue()]++;
                int promedio = (colorRGB.getBlue() + colorRGB.getGreen() + colorRGB.getRed()) / 3;
                grises[promedio]++;
            }
        }
        calcularMinimosYMaximos();
    }

    private void calcularMinimosYMaximos() {
        this.minR = -1;
        this.minG = -1;
        this.minB = -1;
        this.maxR = 256;
        this.maxG = 256;
        this.maxB = 256;



        for(int t1 = 0, t2= r.length-1; minR==-1 || maxR==256 ;t1++,t2--){
            if(r[t1]!=0 && minR ==-1){
                minR = t1;
            }
            if(r[t2]!=0 && maxR==256){
                maxR = t2;
            }


        }

        for(int t1 = 0, t2= g.length-1; minG==-1 || maxG==256 ;t1++,t2--){
            if(g[t1]!=0 && minG ==-1){
                minG = t1;
            }
            if(g[t2]!=0 && maxG==256){
                maxG = t2;
            }

        }

        for(int t1 = 0, t2= b.length-1; minB==-1 || maxB==256 ;t1++,t2--){
            if(b[t1]!=0 && minB ==-1){
                minB = t1;
            }
            if(b[t2]!=0 && maxB==256){
                maxB = t2;
            }

        }
    }

    public void graficarHistogramas(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie(this.r,"rojo");
        aux.agregarSerie(this.b,"azul");
        aux.agregarSerie(this.g,"verde");

        aux.crearGrafica();
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.RED.getRGB()));
        plot.getRenderer().setSeriesPaint(1, new Color(Color.BLUE.getRGB()));
        plot.getRenderer().setSeriesPaint(2, new Color(Color.GREEN.getRGB()));
        aux.muestraGrafica();

    }
    public void GraphRed(){
        Grafica graph = new Grafica("Magnitud","Frecuencia","Histograma de imagen");
        graph.agregarSerie(r, "Rojo");
        graph.crearGrafica();
        XYPlot plot = graph.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.RED.getRGB()));
        graph.muestraGrafica();

    }
    public void GraphBlue(){
        Grafica graph = new Grafica("Magnitud","Frecuencia","Histograma de imagen");
        graph.agregarSerie(b, "Azul");
        graph.crearGrafica();
        XYPlot plot = graph.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.BLUE.getRGB()));
        graph.muestraGrafica();

    }
    public void GraphGreen(){
        Grafica graph = new Grafica("Magnitud","Frecuencia","Histograma de imagen");
        graph.agregarSerie(g, "Verde");
        graph.crearGrafica();
        XYPlot plot = graph.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.GREEN.getRGB()));
        graph.muestraGrafica();

    }

    public double[] getGrises() {
        return grises;
    }

    public int getMinR() {
        return minR;
    }

    public int getMaxR() {
        return maxR;
    }

    public int getMinG() {
        return minG;
    }

    public int getMaxG() {
        return maxG;
    }

    public int getMinB() {
        return minB;
    }

    public int getMaxB() {
        return maxB;
    }

    public double[] getR() {
        return r;
    }

    public double[] getG() {
        return g;
    }

    public double[] getB() {
        return b;
    }







}
