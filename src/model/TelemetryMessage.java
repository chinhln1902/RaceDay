/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

/**
 * Contain Telemetry Message object.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class TelemetryMessage extends AbstractMessage {
    /**
     * 
     */
    private static String COLON = ":";
    
    /**
     * 
     */
    private static String TELEMETRY_TYPE = "$T";
    
    /**
     * 
     */
    private String myRaceID;
    
    /**
     * 
     */
    private String myDistance;
    
    /**
     * 
     */
    private String myLap;
    

    
    /**
     * constructor creating telemetry message.
     * @param theTimeStamp time stamp
     * @param theRaceID race id
     * @param theDistance distance
     * @param theLap lap
     */
    public TelemetryMessage(final String theTimeStamp, 
                            final String theRaceID, 
                            final String theDistance, 
                            final String theLap) {
        super(theTimeStamp, TELEMETRY_TYPE);
        myRaceID = theRaceID;
        myDistance = theDistance;
        myLap = theLap;
    }
    
    /**
     * return racer id.
     * @return racer id
     */
    public String getRaceID() {
        return myRaceID;
    }
    
    /**
     * return distance.
     * @return distance
     */
    public String getDistance() {
        return myDistance;
    }
    
    /**
     * return the lap.
     * @return lap
     */
    public String getLap() {
        return myLap;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(TELEMETRY_TYPE);
        sb.append(COLON);
        sb.append(getTimeStamp());
        sb.append(COLON);
        sb.append(myRaceID);
        sb.append(COLON);
        sb.append(myDistance);
        sb.append(COLON);
        sb.append(myLap);
        
        return sb.toString();
    }
}
