package Control;

import Model.ConstantStrategy;
import Model.FactoryPattern;
import Model.IteratorPattern;
import Object.ClownObject;
import Model.MovingStrategy;
import Object.PlateObject;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import Model.StatePatternn;

public class Game implements World {
 private final ArrayList<GameObject> NonChangeable = new ArrayList<>();
    private final ArrayList<GameObject> movingObjects = new ArrayList<>();
    private final ArrayList<GameObject> control = new ArrayList<>();
    private static final int gameOverTime = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private final long beginningTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private ArrayList<GameObject> LStick = new ArrayList<>();
    private ArrayList<GameObject> rStick = new ArrayList<>();

    private StatePatternn gameStrategy;
    private FactoryPattern factory;
    private IteratorPattern iter;
    private int j,k = 0;
    private PlateController pCtrl;

    public Game(int screenWidth, int screenHeight, StatePatternn gameStrategy, FactoryPattern factory) {
        this.gameStrategy = gameStrategy;
        this.factory = factory;
        pCtrl = new PlateController(this);
        control.add(factory.createShapes("clown", screenWidth, screenHeight, 1));
        iter = new IteratorPattern(movingObjects);
        NonChangeable.add(factory.createShapes("bar", 0, 68, 1));
        width = screenWidth;
        height = screenHeight;
        
        NonChangeable.add(factory.createShapes("bar", width - 230, 68, 1));
        if (gameStrategy.getStrategy() == 1) {
            for (int i = 0; i < 20; i++) {
                movingObjects.add(factory.createShapes("plate", -70 * i, 43, 1));
            }
            for (int i = 0; i < 20; i++) {
                PlateObject p = (PlateObject) factory.createShapes("plate", width + 70 * i, 43, 1);
                p.setLeftOrRightBar(1);
                movingObjects.add(p);
            }
        }
        if (gameStrategy.getStrategy() == 2) {
            for (int i = 0; i < 20; i++) {
                movingObjects.add((PlateObject) factory.createShapes("plate", -70 * i, 43, 2));
            }
            for (int i = 0; i < 20; i++) {
                PlateObject p = (PlateObject) factory.createShapes("plate", width + 70 * i, 43, 2);
                p.setLeftOrRightBar(1);
                movingObjects.add(p);
            }
        }
    }

    public List<GameObject> getLeft() {
        return LStick;
    }

    public List<GameObject> getRight() {
        return rStick;
    }

    public List<GameObject> getControl() {
        return control;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private boolean intersect(/*El plate el beyo2a3*/GameObject o1, /* a5r plate el f id el clown */ GameObject o2) {

        // Bey5ali point el shape f nos el shape
        // x + half el width : 5ala el x f nos el taba2
        // y + half el height : 5ala el y f nos el taba2
        // ba3den beygib far2 el masafa maben el plate el f 2id el clown wl plate el beyo2a3 
        // law far2 el 3ard as8ar mn width el plate el beyo2a3 w far2 el tool as8ar mn height plate el beyo2a3
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth())
                && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - beginningTime > gameOverTime; // time end and game over
        GameObject clown = control.get(0);
        // movingObjects starts

        // for (int i = 0; i < movingObjects.size(); i++) {
        while (iter.hasNext()) {
            GameObject m = iter.next();
            PlateObject p = (PlateObject) m;
            int x = m.getX();
            int y = m.getY();
            y = y + (int) (Math.random() * 10) % 5;
            if (p.getLeftOrRightBar() == 0) { //if the plate is on the LStick side 
                x = x + gameStrategy.getSpeed();
                if (m.getX() + 150 > width / 2) {
                    p.setCurrentstState(new MovingStrategy(p));
                }
            } else { //if it's in the rStick side
                x = x - gameStrategy.getSpeed();
                if (m.getX() < width - 300) {
                    p.setCurrentstState(new MovingStrategy(p));
                }
            }
            p.getCurrentstState().move(x, y); //to move the plates in the desired direction
            if (LStick.isEmpty()) {
                if (clownIntersectleft(m)) {
             
                    System.out.println("in is empty for left");
                    movingObjects.remove(m);
                    p.setClown((ClownObject) clown); //so they stay in place even at the sides of the window 
                    p.setX(clown.getX() - 15);
                    p.setY(clown.getY() - p.getHeight());
                    p.setHorz(true);
                    p.setMovingWhere(1);
                    control.add(m); //clone the 2 images together
                    LStick.add(m);
                }
            } else if (intersect(m, LStick.get(LStick.size() - 1))) {

                System.out.println("In intersect of left");
                movingObjects.remove(m);
                p.setClown((ClownObject) clown);
                p.setX((int) (clown.getX()) - 15);
                p.setY(LStick.get(LStick.size() - 1).getY() - p.getHeight());
                p.setHorz(true);
                p.setMovingWhere(1);
                control.add(m);
                LStick.add(m);

            }

            pCtrl.update(1);
            if (rStick.isEmpty()) {

                if (clownIntersectright(m)) {
                    System.out.println("in is empty for right");
                 
                    movingObjects.remove(m);
                    p.setClown((ClownObject) clown);
                    p.setY(clown.getY() - p.getHeight());
                    p.setX(clown.getX() + 145);
           
                    p.setHorz(true);
               
                    control.add(m);
                    rStick.add(m);

                }
            } else if (intersect(m, rStick.get(rStick.size() - 1))) {

                System.out.println("in intersect of right");
                // System.out.println("--------" + rStick.get(rStick.size() - 1).getY());
                movingObjects.remove(m);
                p.setClown((ClownObject) clown);
                p.setX((int) (clown.getX()) + 145);
                //p.setX(rStick.get(rStick.size() - 1).getX());
                //System.out.println("--------" + (rStick.get(rStick.size() - 1).getY() - 27));
                p.setY((rStick.get(rStick.size() - 1).getY()) - p.getHeight());
                p.setHorz(true);
              
                control.add(m);
                rStick.add(m);

            }

            pCtrl.update(2);
            if (m.getY() > height) {
                movingObjects.remove(p);
                p.setCurrentstState(new ConstantStrategy(p));
                p.setY(43);
                if (p.getLeftOrRightBar() == 0) {
                    p.setX(-30 * j);
                    j++;
                } else {
                    p.setX(width + 30 * k);
                    k++;
                }
                movingObjects.add(p);

            }

            if (rStick.size() == 10 || LStick.size() == 10) {

                return false;
            }

            iter.setMyIterator(iter.getMyIterator() + 1);
        }
        iter.setMyIterator(0);
        return !timeout;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return NonChangeable;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movingObjects;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (gameOverTime - (System.currentTimeMillis() - beginningTime)) / 1000);	// update status
    }

    private boolean clownIntersectleft(GameObject o) {
        ClownObject clown = (ClownObject) control.get(0);
        return Math.abs(clown.getX() - o.getX()) <= o.getWidth() - 10
                && Math.abs(o.getY() - control.get(0).getY()) <= 10;
    }

    private boolean clownIntersectright(GameObject o) {
        ClownObject clown = (ClownObject) control.get(0);
        return Math.abs(clown.getX() + clown.getWidth() - 65 - o.getX()) <= o.getWidth() + 10
                && Math.abs(o.getY() - control.get(0).getY()) <= 10;
    }


}
