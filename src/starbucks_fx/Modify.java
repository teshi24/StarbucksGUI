package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import starbucks.*;
import starbucks.Menu;

import java.io.IOException;

public class Modify {

    private ScrollPane sp;
    private VBox box;

    private GridPane pane, cPane, bPane, ePane, fPane;
    Menu menu      = Menu.getInstance();
    DataHolder dh;

    int cIndex = 3;
    int bIndex = 3;
    int eIndex = 3;
    int fIndex = 3;

    private Label titleL, nameL, productL, priceL, ingredientsL, optionalL, errorL;


    private Button edit, delete;

    private Stage primaryStage;
    private BorderPane layout;

    public Modify(DataHolder dh, Stage primaryStage, BorderPane layout){
        this.dh = dh;

        this.primaryStage = primaryStage;
        this.layout = layout;
    }
    /**
     * get whole view
     * @return Scene with view for menu point 'Modify'
     */
    public Node getModifyView() {
        box = new VBox();
        sp = new ScrollPane();
        box.setPadding(new Insets(10));
        box.setSpacing(10);

        cPane = getCoffeePane();
        bPane = getBeveragePane();
        ePane = getExtraPane();
        fPane = getFoodPane();

        cPane.setPadding(new Insets(10,10,10,0));
        bPane.setPadding(new Insets(10,10,10,0));
        ePane.setPadding(new Insets(10,10,10,0));
        fPane.setPadding(new Insets(10,10,10,0));

        titleL = new Label("Modify Starbucks Menu");
        titleL.pseudoClassStateChanged(CssConstants.TITLE,true);

        cIndex = 3;
        bIndex = 3;
        eIndex = 3;
        fIndex = 3;

        setList();

        sp.setContent(box);
        return sp;
    }

    public void setList() {
        if (!Menu.items.isEmpty()){

            for (Category item : Menu.items) {
                nameL       = new Label((item).getName());
                priceL      = new Label(Double.toString((item).getPrice()));
                edit        = new Button("edit");
                delete      = new Button("delete");

                delete.setOnAction((ActionEvent e) ->{
                    menu.remove(item);

                    File file = File.getInstance();
                    try {
                        file.save(Menu.toStringArray());
                        //TODO: Message alà 'delete hat geklappt'
                    } catch (IOException ex) {
                        ErrorMsg.addErrorMsg(primaryStage,"A file error occurred.");
                    }
                    layout.setCenter(getModifyView());
                });
                //TODO: write edit
                edit.setOnAction((ActionEvent e) -> {
                    ChangeItem changeItem = new ChangeItem(dh, primaryStage);
                    changeItem.showEditStage(item);

                    File file = File.getInstance();
                    try {
                        file.save(Menu.toStringArray());
                        //TODO: Message alà 'delete hat geklappt'
                    } catch (IOException ex) {
                        ErrorMsg.addErrorMsg(primaryStage,"A file error occurred.");
                    }
                    layout.setCenter(getModifyView());
                });

                if (item instanceof Coffee) {
                    ingredientsL    = new Label(((Coffee) item).getIngredients());

                    cPane.add(nameL, 0, cIndex);
                    cPane.add(priceL, 1, cIndex);
                    cPane.add(ingredientsL, 2, cIndex);
                    cPane.add(edit, 4, cIndex);
                    cPane.add(delete, 5, cIndex);

                    cIndex ++;
                }
                if (item instanceof Beverage) {
                    ingredientsL = new Label(((Beverage) item).getIngredients());
                    optionalL = new Label();
                    if(((Beverage) item).getHot()){
                        optionalL.setText("Hot");
                    } else {
                        optionalL.setText("Cold");
                    }
                    bPane.add(nameL, 0, bIndex);
                    bPane.add(priceL, 1, bIndex);
                    bPane.add(ingredientsL, 2, bIndex);
                    bPane.add(optionalL, 3, bIndex);
                    bPane.add(edit, 4, bIndex);
                    bPane.add(delete, 5, bIndex);

                    bIndex ++;
                }

                if (item instanceof Extra) {
                    ePane.add(nameL, 0, eIndex);
                    ePane.add(priceL, 1, eIndex);
                    ePane.add(edit, 4, eIndex);
                    ePane.add(delete, 5, eIndex);

                    eIndex ++;
                }
                if (item instanceof Food) {
                    ingredientsL = new Label(((Food) item).getIngredients());
                    optionalL = new Label(((Food) item).getDietaryInfo());

                    fPane.add(nameL, 0, fIndex);
                    fPane.add(priceL, 1, fIndex);
                    fPane.add(ingredientsL, 2, fIndex);
                    fPane.add(optionalL, 3, fIndex);
                    fPane.add(edit, 4, fIndex);
                    fPane.add(delete, 5, fIndex);

                    fIndex ++;
                }
            }
            box.getChildren().addAll(titleL,cPane, bPane, ePane, fPane);
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
        optionalL = new Label("Hot/Cold");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        optionalL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);
        pane.add(ingredientsL, 2, 1);
        pane.add(optionalL, 3,1);

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
        optionalL = new Label("Dietary Info");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        optionalL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(priceL, 1, 1);
        pane.add(ingredientsL, 2, 1);
        pane.add(optionalL, 3,1);

        return pane;
    }
}
