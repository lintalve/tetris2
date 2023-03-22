import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;

public class GameArea extends JPanel {
    private final int gridRows;   //private, because all drawing functions will belong to the instance of this class
    private final int gridColumns;    //because storing cells and drawing cells is a private matter of this class
    private final int gridCellSize;
    boolean[][] block = {{true, true}, {true, false}, {true, false}};
    public GameArea(Rectangle bounds, int columns){
        this.setBounds(bounds);
        this.setBackground(Color.lightGray);
        Border blackLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(blackLine);
        gridColumns = columns;                              // we do not use this. because names are different
        gridCellSize = this.getBounds().width/columns;      // looks like this is enough reason
        gridRows = this.getBounds().height/gridCellSize;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);    //we need to call super in order to draw background for some reason
        g.setColor(Color.gray);
        //g.fillRect(0, 0, 50, 50);   //paints a rectangle at given coordinates
        for(int row=0; row<gridRows; row++){      //drawing background cells row by row
            for(int col=0; col<gridColumns; col++) {
                g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawBlock(g);
    }
    public void drawBlock(Graphics g){
        for(int r=0; r<block.length; r++){
            for(int c=0; c<block[0].length; c++) {
                if(block[r][c]) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(c * gridCellSize, r * gridCellSize, gridCellSize, gridCellSize);
                    g.setColor(Color.darkGray);
                    g.drawRect(c * gridCellSize, r * gridCellSize, gridCellSize, gridCellSize);
                }
            }
        }
    }
}
