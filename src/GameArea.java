import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class GameArea extends JPanel {
    private final int gridRows;   //private, because all drawing functions will belong to the instance of this class
    private final int gridColumns;    //because storing cells and drawing cells is a private matter of this class
    private final int gridCellSize;
    TetrisBlock block;
    public GameArea(Rectangle bounds, int columns){
        this.setBounds(bounds);
        this.setBackground(Color.lightGray);
        Border blackLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(blackLine);
        gridColumns = columns;                              // we do not use this. because names are different
        gridCellSize = this.getBounds().width/columns;      // looks like this is enough reason
        gridRows = this.getBounds().height/gridCellSize;
        spawnBlock();                                       //initialize block
    }
    //next function isn't necessary, but it encapsulates initialization procedure, make it more human-readable
    private void spawnBlock(){          //initializes block object in memory and assign it to variable block. No drawing
        block = new TetrisBlock(new boolean[][]{{true, true}, {true, false}, {true, false}}, Color.ORANGE);
        block.spawn(gridColumns);
    }
    public void moveBlockDown(){
        block.moveDown();
        repaint();                 //calls paintComponent internally
    }
    @Override                                 //overrides drawing method, which we do not call ourselves
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
        int h = block.getHeight();               //first data
        int w = block.getWidth();
        boolean[][] shape = block.getShape();
        Color color = block.getColor();

        for(int r=0; r<h; r++){
            for(int c=0; c<w; c++) {             //then manipulation
                if(shape[r][c]){                //actually drawing a block
                    int x = (block.getX() + c) * gridCellSize;          //calculating coordinate for each cell by multiplying simple int by grid cell size
                    int y = (block.getY() + r) * gridCellSize;
                    g.setColor(color);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.darkGray);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }
}
