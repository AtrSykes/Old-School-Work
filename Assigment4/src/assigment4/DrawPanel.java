package assigment4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private List<RectangularShape> rectangles;
    private RectangularShape selectedRectangle;
    
    public DrawPanel() {
        setBackground(Color.white);
        rectangles = new ArrayList<>();
    }
    
    private void createAndShowFrame(){
        JFrame frame = new JFrame("Midterm 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setRectangleModel(List<RectangularShape> rect){
        rectangles = rect;
        repaint();
    }
    
    public void setSelectedRectangle(RectangularShape r){
        selectedRectangle = r;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        for (RectangularShape r: rectangles){
            g2.draw(r);
            g2.fill(r);
        }
        highlightSelectedRectangle(g2);
        
    }
    
    private void highlightSelectedRectangle(Graphics2D g2){
        if(selectedRectangle != null){
            Stroke s = new BasicStroke(10);
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(s);
            g2.draw(selectedRectangle);
        }
    }
   
}
