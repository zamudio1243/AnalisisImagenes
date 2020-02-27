package gui;

import analisisespacial.FiltrosEspaciales;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ExpansionListener implements ChangeListener {


    private JFrameExpansion frame;

    public ExpansionListener(JFrameExpansion frame){
        this.frame = frame;

    }
    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        int u1 = this.frame.getSliderU1().getValue();
        int u2 = this.frame.getSliderU2().getValue();
        Image res = FiltrosEspaciales.contrastarImagen(this.frame.getImagenEscalada(), u1, u2);
        this.frame. getLabelImagen().setIcon(new ImageIcon(res));
    }
}