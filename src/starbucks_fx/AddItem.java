package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import starbucks.MenuItemFactory;

public class AddItem {
    // all possible fields with item information
    private Label message, nameL, priceL, ingredientsL, optionalL;
    private TextField name, price, ingredients, optional;

    private FlowPane choice;
    private VBox bigPicture;
    private Label choose;

    private GridPane form;
    private Button addItem;
    private int category = -1;
    /*
    double priceD = Double.parseDouble(price.getText());
    boolean dietB;

    if(optional.getText().equals("hot")){
        dietB = true;
    }else if(optional.getText().equals("cold")){
        dietB = false;
    }

    //addItem.setOnAction((ActionEvent e) -> add(name.getText(),priceD,ingredients.getText(),dietB));

    //Scene addFood = new Scene();
    */
    public Scene showAddItem(){
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

        addCoffee.setOnAction((ActionEvent e) -> {category=0;showForm(category);});
        addFood.setOnAction((ActionEvent e) -> {category=1;showForm(category);});
        addBeverage.setOnAction((ActionEvent e) -> {category=2;showForm(category);});
        addExtra.setOnAction((ActionEvent e) -> {category=3;showForm(category);});

        choice.getChildren().addAll(addCoffee,addFood,addBeverage,addExtra);

        if(form != null){
            choose.setText("Change the item type if needed.");
            bigPicture.getChildren().addAll(choose,choice,form);
        }else{
            choose.setText("Choose the item you want to add");
            bigPicture.getChildren().addAll(choose,choice);
        }
        return new Scene(bigPicture,500,500);
    }


    public GridPane callAddItem(int category){
        if(category >= 0) {
            //initialize standard information
            message = new Label();
            nameL = new Label("name:");
            priceL = new Label("price:");
            name = new TextField();
            price = new TextField();

            form = new GridPane();
            form.setAlignment(Pos.CENTER);
            form.setHgap(10);
            form.setVgap(5);
            form.setPadding(new Insets(10, 10, 10, 10));

            addItem = new Button("add Item");
            addItem.setMinSize(50, 40);

            addItem.setOnAction((ActionEvent e) -> {
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
                //send values to Factory
                if (ok) {
                    MenuItemFactory factory = MenuItemFactory.getInstance();
                    factory.create(nam, pri, ing, opt);
                } else {
                    message.setText(mes);
                }
            });

            form.add(message, 0, 0, 2, 1);
            form.add(nameL, 0, 1);
            form.add(name, 1, 1);
            form.add(priceL, 0, 2);
            form.add(price, 1, 2);

            switch (category) {
                case 0:
                    addCoffee();
                    break;
                case 1:
                    addFood();
                    break;
                case 2:
                    addBeverage();
                    break;
                case 3:
                    addExtra();
                    break;
            }
            return form;
        }
        return null;
    }

    private void addCoffee(){
        setMessageText("coffee");
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();


        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(addItem,1,4);
    }

    private void addFood(){
        setMessageText("food");
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();
        optionalL = new Label("dietary info:");
        optional = new TextField();

        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(optionalL,0,4);
        form.add(optional,1,4);
        form.add(addItem,1,5);
    }

    private void addBeverage(){
        setMessageText("beverage");
        optionalL = new Label("heat:");
        optional = new TextField();

        form.add(optionalL,0,3);
        form.add(optional,1,3);
        form.add(addItem,1,4);
    }

    private void addExtra(){
        setMessageText("extra");
        form.add(addItem,1,3);
    }

    private void setMessageText(String item){
        message.setText("Add some " + item + " to your menu.");
    }

    private void showForm(int category){
        if(form != null){
            bigPicture.getChildren().remove(2);
        }
        callAddItem(category);
        choose.setText("Change the item type if needed.");
        bigPicture.getChildren().add(form);
    }
    /*
    public void add(String name, double price, String ingredients, boolean optional = null){
        //
    }
    */
}
