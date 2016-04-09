package com.thales.window.deckView.color;

import com.thales.model.Destination;
import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;

import java.util.Hashtable;

/**
 * Created by Administrator on 9/04/2016.
 */
public class DestinationColorFactory implements IColorFactory {

    private final Hashtable<Destination, Color> colors = new Hashtable<>();

    public DestinationColorFactory()
    {
        colors.put(Destination.AU07, Color.AQUAMARINE);
        colors.put(Destination.AU03, Color.BISQUE);
        colors.put(Destination.AU17, Color.ORCHID);
        colors.put(Destination.AU04, Color.CHOCOLATE);
        colors.put(Destination.AU05, Color.BURLYWOOD);
        colors.put(Destination.AU18, Color.CORNSILK);
        colors.put(Destination.AU21, Color.CHARTREUSE);
    }

    public Hashtable<Destination, Color> getColors()
    {
        return colors;
    }


    @Override
    public Color getColor(ItemView itemView) {
        return colors.get(itemView.getItem().getDestination());
    }
}
