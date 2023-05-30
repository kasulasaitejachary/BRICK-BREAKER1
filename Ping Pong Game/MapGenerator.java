

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author chinm
 */
public class MapGenerator {
    public int map[][];
    public int bricksWidth;
    public int bricksHeight;
    public MapGenerator(int row , int col){ // this create  the brick of size 3x7.
        map = new int[row][col];
         for (int i=0; i<map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map1[j] = 1;
            }
        }
        bricksWidth = 540/col;
        bricksHeight = 150/row;
    }
    public void draw(Graphics2D g) { // This draws brick
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.red);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int col)//to change value from 1 to 0(ball hits brick) //to change value from 1 to 0(ball hits brick)
    {
        map[row][col] = value;

    }

}
