package com.thales.window.deckView;

import com.thales.model.Manifest;
import com.thales.window.deckView.CargoView.CargoView;
import com.thales.window.deckView.CargoView.ItemView;
import com.thales.window.deckView.color.DestinationColorFactory;
import com.thales.window.deckView.color.IColorFactory;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/04/2016.
 */
public class DeckView extends Pane
{

    private static final double CAMERA_INITIAL_DISTANCE = -8000;
    private static final double CAMERA_INITIAL_X_ANGLE = 0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0;
    private static final double CAMERA_INITIAL_Z_ANGLE = 90;

    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;


    final PerspectiveCamera camera = new PerspectiveCamera(true);

    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();

    final Xform axisGroup = new Xform();

    final Xform world = new Xform();

    final Group root = new Group();

    final Xform axis = new Axis(10000);

    final VesselView vesselView = new VesselView();

    final Delta dragDelta = new Delta();

    final Xform grid = new Grid(14000, 10000, 100, 100);

    CargoView cargoView;

    private final IColorFactory colorFactory = new DestinationColorFactory();

    private boolean setNewDelta = true;

    final Arrow arrow = new Arrow();




    public DeckView()
    {
        setMaxWidth(1600);
        cargoView = new CargoView();

        world.getChildren().addAll(axis, grid, vesselView, cargoView, arrow);
//        vesselView.setVisible(false);
        grid.setVisible(false);
        axis.setVisible(false);

        buildCamera();


        arrow.setTranslateZ(40);

        vesselView.setTranslateZ(40);
        grid.setTranslateZ(20);

        double vesselWidth = vesselView.getLayoutBounds().getWidth();
        double vesselHeight = vesselView.getLayoutBounds().getHeight();

        double arrowHeight = arrow.getLayoutBounds().getHeight();
        arrow.setTranslateY(-vesselHeight / 2 - 30 - arrowHeight/2);
//        vesselView.setTranslate(vesselWidth / 2 + 50, -vesselHeight / 2 - 50);

        cargoView.setTranslate(-vesselWidth / 2 + 50, (vesselHeight / 2) - 50);


        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        SubScene subScene = new SubScene(root, 1400, 800);
        subScene.setCamera(camera);
        getChildren().add(subScene);
        setSceneEvents();
    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(250, 1, 1);
        final Box yAxis = new Box(1, 250, 1);
        final Box zAxis = new Box(1, 1, 250);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }

    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);


        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        cameraXform.rz.setAngle(CAMERA_INITIAL_Z_ANGLE);

    }
    private void setSceneEvents() {
        //handles mouse scrolling
        this.setOnScroll(
            event -> {
                camera.setTranslateZ(camera.getTranslateZ() + event.getDeltaY() * 5);
                event.consume();
            });

        this.setOnMousePressed((dragover) ->
        {
            setNewDelta = true;
            dragover.consume();
        });

        this.setOnMouseDragged(dragEvent ->
        {
            if (setNewDelta) {
                dragDelta.x = camera.getLayoutX() - dragEvent.getSceneX();
                dragDelta.y = camera.getLayoutY() - dragEvent.getSceneY();
                setNewDelta=false;
            }
            camera.setLayoutX(dragEvent.getSceneX() + dragDelta.x);
            camera.setLayoutY(dragEvent.getSceneY()+ dragDelta.y);
            dragEvent.consume();
        });

    }
    class Delta { double x, y; }


    public void updateDeck(Manifest m)
    {
        int maxWidth =  m.getVessel().getDimension().width;
        int maxHeight = m.getVessel().getDimension().height;

        List<List<ItemView>> list = new ArrayList<>();


        for(int i = 0; i < maxWidth; i++)
        {
            List<ItemView> row = new ArrayList<>();

            for(int j = 0; j < maxHeight; j++)
            {
                ItemView itemView = new ItemView(m.getPosition(i, j));
                itemView.setMaterial(new PhongMaterial(colorFactory.getColor(itemView)));

                row.add(itemView);
            }

            list.add(row);
        }

        cargoView.update(list);
    }
}
