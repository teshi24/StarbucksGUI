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
public class Beverage implements Category {
 
    private String name;
    private double price;
    private String ingredients;
    private boolean hot; 
    Scanner userInput = new Scanner(System.in);

    public Beverage(String name, double price, String ingredients, boolean hot){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.hot = hot;
    }
    
    @Override
    public void print() {
        String temp = "cold";
        if(hot){
            temp = "hot";
        }
        String format = "%-20s%2s%5s%2s%-50s%-5s";
        System.out.println(String.format(format, name, " ", price, " ", ingredients, temp));
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
    public void edit() {

    }

}
