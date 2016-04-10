package com.thales.window.Manifest;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * @author Will Crisp
 */
public class GenerationView extends HBox {

  private Label generationCount = new Label();
  private Label boxCount = new Label();
  private Label totalPriority = new Label();

  public GenerationView() {
    super(40);
    setPadding(new Insets(20, 0, 20, 20));
    setMinWidth(600);
    generationCount.setFont(new Font("Arial", 24));
    boxCount.setFont(new Font("Arial", 24));
    totalPriority.setFont(new Font("Arial", 24));
    getChildren().addAll(generationCount, boxCount, totalPriority);
  }


  public void setGeneration(long generation) {
    generationCount.setText("Gen: " + String.valueOf(generation));
  }

  public void setBoxCount(long generation) {
    boxCount.setText("Containers: " + String.valueOf(generation));
  }
  public void setPriority(long generation) {
    totalPriority.setText("Total Priority: " + String.valueOf(generation));
  }

}
