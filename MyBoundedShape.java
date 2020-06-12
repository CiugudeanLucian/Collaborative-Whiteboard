import java.awt.Color;
import java.awt.Graphics;


abstract class MyBoundedShape extends MyShape
{
    private boolean fill; 
    private boolean stroke;

    
    public MyBoundedShape()
    {
        super();
        fill=false;
        stroke=false;
    }
    

    
    
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean fill, boolean stroke)
    {
        super(x1, y1, x2, y2, color, stroke);
        this.fill=fill;
        this.stroke=stroke;
    }
    

    
    
    public void setFill(boolean fill)
    {
        this.fill=fill;
    } 
    public void setStroke(boolean stroke)
    {
        this.stroke=stroke;
    }

    
    
    
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    

    
    
    
    
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    

    
    
    
    
    public boolean getFill()
    {
        return fill;
    }
    public boolean getStroke()
    {
        return stroke;
    }

    
    
    
    abstract public void draw( Graphics g );
} 