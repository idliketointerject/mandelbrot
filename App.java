import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Alexander Smith && Nicholas Davis: alexander_smith@umail.ucsb.edu, ndavis@umail.ucsb.edu
 */
public class App extends JFrame
{
    private static final int NUM_PIXELS = 600;
    private Model model = new Model( NUM_PIXELS );
    
    private ImageJPanel imageJPanel = new ImageJPanel( model );
    private ModelJPanel modelJPanel = new ModelJPanel( model, imageJPanel );
                
    App() 
    {
        setTitle( "Mandelbrot Set Visualization" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        imageJPanel.setModelJPanel( modelJPanel );
                
        add( imageJPanel, BorderLayout.CENTER );
        add( modelJPanel, BorderLayout.SOUTH );
        
        setSize( NUM_PIXELS, NUM_PIXELS + 230 );
        setVisible( true );
    }
    
    /**
     * Run the Mandelbrot set visualization application.
     * @param args unused 
     */
    public static void main( String[] args ) { App app = new App(); }
}