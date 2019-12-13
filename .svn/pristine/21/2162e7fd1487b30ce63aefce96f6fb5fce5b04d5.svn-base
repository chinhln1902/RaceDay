/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

/**
 * Contain Line Crossing Message object.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class LineCrossingMessage extends AbstractMessage {
    
    /**
     * 
     */
    private static String LINECROSSING_TYPE = "$C";
    /**
     * 
     */
    private static String COLON = ":";
    
    /**
     * 
     */
    private String myRaceID;
    
    /**
     * 
     */
    private String myNewLap;
    
    /**
     * 
     */
    private boolean myIsFinished;

    /**
     * Constructor for creating line crossing message object.
     * @param theTimeStamp the time stamp of message.
     * @param theRaceID racer id.
     * @param theNewLap new lap.
     * @param theIsFinished state of the race.
     */
    public LineCrossingMessage(final String theTimeStamp, 
                               final String theRaceID, 
                               final String theNewLap, 
                               final boolean theIsFinished) {
        super(theTimeStamp, LINECROSSING_TYPE);
        myRaceID = theRaceID;
        myNewLap = theNewLap;
        myIsFinished = theIsFinished;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(LINECROSSING_TYPE);
        sb.append(COLON);
        sb.append(getTimeStamp());
        sb.append(COLON);
        sb.append(myRaceID);
        sb.append(COLON);
        sb.append(myNewLap);
        sb.append(COLON);
        sb.append(myIsFinished);
        
        return sb.toString();
    }
}
