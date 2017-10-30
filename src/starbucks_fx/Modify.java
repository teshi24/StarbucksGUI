package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import starbucks.*;
import starbucks.Menu;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Modify {

    private ScrollPane sp;
    private VBox box;

    private GridPane pane, cPane, bPane, ePane, fPane;
    int COLUMN_WIDTH = 80;

    Menu menu      = Menu.getInstance();
    DataHolder dh;

    int cIndex = 3;
    int bIndex = 3;
    int eIndex = 3;
    int fIndex = 3;

    private Label titleL, nameL, productL, priceL, ingredientsL, optionalL, errorL;
    private Text nameT, ingredientsT, optionalT;

    private Button edit, delete;

    private Stage primaryStage;
    private BorderPane layout;

    private Label dummy;
    private Label dummie;

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
        box.getStylesheets().add("resources/css/style.css");

        cPane = getCoffeePane();
        bPane = getBeveragePane();
        ePane = getExtraPane();
        fPane = getFoodPane();

        cPane.setPadding(new Insets(10,10,10,0));
        bPane.setPadding(new Insets(10,10,10,0));
        ePane.setPadding(new Insets(10,10,10,0));
        fPane.setPadding(new Insets(10,10,10,0));

        cPane.getColumnConstraints().add(new ColumnConstraints(80));
        cPane.getColumnConstraints().add(new ColumnConstraints(100));
        cPane.getColumnConstraints().add(new ColumnConstraints(100));
        cPane.getColumnConstraints().add(new ColumnConstraints(40));
        bPane.getColumnConstraints().add(new ColumnConstraints(80));
        bPane.getColumnConstraints().add(new ColumnConstraints(100));
        bPane.getColumnConstraints().add(new ColumnConstraints(100));
        bPane.getColumnConstraints().add(new ColumnConstraints(40));
        ePane.getColumnConstraints().add(new ColumnConstraints(80));
        ePane.getColumnConstraints().add(new ColumnConstraints(100));
        ePane.getColumnConstraints().add(new ColumnConstraints(100));
        ePane.getColumnConstraints().add(new ColumnConstraints(40));
        fPane.getColumnConstraints().add(new ColumnConstraints(80));
        fPane.getColumnConstraints().add(new ColumnConstraints(100));
        fPane.getColumnConstraints().add(new ColumnConstraints(100));
        fPane.getColumnConstraints().add(new ColumnConstraints(40));

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
                nameT       = new Text(item.getName());
                nameT.setWrappingWidth(80);
                //nameT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);
                priceL      = new Label(setPrice(item.getPrice()));
                edit        = new Button("edit");
                delete      = new Button("delete");
                dummy       = new Label(" ");
                dummie      = new Label(" ");


                delete.setOnAction((ActionEvent e) ->{
                    menu.remove(item);
                    File file = File.getInstance();
                    try {
                        file.save(Menu.toStringArray());
                        String toastMsg = "Delete was successful.";
                        Toast.makeText(primaryStage, toastMsg);
                    } catch (IOException ex) {
                        ErrorMsg.addErrorMsg(primaryStage,"A file error occurred.");
                    }
                    layout.setCenter(getModifyView());
                });

                edit.setOnAction((ActionEvent e) -> {
                    ChangeItem changeItem = new ChangeItem(dh, primaryStage, layout, this);
                    changeItem.showEditStage(item);
                });

                if (item instanceof Coffee) {
                    ingredientsT = new Text(((Coffee) item).getIngredients());
                    ingredientsT.setWrappingWidth(100);
                    //ingredientsT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);

                    cPane.add(nameT, 0, cIndex);
                    cPane.add(ingredientsT, 1, cIndex);
                    cPane.add(dummy, 2, cIndex);
                    cPane.add(priceL, 3, cIndex);
                    cPane.add(edit, 4, cIndex);
                    cPane.add(delete, 5, cIndex);

                    cIndex ++;
                }
                if (item instanceof Beverage) {
                    ingredientsT = new Text(((Beverage) item).getIngredients());
                    ingredientsT.setWrappingWidth(100);
                    //ingredientsT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);

                    optionalT = new Text();
                    optionalT.setWrappingWidth(100);
                    //optionalT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);

                    if(((Beverage) item).getHot()){
                        optionalT.setText("Hot");
                    } else {
                        optionalT.setText("Cold");
                    }
                    bPane.add(nameT, 0, bIndex);
                    bPane.add(ingredientsT, 1, bIndex);
                    bPane.add(optionalT, 2, bIndex);
                    bPane.add(priceL, 3, bIndex);
                    bPane.add(edit, 4, bIndex);
                    bPane.add(delete, 5, bIndex);

                    bIndex ++;
                }

                if (item instanceof Extra) {
                    ePane.add(nameT, 0, eIndex);
                    ePane.add(dummy, 1, eIndex);
                    ePane.add(dummie, 2, eIndex);
                    ePane.add(priceL, 3, eIndex);
                    ePane.add(edit, 4, eIndex);
                    ePane.add(delete, 5, eIndex);

                    eIndex ++;
                }
                if (item instanceof Food) {
                    ingredientsT = new Text(((Food) item).getIngredients());
                    ingredientsT.setWrappingWidth(100);
                    //ingredientsT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);

                    optionalT = new Text(((Food) item).getDietaryInfo());
                    optionalT.setWrappingWidth(100);
                    //optionalT.pseudoClassStateChanged(CssConstants.TEXT_STYLE,true);

                    fPane.add(nameT, 0, fIndex);
                    fPane.add(ingredientsT, 1, fIndex);
                    fPane.add(optionalT, 2, fIndex);
                    fPane.add(priceL, 3, fIndex);
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

    private String setPrice(Double price){
        NumberFormat format = new DecimalFormat("#0.00");
        return format.format(price);
    }

    private GridPane getCoffeePane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Coffees");
        productL = new Label("Product");
        priceL = new Label("Price");
        ingredientsL = new Label("Ingredients");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(ingredientsL, 1, 1);
        pane.add(priceL, 3, 1);
        return pane;
    }

    private GridPane getBeveragePane(){
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        titleL = new Label("Beverages");
        productL = new Label("Product");
        priceL = new Label("Price");
        ingredientsL = new Label("Ingredients");
        optionalL = new Label("Temperature");

        titleL.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        productL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        priceL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        ingredientsL.pseudoClassStateChanged(CssConstants.COLUMN,true);
        optionalL.pseudoClassStateChanged(CssConstants.COLUMN,true);

        pane.add(titleL,0,0,6,1);
        pane.add(productL, 0, 1);
        pane.add(ingredientsL, 1, 1);
        pane.add(optionalL, 2,1);
        pane.add(priceL, 3, 1);

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
        pane.add(priceL, 3, 1);

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
        pane.add(ingredientsL, 1, 1);
        pane.add(optionalL, 2,1);
        pane.add(priceL, 3, 1);

        return pane;
    }
}
