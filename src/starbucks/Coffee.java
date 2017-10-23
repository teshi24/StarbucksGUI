/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.util.Scanner;

/**
 *
 * @author natal
 */
public class Coffee implements Category{
    
    private String name;
    private double price;
    private String ingredients;
    Scanner userInput = new Scanner(System.in);    
    
    public Coffee(String name, double price, String ingredients){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        String concat = name + "¦" + price + "¦" + ingredients;
        return concat;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() { return price; }

    public String getIngredients() { return ingredients; }

    @Override
    public void edit() {

    }


}
