import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;



public class MyLine extends MyShape
{  


	
    public MyLine()
    {
        super();
    }
    

    public MyLine( int x1, int y1, int x2, int y2, Color color, boolean stroke )
    {
        super(x1, y1, x2, y2, color,stroke);
    } 
     

    
    
    @Override
    public void draw( Graphics g )
    {
    	if (getStroke()) {
    		((Graphics2D) g).setColor( getColor() );
    		((Graphics2D) g).setStroke(new BasicStroke(10));
    		((Graphics2D) g).drawLine( getX1(), getY1(), getX2(), getY2() );
    		((Graphics2D) g).setStroke(new BasicStroke(1));
    		System.out.print("am fost aici");
    	}
    	else {
        g.setColor( getColor() ); 
        g.drawLine( getX1(), getY1(), getX2(), getY2() );
    }
    }
} 