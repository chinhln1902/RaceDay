/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * contain all participant objects with their shape.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class ParticipantShape {
    
    /**
     * Racer ID of the shape participant.
     */
    private String myRacerID;
    
    /**
     * 
     */
    private String myRacerName;
    
    /**
     * The shape of participant.
     */
    private Ellipse2D myRacerShape;
    
    /**
     * The corlor of participant.
     */
    private Color myRacerColor;
    
    /**
     * Constructor to create a participant with their own shape and color.
     * @param theRacerID racer id
     * @param theRacerShape racer circle
     * @param theName racer name
     */
    public ParticipantShape(final String theRacerID, 
                            final Ellipse2D theRacerShape, 
                            final String theName) {
        myRacerID = theRacerID;
        myRacerShape = theRacerShape;
        myRacerName = theName;
        myRacerColor = createRandomColor();
    }
    
    /**
     * return the name of racer.
     * @return racer name
     */
    public String getRacerName() {
        return myRacerName;
    }
    
    /**
     * return the id of racer.
     * @return racer id
     */
    public String getRacerID() {
        return myRacerID;
    }
    
    /**
     * set the shape of participant.
     * @param theShape the circle
     */
    public void setRacerShape(final Ellipse2D theShape) {
        myRacerShape = theShape;
    }
    
    /**
     * return the shape of racer.
     * @return the racer's shape
     */
    public Ellipse2D getRacerShape() {
        return myRacerShape;
    }
    
    /**
     * return the color of racer.
     * @return the racer's color.
     */
    public Color getColor() {
        return myRacerColor;
    }
    
    /**
     * 
     * @return a random color
     */
    private Color createRandomColor() {
        final Random rand = new Random();
        final int r = rand.nextInt(256);
        final int g = rand.nextInt(256);
        final int b = rand.nextInt(256);
        final Color randomColor = new Color(r, g, b);
        return randomColor.brighter();
    }
    
}
