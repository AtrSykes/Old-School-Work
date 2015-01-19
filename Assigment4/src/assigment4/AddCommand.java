/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adam Sykes
 */
public class AddCommand implements Command{
    public String shape;
    public int startx;
    public int starty;
    public int index;
    DrawPanel panel;
    rectangleTable table;
    DefaultTableModel tableModel;
    
    public AddCommand(rectangleTable rtable,DefaultTableModel dtm,DrawPanel dp,String sh){
        table = rtable;
        panel = dp;
        tableModel = dtm;
        shape = sh;
    }
    @Override
    public void act(MouseEvent e){
        if (shape.equals("Rectangle")){
            Rectangle2D r = new Rectangle2D.Double();
            r.setRect(e.getX(),e.getY(),50.0,50.0);
            table.addRectangle(r);
            Object[] rectangle = {"Rectangle",Integer.toString(e.getX())+","+Integer.toString(e.getY()),50.0,50.0};
            tableModel.addRow(rectangle);
            startx = e.getX();
            starty = e.getY();
            index = table.getRectangles().indexOf(r);
        }
        else{
            Ellipse2D s = new Ellipse2D.Double();
            s.setFrame(e.getX(),e.getY(),50.0,50.0);
            table.addRectangle(s);
            Object[] circle = {"Circle",Integer.toString(e.getX())+","+Integer.toString(e.getY()),50.0,50.0};
            tableModel.addRow(circle);
            startx = e.getX();
            starty = e.getY();
            index = table.getRectangles().indexOf(s);
            table.setSelectedRectangle(index);
        }
        panel.repaint();
    }
    
    @Override
    public void Undo(){
        table.getRectangles().remove(index);
        table.clearSelectedRectangle();
        tableModel.removeRow(index);
        panel.repaint();
    }
    
    @Override
    public void reAct(){
        if(shape.equals("Rectangle")){
            Rectangle2D r = new Rectangle2D.Double();
            r.setRect(startx,starty,50.0,50.0);
            table.addRectangle(r);
            Object[] rectangle = {"Rectangle",Integer.toString(startx)+","+Integer.toString(starty),50.0,50.0};
            tableModel.insertRow(index,rectangle);
        }
        else{
            Ellipse2D s = new Ellipse2D.Double();
            s.setFrame(startx,starty,50.0,50.0);
            table.addRectangle(s);
            Object[] circle = {"Circle",Integer.toString(startx)+","+Integer.toString(starty),50.0,50.0};
            tableModel.insertRow(index,circle);
        }
    }
}
