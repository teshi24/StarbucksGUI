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
public class Extra implements Category {

    private String name;
    private double price;
    Scanner userInput = new Scanner(System.in);    
    
    public Extra(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public void print() {
        String format = "%-20s%2s%5s";
        System.out.println(String.format(format, name, "  ", price));
    }

    @Override
    public String toString() {
        String concat = name + "¦" + price;
        return concat;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void edit() {
        String input;
        String format = "%-7s%-20s%-12s";
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
    }
    
}
