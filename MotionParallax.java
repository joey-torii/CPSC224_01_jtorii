package motionparallax;

/**
 *
 * @author jetor
 */

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.PopupMenu;
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
        panel.setBackground(Color.ORANGE);
                
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


            g.setColor(Color.yellow);
            //g.fillOval(300, 50, radius*4, radius*4);
            g.fillOval(x - radius, y - radius, radius*4, radius*4);

            g.setColor(Color.green);
            g.fillRect(0, 375, 500, 125);

            g.setColor(Color.blue);
            g.fillPolygon(aValues, bValues, 3);

            g.setColor(Color.cyan);
            g.fillPolygon(xValues, yValues, 3);

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
                setBackground(Color.GRAY);
            }
            public void mouseReleased(MouseEvent e)
            {
                repaint();
            }
            public void mouseEntered(MouseEvent e)
            {

            }
            public void mouseExited(MouseEvent e)
            {

            }
        }
    }

    public static void main(String[] args) {
        new MotionParallax();
     
    }
}


   
