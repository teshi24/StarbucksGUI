package starbucks_fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import starbucks.*;
import starbucks.Menu;

import java.io.IOException;

public class Modify extends DataObserver{

    ScrollPane sp;
    VBox box;

    GridPane pane;
    GridPane cPane;
    GridPane bPane;
    GridPane ePane;
    GridPane fPane;
    int COLUMN_WIDTH = 80;
    Menu menu      = Menu.getInstance();

    int cIndex = 3;
    int bIndex = 3;
    int eIndex = 3;
    int fIndex = 3;

    private Label titleL, nameL, productL, priceL, ingredientsL, optionalL, errorL;
    private TextField name, price, ingredients, optional;
    private HBox heatContainer;
    private ToggleGroup heat;
    private RadioButton hot;
    private RadioButton cold;

    Button edit, delete;

    Stage primaryStage;
    BorderPane layout;

    public Modify(DataHolder dh, Stage primaryStage, BorderPane layout){
        this.dh = dh;
        this.dh.attach(this);

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

        cPane.getColumnConstraints().add(new ColumnConstraints(80));
        bPane.getColumnConstraints().add(new ColumnConstraints(80));
        ePane.getColumnConstraints().add(new ColumnConstraints(80));
        fPane.getColumnConstraints().add(new ColumnConstraints(80));

        titleL = new Label("Modify Starbucks Menu");
        titleL.pseudoClassStateChanged(CssConstants.TITLE,true);

        cIndex = 3;
        bIndex = 3;
        eIndex = 3;
        fIndex = 3;

        setModify();

        sp.setContent(box);
        return sp;
    }

    public void setModify() {
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
                        String toastMsg = "Delete was successful.";
                        Toast.makeText(primaryStage, toastMsg);
                    } catch (IOException ex) {
                        ErrorMsg.addErrorMsg(primaryStage,"A file error occurred.");
                    }
                    layout.setCenter(getModifyView());
                });
                //TODO: write edit
                /*edit.setOnAction((ActionEvent e) -> {

                 }); */

                if (item instanceof Coffee) {
                    ingredientsL    = new Label(((Coffee) item).getIngredients());

                    //edit.setOnAction((ActionEvent e) -> menu.edit(item.getName(), item.getPrice(), item.getIngredients()));

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

                    //edit.setOnAction((ActionEvent e) -> menu.edit(item.getName(), item.getPrice(), item.getIngredients(),));

                    bPane.add(nameL, 0, bIndex);
                    bPane.add(priceL, 1, bIndex);
                    bPane.add(ingredientsL, 2, bIndex);
                    bPane.add(optionalL, 3, bIndex);
                    bPane.add(edit, 4, bIndex);
                    bPane.add(delete, 5, bIndex);

                    bIndex ++;
                }

                if (item instanceof Extra) {
                    // TODO Natalie: do same for the other categories --> button kann vielleicht in einer eigenen Methode gehandelt werden - nur extras unterstützt bis jetzt

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

    // TODO: check if we could use same frames to add and modify products


    private void initName(){
        nameL = new Label("name:");
        name = new TextField();
        name.setText(dh.getName());
        name.textProperty().addListener((observable, oldValue, newValue)->{
            String text = name.getText().toString();
            if(!text.contains("|") && !text.contains("¦")){
                dh.setName(text);
            }else{
                name.setText(dh.getName());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    private void initPrice(){
        priceL = new Label("price:");
        price = new TextField();
        price.setText(dh.getPriceString());
        price.setEditable(false);
        price.setOnMouseClicked(e -> {
            Price p = new Price(primaryStage);
            p.enterPrice(dh);
        });
    }

    private void initIngredients(){
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();
        ingredients.setText(dh.getIngredients());
        ingredients.textProperty().addListener((observable, oldValue, newValue)->{
            String text = ingredients.getText().toString();
            if(!text.contains("|") && !text.contains("¦")){
                dh.setIngredients(text);
            }else{
                ingredients.setText(dh.getIngredients());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    private void initOptional(){
        optionalL = new Label("dietary info:");
        optional = new TextField();
        optional.setText(dh.getOptional());
        optional.textProperty().addListener((observable, oldValue, newValue)->{
            String text = optional.getText().toString();
            if(!text.contains("|") && !text.contains("¦")){
                dh.setOptional(text);
            }else{
                optional.setText(dh.getOptional());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    private void initHeath(){
        optionalL = new Label("heat:");
        optional = new TextField();
        heat = new ToggleGroup();
        hot = new RadioButton("hot");
        hot.setToggleGroup(heat);
        cold = new RadioButton("cold");
        cold.setToggleGroup(heat);
        if(dh.isHot()){
            hot.setSelected(true);
        }else{
            cold.setSelected(true);
        }
        heat.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(cold.isSelected()){
                    dh.setHot(false);
                }else{
                    dh.setHot(true);
                }
            }
        });
        heatContainer = new HBox(hot,cold);
        heatContainer.setSpacing(10);
    }
/*
    public void modify(){
        if(category >= 0 && category < 4) {
            GridPane form = new GridPane();
            form.setAlignment(Pos.TOP_LEFT);
            form.setHgap(10);
            form.setVgap(5);
            form.setPadding(new Insets(10, 10, 10, 10));
            form.getColumnConstraints().add(new ColumnConstraints(80));

            // fill standard attributes
            initName();
            initPrice();

            form.add(nameL, 0, 0);
            form.add(name, 1, 0);
            form.add(priceL, 0, 1);
            form.add(price, 1, 1);

            Button editOK = new Button("add item");
            editOK.setMinSize(50, 40);
            editOK.setOnAction((ActionEvent e) -> sendValues(category));

            // fill specific attributes
            switch (category) {
                case 0: getCoffeeAttributes();
                    break;
                case 1: getFoodAttributes();
                    break;
                case 2: getBeverageAttributes();
                    break;
                case 3: getExtraAttributes();
                    break;
                default: return null;
            }
            return form;
        }
        return null;
    }
*/
    /**
     * get specific attributes
     *//*
    private void getCoffeeAttributes(){
        setChooseText("coffee");
        initIngredients();
        form.add(ingredientsL,0,2);
        form.add(ingredients,1,2);
        form.add(addItem,1,3);

        heat = null;
        optional = null;
    }
*/
    /**
     * get specific attributes
     *//*
    private void getFoodAttributes(){
        setChooseText("food");
        initIngredients();
        initOptional();

        form.add(ingredientsL,0,2);
        form.add(ingredients,1,2);
        form.add(optionalL,0,3);
        form.add(optional,1,3);
        form.add(addItem,1,4);

        heat = null;
    }
*/
    /**
     * get specific attributes
     *//*
    private void getBeverageAttributes(){
        setChooseText("beverage");
        initHeath();
        initIngredients();

        form.add(ingredientsL,0,2);
        form.add(ingredients,1,2);
        form.add(optionalL,0,3);
        form.add(heatContainer,1,3);
        form.add(addItem,1,4);
    }
*/
    /**
     * get specific attributes
     *//*
    private void getExtraAttributes(){
        setChooseText("extra");
        form.add(addItem,1,2);

        heat = null;
        ingredients = null;
        optional = null;
    }
*/
    @Override
    public void update(){
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
    }
}
