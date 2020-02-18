package gui;

import analisisespacial.FiltrosEspaciales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SegmentacionListener implements ActionListener {
    private JFrameSegmentacion frame;

    public SegmentacionListener(JFrameSegmentacion frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int u1 = frame.getSliderU1().getValue();
        int u2 = frame.getSliderU2().getValue();
        Image res = FiltrosEspaciales.segmentarImagen(frame.getImagenEscalada(),u1,u2);
        this.frame.getLabelImagen().setIcon(new ImageIcon(res));
    }

}
