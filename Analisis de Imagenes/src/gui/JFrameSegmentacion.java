package gui;
/*
Hector febrero 2020 
*/
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import analisisespacial.Histrograma;
import open.AbrirImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JFrameSegmentacion extends JFrame {

    private JSlider sliderU1, sliderU2;
    private JLabel labelImagen;
    private JPanel panelSliders;
    private Image imagenEscalada;


    public JFrameSegmentacion (String title, Image io){
        setTitle(title);
        int ancho = io.getWidth(null)/2;
        int alto = io.getHeight(null)/2;
        setSize(ancho*2,alto*2);
        this.imagenEscalada = AbrirImagen.toBufferedImage(io).getScaledInstance(ancho,alto, BufferedImage.TYPE_INT_RGB);
        initcomponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initcomponents(){
        // layout
        setLayout(new BorderLayout());
        this.labelImagen = new JLabel(new ImageIcon(this.imagenEscalada));
        add(this.labelImagen,BorderLayout.CENTER);
        this.sliderU1 = new JSlider();
        this.sliderU1.setMinimum(0);
        this.sliderU1.setMaximum(255);
        this.sliderU1.setValue(0);
        this.sliderU1.setPaintLabels(true);
        this.sliderU1.setPaintTicks(true);
        this.sliderU1.setMinorTickSpacing(1);
        this.sliderU1.setMajorTickSpacing(25);
        this.sliderU2 = new JSlider();
        this.sliderU2.setMinimum(0);
        this.sliderU2.setMaximum(255);
        this.sliderU2.setValue(255);
        this.sliderU2.setPaintLabels(true);
        this.sliderU2.setPaintTicks(true);
        this.sliderU2.setMinorTickSpacing(1);
        this.sliderU2.setMajorTickSpacing(25);
        panelSliders = new JPanel(new GridLayout(2,1));
        panelSliders.add(this.sliderU1);
        panelSliders.add(this.sliderU2);
        add(panelSliders,BorderLayout.SOUTH);

        SegmentacionListener lis = new SegmentacionListener(this);

        sliderU1.addChangeListener(lis);
        sliderU2.addChangeListener(lis);
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