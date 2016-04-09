package com.thales.window.Manifest;

import com.sun.org.apache.xpath.internal.operations.String;
import com.thales.model.Item;
import com.thales.model.Manifest;
import com.thales.model.Priority;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * Created by Administrator on 9/04/2016.
 */
public class ManifestView extends Pane
{
    // TODO
    public ManifestView()
    {
        TableColumn<Item, String> idColumn = new TableColumn<>("ID");
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Item, String> priorityColumn = new TableColumn<>("Priority");
        TableColumn<Item, String> urgencyColumn = new TableColumn<>("Urgency");
        TableColumn<Item, String> destinationColumn = new TableColumn<>("Destination");



        TableView<Item> tableView = new TableView<>();
        tableView.getColumns().addAll(idColumn, nameColumn, priorityColumn, urgencyColumn, destinationColumn);

        this.getChildren().addAll(tableView);
    }
}
