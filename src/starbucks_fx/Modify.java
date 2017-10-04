package starbucks_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

        VBox box = new VBox();

        Label title;
        title       = new Label("Modify Starbucks Menu");
        GridPane cPane = getCoffeePane();
        GridPane bPane = getBeveragePane();
        GridPane ePane = getExtraPane();
        GridPane fPane = getFoodPane();

        box.getChildren().addAll(title,cPane, bPane, ePane, fPane);

        scene = new Scene(box, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //TODO: use static arraylist<category> from Menu
    public void setModify(ArrayList<Category> menuList) {
        if (!menuList.isEmpty()){
            for (Category item : menuList) {
                if (item instanceof Coffee) {
                    //TODO: add action

                }
                if (item instanceof Beverage) {
                    //TODO: add action
                }
                if (item instanceof Extra) {
                    //TODO: add action
                }
                if (item instanceof Food) {
                    //TODO: add action
                }
            }
        }
    }

    private GridPane getCoffeePane(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label title, product, price, ingredients;
        title        = new Label("Coffee");
        product     = new Label("Product");
        price       = new Label("Price");
        ingredients = new Label("Ingredients");

        pane.add(title,0,0,6,1);
        pane.add(product, 1, 0);
        pane.add(price, 1, 1);
        pane.add(ingredients, 1, 2);

        return pane;
    }

    private GridPane getBeveragePane(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label title, product, price, ingredients, temp;
        title        = new Label("Beverage");
        product     = new Label("Product");
        price       = new Label("Price");
        ingredients = new Label("Ingredients");
        temp        = new Label("Hot/Cold");

        pane.add(title,0,0,6,1);
        pane.add(product, 1, 0);
        pane.add(price, 1, 1);
        pane.add(ingredients, 1, 2);
        pane.add(temp, 1,3);

        return  pane;
    }

    private GridPane getExtraPane(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label title, product, price;
        title    = new Label("Extras");
        product     = new Label("Product");
        price       = new Label("Price");

        pane.add(title,0,0,6,1);
        pane.add(product, 1, 0);
        pane.add(price, 1, 1);

        return pane;
    }

    private GridPane getFoodPane(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label title, product, price, ingredients, info;
        title        = new Label("Food");
        product     = new Label("Product");
        price       = new Label("Price");
        ingredients = new Label("Ingredients");
        info        = new Label("Dietary Info");

        pane.add(title,0,0,6,1);
        pane.add(product, 1, 0);
        pane.add(price, 1, 1);
        pane.add(ingredients, 1, 2);
        pane.add(info, 1,3);

        return pane;
    }

}
