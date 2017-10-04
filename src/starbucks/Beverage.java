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
        String input;
        String format = "%-13s%-50s%-18s";
        System.out.print(String.format(format, "Name: ", name, " New name: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            name = input;
        }
        Boolean pOk = false;
        while(!pOk){
            System.out.print(String.format(format, "Price: ", price, " New Price: "));
            input = userInput.nextLine();
            if(!input.equals("null")){
                try {
                    price = Double.parseDouble(input);
                    pOk = true;
                } catch (NumberFormatException e){
                    System.out.println("-- Price could not be changed. Input was not nummeric. --");
                }
            } else {
                pOk = true;
            }            
        }
        System.out.print(String.format(format, "Ingredients: ", ingredients, " New ingredients: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            ingredients = input;
        }
        System.out.print(String.format(format, "Hot: ", hot, " New hot (hot/cold): "));
        input = userInput.nextLine();
        switch (input) {
            case "hot": 
                input = "true";
                break;
            case "cold":
                input = "false";
                break;
        }
        try {
            hot = Boolean.parseBoolean(input);
        } catch (NumberFormatException e){
            System.out.println("-- Hot could not be changed. Input was not a boolean. --");
        }
    }

}
