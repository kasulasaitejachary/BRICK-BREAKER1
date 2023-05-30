

import javax.swing.JPanel;
import javax.swing.Timer;// project time
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;//every moment is called action event
import java.awt.event.ActionListener;//The moment which is used to record by action Listener
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//it will record what key you pressed

/**
 *
 * @author chinm
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;//slider position
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;

    public GamePlay() {//constructor
        map = new MapGenerator(3, 7);// initialization
        addKeyListener(this);//parent class
        setFocusable(true);  // it is used to run the game
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        map.draw((Graphics2D) g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);//only X-position is changing and Y is fixed

        //ball
        g.setColor(Color.GREEN);
        g.fillOval(ballposX, ballposY, 20, 20);
        
         //Edge Cases
        if (ballposY > 570) {  //If ball goes below the slider then you loose
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }
        if(totalbricks == 0){ //if all bricks are destroyed then you win
            play = false;
            ballYdir = -2;
            ballXdir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Game Over: "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);


        }

        g.dispose();// To kill the graphics & program and restart the game(New graphics will generate)


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if (play) {  // Ball - slider Interaction
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            A://  Anchor tag
            for (int i = 0; i < map.map.length; i++) {          // Ball-Brick Interaction
                for (int j = 0; j < map.map[0].length; j++) {   // Accessing length of 2D-Array of map inside Map class
                    if (map.map[i][j] > 0) {                 //class.2D array(That means there is a visible brick)
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;//if we want break outer loop(106)
                        }
                    }


                }
            }


            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {// ball hits left boundary, the ball has to reflect
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {// ball hits top
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {// ball hits right boundary
                ballXdir = -ballXdir;
            }
        }
        repaint();// When ball hits brick it will paint black(0) or it will paint red(1)
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {    // If right arrow key is pressed then the slider moves right
            if (playerX >= 600) {// keep the slider there only
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {     // If left arrow key is pressed then the slider moves left
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {    // If enter key is pressed then game restarts
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 21;
                map = new MapGenerator(3, 7);// Re-generate map again

                repaint();
            }
        }


    }

    public void moveRight ()   //slider move right by 20 pixels
    {
        play = true;// game start
        playerX += 20;
    }
    public void moveLeft ()    //slider move left by 20 pixels
    {
        play = true;
        playerX -= 20;
    }



}
