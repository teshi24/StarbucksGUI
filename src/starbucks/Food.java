/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

public class Food implements Category{
    
    private String name;
    private double price;
    private String ingredients;
    private String dietaryInfo;

    public Food(String name, double price, String ingredients, String dietaryInfo){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.dietaryInfo = dietaryInfo;
    }

    @Override
    public String toString() {
        String concat = name + "¦" + price + "¦" + ingredients + "¦" + dietaryInfo;
        return concat;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() { return price; }

    public String getIngredients() { return ingredients; }

    public String getDietaryInfo() { return dietaryInfo; }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setPrice(double price){
        this.price = price;
    }

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public void setDietaryInfo(String dietaryInfo){
        this.dietaryInfo = dietaryInfo;
    }
    
}
