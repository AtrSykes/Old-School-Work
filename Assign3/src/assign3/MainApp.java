/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

/**
 *
 * @author Adam Sykes
 */
public class MainApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                FrameGUI gui = new FrameGUI();
                DialogGUI gooey = new DialogGUI(gui,true);
                TModel sticky = new TModel();
                IllegalEntry tacky = new IllegalEntry(gui,true);
                MainController controller = new MainController(gui,gooey,sticky,tacky);
                controller.showGUI();
            }
        });
    }
    
}
