package analisisespacial;
/*
Hector febrero 2020 
*/
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {
    private JFreeChart grafica;

    private XYSeriesCollection series;
    private String ejeX,ejeY,titulo;

    public Grafica(String ejeX, String ejeY, String titulo){
        this.grafica = null;
        this.series = new XYSeriesCollection();
        this.titulo=titulo;
        this.ejeX=ejeX;
        this.ejeY=ejeY;

    }

    public JFreeChart getGrafica() {
        return grafica;
    }

    public void agregarSerie(double[] datos, String id){
        //crear la serie
        XYSeries serie = new XYSeries(id);
        for(int x=0; x< datos.length;x++){
            serie.add(x, datos[x]);

        }
        this.series.addSeries(serie);
    }

    public void agregarSerie(Double[] datos, String id){
        //crear la serie

        XYSeries serie = new XYSeries(id);
        for(int x=0; x< datos.length;x++){
            serie.add(x, datos[x]);

        }
        this.series.addSeries(serie);
    }

    public void muestraGrafica(){
        ChartFrame panel = new ChartFrame(titulo, grafica);
        panel.pack();
        panel.setSize(500, 370);
        panel.setVisible(true);

    }

    public void crearGrafica(){
        grafica = ChartFactory.createXYAreaChart(titulo, ejeX, ejeY, series);
    }

}