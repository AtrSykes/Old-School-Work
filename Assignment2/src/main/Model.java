/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 *
 * @author Adam Sykes
 */
public class Model {
    static List<Rectangle2D.Double> list = new ArrayList<>();
    
    public void Model(){
        list = null;
    }
}
class Collect{
    String shape;
    String position;
    String width;
    String height;
    
    public void Collect(){
        shape = null;
        position = null;
        width = null;
        height = null;
    }
    
    public void setName(String s){
        shape = s;
    }
    
    public void setEmail(String s){
        position = s;
    }
    
    public void setWorkPhone(String s){
        width = s;
    }
    
    public void setHomePhone(String s){
        height = s;
    }
    
    public String getName(){
        return shape;
    }
    
    public String getEmail(){
        return position;
    }
    
    public String getWorkPhone(){
        return width;
    }
    
    public String getHomePhone(){
        return height;
    }
    
    @Override
    public String toString(){
        StringBuilder l = new StringBuilder();
        
        l.append("Name: ");
        l.append(getName());
        l.append("\n");
        l.append("Email: ");
        l.append(getEmail());
        l.append("\n");
        l.append("Work Phone: ");
        l.append(getWorkPhone());
        l.append("\n");
        l.append("Home Phone: ");
        l.append(getHomePhone());
        l.append("\n");
        return l.toString();
    }
}
