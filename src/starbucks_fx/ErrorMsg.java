package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorMsg {

    private void ErrorMsg() {}

    /**
     * generates a popup for error messages
     * @param ownerStage - stage which is calling the method
     * @param errorMsg - message which should be shown
     */
    public static void addErrorMsg(Stage ownerStage, String errorMsg){
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");
        errorStage.setScene(new Scene(setPane(errorStage, errorMsg)));
        errorStage.initOwner(ownerStage);
        errorStage.initModality(Modality.WINDOW_MODAL);
        errorStage.setResizable(false);
        errorStage.show();
    }
    public static String getCharNotAllowed(){
        return "Entered char is not allowed.";
    }
    /**
     * @param errorStage
     * @param errorMsg
     * @return
     */
    private static VBox setPane(Stage errorStage, String errorMsg){
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setSpacing(10);
        pane.getStylesheets().add("resources/css/style.css");

        Label msg = new Label(errorMsg);
        Button ok = new Button("OK");
        ok.setPrefSize(100, 30);

        ok.setOnAction((ActionEvent e) -> {
                errorStage.close();
        });

        pane.getChildren().addAll(msg, ok);
        return pane;
    }
}
