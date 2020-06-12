import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;






public class DrawPanel extends JPanel
{
    private LinkedList<MyShape> myShapes; //stack pentru forme
    private LinkedList<MyShape> clearedShapes; //stack pentru forme sterse
    Graphics2D g1;
	private JFileChooser fileChooser;

	
    private int currentShapeType; //0 pentru linie, 1 pentru dreptunghi, 2 pentru oval
    private Image img, background;
    private MyShape currentShapeObject; 
    private Color currentShapeColor; 
    private boolean currentShapeFilled;
    private boolean currentShapeStroked;
    
    JLabel statusLabel; //coordonatele mouse-ului
    

    
    
    
    public DrawPanel(JLabel statusLabel){
        
        myShapes = new LinkedList<MyShape>(); 
        clearedShapes = new LinkedList<MyShape>(); 
        
        
        currentShapeType=0;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        currentShapeFilled=false;
        currentShapeStroked=false;
        
        this.statusLabel = statusLabel; 
        
        setLayout(new BorderLayout()); 
        setBackground( Color.WHITE ); 
        add( statusLabel, BorderLayout.SOUTH );  //margine jos
        
        
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener( handler );
        addMouseMotionListener( handler ); 
    }
    

    
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        


	        // desenarea formelor
	        ArrayList<MyShape> shapeArray=myShapes.getArray();
	        for ( int counter=shapeArray.size()-1; counter>=0; counter-- )
	           shapeArray.get(counter).draw(g);
	        
	        
	        if (currentShapeObject!=null)
	            currentShapeObject.draw(g);
		


    }
    

    
    
    
    public void setCurrentShapeType(int type)
    {
        currentShapeType=type;
    }
    
    
    
    
    public void setCurrentShapeColor(Color color)
    {
        currentShapeColor=color;
    }
    
    
    
    
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled=filled;
    }
    
    
    
    
    public void setCurrentShapeStroked(boolean stroke){
    	currentShapeStroked=stroke;
    }
    
    
    
    
    public void clearLastShape()
    {
        if (! myShapes.isEmpty())
        {
            clearedShapes.addFront(myShapes.removeFront());
            repaint();
        }
    }
    
    
    
    
    public void redoLastShape()
    {
        if (! clearedShapes.isEmpty())
        {
            myShapes.addFront(clearedShapes.removeFront());
            repaint();
        }
    }
    
    
    
    
    public void clearDrawing()
    {
        myShapes.makeEmpty();
        clearedShapes.makeEmpty();
        setBackground(Color.WHITE);
        repaint();
    }
    
    
    
    
    
    public void PickColor()
    {
    	Color color = null;
    	color = JColorChooser.showDialog(null, "Pick your color!",
				color);
    	currentShapeColor=color;
    }  
    
    
    
    
    
	public void save(File file) {
		try {
			ImageIO.write((RenderedImage) img, "png", file);
		} catch (IOException ex) {

		}
	}
	
	
	
	
	public void load(File file) {
		try {
			
			img = ImageIO.read(file);
			//g1 = (Graphics2D) img.getGraphics();
			//img = Toolkit.getDefaultToolkit().createImage("file.png");
			
			img = createImage(getSize().width, getSize().height);
			g1 = (Graphics2D) img.getGraphics();
			((Graphics2D) g1).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			clearDrawing();	
			g1.drawImage(img, 0, 0, null);
			
			//g1.drawImage(img, 0, 0, null);
			System.out.println(file);
			//setBackground(Color.BLACK);
		}

		catch (IOException ex) {
		}
	}
	
	
	
	
	
	public void Paint() {
		setBackground(currentShapeColor);
	}
	
	
	
	private void setImage(Image img) {
		g1 = (Graphics2D) img.getGraphics();
		g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g1.setPaint(Color.black);
		this.img = img;
		repaint();
	}
	
	
	
	public void setBackground(Image img) {
		background = copyImage(img);
		setImage(copyImage(img));
	}
	
	

	private BufferedImage copyImage(Image img) {
		BufferedImage copyOfImage = new BufferedImage(getSize().width,
				getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics g = copyOfImage.createGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		return copyOfImage;
	}
	
	
	

    private class MouseHandler extends MouseAdapter 
    {

        public void mousePressed( MouseEvent event )
        {
            switch (currentShapeType)
            {
                case 0:
                    currentShapeObject= new MyLine( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeStroked);
                    break;
                case 1:
                    currentShapeObject= new MyRectangle( event.getX(), event.getY(), 
                                                        event.getX(), event.getY(), currentShapeColor, currentShapeFilled, currentShapeStroked);
                    break;
                case 2:
                    currentShapeObject= new MyOval( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled, currentShapeStroked);            	
                    break;

                    
            }
	        if(img!=null) {
	        	g1.drawImage(img, getWidth(), getHeight(), null);
	        }
        } 
        

        public void mouseReleased( MouseEvent event )
        {

            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            myShapes.addFront(currentShapeObject); 
            
            currentShapeObject=null; 
            clearedShapes.makeEmpty(); 
            repaint();
            
        } 
        
        
        

        
        public void mouseMoved( MouseEvent event )
        {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
        } 

        
        
        
        
        public void mouseDragged( MouseEvent event )
        {
           
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
            
            repaint();
            
        } 
    }
    
} 