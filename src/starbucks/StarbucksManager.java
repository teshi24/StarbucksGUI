/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author natal
 */
public class StarbucksManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Menu menu = Menu.getInstance();
        File file = File.getInstance();
        Scanner userInput = new Scanner(System.in);
        
        file.load();
        
        String input = "";
        while(!input.equals("exit")){
            System.out.println("____________________________________________________");
            System.out.println("");                   
            System.out.println("                Starbucks Manager");
            System.out.println("____________________________________________________");
            System.out.println("");        
            System.out.println("Choose what to do:");
            System.out.println("");   
            System.out.println("show   - show Starbucks Menu");   
            System.out.println("add    - add product to Starbucks Menu");   
            System.out.println("edit   - edit a product");  
            System.out.println("delete - delete a product");            
            System.out.println("exit   - exit Starbucks Manager");
            System.out.println("");
            System.out.print("--> ");
            input = userInput.next();
            System.out.println("");
            if(input.equals("show")){
                menu.print();
            } else if(input.equals("add")){
                menu.add();
            } else if(input.equals("edit")){
                menu.edit();
            } else if(input.equals("delete")){
                menu.remove();
            } else if(input.equals("exit")){   
            } else {
                System.out.println("-- Your entry is not valid. --");
                String that = userInput.nextLine();
                input = userInput.nextLine();
            }
        }
        
        file.save(menu.toStringArray());
    }
    
}
