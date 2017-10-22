package html_generation;


import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import starbucks.*;
import starbucks_fx.Home;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HtmlMenuGenerator {
    Document doc;
    private ArrayList<Coffee> coffees;
    private ArrayList<Beverage> beverages;
    private ArrayList<Extra> extras;
    private ArrayList<Food> foods;

    public void generateMenuAsDocument() {
        initializeItemArrays();
        categorizeItems();
        generateBaseDocument();
    }

    private void categorizeItems() {
        try {
            File file = File.getInstance();
            file.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Category item : Menu.items) {
            if (item instanceof Coffee) {
                coffees.add((Coffee) item);
            } else if (item instanceof Beverage) {
                beverages.add((Beverage) item);
            } else if (item instanceof Extra) {
                extras.add((Extra) item);
            } else if (item instanceof Food) {
                foods.add((Food) item);
            }
        }
    }

    private void generateBaseDocument() {
        WebView browser = new WebView();
        browser.getEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                setDoc(browser.getEngine().getDocument());

                Element coffeeList = doc.getElementById("coffees");
                Element beverageList = doc.getElementById("beverages");
                Element extraList = doc.getElementById("extras");
                Element foodList = doc.getElementById("foods");

                for (Coffee coffee : coffees) {
                    getCoffeeRow(coffeeList, coffee);
                }
                for (Beverage beverage : beverages) {
                    getBeverageRow(beverageList, beverage);
                }
                for (Extra extra : extras) {
                    getExtraRow(extraList, extra);
                }
                for (Food food : foods) {
                    getFoodRow(foodList, food);
                }
                String docToLoad = (String) browser.getEngine().executeScript("document.documentElement.outerHTML");
                Home.browser.getEngine().loadContent(docToLoad);

            }
        });

        URL url = this.getClass().getResource("/html_generation/generated/template.html");
        browser.getEngine().load(url.toString());
    }

    private void getFoodRow(Element foodList, Food food) {
        Element foodToAdd = doc.createElement("tr");
        Element foodName = doc.createElement("td");
        Element foodIngredients = doc.createElement("td");
        Element foodDiet = doc.createElement("td");
        Element foodPrice = doc.createElement("td");

        foodName.setTextContent(food.getName());
        foodPrice.setTextContent(Double.toString(food.getPrice()) + " CHF");
        foodIngredients.setTextContent(food.getIngredients());
        foodDiet.setTextContent(food.getDietaryInfo());
        foodToAdd.appendChild(foodName);
        foodToAdd.appendChild(foodIngredients);
        foodToAdd.appendChild(foodDiet);
        foodToAdd.appendChild(foodPrice);
        foodList.appendChild(foodToAdd);
    }

    private void getExtraRow(Element extraList, Extra extra) {
        Element extraToAdd = doc.createElement("tr");
        Element extraName = doc.createElement("td");
        Element extraPrice = doc.createElement("td");

        extraName.setTextContent(extra.getName());
        extraPrice.setTextContent(Double.toString(extra.getPrice()) + " CHF");
        extraToAdd.appendChild(extraName);
        extraToAdd.appendChild(extraPrice);
        extraList.appendChild(extraToAdd);
    }

    private void getBeverageRow(Element beverageList, Beverage beverage) {
        Element beverageToAdd = doc.createElement("tr");
        Element beverageName = doc.createElement("td");
        Element beverageIngredients = doc.createElement("td");
        Element beverageTemperature = doc.createElement("td");
        Element beveragePrice = doc.createElement("td");

        beverageName.setTextContent(beverage.getName());
        beveragePrice.setTextContent(Double.toString(beverage.getPrice()) + " CHF");
        beverageIngredients.setTextContent(beverage.getIngredients());
        if (beverage.getHot()){
            beverageTemperature.setTextContent("Hot");
        } else {
            beverageTemperature.setTextContent("Cold");
        }
        beverageToAdd.appendChild(beverageName);
        beverageToAdd.appendChild(beverageIngredients);
        beverageToAdd.appendChild(beverageTemperature);
        beverageToAdd.appendChild(beveragePrice);
        beverageList.appendChild(beverageToAdd);
    }

    private void getCoffeeRow(Element coffeeList, Coffee coffee) {
        Element coffeeToAdd = doc.createElement("tr");
        Element coffeeName = doc.createElement("td");
        Element coffeeIngredients = doc.createElement("td");
        Element coffeePrice = doc.createElement("td");

        coffeeName.setTextContent(coffee.getName());
        coffeePrice.setTextContent(Double.toString(coffee.getPrice()) + " CHF");
        coffeeIngredients.setTextContent(coffee.getIngredients());
        coffeeToAdd.appendChild(coffeeName);
        coffeeToAdd.appendChild(coffeeIngredients);
        coffeeToAdd.appendChild(coffeePrice);
        coffeeList.appendChild(coffeeToAdd);
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    private void initializeItemArrays() {
        coffees = new ArrayList<>();
        beverages = new ArrayList<>();
        extras = new ArrayList<>();
        foods = new ArrayList<>();
    }
}
