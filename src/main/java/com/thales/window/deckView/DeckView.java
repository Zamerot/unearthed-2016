package com.thales.window.deckView;

import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Created by Administrator on 8/04/2016.
 */
public class DeckView extends Pane
{

    private static final double CAMERA_INITIAL_DISTANCE = -10000;
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

    private boolean setNewDelta = true;

    public DeckView()
    {
        world.getChildren().addAll(axis, grid, vesselView);
        //vesselView.setVisible(false);
        grid.setVisible(false);
        buildCamera();

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        SubScene subScene = new SubScene(root, 1400, 1000);
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
                double zoomFactor = 1.10;
                double deltaY = event.getDeltaY();
                if (deltaY < 0) {
                    zoomFactor = 2.0 - zoomFactor;
                }
                this.setScaleX(this.getScaleX() * zoomFactor);
                this.setScaleY(this.getScaleY() * zoomFactor);
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
            camera.setLayoutY(dragEvent.getSceneY() + dragDelta.y);
            dragEvent.consume();
        });

    }
    class Delta { double x, y; }
}
