/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

/**
 *
 * @author natal
 */
public interface Category {
 
    
    public void print();
        
    public void edit();
    
    public String getName();
    
    @Override
    public String toString();
    
}
