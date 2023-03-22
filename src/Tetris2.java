import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Tetris2 {
    JFrame window;
    GameArea ga;

    public Tetris2(){
        final int mainWidth = 800;
        final int mainHeight = 800;
        final int gaWidth = 600;
        final int columns = 10;
        Rectangle bounds = new Rectangle(mainWidth/2 - gaWidth/2, 0, gaWidth, mainHeight);
        window = new JFrame();
        window.setBounds(400, 200, mainWidth, mainHeight);  // setting dimentions for main window
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ga = new GameArea(bounds, columns);                                 //new GameArea
        window.getContentPane().add(ga, BorderLayout.CENTER);     //is added to main window
        window.getContentPane().setLayout(null);                  //enabling manual dimentions

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
