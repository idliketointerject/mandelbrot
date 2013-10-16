import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;


public class Utility {
	private Image image;
    private static ImageIcon imageIcon;
    private static JLabel jLabel;
    
	static JLabel imageToJLabel( Image x ){
        imageIcon = new ImageIcon( x );
        jLabel = new JLabel(imageIcon);
		return jLabel;
	}
	
    static Color getColor( int iterationCount, int iterationLimit )
    {
    	 // paint the black pixel
        if (iterationCount >= iterationLimit){
        	return Color.black;
        }
        else{
	    float numerator = (float)iterationCount;
	    float denominator = (float)iterationLimit;
	    float fraction = numerator/denominator;
	    Color currentBar = new Color(fraction,(float)0.0,(float)(1.0-fraction));
        	return currentBar;
        	
        }
        // if interation count equals iteration limit, return black
        // Alternatively, if not black, return a color that is a function of iteration count.
    }
	
}
