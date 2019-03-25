package motionparallax;

/**
 *
 * @author jetor
 */

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.PopupMenu;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MotionParallax extends JFrame{
    
    static int WIDTH = 500;
    static int HEIGHT = 500;
    
    
    public MotionParallax()
    {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Motion Parallax");
        
        BallPanel panel = new BallPanel();
        
        add(panel);
        panel.setBackground(Color.WHITE);
                
        setVisible(true);
    }
    
    class BallPanel extends JPanel implements ActionListener
    {
       private int delay = 100;
       protected Timer timer;

       private int x = 50;		// x position
       private int y = 50;		// y position
       private int radius = 15;	// ball radius

       private int dx = 2;		// increment amount (x coord)
       private int dy = 2;		// increment amount (y coord)
       
       private int currentX = 0;
       private int currentY = 0;
       
       Random rand = new Random();
       Color color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
       Color color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
       Color color3, color4;

       
   
       public BallPanel()
       {
            timer = new Timer(delay, this);
            timer.start();		// start the timer
            
            addMouseListener(new MyMouseListener());
       }

       public void actionPerformed(ActionEvent e)
       // will run when the timer fires
       {
            repaint();
       }

       // draw rectangles and arcs
       public void paintComponent( Graphics g )
       {
            super.paintComponent(g); // call superclass's paintComponent 
            g.setColor(Color.blue);

            int xValues[] = {50, 175, 300};
            int yValues[] = {375, 175, 375};

            int aValues[] = {150, 250, 350};
            int bValues[] = {375, 150, 375};

            // check for boundaries
            if (x < radius)			dx = Math.abs(dx);
            //if (x > getWidth() - radius)	dx = -Math.abs(dx); //keeps the object within the window
            if (y < radius)			dy = Math.abs(dy);
            //if (y > getHeight() - radius)	dy = -Math.abs(dy); //keeps the object within the window

            // adjust ball position
            x += dx;
            y += dy;
            //g.fillOval(x - radius, y - radius, radius*2, radius*2);

            int oValues[] = {100, 120, 140, 140, 120, 100};
            int pValues[] = {100, 103, 100, 104, 108, 103};

            int sValues[] = {140, 160, 180, 180, 160, 140};
            int tValues[] = {115, 118, 115, 119, 123, 118};

            // creates the sun
            g.setColor(Color.yellow);
            //g.fillOval(300, 50, radius*4, radius*4);
            g.fillOval(x - radius, y - radius, radius*4, radius*4);

            // creates the moon
            g.setColor(Color.white);
            g.fillOval(350, 50, radius*4, radius*4);
            
            // creates the grass
            g.setColor(Color.green);
            g.fillRect(0, 375, 500, 125);

            // creates a mountain
            g.setColor(color1);
            g.fillPolygon(aValues, bValues, 3);

            // creates another mountain
            g.setColor(color2);
            g.fillPolygon(xValues, yValues, 3);

            // creates the birds           
            g.setColor(Color.black);
            g.fillPolygon(oValues, pValues, 6);
            g.fillPolygon(sValues, tValues, 6);

       }
       
       private class MyMouseListener extends MouseAdapter
       {
           
            public void mousePressed(MouseEvent e)
            {
               setBackground(Color.GRAY);
               repaint();
               
            }        
            public void mouseClicked(MouseEvent e)
            {
                color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                repaint();
              
            }
            public void mouseReleased(MouseEvent e)
            {
                setBackground(Color.ORANGE);
                repaint();
            }
            public void mouseEntered(MouseEvent e)
            {
                setBackground(Color.cyan);
                repaint();         
                    
            }
            public void mouseExited(MouseEvent e)
            {
                setBackground(Color.blue);
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        new MotionParallax();
     
    }
}


   
