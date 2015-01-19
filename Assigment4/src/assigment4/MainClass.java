package assigment4;

import java.awt.EventQueue;

public class MainClass {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI gui = new MainGUI(); 
                rectangleTable rtable = new rectangleTable();
                RectController cont = new RectController(gui,rtable);
                gui.setVisible(true);
            }
        });
    }
}
