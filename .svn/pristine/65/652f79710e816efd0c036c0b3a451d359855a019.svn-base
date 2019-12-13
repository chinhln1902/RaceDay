/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

/**
 * contain all participant objects.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class Participant {
    /**
     * seperate part of racer info.
     */
    private static String COLON = ":";
   
    /**
     * represent racer id.
     */
    private String myRacerID;
    
    /**
     * represent racer name.
     */
    private String myRacerName;
    
    /**
     * represent racer distance.
     */
    private String myRacerDistance;
   
    
    /**
     * Constructor for creating the participant object.
     * @param theRacerID racer id.
     * @param theRacerName racer name.
     * @param theRacerDistance racer distance.
     */
    public Participant(final String theRacerID, 
                       final String theRacerName, 
                       final String theRacerDistance) {
        myRacerID = theRacerID;
        myRacerName = theRacerName;
        myRacerDistance = theRacerDistance;
    }
    
    /**
     * method which returning racer id.
     * @return racer id.
     */
    public String getRacerID() {
        return myRacerID;
    }
    
    /**
     * method which returning racer name.
     * @return racer name.
     */
    public String getRacerName() {
        return myRacerName;
    }
    
    /**
     * method which returning racer distance.
     * @return racer distance.
     */
    public String getRacerDistance() {
        return myRacerDistance;
    }
    
    /**
     * combine all the parts of message to a string.
     * @return a string of participant message.
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(myRacerID);
        sb.append(COLON);
        sb.append(myRacerName);
        sb.append(COLON);
        sb.append(myRacerDistance);
        
        return sb.toString();
    }
    
 
}
