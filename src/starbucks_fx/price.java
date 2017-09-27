package starbucks_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class price extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Starbucks Menu");
        Scene scene;

        Stage priceStage = new Stage();
        priceStage.setTitle("Price");
        Scene priceScene;

        Button key0, key1, key2, key3, key4, key5, key6, key7, key8, key9;
        Button comma, ok;
        Label priceLabel;

        key0  = new Button("0");
        key1  = new Button("1");
        key2  = new Button("2");
        key3  = new Button("3");
        key4  = new Button("4");
        key5  = new Button("5");
        key6  = new Button("6");
        key7  = new Button("7");
        key8  = new Button("8");
        key9  = new Button("9");
        comma = new Button(".");
        ok    = new Button("OK");

        priceLabel = new Label();

        MenuBar menuBar    = new MenuBar();
        Menu properties    = new Menu("Properties");
        MenuItem colorItem = new MenuItem("Background Color");
        properties.add(colorItem);
        menuBar.add(properties);


    }
}
