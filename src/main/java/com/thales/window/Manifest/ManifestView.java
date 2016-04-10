package com.thales.window.Manifest;


import com.thales.model.Item;
import com.thales.model.Manifest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * Created by Administrator on 9/04/2016.
 */
public class ManifestView extends Pane
{

    final TableView<Item> tableView;
    // TODO
    public ManifestView()
    {

        TableColumn<Item, String> idColumn = new TableColumn<>("ID");
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Item, String> priorityColumn = new TableColumn<>("Priority");
        TableColumn<Item, String> urgencyColumn = new TableColumn<>("Urgency");
        TableColumn<Item, String> destinationColumn = new TableColumn<>("Destination");

        destinationColumn.setPrefWidth(100);





        tableView = new TableView<>();
        tableView.getColumns().addAll(nameColumn, priorityColumn, urgencyColumn, destinationColumn);
        tableView.setPadding(new Insets(20, 0, 20, 20));
        tableView.setMinWidth(600);
        tableView.setMinHeight(500);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new SimpleStringProperty("ID");
            }
        });

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });

        priorityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {

                //TODO Stringify
                return new SimpleStringProperty(param.getValue().getPriority().name());
            }
        });


        urgencyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {

                //TODO Stringify
                return new SimpleStringProperty(param.getValue().getUrgency().name());
            }
        });

        destinationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {

                //TODO Stringify
                return new SimpleStringProperty(param.getValue().getDestination().name());
            }
        });

        tableView.setItems(FXCollections.observableArrayList());


        this.getChildren().addAll(tableView);
    }


    public void update(Manifest manifest)
    {
        tableView.getItems().clear();
        tableView.getItems().addAll(manifest.getItems());
    }
}
