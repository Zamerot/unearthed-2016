package com.thales.window.deckView;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * @author Will Crisp
 */
public class VesselView extends Xform{

  public VesselView()//Vessel vesselToShow)
  {
    final PhongMaterial material = new PhongMaterial(Color.LIGHTGREY);

    final Box vessel = new Box(1400,5000,1);

    vessel.setMaterial(material);

    this.getChildren().addAll(vessel);
  }
}
