package com.thales.window.deckView.CargoView;

import com.thales.window.deckView.Xform;
import javafx.util.Pair;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 9/04/2016.
 */
public class CargoView extends Xform {


    public CargoView(List<List<ItemView>> items)
    {
        double rowTrans = 0;
        double colTrans = 0;

        double largestHeight = 0;

        /*
        for(int r = items.size(); r < items.size(); r++)
        {
            List<ItemView> row = items.get(r);
            for(int c = row.size(); c< row.size(); c++){
                ItemView col = row.get(c);

                colTrans = colTrans + col.getWidth() / 2;

                col.setTranslateX(colTrans);
                col.setTranslateY(rowTrans);

                this.getChildren().add(col);

                if(col.getHeight() > largestHeight)
                {
                    largestHeight = col.getHeight();
                }
            }

            rowTrans = rowTrans += largestHeight;
            largestHeight = 0;
            colTrans  = 0;
        }
*/


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