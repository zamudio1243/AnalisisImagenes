package gui;
/*
Hector febrero 2020 
*/

import open.AbrirImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JFrameSegmentacion extends JFrame {
    private JSlider sliderU1,sliderU2;
    private JLabel labelImagen;
    private JButton btnAbrir;
    private Image imagenEscalada;

    public JFrameSegmentacion(String title, Image io){
        setTitle(title);
        int alto = io.getHeight(null)/2;
        int ancho= io.getWidth(null)/2;
        this.imagenEscalada = AbrirImagen.toBufferedImage(
                io.getScaledInstance(alto,ancho, BufferedImage.TYPE_INT_RGB)
        );
        setVisible(true);
        setSize(ancho,alto);
        initcomponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initcomponents() {
        setLayout(new GridLayout(4,1));
        this.labelImagen = new JLabel( new ImageIcon(this.imagenEscalada));
        add(labelImagen);
        this.sliderU1 = new JSlider();
        this.sliderU1.setMinimum(0);
        this.sliderU1.setMaximum(255);
        this.sliderU1.setValue(0);
        this.sliderU1.setPaintTicks(true);
        this.sliderU1.setPaintLabels(true);
        this.sliderU1.setMinorTickSpacing(1);
        this.sliderU1.setMajorTickSpacing(25);
        this.sliderU2 = new JSlider();
        this.sliderU2.setMinimum(0);
        this.sliderU2.setMaximum(255);
        this.sliderU2.setValue(0);
        this.sliderU2.setPaintTicks(true);
        this.sliderU2.setPaintLabels(true);
        this.sliderU2.setMinorTickSpacing(1);
        this.sliderU2.setMajorTickSpacing(25);
        add(sliderU1);
        add(sliderU2);
        this.btnAbrir = new JButton("Segmentacion");
        SegmentacionListener segmentacionListener = new SegmentacionListener(this);
        this.btnAbrir.addActionListener(segmentacionListener);
        add(this.btnAbrir);


    }


    public JSlider getSliderU1() {
        return sliderU1;
    }

    public JSlider getSliderU2() {
        return sliderU2;
    }

    public JLabel getLabelImagen() {
        return labelImagen;
    }

    public Image getImagenEscalada() {
        return imagenEscalada;
    }
}
