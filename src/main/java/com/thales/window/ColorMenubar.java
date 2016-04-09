package com.thales.window;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Will Crisp
 */
public class ColorMenubar extends VBox {

  private MenuBar menuBar = new MenuBar();

  public ColorMenubar(Pane deckView) {
    buildMenuButtons();
    getChildren().addAll(menuBar,deckView);
  }

  private void buildMenuButtons(){
    Menu menuButton = new Menu("Colour Filter");
    Menu gisView = new Menu("GIS View");

    MenuItem destination = new MenuItem("Destination");
    MenuItem priority = new MenuItem("Priority");
    MenuItem urgency = new MenuItem("Urgency");

    destination.setOnAction(e -> System.out.println("dest"));//colourfactory);
    priority.setOnAction(e -> System.out.println("priority"));//colourfactory);
    urgency.setOnAction(e -> System.out.println("urgency"));//colourfactory);

    menuButton.getItems().addAll(destination, priority, urgency);
    menuBar.getMenus().addAll(menuButton, gisView);
  }
}
