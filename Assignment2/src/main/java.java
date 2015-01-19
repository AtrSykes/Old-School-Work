/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.awt.EventQueue;
import javax.swing.*;
/**
 *
 * @author Adam Sykes
 */
public class java{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                Model model = new Model();
                view frame = new view();
                Controller controller = new Controller(model, frame);
                frame.setTitle("Lab 4 Assignment");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        
    }
}

