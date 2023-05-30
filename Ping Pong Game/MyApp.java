

import javax.swing.JFrame;

/**
 *
 * @author chinm
 */
public class MyApp {
    public static void main(String[] args) {
        JFrame obj = new JFrame();//frame
        GamePlay gameplay = new GamePlay();//panel
        obj.setBounds(10,10,700,600);
        obj.setTitle("BrickBreaker");
        obj.setResizable(false);//disable maximize button(Static)
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);//frame.add(panel);
    }

}
