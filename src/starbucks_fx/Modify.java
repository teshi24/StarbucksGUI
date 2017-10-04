package starbucks_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.html.HTMLTitleElement;
import starbucks.*;

import java.util.ArrayList;

public class Modify extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Modify Starbucks Menu");
        Scene scene;

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label title, product, price, ingredients, other, editLabel, deleteLabel;
        Button editButton, deleteButton;

        title       = new Label("Modify Starbucks Menu");
        product     = new Label("Product");
        price       = new Label("Price");
        ingredients = new Label("Ingredients");
        other       = new Label("Hot/Cold or Diet");
        editLabel   = new Label("Edit");
        deleteLabel = new Label("Delete");

        editButton        = new Button("Edit");
        deleteButton      = new Button("Delete");

        pane.add(title,0,0,6,1);
        pane.add(product,0,2);
        pane.add(price,1,2);
        pane.add(ingredients,2,2);
        pane.add(other,3,2);
        pane.add(editLabel,4,2);
        pane.add(deleteLabel,5,2);

        scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setModify(ArrayList<Category> menuList) {
        if (!menuList.isEmpty()){
            for (Category item : menuList) {
                if (item instanceof Coffee) {

                }
                if (item instanceof Beverage) {
                }
                if (item instanceof Extra) {
                }
                if (item instanceof Food) {
                }
            }
        }
    }

}
