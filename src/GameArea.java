import javax.swing.JPanel;
import java.awt.*;

public class GameArea extends JPanel {
    public GameArea(){
        this.setBounds(0, 0, 150, 150);
        this.setBackground(Color.CYAN);

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(0, 0, 50, 50);
    }
}
