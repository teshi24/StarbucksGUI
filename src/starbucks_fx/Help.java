/**
 * TODO Nadja: gewünschter Text einfüllen
 */

package starbucks_fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Help {

    private Label showTitle, addTitle, modifyTitle, deleteTitle, wrongCharTitle, show, add, modify, delete, wrongChar;

    public Help(Stage ownerStage){
        Stage helpStage = new Stage();
        helpStage.getIcons().add(new Image("resources/images/questionMark.jpg"));
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(setPane()));
        helpStage.initOwner(ownerStage);
        helpStage.setResizable(false);
        helpStage.show();
    }

    /**
     * @return
     */
    private VBox setPane(){
        VBox pane = new VBox();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setSpacing(10);
        pane.getStylesheets().add("resources/css/style.css");

        showTitle       = new Label("Client view");
        show            = new Label("To show the menu to the clients, simply press the 'Home'-Button." + System.lineSeparator()
                                    +   "If there aren't any items shown, add some items to the menu.");
        addTitle        = new Label("Add an item");
        add             = new Label("To add a new menu item, press the menu button 'Add'." + System.lineSeparator()
                                    +   "Choose the type of item you want to add." + System.lineSeparator()
                                    +   "Enter the required infos");
        modifyTitle     = new Label("Modify an item");
        modify          = new Label("This is a Text.");
        deleteTitle     = new Label("Delete an item");
        delete          = new Label("This is a Text.");
        wrongCharTitle  = new Label("Delete an item");
        wrongChar       = new Label("This is a Text.");

        pane.getChildren().addAll(showTitle     ,show
                                 ,addTitle      ,add
                                 ,modifyTitle   ,modify
                                 ,deleteTitle   ,delete
                                 ,wrongCharTitle,wrongChar);
        return pane;
    }

    private void setStyle(Label label, int size){
        label.setFont(Font.font("Veranda", FontWeight.BOLD, size));
    }
}