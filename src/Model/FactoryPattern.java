package Model;


import Object.BarObject;
import Object.ClownObject;
import static Object.ClownObject.createClown;
import Object.PlateObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import static javax.swing.Spring.width;

public class FactoryPattern {
    private static FactoryPattern objectFactory = null;
    
     private FactoryPattern() {
    	
    }
//Singleton Pattern
    public static FactoryPattern getInstance() {
        if (objectFactory == null) {
            objectFactory = new FactoryPattern();
        }
        return objectFactory;

    }

    public GameObject createShapes(String type, int x, int y,int level) {
        
        if (type.equals("plate")) {
            if(level == 1)
           return new PlateObject(x, y, "/plate" + ((int) (Math.random() * 1000) % 3 + 1) + ".png");
            else if(level == 2)
           return new PlateObject(x, y, "/plate" + ((int) (Math.random() * 1000) % 5 + 1) + ".png");
        }
        else if (type.equals("bar")){
            return new BarObject(x, y, 250, true, Color.RED);
        }
        else if (type.equals("clown")){
            return createClown(x / 3, (int) (y * 0.6), "/clown.png");
        }
       
        return null;
    }
  
}
