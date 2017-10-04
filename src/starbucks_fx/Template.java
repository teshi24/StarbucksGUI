package starbucks_fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Template extends Application {
    private BorderPane layout = new BorderPane();

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
        Menu home = new Menu();
        Menu modify = new Menu();
        Menu add = new Menu();
        Menu help = new Menu();

        Label homeMenuLabel = new Label("Home");
        homeMenuLabel.setOnMouseClicked(event -> {
            //TODO: Add action
        });
        home.setGraphic(homeMenuLabel);

        Label modifyMenuLabel = new Label("Modify");
        modifyMenuLabel.setOnMouseClicked(event -> {
            //TODO: Add action
        });
        modify.setGraphic(modifyMenuLabel);

        Label addMenuLabel = new Label("Add");
        addMenuLabel.setOnMouseClicked(event -> {
            AddItem a = new AddItem();
            layout.setCenter(a.getAddItemView());
        });
        add.setGraphic(addMenuLabel);

        Label helpMenuLabel = new Label("Help");
        helpMenuLabel.setOnMouseClicked(event -> {
            //TODO: Add action
        });
        help.setGraphic(helpMenuLabel);

        menu.getMenus().addAll(home, modify, add, help);
        return menu;
    }
}