import java.awt.*;

public class TetrisBlock {
    private boolean[][] shape;
    private Color color;
    private int x;
    private int y;
    private final boolean[][][] shapes = {{{true, true}, {true, false}, {true, false}},
                                        {{true, false}, {true, false}, {true, true}},
                                          {{true, false}, {true, true}, {true, false}},
                                        {{false, true}, {true, true}, {false, true}},
                                        {{true, true, true, true}},
                                        {{true, false}, {true, true}, {false, true}}};
    public boolean[][] rotatedShape;
    public TetrisBlock(int shape, Color color){
        this.shape = shapes[shape];
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
    public void rotate(){
        boolean[][] tempShape = new boolean[shape[0].length][shape.length];
        for(int r=0; r<shape.length; r++){
            for(int c=0; c<shape[0].length; c++){
                tempShape[c][r] = shape[r][(shape[0].length-1)-c];
            }
        }
        shape = new boolean[tempShape.length][tempShape[0].length];
        for(int r=0; r<tempShape.length; r++){
            for(int c=0; c<tempShape[0].length; c++){
                shape[r][c] = tempShape[r][c];
            }
        }

    }
    public boolean[][] getRotatedShape(){
        rotatedShape = new boolean[shape[0].length][shape.length];
        for(int r=0; r<shape.length; r++){
            for(int c=0; c<shape[0].length; c++){
                rotatedShape[c][r] = shape[r][(shape[0].length-1)-c];
            }
        }
        return rotatedShape;
    }
    public int getBottomEdge(){return getY() + getHeight();}
    public void removeBlock(){
        //shape = new boolean[][];
    }
    public int getRightEdge(){
        return getX() + getWidth();
    }
    public int getLeftEdge(){
        return getX();
    }
}
