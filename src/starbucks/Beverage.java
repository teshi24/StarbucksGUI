/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

public class Beverage implements Category {
 
    private String name;
    private double price;
    private String ingredients;
    private boolean hot; 

    public Beverage(String name, double price, String ingredients, boolean hot){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.hot = hot;
    }


    @Override
    public String toString() {
        String concat = name + "¦" + price + "¦" + ingredients + "¦" + hot;
        return concat;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() { return price; }

    public String getIngredients() { return ingredients; }

    public boolean getHot() { return hot; }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setPrice(int price){
        this.price = price;
    }

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public void setHot(boolean hot){
        this.hot = hot;
    }

}
