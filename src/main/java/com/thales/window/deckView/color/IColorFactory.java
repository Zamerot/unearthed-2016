package com.thales.window.deckView.color;

import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;

/**
 * Created by Administrator on 9/04/2016.
 */
public interface IColorFactory {

    Color getColor(ItemView itemView);
}
