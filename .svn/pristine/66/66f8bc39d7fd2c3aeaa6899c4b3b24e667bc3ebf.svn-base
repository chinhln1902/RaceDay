/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package model;

/**
 * Contain Abstract Messages with 3 different types.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public abstract class AbstractMessage implements Message {
    
    /**
     * 
     */
    private String myTimeStamp;
    
    /**
     * 
     */
    private String myType;
    
    /**
     * the constructor of abstract message.
     * @param theTimeStamp time stamp of message.
     * @param theType type of message.
     */
    public AbstractMessage(final String theTimeStamp, final String theType) {
        myTimeStamp = theTimeStamp;
        myType = theType;
    }

    @Override
    public String getTimeStamp() {
        return myTimeStamp;
    }
    
    @Override
    public String toString() {
        return "This is Abstract Message class";
    }

    @Override
    public String getType() {
        return myType;
    }
}
