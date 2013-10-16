import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.StringBuilder;

/**
 * A model of a region of the complex plane for viewing the Mandelbrot set elements within it.
 * 
 * @author Alexander Smith && Nicholas Davis: alexander_smith@umail.ucsb.edu, ndavis@umail.ucsb.edu
 */
final class Model
{
    // default attribute values
    private static final double CENTER_X = -0.7440975859375;
    private static final double CENTER_Y = 0.1385680625;
    private static final double EDGE_LENGTH = 0.01611;
    private static final int    ITERATION_LIMIT = 512;
    
    // constants used in getIterationCount method
    private static final double ONE_FOURTH = 1.0 / 4.0;
    private static final double ONE_SIXTEENTH = ONE_FOURTH * ONE_FOURTH;

    // model attributes initialized to default values 
    // Note: The region is characterized by its center point.
    private double centerReal = CENTER_X;
    private double centerImag = CENTER_Y;
    private double edgeLength = EDGE_LENGTH;
    private int    iterationLimit = ITERATION_LIMIT;
    private double scalar;
    
    // computed model attribute
    private BufferedImage bufferedImage;
    
    // view attribute
    private final int numPixels;

    Model( int numPixels )
    {
        this.numPixels = numPixels;
        scalar = edgeLength / numPixels;
        
        // initialize the BufferedImage
        bufferedImage = new BufferedImage(numPixels, numPixels, BufferedImage.TYPE_INT_ARGB);

    }
    
    // get methods are here.
    public int getIterationLimit(){
    	return iterationLimit;
    }
    public double getCenterImag(){
    	return centerImag;
    }
    public double getCenterReal(){
    	return centerReal;
    }
    public int getNumPixels(){
    	return numPixels;
    }
    public BufferedImage getBufferedImage(){
    	return bufferedImage;
    }
    public double getEdgeLength(){
    	return edgeLength;
    }
    
    // set methods are here. Each method recomputes the BufferedImage.

    public void setEdgeLength(double edgeLength){
    	this.edgeLength = edgeLength;
    	setImage();
    }
    public void setIterationLimit(int iterationLimit){
    	this.iterationLimit = iterationLimit;
    	setImage();
    }
    public void setCenterImag(double centerImag){
    	this.centerImag = centerImag;
    	setImage();
    }
    public void setCenterReal(double centerReal){
    	this.centerReal = centerReal;
    	setImage();
    }
    public void setBufferedImage(BufferedImage bufferedImage){
    	this.bufferedImage = bufferedImage;
    	setImage();
    }
    
    void setImage()
    {
    	scalar = edgeLength/numPixels;
        bufferedImage = new BufferedImage( numPixels, numPixels, BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = bufferedImage.getGraphics();
        int row,col;
        for(row = 0; row<numPixels; row++){
        	for(col = 0; col<numPixels; col++){
        		graphics.setColor(getColor(getIterationCount(scaleX(row), scaleY(col))));
                graphics.fillRect(row, col, 1, 1);
        	}
        }
        // constructs a new BufferedImage of the appropriate size and gets it's Graphics 
        // uses the Graphics to draw the image as was done in the previous assignments
    }

    void recenter( int X, int Y )
    {
        /* Recenters the region based on the pixel coordinates X and Y.*/
    	
    	if (X > numPixels/2)
    	{
    		centerReal = centerReal + scalar*(X - numPixels/2);
    	}
    	else
    	{
    		centerReal = centerReal - (edgeLength/numPixels)*(numPixels/2 - X);
    	}
    	if (Y > numPixels/2)
    	{
    		centerImag = centerImag - scalar*(Y - numPixels/2);
    	}
    	else
    	{
    		centerImag = centerImag + scalar*(numPixels/2 - Y);
    	}
    	edgeLength = edgeLength/2;
    	setImage();
         
        // zoom in: halve the edge length.
        
        // recomputes the BufferedImage
    }

    
    private int getIterationCount( double x0, double y0 )
    {
        double x = x0;
        double y = y0;

        /* The 2 quick checks below (cardoid and period-2 bulb) see if the point is in
         * a known region of the Mandelbrot set, which may speed up the overall computation.
         */
         
        // in cardoid ?
        double xMinusOneFourth = x - ONE_FOURTH;
        double ySquared = y * y;
        double q = xMinusOneFourth * xMinusOneFourth + ySquared;
        if ( q * ( q + xMinusOneFourth ) < ONE_FOURTH * ySquared )
        {
            return iterationLimit; 
        }

        // in period-2 bulb ?
        double xPlusOne = x + 1.0;
        if ( xPlusOne * xPlusOne + ySquared < ONE_SIXTEENTH )
        {
            return iterationLimit; 
        }

        // perform typical iterationCount computation
        int iterationCount = 0;
        for ( double xtemp ; x*x + y*y <= 4.0 && iterationCount < iterationLimit; iterationCount++ )
        {
            xtemp = x*x - y*y + x0;
            y = 2*x*y + y0;
            x = xtemp;
        }
        return iterationCount;
    }
    
    private double scaleX(int x)
    {
    	double bottomX = centerReal - edgeLength/2;
    	return (x*scalar + bottomX);
    }
    
    private double scaleY(int y)
    {
    	double bottomY = centerImag - edgeLength/2;
    	return (edgeLength - y*scalar + bottomY);
    }

    private Color getColor( int iterationCount )
    {
        return Utility.getColor( iterationCount, iterationLimit );
    }
    
    void reset()
    {
    	centerReal = CENTER_X;
    	centerImag = CENTER_Y;
    	edgeLength = EDGE_LENGTH;
    	iterationLimit = ITERATION_LIMIT;
    	scalar = edgeLength/numPixels;
    	setImage();
        // resets attributes to default value
        // recomputes the BufferedImage
    }

    @Override
    public String toString()
    {
    	StringBuilder result = new StringBuilder();
    	
    	result.append(centerReal + " ");
    	result.append(edgeLength + " ");
    	result.append(iterationLimit + " ");
    	result.append(numPixels);
    	String res = result.toString();
    	return res;
        // use StringBuilder to create a String version of object attribute values:
        // the center point, edge length, iteration limit and number of pixels. 
    }
}