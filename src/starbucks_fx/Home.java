package starbucks_fx;

import html_generation.HtmlMenuGenerator;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;



public class Home {
    public static WebView browser;

    public Node getHomeView(){
        VBox container = new VBox();
        container.setPadding(new Insets(10,10,10,10));
        container.setSpacing(10);

        browser = new WebView();
        browser.getEngine().setUserStyleSheetLocation(getClass().getResource("/resources/css/menuStyle.css").toString());
        HtmlMenuGenerator htmlMenuGenerator = new HtmlMenuGenerator();
        htmlMenuGenerator.generateMenuAsDocument();
        container.getChildren().add(browser);

        return browser;
    }
}
