import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

 class MyOval extends MyBoundedShape
{ 

    public MyOval()
    {
        super();
    }
    

    public MyOval( int x1, int y1, int x2, int y2, Color color, boolean fill, boolean stroke )
    {
        super(x1, y1, x2, y2, color,fill,stroke);
    }
     

    
    
    @Override
    public void draw( Graphics g )
    {
        g.setColor( getColor() ); 
        if (getFill()) { 
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); 
        }
        else if (getStroke()) {
        	((Graphics2D) g).setStroke(new BasicStroke(10));
        	((Graphics2D) g).drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        	((Graphics2D) g).setStroke(new BasicStroke(1));
        }
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); 
        
    }
    
} 