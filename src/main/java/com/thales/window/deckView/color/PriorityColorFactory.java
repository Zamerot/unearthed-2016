package com.thales.window.deckView.color;

import com.thales.window.deckView.CargoView.ItemView;

import javafx.scene.paint.Color;

/**
 * Created by Administrator on 9/04/2016.
 */
public class PriorityColorFactory implements IColorFactory {
    @Override
    public Color getColor(ItemView itemView) {

        switch(itemView.getItem().getPriority())
        {
            case HIGH:
                return Color.RED;
            case MEDIUM:
                return Color.YELLOW;

            case LOW:
                return Color.DARKGREEN;

            case LOWEST:
                return Color.LIGHTGREEN;

            default:
                return Color.WHITE;
        }
    }
}
