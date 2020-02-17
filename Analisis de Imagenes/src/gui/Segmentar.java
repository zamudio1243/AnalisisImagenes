package gui;
/*
Hector febrero 2020 
*/

import javafx.scene.control.Slider;
import open.AbrirImagen;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Segmentar extends JFrame implements ChangeListener {
    private JPanel panelImagen;
    private JPanel panelSlider;
    private Image imagenOriginal;
    private Image imagenModificada;
    private JSlider seg;
    private JLabel etiqueta;

    public Segmentar(Image image) {
        panelImagen = new JPanel();
        panelSlider = new JPanel();
        etiqueta = new JLabel();
        this.imagenOriginal= image;
        agregarImagen(imagenOriginal);
        add(panelImagen, BorderLayout.PAGE_START);
        agregarSlider();
        add(panelSlider, BorderLayout.PAGE_END);
        setSize(640,640);
        setVisible(true);
        setDefaultCloseOperation(JFrameImage.DISPOSE_ON_CLOSE);
    }
    private void agregarImagen(Image image){
        imagenModificada= image;
        Image aux = image.getScaledInstance(640,480,Image.SCALE_SMOOTH);
        etiqueta.setIcon(new ImageIcon(aux));
        panelImagen.add(etiqueta);
        panelImagen.updateUI();
    }
    private void agregarSlider(){
        this.seg = new JSlider();
        seg.setOrientation(JSlider.HORIZONTAL);
        seg.setPaintLabels(true);
        seg.setPaintTicks(true);
        seg.setMinimum(0);
        seg.setMaximum(255);
        seg.setMinorTickSpacing(25);
        seg.setMajorTickSpacing(50);
        seg.setValue(255);
        seg.addChangeListener(this);
        panelSlider.add(seg);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        BufferedImage bi = AbrirImagen.toBufferedImage(imagenModificada);
        BufferedImage biOriginal = AbrirImagen.toBufferedImage(imagenOriginal);
        Color colorFondo = new Color(255,255,255);
        int umbral = seg.getValue();

        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color color = new Color(bi.getRGB(i,j));
                Color colorOrignal = new Color(biOriginal.getRGB(i,j));
                int prom = (color.getBlue() + color.getGreen() + color.getRed())/3;
                bi.setRGB(i,j,colorOrignal.getRGB());
                if(prom>umbral)
                    bi.setRGB(i,j,colorFondo.getRGB());
            }
        }
        Image aux = AbrirImagen.toImage(bi);
        agregarImagen(aux);

    }
}