package Model;


import eg.edu.alexu.csd.oop.game.GameObject;

public class ConstantStrategy extends StrategyPattern {

    public ConstantStrategy(GameObject g) {
        super(g);
    }

    @Override
    public void move( int x, int y) {
        g.setX(x);
    }

}
