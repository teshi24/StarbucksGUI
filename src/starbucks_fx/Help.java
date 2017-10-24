/**
 * TODO Nadja: gewünschter Text einfüllen
 */

package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Help {

    public Help(Stage ownerStage){
        Stage helpStage = new Stage();
        helpStage.getIcons().add(new Image("resources/images/questionMark.jpg"));
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(setPane(helpStage)));
        helpStage.initOwner(ownerStage);
        helpStage.setResizable(false);
        helpStage.show();
    }

    /**
     * @param errorStage
     * @return
     */
    private static VBox setPane(Stage errorStage){
        VBox pane = new VBox();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setSpacing(10);
        pane.getStylesheets().add("resources/css/style.css");

        Label msg = new Label("This is a Text.");
        Button ok = new Button("OK");
        ok.setPrefSize(100, 30);

        ok.setOnAction((ActionEvent e) -> {
            errorStage.close();
        });

        pane.getChildren().addAll(msg, ok);
        return pane;
    }

    private void setStyle(Label label, int size){
        label.setFont(Font.font("Veranda", FontWeight.BOLD, size));
    }
}