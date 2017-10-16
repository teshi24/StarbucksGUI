package starbucks_fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.html.HTMLTitleElement;
import starbucks.*;

import javax.swing.*;
import java.util.ArrayList;

public class Modify {

    VBox box = new VBox();

    Label title;

    GridPane cPane = getCoffeePane();
    GridPane bPane = getBeveragePane();
    GridPane ePane = getExtraPane();
    GridPane fPane = getFoodPane();
    Menu menu      = Menu.getInstance();

    int cIndex, bIndex, eIndex, fIndex = 3;

    //TODO:Redundanzen löschen variablen mehrmals verwenden

    /**
     * get whole view
     * @return Scene with view for menu point 'Modify'
     */
    public Node getModifyView() {

        box.setPadding(new Insets(10));
        box.setSpacing(10);

        cPane.setPadding(new Insets(10,10,10,0));
        bPane.setPadding(new Insets(10,10,10,0));
        ePane.setPadding(new Insets(10,10,10,0));
        fPane.setPadding(new Insets(10,10,10,0));

        title       = new Label("Modify Starbucks Menu");

        setModify();

        return box;
    }

    public void setModify() {
        if (!Menu.items.isEmpty()){
            box.getChildren().addAll(title,cPane, bPane, ePane, fPane);
            for (Category item : Menu.items) {
                if (item instanceof Coffee) {
                    Label name        = new Label(((Coffee) item).getName());
                    Label price       = new Label(Double.toString(((Coffee) item).getPrice()));
                    Label ingredients = new Label(((Coffee) item).getIngredients());
                    Button edit     = new Button("edit");
                    Button delete     = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));
                    //TODO: edit modifi action
                    edit.setOnAction((ActionEvent e) -> menu.edit());

                    cPane.add(name, 0, cIndex);
                    cPane.add(price, 1, cIndex);
                    cPane.add(ingredients, 2, cIndex);
                    cPane.add(edit, 4, cIndex);
                    cPane.add(delete, 5, cIndex);

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
                    Button edit     = new Button("edit");
                    Button delete     = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));

                    bPane.add(name, 0, bIndex);
                    bPane.add(price, 1, bIndex);
                    bPane.add(ingredients, 2, bIndex);
                    bPane.add(temp, 3, bIndex);
                    bPane.add(edit, 4, bIndex);
                    bPane.add(delete, 5, bIndex);

                    bIndex ++;
                }
                if (item instanceof Extra) {
                    Label name        = new Label(((Extra) item).getName());
                    Label price       = new Label(Double.toString(((Extra) item).getPrice()));
                    Button edit     = new Button("edit");
                    Button delete     = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));

                    ePane.add(name, 0, eIndex);
                    ePane.add(price, 1, eIndex);
                    ePane.add(edit, 4, eIndex);
                    ePane.add(delete, 5, eIndex);

                    eIndex ++;
                }
                if (item instanceof Food) {
                    Label name        = new Label(((Food) item).getName());
                    Label price       = new Label(Double.toString(((Food) item).getPrice()));
                    Label ingredients = new Label(((Food) item).getIngredients());
                    Label dietaryInfo = new Label(((Food) item).getDietaryInfo());
                    Button edit     = new Button("edit");
                    edit.setOnAction((ActionEvent e) -> {});
                    Button delete     = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));

                    fPane.add(name, 0, fIndex);
                    fPane.add(price, 1, fIndex);
                    fPane.add(ingredients, 2, fIndex);
                    fPane.add(dietaryInfo, 3, fIndex);
                    fPane.add(edit, 4, fIndex);
                    fPane.add(delete, 5, fIndex);

                    fIndex ++;
                }
            }
        } else {
            Label error = new Label("Menu is empty. You can add menu-items in the Add-Option.");
            box.getChildren().addAll(title, error);
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
        pane.add(product, 0, 1);
        pane.add(price, 1, 1);
        pane.add(ingredients, 2, 1);

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
        pane.add(product, 0, 1);
        pane.add(price, 1, 1);
        pane.add(ingredients, 2, 1);
        pane.add(temp, 3,1);

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
        pane.add(product, 0, 1);
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
        pane.add(product, 0, 1);
        pane.add(price, 1, 1);
        pane.add(ingredients, 2, 1);
        pane.add(info, 3,1);

        return pane;
    }

}
