
package project250;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

public  class getRandomColor {
    
     static int wow=0;
     public static Color color(){
         
        ArrayList<Color> mycol = new ArrayList<> () ;
        mycol.add(Color.DARKBLUE);//2
        mycol.add(Color.DARKOLIVEGREEN);//1
        mycol.add(Color.DARKSLATEGRAY);
        
        Random r=new Random();
        wow++;
         return mycol.get(wow%3);
     }
}
