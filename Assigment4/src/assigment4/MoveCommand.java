/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4;

import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adam Sykes
 */
public class MoveCommand implements Command{
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    rectangleTable table;
    DefaultTableModel tableModel;
    DrawPanel panel;
    
    public MoveCommand(rectangleTable rt,DefaultTableModel dtm,DrawPanel dp){
        table = rt;
        tableModel = dtm;
        panel = dp;
    }
    @Override
    public void act(MouseEvent e){
        // Unused, due to the nature of MouseEvents
    }
    
    @Override
    public void Undo(){
        table.moveSelectedRectangle((double)startx,(double)starty);
        Object X = Integer.toString(startx)+","+Integer.toString(starty);
        tableModel.setValueAt(X,table.getRectangles().indexOf(table.getSelectedRectangle()),1);
        panel.repaint();
    }
    
    @Override
    public void reAct(){
        for(RectangularShape r:table.getRectangles()){
            if(r.contains((double)startx,(double)starty) == true){
                table.setSelectedRectangle(startx,starty);
                break;
            }
            if(table.getSelectedRectangle() != null){
                table.moveSelectedRectangle(endx,endy);
                Object X = Integer.toString(endx)+","+Integer.toString(endy);
                tableModel.setValueAt(X,table.getRectangles().indexOf(table.getSelectedRectangle()),1);
                panel.repaint();
            }
        }
    }
    
    public void setStart(int x,int y){
        startx = x;
        starty = y;
    }
    
    public void setEnd(int x,int y){
        endx = x;
        endy = y;
    }
    
    @Override
    public String toString(){
        return Integer.toString(startx)+","+Integer.toString(starty)+","+Integer.toString(endx)+","+Integer.toString(endy);
    }
    
}
