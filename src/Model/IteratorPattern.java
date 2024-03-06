package Model;



import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorPattern //implements Iterator<GameObject> 
{

    private int myIterator = 0;
    ArrayList<GameObject> movingObjects;

    public IteratorPattern(ArrayList<GameObject> x) {
        this.movingObjects = x;
        
    }

    public void setMyIterator(int myIterator) {
        this.myIterator = myIterator;
    }

   // @Override
    public boolean hasNext() {
        if (myIterator < movingObjects.size()) {
            return true;
        } else {
            return false;
        }
    }

    //@Override
    public GameObject next() {
        return movingObjects.get(myIterator);
    }

    public List<GameObject> getMovingObjects() {
        return movingObjects;
    }

    public void setMovingObjects(ArrayList<GameObject> movingObjects) {
        this.movingObjects = movingObjects;
    }

    public int getMyIterator() {
        return myIterator;
    }
    
}
