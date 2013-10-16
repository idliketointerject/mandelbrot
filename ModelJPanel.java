import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Alexander Smith && Nicholas Davis: alexander_smith@umail.ucsb.edu, ndavis@umail.ucsb.edu
 */
public class ModelJPanel extends JPanel
{
    private Model model;
    private ImageJPanel imageJPanel;
    
    private JLabel centerRealLabel = new JLabel( " Real coordinate " );
    private JTextField centerRealTextField = new JTextField( 15 );
    
    private JLabel centerImagLabel = new JLabel( " Imag coordinate " );
    private JTextField centerImagTextField = new JTextField( 15 );
    
    private JLabel edgeLengthLabel = new JLabel( " Edge Length " );
    private JTextField edgeLengthTextField = new JTextField( 15 );
    
    private JLabel iterationLimitLabel = new JLabel( " Iteration Limit " );
    private JTextField iterationLimitTextField = new JTextField( 15 );
    
    private JButton resetButton = new JButton( "Reset");
    private JButton saveButton = new JButton( "Save");
        
    private File imageFolder;
    
    ModelJPanel( Model model, ImageJPanel imageJPanel )
    { 
        // sets attribute values
    	this.model = model;
    	this.imageJPanel = imageJPanel;
        // sets a GridLayout layount manager for this object

        setLayout( new GridLayout( 5, 2 ) );

        // adds the Jlabel, JTextField, and JButton objects to the layout of this JPanal
        add(centerRealLabel);
        add(centerRealTextField);
        add(centerImagLabel);
        add(centerImagTextField);
        add(edgeLengthLabel);
        add(edgeLengthTextField);
        add(iterationLimitLabel);
        add(iterationLimitTextField);
        add(resetButton);
        add(saveButton);
        
        initialize();      
    }
    
    private void initialize()
    {
        //------------------------------------------
        // contoller TEMPLATE CODE for each action
        //------------------------------------------
    	
        centerRealTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                centerRealTextFieldActionPerformed( actionEvent );
            }
        });
        
        edgeLengthTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                edgeLengthTextFieldActionPerformed( actionEvent );
            }
        });
        
        iterationLimitTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                iterationLimitTextFieldActionPerformed( actionEvent );
            }
        });
        
        centerImagTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                centerImagTextFieldActionPerformed( actionEvent );
            }
        });
        
        resetButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                resetButtonActionPerformed( actionEvent );
            }
        });
        
        saveButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                saveMenuItemActionPerformed( actionEvent );
            }
        });
        
        view();
    }
    
    void view()
    {
    	
    	centerRealTextField.setText("" + model.getCenterReal());
    	centerImagTextField.setText("" + model.getCenterImag());
    	iterationLimitTextField.setText("" + model.getIterationLimit());
    	edgeLengthTextField.setText("" + model.getEdgeLength());

    }
    
    // _____________________________
    //  controller for each action
    // _____________________________
    private void centerRealTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( centerRealTextField.getText() );
        
        model.setCenterReal( value );
        imageJPanel.repaint();
    }
    
    private void centerImagTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( centerImagTextField.getText() );
     
        model.setCenterImag( value );
        imageJPanel.repaint();
    }
    
    private void iterationLimitTextFieldActionPerformed( ActionEvent actionEvent )
    {
        int value = Integer.parseInt( iterationLimitTextField.getText() );
        model.setIterationLimit( value );
        imageJPanel.repaint();
    }
    
    private void edgeLengthTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( edgeLengthTextField.getText() );
        model.setEdgeLength( value );
        imageJPanel.repaint();
    }
    
    private void resetButtonActionPerformed( ActionEvent actionEvent )
    {
        // resets the model attribute values
        model.reset();
        // makes them viewable to the user
        imageJPanel.repaint();
        // updates the image in the ImageJPanel to reflect these model attributes
    }
    
    private void saveMenuItemActionPerformed( ActionEvent actionEvent )
    {
        JFileChooser fileChooser = new JFileChooser( imageFolder );
        int returnValue = fileChooser.showDialog( this, "Save");
        if ( returnValue == JFileChooser.APPROVE_OPTION )
        {
            File imageFile = fileChooser.getSelectedFile();
            try
            {
                ImageIO.write( model.getBufferedImage(), "png", imageFile );
            }
            catch ( IOException ioException )
            {
                ioException.printStackTrace();
            }
        }
    }
}