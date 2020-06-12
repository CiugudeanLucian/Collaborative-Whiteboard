import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;



public class DrawFrame extends JFrame
{
    private JLabel stausLabel; 
    private DrawPanel panel; 
	private JFileChooser fileChooser;
	private File file;
    private JButton undo; 
    private JButton redo; 
    private JButton clear; 
    private JButton color;
    private JButton save;
    private JButton load;
    private JButton paint;
    private JComboBox colors; 
    
    
    private String colorOptions[]=
    {"Black","Blue","Cyan","Dark Gray","Gray","Green","Light Gray",
        "Magenta","Orange","Pink","Red","White","Yellow"};
    
    
    private Color colorArray[]=
    {Color.BLACK , Color.BLUE , Color.CYAN , Color.darkGray , Color.GRAY , 
        Color.GREEN, Color.lightGray , Color.MAGENTA , Color.ORANGE , 
    Color.PINK , Color.RED , Color.WHITE , Color.YELLOW};
    
    private JComboBox shapes; 
    

    private String shapeOptions[]=
    {"Line","Rectangle","Oval"};
    
    private JCheckBox filled; 
    private JCheckBox stroke;
    private JPanel widgetJPanel; //stocheaza butoanele si comobox-urile
    private JPanel widgetPadder; //stocheaza widgetJPanel
    

    
    
    
    public DrawFrame()
    {
        super("Paint-Ciugudean Lucian-Mihai"); 
        
        JLabel statusLabel = new JLabel( "" ); 
        
        panel = new DrawPanel(statusLabel); 
        
        
        undo = new JButton( "Undo" );
        redo = new JButton( "Redo" );
        clear = new JButton( "Clear" );
        color = new JButton("Pick Color");
        save = new JButton("Save");
        load = new JButton("Load");
        paint = new JButton("Paint");
        
        
        colors = new JComboBox( colorOptions );
        shapes = new JComboBox( shapeOptions );

        
        
        filled = new JCheckBox( "Filled" );
        stroke = new JCheckBox("Stroke(10)");


        
        widgetJPanel = new JPanel();
        
        
        
        widgetJPanel.setLayout( new GridLayout( 1, 6, 10, 10 ) ); //spatiu intre widgets 
        widgetJPanel.setBackground(new Color(125,141,234));


        widgetPadder = new JPanel();
        widgetPadder.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10)); //spatiu in afara
        widgetPadder.setBackground(new Color(125,141,234));
        
        
        
        widgetJPanel.add( undo );
        widgetJPanel.add( redo );
        widgetJPanel.add( clear );
        widgetJPanel.add( colors );
        widgetJPanel.add( shapes );
        widgetJPanel.add(color);
        widgetJPanel.add( filled );
        widgetJPanel.add(stroke);
        widgetJPanel.add(paint);
        widgetJPanel.add(save);
        widgetJPanel.add(load);
        
        
        
        widgetPadder.add( widgetJPanel );
        
        
        
        add( widgetPadder, BorderLayout.NORTH);
        add( panel, BorderLayout.CENTER);
        
        
        ButtonHandler buttonHandler = new ButtonHandler();
        undo.addActionListener( buttonHandler );
        redo.addActionListener( buttonHandler );
        clear.addActionListener( buttonHandler );
        color.addActionListener(buttonHandler);
        save.addActionListener(buttonHandler);
        load.addActionListener(buttonHandler);
        paint.addActionListener(buttonHandler);
        
       
        ItemListenerHandler handler = new ItemListenerHandler();
        colors.addItemListener( handler );
        shapes.addItemListener( handler );
        filled.addItemListener( handler );
        stroke.addItemListener( handler );
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        setSize( 1300, 800 );
        setVisible( true );
        
        

    } 
    

    
    
    
    private class ButtonHandler implements ActionListener
    {
        
    	
        public void actionPerformed( ActionEvent event )
        {
            if (event.getActionCommand().equals("Undo")){
                panel.clearLastShape();
            }
            else if (event.getActionCommand().equals("Redo")){
                panel.redoLastShape();
            }
            else if (event.getActionCommand().equals("Clear")){
                panel.clearDrawing();
            }
            else if(event.getActionCommand().equals("Pick Color")) {
            	panel.PickColor();
            }
            else if(event.getActionCommand().equals("Paint")) {
            	panel.Paint();
            }
            else if(event.getActionCommand().equals("Save")) {
            	fileChooser = new JFileChooser();
            	if (fileChooser.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
            	panel.save(file);
            	}
            }
            else if(event.getActionCommand().equals("Load")) {
            	fileChooser = new JFileChooser();
            	if (fileChooser.showOpenDialog(load) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
            	panel.load(file);
            	}
            }
    }
    }
    

    private class ItemListenerHandler implements ItemListener
    {
        public void itemStateChanged( ItemEvent event )
        {
           
            if ( event.getSource() == filled )
            {
                boolean checkFill=filled.isSelected() ? true : false; //
                panel.setCurrentShapeFilled(checkFill);
            }
            
            if ( event.getSource() == stroke )
            {
                boolean checkStroke=stroke.isSelected() ? true : false; //
                panel.setCurrentShapeStroked(checkStroke);
            }

            
            
            if ( event.getStateChange() == ItemEvent.SELECTED )
            {
                
                if ( event.getSource() == colors)
                {
                    panel.setCurrentShapeColor
                        (colorArray[colors.getSelectedIndex()]);
                }
                
                
                else if ( event.getSource() == shapes)
                {
                    panel.setCurrentShapeType(shapes.getSelectedIndex());
                }
            }
            
        } 
    }
    
}