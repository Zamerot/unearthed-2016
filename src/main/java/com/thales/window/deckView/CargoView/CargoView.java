package com.thales.window.deckView.CargoView;

import com.thales.window.deckView.Xform;

import java.util.List;

/**
 * Created by Administrator on 9/04/2016.
 */
public class CargoView extends Xform {


    public void update(List<List<ItemView>> items)
    {
        this.getChildren().clear();
        double rowTrans = 0;
        double colTrans = 0;

        double largestHeight = 0;

        double transX = 0;
        double transY = 0;

        double width = 0;

        for(int r = 0; r < items.size(); r++)
        {
            List<ItemView> view = items.get(r);

            for(int c = 0; c < view.size(); c++)
            {
                ItemView temp = view.get(c);

                if(c > 0)
                {
                    transY += temp.getHeight() /2;
                }

                temp.setTranslateX(transX);
                temp.setTranslateY(-transY);

                transY += temp.getHeight() /2;

                // TODO
                width = temp.getWidth();

                this.getChildren().add(temp);
            }

            transY = 0;
            transX += width;
        }
    }
}
