import java.awt.*;

public class TetrisBlock {
    private boolean[][] shape;
    private Color color;
    private int x;
    private int y;
    public TetrisBlock(boolean[][] shape, Color color){
        this.shape = shape;
        this.color = color;
        //this.x = 2;
        //this.y = 3;
    }
    public void spawn(int gridWidth){
        y = 0 - getHeight();
        x = gridWidth/2 - getWidth()/2;
    }
    public boolean[][] getShape(){return shape;}
    public Color getColor(){return color;}
    public int getHeight(){return shape.length;}
    public int getWidth(){return shape[0].length;}
    public int getX(){ return x;}    // for convenience, we store coordinates as 0, 1, 2, 3, 4,
    public int getY(){ return y;}     // so we could use it with loop and later multiply with grdCellSize
    public void moveDown(){y++;}
    public void moveLeft(){x--;}
    public void moveRight(){x++;}
    public int getBottomEdge(){return getY() + getHeight();}

}
