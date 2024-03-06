package View;
import Control.Game;
import Model.FactoryPattern;
import Model.StateEasyPattern;
import Model.StateMediumPattern;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

    public static void main(String[] args) {
        //JLabel lblNewLabel = new JLabel("");
        //lblNewLabel.setBounds(0, 0, 300, 300);
        //lblNewLabel.setForeground(Color.BLACK);
        //frame.getContentPane().setLayout(null);
        //try {
          //  lblNewLabel.setIcon(new ImageIcon(ImageIO.read(Main.class.getClassLoader().getResourceAsStream("circus.png"))));
        //} catch (IOException e) {
         //   e.printStackTrace();
        //}
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        JMenu levelMenu = new JMenu("Choose Level");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        //JMenuItem Hard = new JMenuItem("Hard");
        levelMenu.add(easy);
        levelMenu.addSeparator();
        levelMenu.add(medium);
       // levelMenu.add(Hard);
        menuBar.add(levelMenu);
       
        final GameEngine.GameController gameController = GameEngine.start("Hello", new Game(800, 500,new StateEasyPattern(),FactoryPattern.getInstance()), menuBar, Color. ORANGE);
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               gameController.changeWorld(new Game(800, 500,new StateEasyPattern(), FactoryPattern.getInstance() ));
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               gameController.changeWorld(new Game(800, 500,new StateMediumPattern(), FactoryPattern.getInstance()));
            }
        });
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               gameController.changeWorld(new Game(800, 500,new StateEasyPattern() ,FactoryPattern.getInstance()));
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });

         resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameController.resume();

            }
        });
    }
}