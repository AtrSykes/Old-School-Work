/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adam Sykes
 */
public class RectController {
    private final MainGUI view;
    private final rectangleTable rtable;
    private final JTable table;
    private final DrawPanel panel;
    private DefaultTableModel tableModel;
    private final Stack<Command> undo = new Stack<>();
    private final Stack<Command> redo = new Stack<>();
    
    public RectController(MainGUI view,rectangleTable rtable){
        this.view = view;
        this.rtable = rtable;
        this.table = view.getRectangleTable();
        this.panel = view.getDrawPanel();
        panel.setRectangleModel(rtable.getRectangles());
        view.addMouseListenerToDrawPanel(new DrawListener());
        view.addMouseMotionListenerToDrawPanel(new DrawMotionListener());
        view.addToggleButtonListener(new ButtonListener());
        view.addCToggleButtonListener(new CButtonListener());
        view.addUndoButtonListener(new UndoButtonListener());
        view.addRedoButtonListener(new RedoButtonListener());
        view.addMenuUndoListener(new MenuUndoListener());
        view.addMenuRedoListener(new MenuRedoListener());
        view.addMenuExitListener(new MenuExitListener());
        initTableModel();
        initTableColumns();
        initListSelectionModel();
    }
    
    private void initTableModel(){
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Shape");
        tableModel.addColumn("Top Left(X,Y)");
        tableModel.addColumn("Width");
        tableModel.addColumn("Height");
        table.setModel(tableModel);
    }
    
    private void initTableColumns(){
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
    }
    
    private void initListSelectionModel(){
        ListSelectionModel lsm = table.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new CourseTableListener());
    }
    
    private class CourseTableListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e){
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(!e.getValueIsAdjusting()){
                if(lsm.getMinSelectionIndex() < 0){
                    if(!rtable.getRectangles().isEmpty()){
                        rtable.setSelectedRectangle(rtable.getRectangles().size()-1);
                    }
                }
                else{
                    rtable.setSelectedRectangle(lsm.getMinSelectionIndex());
                }
                panel.setSelectedRectangle(rtable.getSelectedRectangle());
                panel.repaint();
            }
        }
    }
    
    private class DrawListener implements MouseListener{
        List<MoveCommand> moves = new ArrayList<>();
        @Override
        public void mouseClicked(MouseEvent e) {
            if(view.getToggleButton().isSelected()){
                AddCommand add = new AddCommand(rtable,tableModel,panel,"Rectangle");
                add.act(e);
                undo.push(add);
            }
            if(view.getCToggleButton().isSelected()){
                AddCommand add = new AddCommand(rtable,tableModel,panel,"Circle");
                add.act(e);
                undo.push(add);
            }
            for(RectangularShape r:rtable.getRectangles()){
                if((r.contains((double)e.getX(),(double)e.getY()) == true)||(r.contains((double)e.getX()+25.0,(double)e.getY()+25.0) == true)){
                    rtable.setSelectedRectangle(e.getX(),e.getY());
                    panel.setSelectedRectangle(r);
                    table.setRowSelectionInterval(rtable.getRectangles().indexOf(r),rtable.getRectangles().indexOf(r));
                    break;
                }
            }
            panel.repaint();
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(!view.getToggleButton().isSelected()&&!view.getCToggleButton().isSelected()){
                MoveCommand move = new MoveCommand(rtable,tableModel,panel);
                for(RectangularShape r:rtable.getRectangles()){
                    if(r.contains((double)e.getX(),(double)e.getY()) == true){
                        rtable.setSelectedRectangle(e.getX(),e.getY());
                        move.setStart((int) rtable.getSelectedRectangle().getMinX(),(int) rtable.getSelectedRectangle().getMinY());
                        break;
                    }
                }
                moves.add(move);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(!view.getToggleButton().isSelected()&&!view.getCToggleButton().isSelected()){
                MoveCommand move = moves.get(0);
                move.setEnd(e.getX(),e.getY());
                undo.push(move);
                moves.clear();
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            //Uneccessary
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //Unceccessary
        }
        
    }
    
    private class DrawMotionListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            for(RectangularShape r:rtable.getRectangles()){
                if(r.contains((double)e.getX(),(double)e.getY()) == true){
                    rtable.setSelectedRectangle(e.getX(),e.getY());
                    break;
                }
                if(rtable.getSelectedRectangle() != null){
                    rtable.moveSelectedRectangle(e.getX(),e.getY());
                    Object X = Integer.toString(e.getX())+","+Integer.toString(e.getY());
                    tableModel.setValueAt(X,rtable.getRectangles().indexOf(rtable.getSelectedRectangle()),1);
                    panel.repaint();
                }
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            //Unneccesary
        }
        
    }
    
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(view.addRectangleButtonIsSelected()){
                view.getToggleButton().setSelected(true);
                view.getCToggleButton().setSelected(false);
            }
            else{
                view.getToggleButton().setSelected(false);
            }
        }       
    }
    
    private class CButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(view.addRectangleButtonIsSelected()){
                view.getCToggleButton().setSelected(true);
                view.getToggleButton().setSelected(false);
            }
            else{
                view.getToggleButton().setSelected(false);
            }
        }       
    }
    private class UndoButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Command back = undo.pop();
                back.Undo();
                redo.push(back);
            }
            catch(EmptyStackException s){}
        }
    }
    private class RedoButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Command forth = redo.pop();
                forth.reAct();
                undo.push(forth);
            }
            catch(EmptyStackException s){}
        }
    }
    private class MenuUndoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Command back = undo.pop();
                back.Undo();
                redo.push(back);
            }
            catch(EmptyStackException s){}
        }
    }
    private class MenuRedoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Command forth = redo.pop();
                forth.reAct();
                undo.push(forth);
            }
            catch(EmptyStackException s){}
        }
    }
    private class MenuExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.dispose();
        }
    }
}

