package com.thales.window.VesselView;

import com.thales.model.Vessel;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

/**
 * Created by Administrator on 9/04/2016.
 */
public class VesselView extends ListCell<Vessel> {

    public void updateItem(Vessel vessel, boolean empty)
    {
        super.updateItem(vessel, empty);

        if(vessel != null) {
            VBox mainBox = new VBox();

            HBox titleRow = new HBox();

            InputStream icon = this.getClass().getResourceAsStream("/icon.png");

            Image image = new Image(icon);

            titleRow.getChildren().add(new ImageView(image));
            // TODO Name
            titleRow.getChildren().add(new Label(vessel.getId()));

            mainBox.getChildren().add(titleRow);


            this.setGraphic(mainBox);
        }
    }
}
