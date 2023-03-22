import javax.swing.JFrame;
import java.awt.*;

public class Tetris2 {
    JFrame window;
    GameArea ga;

    public Tetris2(){
        final int Width = 600;
        final int Height = 800;

        window = new JFrame();
        window.setBounds(400, 200, Width, Height);  // setting dimentions for main window
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ga = new GameArea();                                 //new GameArea
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
