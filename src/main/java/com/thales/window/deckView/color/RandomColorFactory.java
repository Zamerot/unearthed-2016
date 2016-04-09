package com.thales.window.deckView.color;

import com.thales.window.deckView.CargoView.ItemView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 9/04/2016.
 */
public class RandomColorFactory implements IColorFactory{

    final List<Color> colours = new ArrayList<>();

    Random random = new Random();

    public RandomColorFactory()
    {
        colours.add(Color.RED);
        colours.add(Color.YELLOW);
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.BLACK);

    }


    @Override
    public Color getColor(ItemView itemView) {



        int colour = random.nextInt(colours.size());
        return colours.get(colour);
    }
}
