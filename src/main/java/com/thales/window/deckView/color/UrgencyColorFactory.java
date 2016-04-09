package com.thales.window.deckView.color;

import com.thales.model.Urgency;
import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;

import java.util.Hashtable;

import static com.thales.model.Urgency.HIGH;
import static com.thales.model.Urgency.LOW;
import static com.thales.model.Urgency.MEDIUM;
import static com.thales.model.Urgency.CRISIS;
import static com.thales.model.Urgency.CRITICAL;
import static com.thales.model.Urgency.ROUTINE;

/**
 * Created by Administrator on 9/04/2016.
 */
public class UrgencyColorFactory implements IColorFactory<Urgency>
{
    final Hashtable<Urgency, Color> colours = new Hashtable<>();

    public UrgencyColorFactory() {
        colours.put(CRISIS, Color.RED);
        colours.put(CRITICAL, Color.ORANGERED);
        colours.put(HIGH, Color.ORANGE);
        colours.put(MEDIUM, Color.YELLOW);
        colours.put(LOW, Color.GREENYELLOW);
        colours.put(ROUTINE, Color.LIGHTGREEN);

    }

    @Override
    public Color getColor(ItemView itemView) {
        return colours.get(itemView.getItem().getUrgency());
    }

    @Override
    public Hashtable<Urgency, Color> getColors() {
        return colours;
    }
}
