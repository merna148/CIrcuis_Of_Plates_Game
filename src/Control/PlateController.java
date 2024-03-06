/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Object.PlateObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.ArrayList;

/**
 *
 * @author Salma
 */
public class PlateController implements Observer {

    private final Game game;

    public PlateController(Game game) {
        this.game = game;
    }
    ArrayList<GameObject> objects;

    @Override
    public void update(int n) {
        if (n == 1) {
            NotifyLeft();
        } else if (n == 2) {
            NotifyRight();
        }
    }

    private void NotifyLeft() {
        ArrayList<PlateObject> plates = new ArrayList<PlateObject>();
        if (game.getLeft().size() >= 3) {
            for (int i = 0; i <= 2; i++) {
                PlateObject p = (PlateObject) game.getLeft().get(game.getLeft().size() - (i+1));
                plates.add(p);
            }
            if (plates.get(0).getPath().equals(plates.get(1).getPath()) && plates.get(1).getPath().equals(plates.get(2).getPath())) {
                for (int k = 0; k <= 2; k++) {
                    game.getLeft().remove(game.getLeft().size() - 1);
                    game.getControl().remove(plates.get(k));
                    game.setScore(game.getScore() + 1);
                }

            }
        }
    }

    private void NotifyRight() {
        ArrayList<PlateObject> plates = new ArrayList<PlateObject>();
        if (game.getRight().size() >= 3) {
            for (int i = 0; i <= 2; i++) {
                PlateObject p = (PlateObject) game.getRight().get(game.getRight().size() - (i+1));
                plates.add(p);
            }
            if (plates.get(0).getPath().equals(plates.get(1).getPath()) && plates.get(1).getPath().equals(plates.get(2).getPath())) {
                for (int k = 0; k <= 2; k++) {
                    game.getRight().remove(game.getRight().size() - 1);
                    game.getControl().remove(plates.get(k));
                    game.setScore(game.getScore() + 1);
                }

            }
        }

    }
}
