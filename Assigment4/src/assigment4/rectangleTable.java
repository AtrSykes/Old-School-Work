/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Sykes
 */
public class rectangleTable {
    private final List<RectangularShape> rectangles;
    private RectangularShape selectedRectangle;
    
    public rectangleTable(){
        rectangles = new ArrayList<>();
        selectedRectangle = null;
    }
    
    public void addRectangle(RectangularShape r){
        rectangles.add(r);
    }
    
    public RectangularShape getSelectedRectangle(){
        return selectedRectangle;
    }
    
    public void setSelectedRectangle(int index){
        selectedRectangle = rectangles.get(index);
    }
    public void clearSelectedRectangle(){
        selectedRectangle = null;
    }
    
    public void setSelectedRectangle(int x,int y){
        selectedRectangle = null;
        for (RectangularShape r: rectangles){
            if (r.contains(x,y)){
                selectedRectangle = r;
            }
        }
    }
    
    public void moveSelectedRectangle(double newx,double newy){
        if(selectedRectangle != null){
            selectedRectangle.setFrame(newx,newy,selectedRectangle.getWidth(),selectedRectangle.getHeight());
        }
    }
    
    public List<RectangularShape> getRectangles(){
        return rectangles;
    }
}
