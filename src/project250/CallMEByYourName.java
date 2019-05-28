
package project250;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.application.Application.launch;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CallMEByYourName {
    
    public static void main(String[] args) {
       
     JFrame frame=new JFrame();
     JButton b1=new JButton("START");
     JButton b2=new JButton("EXIT");
     frame.add(b1,b2);
     frame.setSize(1280, 1024);
     frame.setBackground(Color.RED);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
     frame.setVisible(true);
     frame.setBackground(Color.yellow);
     b1.addActionListener((ActionEvent e) -> {
         frame.setVisible(false);
         Project250.main();
     });
        
  //      Project250.main(args);
    }
}
