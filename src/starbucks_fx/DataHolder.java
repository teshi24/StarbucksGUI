package starbucks_fx;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DataHolder extends Observable {
    private ArrayList<DataObserver> observers = new ArrayList<>();

    private String name;
    private double price;
    private String priceString;
    private String ingredients;
    private String optional;
    private boolean hot;
    private boolean ok;


    public void attach(DataObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for(DataObserver observer : observers){
            observer.update();
        }
    }


    public void initVars(){
        name = "";
        price = 0;
        priceString = "";
        ingredients = "";
        optional = "";
        hot = false;
        ok = false;
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    /**
     * sets price and priceString to be ensure that they stores same value
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
        setPriceString(""+price);
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

    public void setOk(boolean ok) {
        this.ok = ok;
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

    public boolean isOk() {
        return ok;
    }
}
