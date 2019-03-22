package motionparallax;

/**
 *
 * @author jetor
 */

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MotionParallax extends JFrame{
    
    public static void main(String[] args) {
     
        JFrame frame = new JFrame( "Motion Parallax" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        BallPanel bp = new BallPanel(); 
        frame.add( bp );
        frame.setSize(500, 500); // set frame size
        
        
        
        bp.setBackground(Color.cyan);
        frame.setVisible(true); // display frame    
    }
}


// class BallPanel

class BallPanel extends JPanel implements ActionListener
{
   private int delay = 10;
   protected Timer timer;

   private int x = 0;		// x position
   private int y = 0;		// y position
   private int radius = 15;	// ball radius

   private int dx = 2;		// increment amount (x coord)
   private int dy = 2;		// increment amount (y coord)

   public BallPanel()
   {
        timer = new Timer(delay, this);
	timer.start();		// start the timer
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
        
        int oValues[] = {100, 120, 140, 140, 120, 100};
        int pValues[] = {100, 103, 100, 104, 108, 103};
        
        int sValues[] = {140, 160, 180, 180, 160, 140};
        int tValues[] = {115, 118, 115, 119, 123, 118};

	// check for boundaries
	if (x < radius)			dx = Math.abs(dx);
	if (x > getWidth() - radius)	dx = -Math.abs(dx);
	if (y < radius)			dy = Math.abs(dy);
	if (y > getHeight() - radius)	dy = -Math.abs(dy);

	// adjust ball position
	x += dx;
	y += dy;
	g.fillOval(x - radius, y - radius, radius*2, radius*2);
        
        g.setColor(Color.yellow);
        g.fillOval(300, 50, radius*4, radius*4);
        
        g.setColor(Color.green);
        g.fillRect(0, 375, 500, 125);
        
        g.setColor(Color.blue);
        g.fillPolygon(aValues, bValues, 3);
        
        g.setColor(Color.orange);
        g.fillPolygon(xValues, yValues, 3);
        
        g.setColor(Color.black);
        g.fillPolygon(oValues, pValues, 6);
        g.fillPolygon(sValues, tValues, 6);
        
   }
   
   private class MyMouseListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
        
        }
        public void mouseClicked(MouseEvent e)
        {
          
        }
        public void mouseReleased(MouseEvent e)
        {
           
        }
        public void mouseEntered(MouseEvent e)
        {
   
        }
        public void mouseExited(MouseEvent e)
        {
      
        }
    }
   
}
