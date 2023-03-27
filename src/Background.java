import java.awt.*;
import java.util.ArrayList;

public class Background {
    private int rows;
    //private int[] completeLines;
    private ArrayList<Integer> completeLines;
    private int columns;
    private Color[][] bgfield;

    public Background(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        completeLines = new ArrayList<>();
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
        int index=-1;
        for (Color[] line: bgfield) {
            index ++;
            System.out.println(index);
            if (lineIsComplete(line)) {
                System.out.println(index + " line is complete");
                completeLines.add(index);
            }
        }
    }

    public ArrayList<Integer> getCompleteLines() {
        return completeLines;
    }

    private boolean lineIsComplete(Color[] line) {
        boolean currentLineComplete = false;
        for (Color color : line) {
            if (color == null) {
                currentLineComplete = false;
                break;
            } else {
                currentLineComplete = true;
            }
        }
        return currentLineComplete;
    }

    public Color[][] getbgfield() {
        return bgfield;
    }

    public void removeLines() {
        System.out.println("removing lines");
        for (int i = 0; i < completeLines.size(); i++) {
            for (int line = completeLines.get(i); line > 0; line--) {
                for (int cell = 0; cell < columns; cell++) {
                    bgfield[line][cell] = bgfield[line - 1][cell];
                }
            }
        }
    }




    public void resetCompleteLines(){
        completeLines = new ArrayList<>();
    }
    public void makeLinesLighter() {
        for (int line = 0; line < rows; line++) {
                if (completeLines.contains(line)) {
                    for (int cell = 0; cell < columns; cell++) {
                        bgfield[line][cell] = bgfield[line][cell].brighter();


                    }
                }

        }
    }
    public void makeLinesDarker() {
        for (int line = 0; line < rows; line++) {
                if (completeLines.contains(line)) {
                    for (int cell = 0; cell < columns; cell++) {
                        bgfield[line][cell] = bgfield[line][cell].darker();


                    }
                }
            }
    }
}
