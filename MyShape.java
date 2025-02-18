import java.awt.Color;
import java.awt.Graphics;


abstract class MyShape
{
    private int x1,y1,x2,y2; 
    private Color color; 
	private boolean stroke;
    

    public MyShape()
    {
        x1=0;
        y1=0;
        x2=0;
        y2=0;
        color=Color.BLACK;
    }
    
    public MyShape(int x1, int y1, int x2, int y2, Color color, boolean stroke)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.color=color;
        this.stroke=stroke;
    }
    

    
    
    
    public void setX1(int x1)
    {
        this.x1=x1;
    }   
    public void setY1(int y1)
    {
        this.y1=y1;
    }    
    public void setX2(int x2)
    {
        this.x2=x2;
    }   
    public void setY2(int y2)
    {
        this.y2=y2;
    }   
    

    
    
    
    
    public void setColor(Color color)
    {
        this.color=color;
    }
    public Color getColor()
    {
        return color;
    }
    
    
    
    
    
    public int getX1()
    {
        return x1;
    }
    public int getY1()
    {
        return y1;
    }
    public int getX2()
    {
        return x2;
    }
    public int getY2()
    {
        return y2;
    }
    


    
    public void setStroke(boolean stroke)
    {
        this.stroke=stroke;
    } 
    public boolean getStroke()
    {
        return stroke;
    }

    
    
    
    abstract public void draw( Graphics g );

}