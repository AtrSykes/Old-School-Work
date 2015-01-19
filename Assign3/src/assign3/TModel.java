/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Sykes
 */
public class TModel {
    List<Collect> people = new ArrayList<>();
    
    public void TModel(){
        people = null;
    }
    
    public void addEntry(String n,String e,String w,String h){
        Collect person = new Collect();
        
        person.setName(n);
        person.setEmail(e);
        person.setWorkPhone(w);
        person.setHomePhone(h);
        
        people.add(person);
    }
    
    public void removeEntry(int index){
        people.remove(index);
    }
    
    public String entry(int index){
        return people.get(index).toString();
    }
    
}
class Collect{
    String name;
    String email;
    String workPhone;
    String homePhone;
    
    public void Collect(){
        name = null;
        email = null;
        workPhone = null;
        homePhone = null;
    }
    
    public void setName(String s){
        name = s;
    }
    
    public void setEmail(String s){
        email = s;
    }
    
    public void setWorkPhone(String s){
        workPhone = s;
    }
    
    public void setHomePhone(String s){
        homePhone = s;
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getWorkPhone(){
        return workPhone;
    }
    
    public String getHomePhone(){
        return homePhone;
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