import javax.swing.*;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class Tetris2 {
    JFrame window;
    GameArea ga;

    public Tetris2(){
        final int mainWidth = 800;
        final int mainHeight = 900;
        final int gaWidth = 600;
        final int columns = 10;
        Rectangle bounds = new Rectangle(mainWidth/2 - gaWidth/2, 0, gaWidth, mainHeight);
        window = new JFrame();
        window.setBounds(400, 200, mainWidth, mainHeight + 30);  // setting dimentions for main window
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ga = new GameArea(bounds, columns);                                 //new GameArea
        window.getContentPane().add(ga, BorderLayout.CENTER);     //is added to main window
        window.getContentPane().setLayout(null);                  //enabling manual dimentions
        initControls();
        startGame();
    }
    public void initControls(){
        InputMap im = window.getRootPane().getInputMap();
        ActionMap am = window.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right");
                ga.moveBlockRight();
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left");
                ga.moveBlockLeft();
            }
        });

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up");
                ga.rotateBlock();
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("down");
                ga.dropBlockDown();
            }
        });
    }

    public void startGame(){
        new GameThread(ga).start();      //the Thread is in memory, doing its thing
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tetris2 tetris2 = new Tetris2();
                    tetris2.window.setVisible(true);
                    //window.frame.pack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
