package com.thales.window.deckView.color;

import com.thales.model.Urgency;
import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;

import java.util.Hashtable;

/**
 * Created by Administrator on 9/04/2016.
 */
public interface IColorFactory<T> {

    Color getColor(ItemView itemView);


    Hashtable<Urgency, Color> getColors();
}
