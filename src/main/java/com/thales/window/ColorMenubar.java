package com.thales.window;

import com.thales.window.deckView.DeckView;
import com.thales.window.deckView.color.DestinationColorFactory;
import com.thales.window.deckView.color.PriorityColorFactory;
import com.thales.window.deckView.color.UrgencyColorFactory;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

/**
 * @author Will Crisp
 */
public class ColorMenubar extends VBox {

  private MenuBar menuBar = new MenuBar();
  private DeckView deckView;

  public ColorMenubar(DeckView deckView) {
    this.deckView = deckView;
    buildMenuButtons();
    getChildren().addAll(menuBar,deckView);
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
