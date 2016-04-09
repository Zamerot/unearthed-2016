package com.thales.window.UiLegend;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Will Crisp
 */
public class Legend extends VBox {

  public Legend()
  {
  }


  public void updateLegend(final Hashtable<? extends Enum, Color> legendLabels) {
    getChildren().clear();
    setSpacing(20);

    List<HBox> panes = legendLabels.keySet().stream().map((key) -> {
      Label label = new Label(key.name());

      Rectangle colourLabel = new Rectangle(10,10);
      colourLabel.setFill(legendLabels.get(key));
      Pane spacer = new Pane();
      HBox hbox = new HBox();
      hbox.getChildren().addAll(label,spacer, colourLabel);
      hbox.setHgrow(spacer, Priority.ALWAYS);

      return hbox;
    }).collect(Collectors.toList());


    this.getChildren().addAll(panes);


  }
}
