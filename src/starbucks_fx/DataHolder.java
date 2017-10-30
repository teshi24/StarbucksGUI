package starbucks_fx;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Observable;

/**
 * holds data for one item
 */
public class DataHolder extends Observable {
    private ArrayList<DataObserver> observers = new ArrayList<>();

    private String name;
    private double price;
    private String priceString;
    private String ingredients;
    private String optional;
    private boolean hot;

    public void attach(DataObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (DataObserver observer : observers) {
            observer.update();
        }
    }

    public void initVars() {
        name = "";
        price = 0;
        priceString = "";
        ingredients = "";
        optional = "";
        hot = false;
        notifyAllObservers();
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    /**
     * sets priceL and priceString to be ensure that they stores same value
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
        NumberFormat format = new DecimalFormat("#0.00");
        setPriceString(format.format(price));
        notifyAllObservers();
    }

    private void setPriceString(String priceString) {
        this.priceString = priceString;
        notifyAllObservers();
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
        notifyAllObservers();
    }

    public void setOptional(String optional) {
        this.optional = optional;
        notifyAllObservers();
    }

    public void setHot(boolean hot) {
        this.hot = hot;
        notifyAllObservers();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        return priceString;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getOptional() {
        return optional;
    }

    public boolean isHot() {
        return hot;
    }
}
