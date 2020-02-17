package gui;
/*
Hector enero 2020 
*/

import javax.swing.*;
import java.awt.*;

public class JFrameImage extends JFrame {
    private Image imagenOriginal;

    public JFrameImage(Image image){
        this.imagenOriginal = image;
        JLabel etiqueta = new JLabel();
        etiqueta.setIcon(new ImageIcon(this.imagenOriginal));
        add(etiqueta);
        setSize(this.imagenOriginal.getWidth(null),
                this.imagenOriginal.getHeight(null));
        setVisible(true);
        setDefaultCloseOperation(JFrameImage.EXIT_ON_CLOSE);
    }

    public Image getImagenOriginal() {
        return imagenOriginal;
    }

    public void setImagenOriginal(Image imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }
}
