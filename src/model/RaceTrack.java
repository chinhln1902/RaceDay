package model;
/**
 * contain Race Track Information.
 * 
 * @author Chinh Le
 * @version 12/06/2018
 */
public class RaceTrack {
    /**
     * 
     */
    private String myRaceWidth;
    
    /**
     * 
     */
    private String myRaceHeight;
    
    /**
     * 
     */
    private String myRaceDistance;
    
    /**
     * 
     * @param theWidth the width of race track
     * @param theHeight the height of race track
     * @param theDistance the distance of race track
     */
    public RaceTrack(final String theWidth, final String theHeight, final String theDistance) {
        myRaceWidth = theWidth;
        myRaceHeight = theHeight;
        myRaceDistance = theDistance;
    }
    
    /**
     * return the race width.
     * @return the width
     */
    public String getRaceWidth() {
        return myRaceWidth;
    }
    
    /**
     * return the race height.
     * @return the height
     */
    public String getRaceHeight() {
        return myRaceHeight;
    }
    
    /**
     * return the race distance.
     * @return the distance
     */
    public String getRaceDisntace() {
        return myRaceDistance;
    }
    
}
