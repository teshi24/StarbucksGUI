/*
    TODO: - Anpassen an AddItem
    TODO: - 00.00 CHF adden - Natalie!!
 */

package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Price{

    TextField display;
    String price;


    public String getPrice() {


        return price;
    }

    public void enterPrice() {
        Stage priceStage = new Stage();
        priceStage.setTitle("Price");

        priceStage.setScene(getPriceScene());
        priceStage.setResizable(false);
        priceStage.show();
    }

    private Scene getPriceScene(){
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        Button key0, key1, key2, key3, key4, key5, key6, key7, key8, key9;
        Button comma, ok, delete;
        Label priceLabel;

        key0   = new Button("0");
        key1   = new Button("1");
        key2   = new Button("2");
        key3   = new Button("3");
        key4   = new Button("4");
        key5   = new Button("5");
        key6   = new Button("6");
        key7   = new Button("7");
        key8   = new Button("8");
        key9   = new Button("9");
        comma  = new Button(".");
        ok     = new Button("OK");
        delete = new Button("C");

        display = new TextField();

        key0.setPrefSize(60, 30);
        key1.setPrefSize(60, 30);
        key2.setPrefSize(60, 30);
        key3.setPrefSize(60, 30);
        key4.setPrefSize(60, 30);
        key5.setPrefSize(60, 30);
        key6.setPrefSize(60, 30);
        key7.setPrefSize(60, 30);
        key8.setPrefSize(60, 30);
        key9.setPrefSize(60, 30);
        comma.setPrefSize(60, 30);
        ok.setPrefSize(200, 30);
        delete.setPrefSize(60,30);
        display.setPrefSize(200, 30);

        // Set Action of keys
        key0.setOnAction((ActionEvent e) -> setDisplay(key0.getText()));
        key1.setOnAction((ActionEvent e) -> setDisplay(key1.getText()));
        key2.setOnAction((ActionEvent e) -> setDisplay(key2.getText()));
        key3.setOnAction((ActionEvent e) -> setDisplay(key3.getText()));
        key4.setOnAction((ActionEvent e) -> setDisplay(key4.getText()));
        key5.setOnAction((ActionEvent e) -> setDisplay(key5.getText()));
        key6.setOnAction((ActionEvent e) -> setDisplay(key6.getText()));
        key7.setOnAction((ActionEvent e) -> setDisplay(key7.getText()));
        key8.setOnAction((ActionEvent e) -> setDisplay(key8.getText()));
        key9.setOnAction((ActionEvent e) -> setDisplay(key9.getText()));
        comma.setOnAction((ActionEvent e) -> setDisplay(comma.getText()));

        ok.setOnAction((ActionEvent e) -> {
            String priceString = display.getText();

            double price = Double.parseDouble(priceString);
            // set price in java program..
            // close Price
        });

        delete.setOnAction((ActionEvent e) -> {
            display.setText(display.getText().substring(0, display.getText().length() - 1));
        });

        pane.getChildren().addAll(
                display,
                key1, key2, key3,
                key4, key5, key6,
                key7, key8, key9,
                comma, key0, delete,
                ok
        );

        return new Scene(pane, 210, 240);
    }

    private void setDisplay(String input){
        display.setText(display.getText() + input);
    }
}
