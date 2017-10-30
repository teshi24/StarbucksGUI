package starbucks_fx;

/**
 * sample code from https://stackoverflow.com/questions/26792812/android-toast-equivalent-in-javafx
 * made some changes to fit to our concept
 */

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public final class Toast
{
    public static void makeText(Stage ownerStage, String toastMsg){
        makeText(ownerStage, toastMsg, 2000);
    }

    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay) { makeText(ownerStage, toastMsg, toastDelay, 500, 500); }

    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay) {
        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        toastStage.setX(ownerStage.getX() + 18);
        toastStage.setY(ownerStage.getY() + ownerStage.getHeight() - 70);

        StackPane root = new StackPane();
        root.getStylesheets().add("resources/css/style.css");

        Button message = new Button(toastMsg);

        message.pseudoClassStateChanged(CssConstants.TOAST,true);
        root.getChildren().add(message);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(toastDelay);
                } catch (InterruptedException e) {
                    System.out.println("Toast cannot be set sleepy.");
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }
}