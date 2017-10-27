package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import starbucks.*;

public class Modify extends DataObserver{

    ScrollPane sp = new ScrollPane();
    VBox box      = new VBox();

    GridPane pane;
    GridPane cPane = getCoffeePane();
    GridPane bPane = getBeveragePane();
    GridPane ePane = getExtraPane();
    GridPane fPane = getFoodPane();
    Menu menu      = Menu.getInstance();

    int cIndex = 3;
    int bIndex = 3;
    int eIndex = 3;
    int fIndex = 3;

    private Label titleL, nameL, productL, priceL, ingredientsL, tempL, dietaryInfoL, errorL;
    private TextField name, price, ingredients, optional;
    private HBox heatContainer;
    private
    Button edit, delete;

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

        titleL = new Label("Modify Starbucks Menu");
        titleL.pseudoClassStateChanged(CssConstants.TITLE,true);

        setModify();

        sp.setContent(box);
        return sp;
    }

    public void setModify() {
        if (!Menu.items.isEmpty()){
            box.getChildren().addAll(titleL,cPane, bPane, ePane, fPane);
            for (Category item : Menu.items) {
                if (item instanceof Coffee) {
                    nameL = new Label(((Coffee) item).getName());
                    priceL = new Label(Double.toString(((Coffee) item).getPrice()));
                    ingredientsL = new Label(((Coffee) item).getIngredients());
                    edit        = new Button("edit");
                    delete      = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));
                    //edit.setOnAction((ActionEvent e) -> menu.edit(item.getName(), item.getPrice(), item.getIngredients()));

                    cPane.add(nameL, 0, cIndex);
                    cPane.add(priceL, 1, cIndex);
                    cPane.add(ingredientsL, 2, cIndex);
                    cPane.add(edit, 4, cIndex);
                    cPane.add(delete, 5, cIndex);

                    cIndex ++;

                }
                if (item instanceof Beverage) {
                    nameL = new Label(((Beverage) item).getName());
                    priceL = new Label(Double.toString(((Beverage) item).getPrice()));
                    ingredientsL = new Label(((Beverage) item).getIngredients());
                    tempL = new Label();
                    if(((Beverage) item).getHot()){
                        tempL.setText("Hot");
                    } else {
                        tempL.setText("Cold");
                    }
                    edit        = new Button("edit");
                    delete      = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));
                    //edit.setOnAction((ActionEvent e) -> menu.edit(item.getName(), item.getPrice(), item.getIngredients(),));

                    bPane.add(nameL, 0, bIndex);
                    bPane.add(priceL, 1, bIndex);
                    bPane.add(ingredientsL, 2, bIndex);
                    bPane.add(tempL, 3, bIndex);
                    bPane.add(edit, 4, bIndex);
                    bPane.add(delete, 5, bIndex);

                    bIndex ++;
                }

                if (item instanceof Extra) {
                    nameL = new Label(((Extra) item).getName());
                    priceL = new Label(Double.toString(((Extra) item).getPrice()));
                    edit        = new Button("edit");
                    delete      = new Button("delete");

                    delete.setOnAction((ActionEvent e) ->
                            menu.remove(item));
                    //edit.setOnAction((ActionEvent e) -> );

                    ePane.add(nameL, 0, eIndex);
                    ePane.add(priceL, 1, eIndex);
                    ePane.add(edit, 4, eIndex);
                    ePane.add(delete, 5, eIndex);

                    eIndex ++;
                }
                if (item instanceof Food) {
                    nameL = new Label(((Food) item).getName());
                    priceL = new Label(Double.toString(((Food) item).getPrice()));
                    ingredientsL = new Label(((Food) item).getIngredients());
                    dietaryInfoL = new Label(((Food) item).getDietaryInfo());
                    edit        = new Button("edit");
                    edit.setOnAction((ActionEvent e) -> {});
                    delete      = new Button("delete");

                    delete.setOnAction((ActionEvent e) -> menu.remove(item));
                    //edit.setOnAction((ActionEvent e) -> );

                    fPane.add(nameL, 0, fIndex);
                    fPane.add(priceL, 1, fIndex);
                    fPane.add(ingredientsL, 2, fIndex);
                    fPane.add(dietaryInfoL, 3, fIndex);
                    fPane.add(edit, 4, fIndex);
                    fPane.add(delete, 5, fIndex);

                    fIndex ++;
                }
            }
        } else {
            errorL = new Label("Menu is empty. You can add menu-items in the Add-Option.");
            box.getChildren().addAll(titleL, errorL);
        }
    }

    private GridPane getCoffeePane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Coffee");
        productL = new Label("Product");
        priceL = new Label("Price");
        ingredientsL = new Label("Ingredients");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);
        pane.add(ingredientsL, 2, 1);

        return pane;
    }

    private GridPane getBeveragePane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Beverage");
        productL = new Label("Product");
        priceL = new Label("Price");
        ingredientsL = new Label("Ingredients");
        tempL = new Label("Hot/Cold");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        tempL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);
        pane.add(ingredientsL, 2, 1);
        pane.add(tempL, 3,1);

        return  pane;
    }

    private GridPane getExtraPane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Extras");
        productL = new Label("Product");
        priceL = new Label("Price");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);

        return pane;
    }

    private GridPane getFoodPane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Food");
        productL = new Label("Product");
        priceL = new Label("Price");
        ingredientsL = new Label("Ingredients");
        dietaryInfoL = new Label("Dietary Info");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        dietaryInfoL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);
        pane.add(ingredientsL, 2, 1);
        pane.add(dietaryInfoL, 3,1);

        return pane;
    }

    // TODO: check if we could use same frames to add and modify products


    @Override
    public void update(){
        /*
        nameL.setText(dh.getName());
        priceL.setText(dh.getPriceString());
        if(ingredientsL != null){
            ingredientsL.setText(dh.getIngredients());
        }
        if(optional != null){
            optional.setText(dh.getOptional());
        }
        if(heatContainer != null){
            if(dh.isHot()){
                hot.setSelected(true);
            }else{
                cold.setSelected(true);
            }
        }
        */
    }
}
