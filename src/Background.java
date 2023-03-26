import java.awt.*;

public class Background {
    private int rows;
    private int[] completeLines;
    private int columns;
    private Color[][] bgfield;

    public Background(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        completeLines = new int[0];
        bgfield = new Color[rows][columns];
    }

    public void absorbBLock(TetrisBlock block) {
        boolean[][] shape = block.getShape();
        Color color = block.getColor();
        int x = block.getX();
        int y = block.getY();
        for (int r = 0; r < block.getHeight(); r++) {
            for (int c = 0; c < block.getWidth(); c++) {
                if (shape[r][c]) {
                    bgfield[y + r][x + c] = color;
                }
            }
        }
    }

    public void changeLineColor() {

    }

    public void setCompleteLines() {
        System.out.println("inside of setCompleteLines");
        for (int line = 0; line < rows; line++) {
            boolean currentLineComplete = false;
            for (int cell = 0; cell < bgfield[line].length; cell++) {
                if (bgfield[line][cell] == null) {
                    currentLineComplete = false;
                    System.out.println("the line " + line + " is not completed");
                    break;
                } else {
                    currentLineComplete = true;
                }
            }
            if (currentLineComplete) {
                System.out.println("the complete line is " + line);
                int[] newArray = new int[completeLines.length + 1];
                if(completeLines.length>0) {
                    for (int i = 0; i < completeLines.length; i++) newArray[i] = completeLines[i];
                }
                newArray[completeLines.length] = line;
                completeLines = new int[newArray.length];
                System.arraycopy(newArray, 0, completeLines, 0, newArray.length);
            }
        }
        System.out.println("there are " + completeLines.length + " complete lines");
        for(int line : completeLines) System.out.println("the line is " + line);
    }

    public int[] getCompleteLines() {
        return completeLines;
    }

    private boolean lineComplete() {
        return true;
    }

    public Color[][] getbgfield() {
        return bgfield;
    }

    public void removeLines(int[] lines) {
        System.out.println("removing lines");
        for (int line = 0; line < rows; line++) {
            for (int complete = 0; complete < completeLines.length; complete++) {
                if (line == completeLines[complete]) {
                    for (int row = rows-1; row > 0; row--) {
                        if(row >0){
                            for (int cell = 0; cell < columns; cell++) {
                                bgfield[row][cell] = bgfield[row - 1][cell];
                            }
                        }
                    }
                }
            }
        }
    }
    public void resetCompleteLines(){
        completeLines = new int[0];
    }
    public void makeLinesLighter(int[] lines) {
        for (int line = 0; line < rows; line++) {
            for (int complete = 0; complete < completeLines.length; complete++) {
                if (line == completeLines[complete]) {
                    for (int cell = 0; cell < columns; cell++) {
                        bgfield[line][cell] = bgfield[line][cell].brighter();


                    }
                }
            }
        }
    }
    public void makeLinesDarker(int[] lines) {
        for (int line = 0; line < rows; line++) {
            for (int complete = 0; complete < completeLines.length; complete++) {
                if (line == completeLines[complete]) {
                    for (int cell = 0; cell < columns; cell++) {
                        bgfield[line][cell] = bgfield[line][cell].darker();


                    }
                }
            }
        }
    }
}
