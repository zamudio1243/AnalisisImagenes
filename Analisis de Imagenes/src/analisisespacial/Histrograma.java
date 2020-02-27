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
    private double r[];
    private double g[];
    private double b[];
    private double grises[];


    public double[] getR() {
        return r;
    }

    public double[] getG() {
        return g;
    }

    public double[] getB() {
        return b;
    }

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







}
