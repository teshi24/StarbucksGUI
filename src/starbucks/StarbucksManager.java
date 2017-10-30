/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.io.IOException;
import java.util.Scanner;

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
            //not used any more
            //TODO: check what to do with this class
        }
        
        file.save(menu.toStringArray());
    }
    
}
