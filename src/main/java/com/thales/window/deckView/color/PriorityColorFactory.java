package com.thales.window.deckView.color;

import com.thales.model.Priority;
import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;
import org.jenetics.internal.util.Hash;

import java.util.Hashtable;

import static com.thales.model.Priority.*;

/**
 * Created by Administrator on 9/04/2016.
 */
public class PriorityColorFactory implements IColorFactory {

    final Hashtable<Priority, Color> colours = new Hashtable<>();

    public PriorityColorFactory()
    {
        colours.put(HIGH, Color.RED);
        colours.put(MEDIUM, Color.YELLOW);
        colours.put(LOW, Color.DARKGREEN);

    }

    @Override
    public Color getColor(ItemView itemView) {

       return colours.get(itemView.getItem().getPriority());
    }

    @Override
    public Hashtable getColors() {
        return colours;
    }


}
