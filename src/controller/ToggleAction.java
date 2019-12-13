/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * 
 * @author chinh
 * @version 11/18/2018
 */
public class ToggleAction extends AbstractAction implements PropertyChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 8173117560174606146L;
    /** A flag for the toggle. */
    private boolean myFlag;
    
    /** The text to use when the toggle is true. */
    private final String myFirstText;
    
    /** The text to use when the toggle is false. */
    private final String mySecondText;
    
    /** The icon to use when the toggle is true. */
    private final String myFirstIcon;
    
    /** The icon to use when the toggle is false. */
    private final String mySecondIcon;
    
    /** The behavior to run when the toggle is true. */
    private final Runnable myFirstAction;
    
    /** The behavior to run when the toggle is false. */
    private final Runnable mySecondAction;

    /**
     * Constructor to create a toggle action.
     * @param theFirstText name of first action
     * @param theSecondText name of second action
     * @param theFirstIcon icon of first action
     * @param theSecondIcon icon of second action
     * @param theFirstAction the first method that action will run
     * @param theSecondAction the second method that action will run
     */
    public ToggleAction(final String theFirstText,
                        final String theSecondText,
                        final String theFirstIcon,
                        final String theSecondIcon,
                        final Runnable theFirstAction,
                        final Runnable theSecondAction) {
        super(theFirstText);
        myFirstText = theFirstText;
        mySecondText = theSecondText;
        myFirstIcon = theFirstIcon;
        mySecondIcon = theSecondIcon;
        myFirstAction = theFirstAction;
        mySecondAction = theSecondAction;
        
        setIcon(new ImageIcon(theFirstIcon));
        
        myFlag = true;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (myFlag) {
            myFirstAction.run();
            putValue(Action.NAME, mySecondText);
            setIcon(new ImageIcon(mySecondIcon));

        } else {

            mySecondAction.run();
            putValue(Action.NAME, myFirstText);
            setIcon(new ImageIcon(myFirstIcon));
        }
//        myPlayState = !myPlayState;
        myFlag = !myFlag;
    }
    /**
     * Helper to set the Icon to both the Large and Small Icon values. 
     * @param theIcon the icon to set for this Action 
     */
    public void setIcon(final ImageIcon theIcon) {
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
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
//        if (PROPERTY_TIMER_END.equals(theEvt.getPropertyName())) {
//            final boolean timerFlag = (boolean) theEvt.getNewValue();
//            if (timerFlag) {
////                playPauseAction.getFirstAction().run();
////                playPauseAction.putValue(Action.NAME, BUTTON_TEXT_PLAY);
////                playPauseAction.setIcon(new ImageIcon(BUTTON_ICON_PAUSE));
////
////                actionPerformed(null);
//            }
//        }
    }
}
