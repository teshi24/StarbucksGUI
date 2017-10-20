/**
 * TODO:   Nadja - Preisanzeige 0.00
 * TODO:   Nadja - Daten speichern und übernehmen wenn ein neues Menu Item ausgewählt wird --> Static vars
 * TODO:   Nadja - Spezifische Überprüfungen weiterhin über Menu
 * TODO:   Nadja - Not null / format von Name und Preis schon hier umgesetzt, da nicht spezifisch
 */
package starbucks_fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import starbucks.MenuItemFactory;

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
    private Label message, nameL, priceL, ingredientsL, optionalL;
    private TextField name, price, ingredients, optional;
    private HBox heatContainer;
    private ToggleGroup heat;
    private RadioButton hot;
    private RadioButton cold;

    public AddItem(DataHolder dh){
        this.dh = dh;
        this.dh.attach(this);
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

            // TODO: Label entfernen, durch Dialog ersetzen
            // fill standard attributes
            message = new Label();
            initName();
            initPrice();

            form.add(message, 0, 0, 2, 1);
            form.add(nameL, 0, 1);
            form.add(name, 1, 1);
            form.add(priceL, 0, 2);
            form.add(price, 1, 2);

            addItem = new Button("add item");
            addItem.setMinSize(50, 40);
            addItem.setOnAction((ActionEvent e) -> sendValues());

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
        setMessageText("coffee");
        initIngredients();

        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(addItem,1,4);
    }

    /**
     * get specific attributes
     */
    private void getFoodAttributes(){
        setMessageText("food");
        initIngredients();
        initOptional();

        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(optionalL,0,4);
        form.add(optional,1,4);
        form.add(addItem,1,5);
    }

    /**
     * get specific attributes
     */
    private void getBeverageAttributes(){
        setMessageText("beverage");
        initHeath();

        form.add(optionalL,0,3);
        form.add(heatContainer,1,3);
        //form.add(hot,1,3);
        //form.add(cold,2,3);
        form.add(addItem,1,4);
    }

    /**
     * get specific attributes
     */
    private void getExtraAttributes(){
        setMessageText("extra");
        form.add(addItem,1,3);
    }

    private void setMessageText(String item){
        message.setText("Add some " + item + " to your menu.");
    }

    private void initName(){
        nameL = new Label("name:");
        name = new TextField();
        name.setText(dh.getName());
        name.textProperty().addListener((observable, oldValue, newValue)->{
            String text = name.getText().toString();
            dh.setName(text);
        });
        /*
        name.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                dh.setName(name.getText().toString());
            }
        });
        */
        //setOnAction((ActionEvent e) -> dh.setName(name.getText()));
    }
    private void initPrice(){
        priceL = new Label("price:");
        price = new TextField();
        price.setText(dh.getPriceString());
        price.setEditable(false);
        price.setOnMouseClicked(e -> {
            Price p = new Price();
            p.enterPrice(dh);
        });
    }
    private void initIngredients(){
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();
        ingredients = new TextField();
        ingredients.setText(dh.getIngredients());
        ingredients.setOnAction((ActionEvent e) -> dh.setIngredients(ingredients.getText()));
    }
    private void initOptional(){
        optionalL = new Label("dietary info:");
        optional = new TextField();
        optional.setText(dh.getOptional());
        optional.setOnAction((ActionEvent e) -> dh.setOptional(optional.getText()));
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

            }
        });
        heatContainer = new HBox(hot,cold);
        heatContainer.setSpacing(10);
    }

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    private void sendValues(){
        boolean ok = true;
        String mes = "";
        // check inserted values
        if (dh.getName() == null && dh.getName().equals("")) {
            mes += "Please enter a name for your product." + System.lineSeparator();
            ok = false;
        }
        if(dh.getPrice() == 0){
            ok = false;
        }
        // send values to Factory if the input is ok
        if (ok && dh.isOk()) {
            MenuItemFactory factory = MenuItemFactory.getInstance();
            factory.create(dh.getName(), dh.getPrice(), dh.getIngredients(), dh.getOptional());
            dh.initVars();
        } else {
            message.setText(mes);
        }
    }


    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    private void sendValuesOld(){
        boolean ok = false;
        String nam = "";
        double pri = 0;
        String ing;
        String opt;
        String mes = "";
        // check inserted values
        if (name != null && !name.getText().equals("")) {
            nam = name.getText();
        } else {
            mes += "Please enter a name for your product." + System.lineSeparator();
            ok = false;
        }
        try {
            pri = Double.parseDouble(price.getText());
        } catch (Exception ex) {
            mes += "Please enter a valid price. (Format: 9.99)";
            ok = false;
        }
        // send values to Factory if the input is ok
        if (ok) {
            if (ingredients != null) {
                ing = ingredients.getText();
            } else {
                ing = null;
            }
            if (optional != null) {
                opt = optional.getText();
            } else {
                opt = null;
            }
            MenuItemFactory factory = MenuItemFactory.getInstance();
            factory.create(nam, pri, ing, opt);
        } else {
            message.setText(mes);
        }
    }

    @Override
    public void update() {
        name.setText(dh.getName());
        price.setText(dh.getPriceString());
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
