package starbucks_fx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * GUI: short popup messages
 * sample code from https://stackoverflow.com/questions/26792812/android-toast-equivalent-in-javafx
 * made some changes to fit to our concept and add comments
 */
public final class Toast {
    /**
     * set toast text and get a message in the under left corner from the ownerstage for 2 seconds
     *
     * @param ownerStage
     * @param msg
     */
    public static void makeToast(Stage ownerStage, String msg) {
        makeToast(ownerStage, msg, 2000);
    }

    /**
     * set toast text and get a message in the under left corner from the ownerstage for x seconds
     *
     * @param ownerStage
     * @param msg
     * @param timeline   seconds how long the toast is shown
     */
    public static void makeToast(Stage ownerStage, String msg, int timeline) {
        int fadeInDelay = 500;
        int fadeOutDelay = 500;

        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        toastStage.setX(ownerStage.getX() + 18);
        toastStage.setY(ownerStage.getY() + ownerStage.getHeight() - 70);

        StackPane root = new StackPane();
        root.getStylesheets().add("resources/css/style.css");

        Button message = new Button(msg);

        message.pseudoClassStateChanged(CssConstants.TOAST, true);
        root.getChildren().add(message);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(timeline);
                } catch (InterruptedException e) {
                    System.out.println("Toast cannot be set sleepy.");
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }
}