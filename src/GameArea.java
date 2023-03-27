import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
public class GameArea extends JPanel {
    private final int gridRows;   //private, because all drawing functions will belong to the instance of this class
    private final int gridColumns;    //because storing cells and drawing cells is a private matter of this class
    private final int gridCellSize;
    private final Color[] colors = {new Color(188, 246, 69),
            new Color(69, 231, 246),
            new Color(69, 131, 246),
            new Color(113, 69, 246),
            new Color(246, 69, 158),
            new Color(246, 122, 69),
            new Color(246, 201, 69),};
    private Random rand;
    TetrisBlock block;
    //private Color[][] background;
    Background background;
    public GameArea(Rectangle bounds, int columns){
        this.setBounds(bounds);
        this.setBackground(Color.lightGray);
        Border blackLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(blackLine);
        gridColumns = columns;                              // we do not use this. because names are different
        gridCellSize = this.getBounds().width/columns;      // looks like this is enough reason
        gridRows = this.getBounds().height/gridCellSize;
        background = new Background(gridRows, gridColumns);      //Ref type, instantiated with 'null'
        spawnBlock();                                       //initialize block
        //background[2][3] = colors[rand.nextInt(colors.length)];
    }
    //next function isn't necessary, but it encapsulates initialization procedure, make it more human-readable
    public void spawnBlock(){          //initializes block object in memory and assign it to variable block. No drawing
        rand = new Random();
        block = new TetrisBlock(rand.nextInt(6), colors[rand.nextInt(colors.length)]);
        block.spawn(gridColumns);
        repaint();
    }
    public boolean moveBlockDown(){
        if(atBottom()){
            moveBlockToBackground();  //in memory
            return false;
        }
        block.moveDown();
        repaint();                 //calls paintComponent internally
        return true;
    }
    public void moveBlockRight(){
        if(!atRightLimit()) {
            block.moveRight();
            repaint();
        }
    }
    public void moveBlockLeft(){
        if(!atLeftLimit()) {
            block.moveLeft();
            repaint();
        }
    }
    public void rotateBlock(){
        if(!atBottom()) {
            block.rotate();
            repaint();
        }
    }
    public void dropBlockDown(){
        while(!atBottom()){
            block.moveDown();
            repaint();
        }
    }
    public boolean atBottom(){
        if(block.getBottomEdge() == gridRows){
            return true;
        }
        return false;
    }
    public boolean atRightLimit(){
        if(block.getRightEdge() == gridColumns){
            return true;
        }
        return false;
    }
    public boolean atLeftLimit(){
        if(block.getLeftEdge() == 0){
            return true;
        }
        return false;
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
        drawBackground(g);    //
        drawBlock(g);
    }
    public void drawBackground(Graphics g){
        for(int r=0; r<gridRows; r++){
            for(int c=0; c<gridColumns; c++) {             //then manipulation
                if(background.getbgfield()[r][c] != null){                //actually drawing a block
                    int x = c * gridCellSize;          //calculating coordinate for each cell by multiplying simple int by grid cell size
                    int y = r * gridCellSize;
                    Color color = background.getbgfield()[r][c];
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    public void moveBlockToBackground(){
        background.absorbBLock(block);
    }
    public boolean checkCompleteLines(){
        background.setCompleteLines();
        System.out.println(background.getCompleteLines().size() + " is the number of lines");
        for(int line=0; line < background.getCompleteLines().size(); line++)
            System.out.print(line + " ");
        return background.getCompleteLines().size() > 0;
    }
    public void makeCompleteLinesLighter(){
        System.out.println("making lighter");
        background.makeLinesLighter();
        repaint();
    }
    public void makeCompleteLinesDarker(){
        System.out.println("making darker");
        background.makeLinesDarker();
        repaint();

    }
    public void removeCompleteLines(){
        background.removeLines();
        background.resetCompleteLines();
        repaint();
    }
    public void drawGridSquare(Graphics g, Color color, int x, int y){
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.darkGray);
        g.drawRect(x, y, gridCellSize, gridCellSize);


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
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
}
