package com.thales.window.deckView.CargoView;

import com.thales.model.Item;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;

/**
 * Created by Administrator on 9/04/2016.
 */
public class ItemView extends Box{



    int x;

    int y;



    final Item item;

    public ItemView(Item item)
    {
        // TODO access from item
        super(100, 100, 1);

        this.item = item;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Item getItem() {
        return item;
    }
}
