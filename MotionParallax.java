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
        
        MotionPanel panel = new MotionPanel();
        
        add(panel);
        panel.setBackground(Color.WHITE);
                
        setVisible(true);
    }
    
    class MotionPanel extends JPanel implements ActionListener
    {
       private int delay = 100;
       protected Timer timer;

       private int x = 50;		// x position
       private int y = 50;		// y position
       private int car1StartX = 0;
       private int car1StartY = 381;
       private int car2StartX = 0;
       private int car2StartY = 378;
       private int radius = 15;	// ball radius

       private int dx = 2;		// increment amount (x coord)
       private int dy = 2;		// increment amount (y coord)
       
       Random rand = new Random();
       Color color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
       Color color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
       Color color3, color4;

       
       //creates the ball that moves across the screen to simulate a sunset.
       public MotionPanel()
       {
            timer = new Timer(delay, this);
            timer.start();		// start the timer
            
            addMouseListener(new MyMouseListener());
       }
       
       //handles the action that is involved in the construction of the timer
       //in this case, the this in Timer(delay, this) refers to this action
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
            if (y < radius)			dy = Math.abs(dy);

            // adjust ball position
            x += dx;
            y += dy;

            int oValues[] = {100, 120, 140, 140, 120, 100};
            int pValues[] = {100, 103, 100, 104, 108, 103};

            int sValues[] = {140, 160, 180, 180, 160, 140};
            int tValues[] = {115, 118, 115, 119, 123, 118};            
            
            //following are the coordinates for the cars to demonstrate motion parallax in a simple form
            int car1x[] = {car1StartX, car1StartX+10, car1StartX+12, car1StartX+17, car1StartX+19, car1StartX+29, car1StartX+29, car1StartX};            
            int car1y[] = {car1StartY, car1StartY, car1StartY-8, car1StartY-8, car1StartY, car1StartY, car1StartY+6, car1StartY+6};
            
            int car2x[] = {500-car2StartX, 495-car2StartX, 493-car2StartX, 493-car2StartX, 492-car2StartX, 487-car2StartX, 487-car2StartX, 500-car2StartX};
            int car2y[] = {car2StartY, car2StartY, car2StartY-4, car2StartY-4, car2StartY, car2StartY, car2StartY+3, car2StartY+3};
            
            //the following logic statements do the incrementing of starting positions
            //and will loop everything back when it gets to the end.
            if(car1StartX < 500) {
                car1StartX += (dx*4);
            } else {
                car1StartX = 0;
            }
            
            if (car2StartX < 500) {
                car2StartX += (dx*2);
            } else {
                car2StartX = 0;
            }

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
            
            //creates the "cars"
            g.setColor(Color.BLACK);
            g.fillPolygon(car1x, car1y, 8);
            g.fillPolygon(car2x, car2y, 8);
            
            

       }
       
       //all of the mouse listener functions applicable
       private class MyMouseListener extends MouseAdapter
       {
           //allows for the background to change colors while the mouse is pressed in.
            public void mousePressed(MouseEvent e)
            {
               setBackground(Color.GRAY);
               repaint();
               
            }  
            
            //The click allows the colors for the mountains to randomize
            public void mouseClicked(MouseEvent e)
            {
                color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                repaint();
              
            }
            
            //changes the background color after the mouse is realeased
            public void mouseReleased(MouseEvent e)
            {
                setBackground(Color.ORANGE);
                repaint();
            }
            
            //changes the background color when the mouse moves into the component
            //window in this case
            public void mouseEntered(MouseEvent e)
            {
                setBackground(Color.cyan);
                repaint();         
                    
            }
            
            //changes the background color when the mouse moves out of the component
            //window in this case
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