/**
 * TODO Nadja: Handle Button addItem
 */
package starbucks_fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import starbucks.Category;
import starbucks.File;
import starbucks.Menu;
import starbucks.MenuItemFactory;

import java.io.IOException;

/**
 * GUI: add an item to the menu
 */
public class AddItem extends DataObserver{
    /** whole view */
    private VBox bigPicture;
    /** info text to the choice */
    private Label choose;
    /** buttons to choose the item which wants to be added */
    private FlowPane choice;
    /** individual form to enter the item attributes */
    private GridPane form;
    /** form button */
    private Button addItem;
    /** all possible attribute fields */
    private Label nameL, priceL, ingredientsL, optionalL;
    private TextField name, price, ingredients, optional;
    private HBox heatContainer;
    private ToggleGroup heat;
    private RadioButton hot;
    private RadioButton cold;

    private Stage primaryStage;
    private DataHolder dh;

    public AddItem(DataHolder dh, Stage primaryStage){
        this.dh = dh;
        this.dh.attach(this);

        this.primaryStage = primaryStage;
    }

    /**
     * get whole view
     * @return Scene with view for menu point 'add item'
     */
    public Node getAddItemView(){
        dh.initVars();

        bigPicture = new VBox();
        bigPicture.setPadding(new Insets(10,10,10,10));
        bigPicture.setSpacing(10);

        choice = new FlowPane();
        choice.setPadding(new Insets(0,10,0,10));
        choice.setHgap(10);
        choice.setVgap(15);

        choose = new Label();

        Button addCoffee = new Button("coffee");
        Button addFood = new Button("food");
        Button addBeverage = new Button("beverage");
        Button addExtra = new Button("extra");

        addCoffee.setMinSize(50,40);
        addFood.setMinSize(50,40);
        addBeverage.setMinSize(50,40);
        addExtra.setMinSize(50,40);

        addCoffee.setOnAction((ActionEvent e)   -> showForm(0));
        addFood.setOnAction((ActionEvent e)     -> showForm(1));
        addBeverage.setOnAction((ActionEvent e) -> showForm(2));
        addExtra.setOnAction((ActionEvent e)    -> showForm(3));

        choice.getChildren().addAll(addCoffee,addFood,addBeverage,addExtra);
        choose.setPadding(new Insets(0,10,0,10));
        if(form != null){
            choose.setText("Change the item type if needed.");
            bigPicture.getChildren().addAll(choose,choice,form);
        }else{
            choose.setText("Choose the item you want to add.");
            bigPicture.getChildren().addAll(choose,choice);
        }
        return bigPicture;
    }

    /**
     * make form visible
     * @param category int <br> 0 = coffee <br> 1 = food <br> 2 = beverage <br> 3 = extra
     */
    private void showForm(int category){
        if(form != null){
            bigPicture.getChildren().remove(2);
        }
        getAddItemForm(category);
        choose.setText("Change the item type if needed.");
        bigPicture.getChildren().add(form);
    }

    /**
     * prepare form
     * @param category int <br> 0 = coffee <br> 1 = food <br> 2 = beverage <br> 3 = extra
     * @return form, depending on category
     */
    private GridPane getAddItemForm(int category){
        // get form only if category is ok
        if(category >= 0 && category < 4) {
            form = new GridPane();
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

            addItem = new Button("add item");
            addItem.setMinSize(50, 40);
            addItem.setOnAction((ActionEvent e) -> sendValues(category));

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

    /**
     * get specific attributes
     */
    private void getCoffeeAttributes(){
        setChooseText("coffee");
        initIngredients();
        form.add(ingredientsL,0,2);
        form.add(ingredients,1,2);
        form.add(addItem,1,3);

        heat = null;
        optional = null;
    }

    /**
     * get specific attributes
     */
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

    /**
     * get specific attributes
     */
    private void getBeverageAttributes(){
        setChooseText("beverage");
        initIngredients();
        initHeath();

        form.add(ingredientsL,0,2);
        form.add(ingredients,1,2);
        form.add(optionalL,0,3);
        form.add(heatContainer,1,3);
        form.add(addItem,1,4);
    }

    /**
     * get specific attributes
     */
    private void getExtraAttributes(){
        setChooseText("extra");
        form.add(addItem,1,2);

        heat = null;
        ingredients = null;
        optional = null;
    }

    private void setChooseText(String item){
        choose.setText("You are add some " + item + " to your menu.");
    }

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

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    private void sendValues(int category){
        boolean ok = true;
        boolean nameOk = true;
        String mes = "Please add the required information to your product: " + System.lineSeparator();
        // check inserted values
        if (dh.getName() == null || dh.getName().equals("")) {
            mes += "name" + System.lineSeparator();
            ok = false;
        }else{
            for(Category c: Menu.items){
                if(c.getName().equals((String)dh.getName())){
                    mes = "This name does already exist. Please enter another.";
                    ok = false;
                    nameOk = false;
                }
            }
        }
        if(nameOk) {
            if (dh.getPriceString() == null || dh.getPrice() == 0) {
                mes += "price" + System.lineSeparator();
                ok = false;
            }
            if (category < 3) {
                if (dh.getIngredients() == null || dh.getIngredients().equals("")) {
                    mes += "ingredients" + System.lineSeparator();
                    ok = false;
                }
                if (category == 1) {
                    if (dh.getOptional() == null || dh.getIngredients().equals("")) {
                        mes += "dietary info";
                        ok = false;
                    }
                }
            }
            if (ok) {
                if (ingredients == null) {
                    dh.setIngredients(null);
                }
                if (optional == null) {
                    dh.setOptional(null);
                }
                if (heat != null) {
                    if (dh.isHot()) {
                        dh.setOptional("true");
                    } else {
                        dh.setOptional("false");
                    }
                }
            }
        }
        // send values to Factory if the input is ok
        if (ok) {
            MenuItemFactory factory = MenuItemFactory.getInstance();
            Menu.items.add(factory.create(dh.getName(), dh.getPrice(), dh.getIngredients(), dh.getOptional()));
            File file = File.getInstance();
            try {
                file.save(Menu.toStringArray());
                String toastMsg = "Add was successful.";
                Toast.makeText(primaryStage, toastMsg);
            } catch (IOException e) {
                ErrorMsg.addErrorMsg(primaryStage,"An file error occurred.");
            }
            dh.initVars();
        } else {
            ErrorMsg.addErrorMsg(primaryStage, mes);
        }
    }

    @Override
    public void update() {
        if(name != null){
            name.setText(dh.getName());
        }
        if(price != null){
            price.setText(dh.getPriceString());
        }
        if(ingredients != null){
            ingredients.setText(dh.getIngredients());
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