/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

import java.util.List;

/**
 * Contain LeaderBoard Message object.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class LeaderBoardMessage extends AbstractMessage {
    
    /**
     * seperates each part of messages.
     */
    private static String COLON = ":";
    
    /**
     * the type of leader board message.
     */
    private static String LEADERBOARD_TYPE = "$L";
    

    /**
     * contains list of racers ids.
     */
    private List<String> myRaceIDsList;
    
    /**
     * Constructor for creating leader board message object.
     * @param theTimeStamp the time stamp of message.
     * @param theRaceIDsList list of racer ids.
     */
    public LeaderBoardMessage(final String theTimeStamp, final List<String> theRaceIDsList) {
        super(theTimeStamp, LEADERBOARD_TYPE);
        myRaceIDsList = theRaceIDsList;
    }
    
    /**
     * return the racer id of leader board.
     * @param theRaceID the id which looking for.
     * @return the racer id
     */
    public String getRaceID(final String theRaceID) {
        String raceID = "";
        for (String id : myRaceIDsList) {
            if (theRaceID.equals(id)) {
                raceID = theRaceID;
            }
        }
        return raceID;
    }
    
    /**
     * return the list of racers in leader board.
     * @return the list of racers
     */
    public List<String> getRacerIds() {
        return myRaceIDsList;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(LEADERBOARD_TYPE);
        sb.append(COLON);
        sb.append(getTimeStamp());
        for (String id : myRaceIDsList) {
            sb.append(COLON);
            sb.append(id);
        }
        
        return sb.toString();
    }
}
