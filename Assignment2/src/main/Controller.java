/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Adam Sykes
 */
public class Controller implements MouseListener,MouseMotionListener{
    private final Model model;
    private final view view;
    private Rectangle2D select = null;

    public Controller(Model model, view view){
        this.model = model;
        this.view = view;
        
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int sq = 0;sq < 5;sq++){
            if(Model.list.get(sq).contains((double)e.getX(),(double)e.getY()) == true){
                select = Model.list.get(sq);
            }
           
        }
        view.sel = select;
        view.passRect();
        view.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int sq = 0;sq < 5;sq++){
            if(Model.list.get(sq).contains((double)e.getX(),(double)e.getY()) == true){
                select = Model.list.get(sq);
            }
            
        }
        view.sel = select;
        view.passRect();
        view.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int sq = 0;sq < 5;sq++){
            if(Model.list.get(sq).contains((double)e.getX(),(double)e.getY()) == true){
                select = Model.list.get(sq);
                break;
            }
            else{
                select = null;
            }
           
        }
        view.sel = select;
        view.passRect();
        view.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(int sq = 0;sq < 5;sq++){
            if(Model.list.get(sq).contains((double)e.getX(),(double)e.getY()) == true){
                select = Model.list.get(sq);
            }
           
        }
        if (select != null){
            select.setRect((double)e.getX()-25.0,(double)e.getY()-25.0,50.0,50.0);
            if (select.getX() >= 533){
                select.setRect(533.0,select.getY(),50.0,50.0);
            }
            if (select.getX() <= 0){
                select.setRect(0.0,select.getY(),50.0,50.0);
            }
            if (select.getY() >= 511){
                select.setRect(select.getX(),511.0,50.0,50.0);
            }
            if (select.getY() <= 0){
                select.setRect(select.getX(),0.0,50.0,50.0);
            }
            view.sel = select;
            view.passRect();
            view.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(e);
    }
    
}
