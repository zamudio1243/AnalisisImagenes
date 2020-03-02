package gui;

import analisisespacial.FiltrosEspaciales;
import expansiones.Expansion;

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
        Image res = Expansion.expansionLineal(u1, u2,this.frame.getImagenEscalada());
        this.frame. getLabelImagen().setIcon(new ImageIcon(res));
    }
}