package Model;


import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingStrategy extends StrategyPattern {

    public MovingStrategy(GameObject g) {
        super(g);
    }
    @Override
    public void move(int x, int y) {
        g.setX(x);
        g.setY(y);
    }
}
