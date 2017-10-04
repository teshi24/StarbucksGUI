package starbucks_fx;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Home {
    public Node getHomeView(){
        VBox container = new VBox();
        container.setPadding(new Insets(10,10,10,10));
        container.setSpacing(10);

        WebView browser = new WebView();
        container.getChildren().add(browser);

        return browser;
    }
}
