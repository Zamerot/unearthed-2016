package com.thales.window.deckView.CargoView;

import javafx.scene.shape.Box;

/**
 * Created by Administrator on 9/04/2016.
 */
public class ItemView extends Box{



    int x;

    int y;


    public ItemView(double width, double height, double depth) {
        super(width, height, depth);
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

}
