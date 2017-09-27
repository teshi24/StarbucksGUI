package starbucks_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddItem extends Application {

    Label message, nameL, priceL, ingredientsL, optionalL;
    // all possible fields - not each item needs each optional
    TextField name, price, ingredients, optional;
    Button addItem;
    GridPane form;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(callAddItem(0));
        primaryStage.show();

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
    }

    public Scene callAddItem(int category){
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
        form.setPadding(new Insets(10,10,10,10));

        addItem = new Button("add Item");
        addItem.setMinSize(50,40);

        form.add(message,0,0,2,1);
        form.add(nameL,0,1);
        form.add(name,1,1);
        form.add(priceL,0,2);
        form.add(price,1,2);

        String item = "error";
        switch(category){
            case 0: item="coffee";
                    setMessageText(item);
                    addCoffee();
                    break;
            case 1: item="food";
                    setMessageText(item);
                    addFood();
                    break;
            case 2: item="beverage";
                    setMessageText(item);
                    addBeverage();
                    break;
            case 3: item="extra";
                    setMessageText(item);
                    addExtra();
                    break;
            default: return null;
        }

        return new Scene(form,300,300);
    }

    private void addCoffee(){
        //initialize specific item information
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();

        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(addItem,1,4);
    }

    private void addFood(){
        //initialize specific item information
        ingredientsL = new Label("ingredients:");
        optionalL = new Label("dietary info:");
        ingredients = new TextField();
        optional = new TextField();

        form.add(ingredientsL,0,3);
        form.add(ingredients,1,3);
        form.add(optionalL,0,4);
        form.add(optional,1,4);
        form.add(addItem,1,5);
    }

    private void addBeverage(){
        //initialize specific item information
        optionalL = new Label("heat:");
        optional = new TextField();

        form.add(optionalL,0,3);
        form.add(optional,1,3);
        form.add(addItem,1,4);
    }

    private void addExtra(){
        //no specific item information

        form.add(addItem,1,3);
    }

    private void setMessageText(String item){
        message.setText("Add some " + item + " to your menu.");
    }

    /*
    public void add(String name, double price, String ingredients, boolean optional = null){
        //
    }
    */
}
