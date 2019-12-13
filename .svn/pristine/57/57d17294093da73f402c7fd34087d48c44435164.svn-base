/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * 
 * @author chinh
 * @version 11/18/2018
 */
public class NoneToggleAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = 8173117560174606146L;
    /** A flag for the toggle. */
    private boolean myFlag;
    
    /** The text to use when the toggle is true. */
    private final String myText;
    
    
    /** The icon to use when the toggle is true. */
    private final String myIcon;
    
    
    /** The behavior to run when the toggle is true. */
    private final Runnable myAction;
    
    /**
     * Constructor to create a none toggle action.
     * @param theText name of action
     * @param theIcon icon of action
     * @param theAction the method that action will run
     */
    public NoneToggleAction(final String theText,
                        final String theIcon,
                        final Runnable theAction) {
        super(theText);
        myText = theText;
        myIcon = theIcon;
        myAction = theAction;
        
        setIcon(new ImageIcon(myIcon));
        
        myFlag = false;
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myAction.run();
        putValue(Action.NAME, myText);
        setIcon(new ImageIcon(myIcon));
        myFlag = !myFlag;
    }
    /**
     * Helper to set the Icon to both the Large and Small Icon values. 
     * @param theIcon the icon to set for this Action 
     */
    private void setIcon(final ImageIcon theIcon) {
        final ImageIcon icon = (ImageIcon) theIcon;
        final Image largeImage =
            icon.getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon largeIcon = new ImageIcon(largeImage);
        putValue(Action.LARGE_ICON_KEY, largeIcon);
        
        final Image smallImage =
            icon.getImage().getScaledInstance(12, -1, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon smallIcon = new ImageIcon(smallImage);
        putValue(Action.SMALL_ICON, smallIcon);
    }
}
