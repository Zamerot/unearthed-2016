package com.thales.window.deckView;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.io.InputStream;

/**
 * Created by Administrator on 9/04/2016.
 */
public class Arrow extends Xform {

    public Arrow()
    {
        Box box = new Box(300, 300, 1);

        InputStream icon = this.getClass().getResourceAsStream("/arrow.png");

        Image image = new Image(icon);


        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(image);
        material.setBumpMap(image);
        material.setSpecularColor(Color.WHITE);
        box.setMaterial(material);


        this.getChildren().addAll(box);
    }

}
