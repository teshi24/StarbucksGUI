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

/**
 * primary stage & scene controller
 */
public class Template extends Application {
    private BorderPane layout = new BorderPane();
    private Stage primaryStage;

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Starbucks Manager");
        primaryStage.getIcons().add(new Image("resources/images/icon.png"));
        configureLayoutBorderPane();
        primaryStage.setScene(new Scene(layout, 510, 400));

        Home h = new Home();
        layout.setCenter(h.getHomeView());

        primaryStage.show();
    }

    private void configureLayoutBorderPane() {
        layout.getStylesheets().add("resources/css/style.css");
        layout.setTop(getMenuBar());
        layout.setBottom(getFooter());
    }

    /**
     * @return footer
     */
    private VBox getFooter() {
        VBox footer = new VBox();
        Label authors = new Label("©NaTaNa");
        footer.getChildren().add(authors);
        footer.setId("footer");
        footer.setAlignment(Pos.CENTER);
        return footer;
    }

    /**
     * @return menu bar
     */
    private MenuBar getMenuBar() {
        MenuBar menu = new MenuBar();
        Menu home = new Menu();
        Menu modify = new Menu();
        Menu add = new Menu();
        Menu help = new Menu();

        DataHolder dh = new DataHolder();

        Label homeMenuLabel = new Label("Home");
        homeMenuLabel.setOnMouseClicked(event -> {
            Home h = new Home();
            layout.setCenter(h.getHomeView());
        });
        home.setGraphic(homeMenuLabel);

        Label modifyMenuLabel = new Label("Modify");
        modifyMenuLabel.setOnMouseClicked(event -> {
            Modify m = new Modify(dh, primaryStage, layout);
            layout.setCenter(m.getModifyView());
        });
        modify.setGraphic(modifyMenuLabel);

        Label addMenuLabel = new Label("Add");
        addMenuLabel.setOnMouseClicked(event -> {
            AddItem a = new AddItem(dh, primaryStage);
            layout.setCenter(a.getAddItemView());
        });
        add.setGraphic(addMenuLabel);

        Label helpMenuLabel = new Label("Help");
        helpMenuLabel.setOnMouseClicked(event -> {
            new Help(primaryStage);
        });
        help.setGraphic(helpMenuLabel);

        menu.getMenus().addAll(home, modify, add, help);
        return menu;
    }
}