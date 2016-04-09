package com.thales.window.deckView.color;

import java.util.Hashtable;

import com.thales.model.Urgency;
import com.thales.window.deckView.CargoView.ItemView;

import javafx.scene.paint.Color;

/**
 * Created by Administrator on 9/04/2016.
 */
public class UrgencyColorFactory implements IColorFactory<Urgency>
{
    final Hashtable<Urgency, Color> colours = new Hashtable<>();

    @Override
    public Color getColor(ItemView itemView) {
        return colours.get(itemView.getItem().getUrgency());
    }

    @Override
    public Hashtable<Urgency, Color> getColors() {
        return colours;
    }
}
