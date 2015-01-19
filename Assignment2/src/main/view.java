/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Adam Sykes
 */

class view extends JFrame{
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;
    Draw panel = new Draw();
    Rectangle2D sel = null;
    
    public view(){
        Model.getRect();
        add(panel);
        panel.setList(Model.list);
        pack();
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    @Override
    public void addMouseListener(MouseListener m){
        panel.addMouseListener(m);
    }
    @Override
    public void addMouseMotionListener(MouseMotionListener m){
        panel.addMouseMotionListener(m);
    }
    public void passRect(){
        panel.rect = this.sel;
    }
}

class Draw extends JPanel{
    static List<Rectangle2D.Double> list;
    Rectangle2D rect;
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        for(Rectangle2D.Double r:list){
            g2.setColor(Color.RED);
            g2.fill(r);
        }
        repaintSelected(g2);
    }
    
    public void setList(List<Rectangle2D.Double> q){
        list = q;
        rect = list.get(4);
        repaint();
    }
    
    public void repaintSelected(Graphics2D g2){
        if(rect != null){
            Stroke stroke = new BasicStroke(4);
            g2.setStroke(stroke);
            g2.setColor(Color.red);
            g2.fill(rect);
            g2.setColor(Color.orange);
            g2.draw(rect);
        }
    }
}
