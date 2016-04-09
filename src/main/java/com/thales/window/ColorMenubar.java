package com.thales.window;

import com.thales.window.deckView.DeckView;
import com.thales.window.deckView.color.DestinationColorFactory;
import com.thales.window.deckView.color.PriorityColorFactory;
import com.thales.window.deckView.color.UrgencyColorFactory;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

/**
 * @author Will Crisp
 */
public class ColorMenubar extends VBox {

  private MenuBar menuBar = new MenuBar();
  private DeckView deckView;

  private  Pane schemaPane;

  public ColorMenubar(DeckView deckView) {
    this.deckView = deckView;
    buildMenuButtons();
//    getChildren().addAll(menuBar,deckView);

    schemaPane = createSchemaPane();
    StackPane stackPane = new StackPane();

    VBox vBox = new VBox();
    vBox.getChildren().addAll(menuBar, deckView);
    stackPane.getChildren().addAll(vBox, schemaPane);

    getChildren().add(stackPane);
  }

  private Pane createSchemaPane()
  {
    Label label = new Label("GSLKDHGFLDSJFL");

    Pane spacer = new Pane();
    HBox hBox = new HBox();

    VBox vBox = new VBox();
    Pane spacer2 = new Pane();

    hBox.getChildren().addAll(spacer, label);
    hBox.setMouseTransparent(true);
    HBox.setHgrow(spacer, Priority.ALWAYS);
    vBox.getChildren().addAll(spacer2, hBox);

    VBox.setVgrow(spacer2, Priority.ALWAYS);

    vBox.setMouseTransparent(true);
    return vBox;
  }


  private void buildMenuButtons(){
    Menu menuButton = new Menu("Colour Filter");
    Menu gisView = new Menu("GIS View");

    CheckMenuItem destination = new CheckMenuItem("Destination");
    CheckMenuItem priority = new CheckMenuItem("Priority");
    CheckMenuItem urgency = new CheckMenuItem("Urgency");
    destination.setSelected(true);
    destination.setOnAction((e) ->{
      deckView.setColourFactory(new DestinationColorFactory());
      priority.setSelected(false);
      urgency.setSelected(false);
    });
    priority.setOnAction((e) -> {
      deckView.setColourFactory(new PriorityColorFactory());
      destination.setSelected(false);
      urgency.setSelected(false);
    });
    urgency.setOnAction((e) -> {
      deckView.setColourFactory(new UrgencyColorFactory());
      priority.setSelected(false);
      destination.setSelected(false);
    });

    menuButton.getItems().addAll(destination, priority, urgency);
    menuBar.getMenus().addAll(menuButton, gisView);
  }
}
