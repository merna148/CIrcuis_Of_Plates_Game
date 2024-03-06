package Model;


import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class StrategyPattern {

    protected GameObject g;

    public StrategyPattern(GameObject g) {
        this.g = g;
    }

    public abstract void move(int x, int y);
}
