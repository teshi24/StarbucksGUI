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
public class Food implements Category{
    
    private String name;
    private double price;
    private String ingredients;
    private String dietaryInfo;
    Scanner userInput = new Scanner(System.in);

    public Food(String name, double price, String ingredients, String dietaryInfo){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.dietaryInfo = dietaryInfo;
    }
        
    @Override
    public void print() {
        String format = "%-20s%2s%5s%2s%-50s%-12s";
        System.out.println(String.format(format, name, " ", price, " ", ingredients, dietaryInfo));
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
    public void edit() {
        String input;
        String format = "%-14s%-50s%-19s";
        System.out.print(String.format(format, "Name: ", name, " New name: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            name = input;
        }
        Boolean pOk = false;
        while(!pOk){
            System.out.print(String.format(format, "Price: ", price, " New price: "));
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
        System.out.print(String.format(format, "Dietary info: ", dietaryInfo, " New dietary info: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            dietaryInfo = input;
        }
    }
    
}
