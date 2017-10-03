package starbucks_fx;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestAddItemViews extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AddItem adden = new AddItem();
        primaryStage.setScene(adden.getAddItemView());
        primaryStage.show();
    }
}
