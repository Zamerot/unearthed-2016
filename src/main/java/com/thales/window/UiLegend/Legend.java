package com.thales.window.UiLegend;

import javafx.geometry.Pos;
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

    Label title = new Label("LEGEND ");

    List<HBox> panes = legendLabels.keySet().stream().map((key) -> {
      Label label = new Label(key.name());

      Rectangle colourLabel = new Rectangle(20,20);
      colourLabel.setFill(legendLabels.get(key));
      Pane spacer = new Pane();
      HBox hbox = new HBox();

      Pane endSpacer = new Pane();
      endSpacer.setMaxWidth(200);

      Pane leftSpacer = new Pane();
      leftSpacer.setPrefWidth(20);


      hbox.getChildren().addAll(new Label("  "), label, new Label("  "), colourLabel, new Label("   "));



      return hbox;
    }).collect(Collectors.toList());


    HBox legendBox = new HBox();
    legendBox.setAlignment(Pos.CENTER);

    legendBox.getChildren().addAll(panes);
    this.getChildren().add(title);
    this.getChildren().add(legendBox);

    this.setPrefWidth(150);
    this.setPrefHeight(200);
    this.setAlignment(Pos.CENTER);

    this.setStyle(" -fx-background-color: GREY;");


  }
}
