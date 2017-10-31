/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author natal
 */
public class File {
    private static final File file = new File();
    Menu menu = Menu.getInstance();
    
    private File(){
        
    }
    
    public ArrayList<ArrayList<String>> load() throws IOException{
        ArrayList<ArrayList<String>> products = new ArrayList<>();
        java.io.File file1 = new java.io.File("products.txt");
        if (!file1.exists()) {
            System.out.println("-- The file 'products.txt' doesn't exist. A new file 'products.txt' will be created. --");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                for (String line; (line = br.readLine()) != null;) {
                    String[] splitString;
                    splitString = line.split("¦");
                    String ingredients = null;
                    String optional = null;

                    switch(splitString.length){
                        case 4: 
                            optional = splitString[3];
                        case 3:
                            ingredients = splitString[2];
                    }
                    ArrayList<String> productAttributes = new ArrayList<>();
                    productAttributes.addAll(Arrays.asList(splitString[0], splitString[1], ingredients, optional));
                    products.add(productAttributes);
                }
                menu.fillItems(products);
            }
        }
        
        return products;
    }
    public void save(ArrayList<String> items) throws IOException{
        java.io.File file1 = new java.io.File("products.txt");        
        if (!file1.exists()) {
            file1.createNewFile();
        }
        if (!items.isEmpty()) {
            try (FileWriter writer1 = new FileWriter(file1)) {
                String lastProduct = items.get(items.size() - 1);
                for (String aProduct : items) {
                    writer1.write(aProduct);
                    if (!aProduct.equals(lastProduct)) {
                        writer1.write(System.getProperty("line.separator"));
                    }
                }
                writer1.flush();
            }
        } else {
            try (FileWriter writer1 = new FileWriter(file1)) {
                writer1.write("");
                writer1.flush();
            }
        }
    }
    
    public static File getInstance(){
        return file;
    }
}
