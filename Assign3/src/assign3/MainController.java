/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adam Sykes
 */
public class MainController {
    private FrameGUI view;
    private DialogGUI box;
    private IllegalEntry message;
    private JTable courseTable;
    private TModel crowd;
    private DefaultTableModel tableModel;
    
    public MainController(FrameGUI view,DialogGUI box,TModel crowd,IllegalEntry message){
        this.view = view;
        this.box = box;
        this.crowd = crowd;
        this.message = message;
        this.courseTable = view.getCourseTable();
        view.addAddButtonListener(new AddButtonListener());
        view.addDeleteButtonListener(new DeleteButtonListener());
        box.addCancelButtonListener(new CancelButtonListener());
        box.addOKButtonListener(new OKButtonListener());
        message.addErrorButtonListener(new ErrorButtonListener());
        
        initTableModel();
        initTableColumns();
        initListSelectionModel();
    }
    
    private void initTableModel(){
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        courseTable.setModel(tableModel);
    }
    
    private void initTableColumns(){
        courseTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        courseTable.getColumnModel().getColumn(1).setPreferredWidth(60);
    }
    
    private void initListSelectionModel(){
        ListSelectionModel lsm = courseTable.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new CourseTableListener());
    }
    
    public void showGUI(){
        view.setVisible(true);
    }
    
    private class CourseTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e){
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(!e.getValueIsAdjusting()){
                view.setTextInPane("");
                view.setEnabledDeleteButton(!lsm.isSelectionEmpty());
                if(lsm.getMinSelectionIndex() < 0){
                    view.setTextInPane(crowd.entry(0));
                }
                else{
                    view.setTextInPane(crowd.entry(lsm.getMinSelectionIndex()));
                }
            }
        }
    }
    
    private class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            box.setVisible(true);
        }
    }
    
    private class OKButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object[] person = {box.getFirstName()+" "+box.getLastName(),box.getEmail()};
            if(("".equals(box.getFirstName())  && "".equals(box.getLastName())) || "".equals(box.getEmail())){
                message.setVisible(true);
            }
            else{
                tableModel.addRow(person);
                crowd.addEntry(box.getFirstName()+" "+box.getLastName(), box.getEmail(), box.getWorkPhone(), box.getHomePhone());
                box.clearFields();
                box.setVisible(false);
            }
        }
    }
    
    private class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            box.clearFields();
            box.setVisible(false);
        }
    }
    
    private class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ListSelectionModel lsm = courseTable.getSelectionModel();
            if(!lsm.isSelectionEmpty()){
                int selectedIndex = lsm.getMinSelectionIndex();
                tableModel.removeRow(selectedIndex);
                crowd.removeEntry(selectedIndex);
            }
        }
    }
    
    private class ErrorButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            message.setVisible(false);
        }
    }
}
