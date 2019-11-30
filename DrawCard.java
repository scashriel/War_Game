//Draw a smiley
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JPanel;
import java.security.SecureRandom;


public class DrawCard extends JPanel{
    private static final SecureRandom randomNumbers = new SecureRandom();
    private String player;
    private String status;
    private int faceCard;
    private int shape;

    //constructor
    public DrawCard(String s1, String s2, Card c){
        shape = c.convertSuit();
        faceCard = c.convertFaceToInt();
        player = s1;
        status = s2;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.black);
        g.drawString(player, 40, 20);
        g.drawString(status, 30, 40);

        switch (shape) {
            //heart
            case 1: {
                g.setColor(Color.red);
                int[] xp = {30, 80, 129};
                int[] yp = {91, 140, 91};
                g.fillOval(29, 65, 54, 40);
                g.fillOval(76, 65, 54, 40);
                g.fillPolygon(xp, yp, 3);
                break;
            }

            //diamond
            case 2:{
                g.setColor(Color.red);
                int [] xp1 = {55, 85, 115, 85};
                int [] yp1 = {100, 50, 100, 150};
                g.fillPolygon(xp1, yp1, 4);
                break;
            }
            //clubs
            case 3:
            //spades
            case 4: {
                g.setColor(Color.black);
                int[] xt = {60, 90, 110};
                int[] yt = {140, 80, 140};
                g.fillPolygon(xt, yt, 3);
                g.setColor(Color.white);
                g.fillOval(95, 105, 30, 33);
                g.fillOval(45, 105, 30, 33);
                g.setColor(Color.black);
                g.fillOval(45, 90, 45, 38); //moved x 16
                g.fillOval(79, 90, 45, 38);
                if (shape == 3){
                    g.fillOval(59, 57, 45, 38);
                }
                else if (shape == 4) {
                    int[] xs = {45, 85, 124};
                    int[] ys = {103, 60, 103};
                    g.fillPolygon(xs, ys, 3);
                }
                break;
            }

            //smile
            case 5:
            //frown
            case 6:{
                //draw face
                g.setColor(Color.yellow);
                g.fillOval(40, 50, 100, 100);
                //draw the eyes
                g.setColor(Color.black);
                g.fillOval(65, 75, 15, 15);
                g.fillOval(100, 75, 15, 15);
                if (shape == 5) {
                    //draw the mouth
                    g.fillOval(65, 90, 50, 50);
                    g.setColor(Color.yellow);
                    g.fillOval(65, 90, 50, 25);
                    g.fillOval(65, 90, 50, 35);
                }
                else if (shape == 6){
                    //draw the mouth
                    g.fillOval(65, 110, 50, 40);
                    g.setColor(Color.yellow);
                    g.fillOval(65, 120, 51, 20);
                    g.fillOval(65, 120, 50, 31);
                }
            }
        }
        g.setColor(Color.white);
        g.getFont().deriveFont(Font.BOLD);
        g.drawString(String.valueOf(faceCard), 79, 110);
    }
}
