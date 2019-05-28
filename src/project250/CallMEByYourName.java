
package project250;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.application.Application.launch;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CallMEByYourName {
    
    public static void main(String[] args) {
       
     JFrame frame=new JFrame();
     JButton b1=new JButton("START");
     frame.add(b1);
     frame.setSize(1280, 720);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
     frame.setVisible(true);
     b1.addActionListener((ActionEvent e) -> {
         Project250.main(args);

     });
    }
}
