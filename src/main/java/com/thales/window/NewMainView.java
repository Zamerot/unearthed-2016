//package com.thales.window;
//
//import com.thales.ga.ManifestOptimiser;
//import com.thales.model.*;
//import com.thales.window.VesselView.VesselView;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.image.Image;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import org.jenetics.util.RandomRegistry;
//
//import java.io.InputStream;
//import java.util.Hashtable;
//import java.util.concurrent.Executors;
//
///**
// * Created by Administrator on 9/04/2016.
// */
//public class NewMainView  extends Application {
//
//    final Pane mainPane = new StackPane();
//
//    final Pane displayPane = new Pane();
//
//    ListView<Vessel> vesselListView;
//
//    Hashtable<Vessel, VesselOptimizationView> optimizationViewHashtable = new Hashtable<>();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Loady Boat");
//
//        Scene scene = new Scene(mainPane);
//
//        buildPane();
//
//        InputStream icon = this.getClass().getResourceAsStream("/icon.png");
//
//        Image image = new Image(icon);
//        primaryStage.getIcons().add(image);
//        // Generate a random collection of store items.
//
//        Store store = new Store();
//        for (int i = 0; i < 3000; i++) {
//            int p = RandomRegistry.getRandom().nextInt(Priority.values().length);
//            int u = RandomRegistry.getRandom().nextInt(Urgency.values().length);
//            int d = RandomRegistry.getRandom().nextInt(Destination.values().length);
//            store.addItem(new Item("Item " + i, Priority.values()[p], Urgency.values()[u], Destination.values()[d]));
//        }
//
//        ManifestOptimiser optimiser = new ManifestOptimiser(store, Vessel.VESSEL16);
//        VesselOptimizationView view = new VesselOptimizationView(optimiser);
//
////        mainPane.getChildren().add(view);
//        primaryStage.setScene(scene);
//        //	primaryStage.setFullScreen(true);
//        primaryStage.show();
//
//        primaryStage.setOnCloseRequest((a)->{
//            System.exit(0);
//
//        });
//
//        Executors.newSingleThreadExecutor().execute(() -> view.go());
//    }
//
//
//    private void buildPane()
//    {
//        HBox hBox = new HBox();
//
//        buildList();
//        hBox.getChildren().addAll(vesselListView);
//
//        hBox.getChildren().addAll(displayPane);
//
//        mainPane.getChildren().add(hBox);
//    }
//
//
//    private void buildList()
//    {
//        vesselListView = new ListView<>();
//
//        vesselListView.getItems().add(Vessel.VESSEL2);
//        vesselListView.getItems().add(Vessel.VESSEL7);
//
//        vesselListView.setOnMouseClicked((event)->{
//            Vessel selected = vesselListView.getSelectionModel().getSelectedItem();
//
//            if(selected != null)
//            {
//                updateMainDisplay(selected);
//            }
//        });
//
//
//        vesselListView.setCellFactory(new Callback<ListView<Vessel>,
//                        ListCell<Vessel>>(){
//
//            @Override
//            public ListCell<Vessel> call(ListView<Vessel> param) {
//                return new VesselView();
//            }
//        });
//
//
//
//    }
//
//
//    private void updateMainDisplay(Vessel vessel)
//    {
//        if(!this.optimizationViewHashtable.containsKey(vessel))
//        {
//
//        }
//    }
//
//    private void buildOptimizationView(Vessel v)
//    {
//        Store store = new Store();
//        for (int i = 0; i < 3000; i++) {
//            int p = RandomRegistry.getRandom().nextInt(Priority.values().length);
//            int u = RandomRegistry.getRandom().nextInt(Urgency.values().length);
//            int d = RandomRegistry.getRandom().nextInt(Destination.values().length);
//            store.addItem(new Item("Item " + i, Priority.values()[p], Urgency.values()[u], Destination.values()[d]));
//        }
//
//        ManifestOptimiser optimiser = new ManifestOptimiser(store, Vessel.VESSEL16);
//        VesselOptimizationView view = new VesselOptimizationView(optimiser);
//    }
//
//}
//
