package starbucks_fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Template extends Application {
//    private static Template template = new Template();
//
//    public static Template getInstance() {
//        return template;
//    }
//
//    private Template() {
//    }
    protected BorderPane layout = new BorderPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Starbucks Manager");
        primaryStage.getIcons().add(new Image("resources/images/icon.png"));
        configureLayoutBorderPane();
        primaryStage.setScene(new Scene(layout, 500, 400));

        primaryStage.show();
    }

    private void configureLayoutBorderPane() {
        layout.setPadding(new Insets(0, 5, 0, 5));
        layout.getStylesheets().add("resources/css/style.css");
        layout.setTop(getMenuBar());
        layout.setBottom(getFooter());
    }

    private VBox getFooter() {
        VBox footer = new VBox();
        Label authors = new Label("©NaTaNa");
        footer.getChildren().add(authors);
        footer.setId("footer");
        footer.setAlignment(Pos.CENTER);
        return footer;
    }

    private MenuBar getMenuBar() {
        MenuBar menu = new MenuBar();
        Menu home = new Menu("Home");
        Menu modify = new Menu("Modify");
        Menu add = new Menu("Add");
        Menu help = new Menu("Help");
        // -> direkter aufruf funktioniert noch nicht, müsste mit Workaround gelöst werden mittels Graphical iwas (Google: java fx menu onclick, 2. Ergebnis)
        // Direkt: add.setOnAction((ActionEvent e) -> {AddItem a = new AddItem();layout.setCenter(a.getAddItemView());});
        // sub menu example
        MenuItem addTest = new MenuItem("Add Test");
        addTest.setOnAction((ActionEvent e) -> {AddItem a = new AddItem();layout.setCenter(a.getAddItemView());});
        add.getItems().addAll(addTest);

        menu.getMenus().addAll(home, modify, add, help);
        return menu;
    }
}