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

    int cIndex, bIndex, eIndex, fIndex = 3;
    GridPane cPane = getCoffeePane();
    GridPane bPane = getBeveragePane();
    GridPane ePane = getExtraPane();
    GridPane fPane = getFoodPane();

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
                    Label name        = new Label(((Coffee) item).getName());
                    Label price       = new Label(Double.toString(((Coffee) item).getPrice()));
                    Label ingredients = new Label(((Coffee) item).getIngredients());
                    Button modify     = new Button("modify");
                    Button delete     = new Button("delete");

                    cPane.add(name, cIndex, 0);
                    cPane.add(price, cIndex, 1);
                    cPane.add(ingredients, cIndex, 2);
                    cPane.add(modify, cIndex,4);
                    cPane.add(delete, cIndex, 5);

                    cIndex ++;

                }
                if (item instanceof Beverage) {
                    Label name        = new Label(((Beverage) item).getName());
                    Label price       = new Label(Double.toString(((Beverage) item).getPrice()));
                    Label ingredients = new Label(((Beverage) item).getIngredients());
                    Label temp        = new Label();
                    if(((Beverage) item).getHot()){
                        temp.setText("Hot");
                    } else {
                        temp.setText("Cold");
                    }
                    Button modify     = new Button("modify");
                    Button delete     = new Button("delete");

                    bPane.add(name, bIndex, 0);
                    bPane.add(price, bIndex, 1);
                    bPane.add(ingredients, bIndex, 2);
                    bPane.add(temp, bIndex, 3);
                    bPane.add(modify, bIndex,4);
                    bPane.add(delete, bIndex, 5);

                    bIndex ++;
                }
                if (item instanceof Extra) {
                    Label name        = new Label(((Extra) item).getName());
                    Label price       = new Label(Double.toString(((Extra) item).getPrice()));
                    Button modify     = new Button("modify");
                    Button delete     = new Button("delete");

                    ePane.add(name, eIndex, 0);
                    ePane.add(price, eIndex, 1);
                    ePane.add(modify, eIndex,4);
                    ePane.add(delete, eIndex, 5);

                    eIndex ++;
                }
                if (item instanceof Food) {
                    Label name        = new Label(((Food) item).getName());
                    Label price       = new Label(Double.toString(((Food) item).getPrice()));
                    Label ingredients = new Label(((Food) item).getIngredients());
                    Label dietaryInfo = new Label(((Food) item).getDietaryInfo());
                    Button modify     = new Button("modify");
                    Button delete     = new Button("delete");

                    fPane.add(name, fIndex, 0);
                    fPane.add(price, fIndex, 1);
                    fPane.add(ingredients, fIndex, 2);
                    fPane.add(dietaryInfo, fIndex, 3);
                    fPane.add(modify, fIndex,4);
                    fPane.add(delete, fIndex, 5);

                    fIndex ++;
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
        title       = new Label("Coffee");
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
        title       = new Label("Beverage");
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
        title   = new Label("Extras");
        product = new Label("Product");
        price   = new Label("Price");

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
        title       = new Label("Food");
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
