package com.thales.window;

import com.thales.utils.FXExecutor;
import com.thales.window.Manifest.ManifestView;
import com.thales.window.deckView.DeckView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 8/04/2016.
 */
public class VesselOptimizationView extends HBox{

    //TODO
    final Pane deckView = new DeckView();

    final Label generationView = new Label("Count");

    private int count = 0;

    final FitnessView fitnessView = new FitnessView();


    final Executor executor = Executors.newSingleThreadExecutor();

    final Pane  manifestView = new ManifestView();

    public VesselOptimizationView()
    {
        VBox leftBox = new VBox();

        leftBox.getChildren().addAll(generationView, fitnessView, manifestView);
        //leftBox.

        executor.execute(() -> {
            while(true){
                count ++;
                FXExecutor.INSTANCE.execute(()->
                {
                    generationView.setText("count " + count);
                    fitnessView.addDataToQueue((int) (Math.random() * 100));
                });
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        });

        this.getChildren().addAll(leftBox, deckView);


    }

}
