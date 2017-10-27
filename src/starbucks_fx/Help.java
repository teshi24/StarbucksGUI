package starbucks_fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Help {
    private ScrollPane sp = new ScrollPane();
    private Label help, showTitle, addTitle, modifyTitle, deleteTitle, show, add, modify, delete;

    public Help(Stage ownerStage){
        Stage helpStage = new Stage();
        helpStage.getIcons().add(new Image("resources/images/questionMark.jpg"));
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(setPane(), 470, 300));
        helpStage.initOwner(ownerStage);
        helpStage.setResizable(false);
        helpStage.show();
    }

    /**
     * @return
     */
    private ScrollPane setPane(){
        VBox pane = new VBox();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setSpacing(10);
        pane.getStylesheets().add("resources/css/style.css");

        help            = new Label("Help");

        showTitle       = new Label("Client view");
        show            = new Label("To show the menu to the clients, simply press the menu button"  + System.lineSeparator()
                                    +   "'Home'. If there aren't any items shown, add some items to the" + System.lineSeparator()
                                    +   "menu.");
        addTitle        = new Label("Add an item");
        add             = new Label("To add a new menu item, press the menu button 'Add'. Choose"   + System.lineSeparator()
                                    +   "the type of item you want to add. You can change the type"     + System.lineSeparator()
                                    +   "also later during the entrance without loosing your data."     + System.lineSeparator()
                                    +   "Enter the required information for this item. Press the button"+ System.lineSeparator()
                                    +   "'add item'.");
        modifyTitle     = new Label("Modify an item");
        modify          = new Label("To change the data of a menu item, press the menu button"      + System.lineSeparator()
                                    +   "'Modify'. Choose the item and press the button 'edit'. Now,"   + System.lineSeparator()
                                    +   "you can change any information you want. Press 'save' to get"  + System.lineSeparator()
                                    +   "the data finally updated.");
        deleteTitle     = new Label("Delete an item");
        delete          = new Label("To delete of a menu item, press the menu button 'Modify'." + System.lineSeparator()
                                    +   "Choose the item and press the button 'delete'. The item"   + System.lineSeparator()
                                    +   "will get deleted.");

        help.pseudoClassStateChanged(CssConstants.TITLE,true);
        showTitle.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        addTitle.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        modifyTitle.pseudoClassStateChanged(CssConstants.SUBTITLE,true);
        deleteTitle.pseudoClassStateChanged(CssConstants.SUBTITLE,true);

        pane.getChildren().addAll(help, showTitle, show, addTitle, add,
                                  modifyTitle, modify, deleteTitle, delete);
        sp.setContent(pane);
        return sp;
    }
}