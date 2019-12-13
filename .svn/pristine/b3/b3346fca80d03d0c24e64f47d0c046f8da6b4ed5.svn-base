/*
 * TCSS 305 - Race Day
 * Assignment 5
 */

package model;

import controller.RaceController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Contain Action Listener interact with GUI.
 * 
 * @author Chinh Le
 * @version 11/08/2018
 */
public class RaceModel implements PropertyChangeEnabledRaceControls {

    /**
     * 
     */
    public static final String ERROR_NOTIFICATION = "Error!";
    
    /**
     * 
     */
    public static final int RACE_ID_LOCATION = 2;
    
    /**
     * 
     */
    public static final int DISTANCE_LOCATION = 3;
    
    /**
     * 
     */
    public static final int NEW_LAP_LOCATION = 3;
    
    /**
     * 
     */
    public static final int LAP_LOCATION = 4;
    
    /**
     * 
     */
    public static final int IFINISHED_LOCATION = 4;
    
    /**
     * 
     */
    public static final int CHECK_TIMES = 7;

    /**
     * 
     */
    public static final String RACE_STARTWITH = "#RACE";

    /**
     * 
     */
    public static final String TRACK_STARTWITH = "#TRACK";

    /**
     * 
     */
    public static final String WIDTH_STARTWITH = "#WIDTH";

    /**
     * 
     */
    public static final String HEIGHT_STARTWITH = "#HEIGHT";

    /**
     * 
     */
    public static final String DISTANCE_STARTWITH = "#DISTANCE";

    /**
     * 
     */
    public static final String TIME_STARTWITH = "#TIME";

    /**
     * 
     */
    public static final String PARTICIPANTS_STARTWITH = "#PARTICIPANTS";

    /**
     * 
     */
    public static final String PARTICIPANT_STARTWITH = "#([0-9]){1,2}";

    /**
     * 
     */
    public static final String TELEMETRY_STARTWITH = "$T";

    /**
     *
     */
    public static final String LINE_CROSSING_STARTWITH = "$C";
    /**
     * 
     */
    public static final String LEADER_STARTWITH = "$L";

    /**
     * 
     */
    public static final String LEADER_TIMESTAMP = "\\d+";

    /**
     * 
     */
    public static final String COLON = ":";

    /**
     * 
     */
    public static final String LEADER_RACEID = "([0-9]){1,3}";

    /**
     * 
     */
    public static final String RACE_FORMAT = "#RACE:.*";

    /**
     * 
     */
    public static final String TRACK_FORMAT = "#TRACK:\\D+";

    /**
     * 
     */
    public static final String WIDTH_FORMAT = "#WIDTH:[0-9]";

    /**
     * 
     */
    public static final String HEIGHT_FORMAT = "#HEIGHT:[0-9]";

    /**
     * 
     */
    public static final String DISTANCE_FORMAT = "#DISTANCE:\\d+";

    /**
     * 
     */
    public static final String TIME_FORMAT = "#TIME:\\d+";

    /**
     * 
     */
    public static final String PARTICIPANTS_FORMAT = "#PARTICIPANTS:\\d+";

    /**
     * 
     */
    public static final String PARTICIPANT_FORMAT = "#[0-9][0-9]:\\D+:-?\\d+.\\d+";

    /**
     * 
     */
    public static final String TELEMETRY_FORMAT =
                    "\\$T:\\d+:([0-9]){1,3}:-?\\d+\\.([0-9]){1,2}:([0-9]){1,2}";

    /**
     * 
     */
    public static final String LINE_CROSSING_FORMAT =
                    "\\$C:\\d+:\\d+{1,2}:\\d+{1,2}:(true|false)";
    
    /**
     * 
     */
    public static final String ZERO = "0";
    /**
     * 
     */
    public static final int HEADERS_SIZE = 7;

    /**
     * 
     */
    private int myTimeTotal;

    
    /**
     * 
     */
    private String myTempTimeStamp = ZERO;

    /**
     * 
     */
    private int myCounterNumberOfMessages;

    /**
     * 
     */
    private int myTime;

    /**
     * Manager for Property Change Listeners.
     */
    private final PropertyChangeSupport myPcs;

    /**
     * 
     */
    private List<String> myCheckHeaderList;

    /**
     * 
     */
    private List<String> myHeaderList;

    /**
     * 
     */
    private List<Participant> myParticipants;

    /**
     * 
     */
    private List<List<Message>> myMessageList;

    /**
     * 
     */
    private List<String> myRaceInfoList;

    /**
     * 
     */
    private List<String> myUpdatedMessageList;

    /**
     * 
     */
    private int myOldTimeStamp;

    /**
     * 
     */
    private boolean myFileLoadedSuccessful;

    /**
     * false when the timer is running. Otherwise, true.
     */
    private boolean myTimerEndFlag;
    
    /**
     * 
     */
    private boolean myMoveToFlag;
    /**
     * 
     */
    private List<String> myFilterParticipantList;
    
    /**
     * 
     */
    private RaceTrack myTrack;
    
    /**
     * constructor create all variable and lists.
     */
    public RaceModel() {
        myPcs = new PropertyChangeSupport(this);
        initialVariables();
        createCheckHeaderFile();
    }
    
    /**
     * declare all variable.
     */
    private void initialVariables() {
        myCheckHeaderList = new ArrayList<>();
        myRaceInfoList = new ArrayList<String>();
        myHeaderList = new ArrayList<>();
        myParticipants = new ArrayList<Participant>();
        myMessageList = new ArrayList<List<Message>>();
        myUpdatedMessageList = new ArrayList<String>();
        myTimerEndFlag = false;
        myMoveToFlag = false;
        myFilterParticipantList = new ArrayList<String>();
    }
    @Override
    public void advance() {
        advance(1);
    }

    @Override
    public void advance(final int theMillisecond) {
        changeTime(myTime + theMillisecond);
        if (myOldTimeStamp >= myTimeTotal) {
            myTimerEndFlag = true;
            myPcs.firePropertyChange(PROPERTY_TIMER_END, null, myTimerEndFlag);
            myOldTimeStamp = 0;
        } else {
            getMessage(myTime);
        }
    }

    /**
     * show all the message at the current time stamp.
     * @param theNewTimeStamp the current time stamp.
     */
    private void getMessage(final int theNewTimeStamp) {
        if (myOldTimeStamp > theNewTimeStamp) {
            myOldTimeStamp = 0;
        }
        for (int i = myOldTimeStamp; i <= theNewTimeStamp; i++) {
            for (final Message a : myMessageList.get(i)) {
                if (a.getType().equals(TELEMETRY_STARTWITH)) {
                    final TelemetryMessage telemetry = (TelemetryMessage) a;
                    if (myFilterParticipantList.isEmpty()) {
                        myPcs.firePropertyChange(PROPERTY_TEXT_AREA_MESSAGES, null, a);
                        myPcs.firePropertyChange(PROPERTY_MOVE_RACER, null, a);
                    } else {
                        if (!myFilterParticipantList.contains(telemetry.getRaceID())) {
                            myPcs.firePropertyChange(PROPERTY_TEXT_AREA_MESSAGES, null, a);
                            myPcs.firePropertyChange(PROPERTY_MOVE_TO_MESSAGE, null, a);
                            myPcs.firePropertyChange(PROPERTY_MOVE_RACER, null, a);
                        }
                    }
                }  else {
                    if (a.getType().equals(LEADER_STARTWITH)) {
                        myPcs.firePropertyChange(PROPERTY_LEADER_MESSAGE, null, a);
                    }
                    myPcs.firePropertyChange(PROPERTY_TEXT_AREA_MESSAGES, null, a);
                }

            }
        }
        myOldTimeStamp = theNewTimeStamp;

    }

    @Override
    public void toggleParticipant(final int theParticpantID, final boolean theToggle) {

        final String participantID = String.valueOf(theParticpantID);
        if (theToggle) {
            for (int i = 0; i < myFilterParticipantList.size(); i++) {
                if (participantID.equals(myFilterParticipantList.get(i))) {
                    myFilterParticipantList.remove(i);
                }
            }
        } else {
            if (!myFilterParticipantList.contains(participantID)) {
                myFilterParticipantList.add(participantID);
            }
        }
    }

    /**
     * change the time of timer.
     * @param theMillisecond current time.
     */
    private void changeTime(final int theMillisecond) {
        final int old = myTime;
        myTime = theMillisecond;
        myPcs.firePropertyChange(PROPERTY_TIME, old, myTime);
        myPcs.firePropertyChange(PROPERTY_SLIDER_MOVE_TO, old, myTime);
    }

    @Override
    public void moveTo(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException(ERROR_NOTIFICATION);
        }
        changeTime(theMillisecond);
        if (myOldTimeStamp != 0 || myMoveToFlag) {
            getMessage(theMillisecond);
            if (myOldTimeStamp == 0) {
                myMoveToFlag = true;
            }
        }
    }
    
    /**
     * 
     */
    private void reInitialVariables() {
        myRaceInfoList = new ArrayList<String>();
        myUpdatedMessageList = new ArrayList<String>();
        myParticipants = new ArrayList<Participant>();
        myMessageList = new ArrayList<List<Message>>();
        myTimerEndFlag = false;
        myCounterNumberOfMessages = 0;
        myTempTimeStamp = ZERO;
    }
    @Override
    public void loadRace(final File theRaceFile) throws IOException {
        
        reInitialVariables();

        final FileReader reader = new FileReader(theRaceFile);
        final Scanner input = new Scanner(theRaceFile);
        if (reader.read() == -1) {
            JOptionPane.showMessageDialog(new JPanel(), "The file is empty!", "Warning!",
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            getHeaderOfRaceFile(theRaceFile);
            getParticipants(theRaceFile);
            while (input.hasNextLine()) {
                final String currentLine = input.nextLine();
                if (checkHeaderIsDeleted(currentLine) && checkRaceFile(currentLine)) {

                    getMessageList(currentLine);
                    myFileLoadedSuccessful = true;

                } else {
                    JOptionPane.showMessageDialog(new JPanel(),
                                                  "The selected file is in wrong format", 
                                                  ERROR_NOTIFICATION,
                                                  JOptionPane.ERROR_MESSAGE);
                    myFileLoadedSuccessful = false;
                    break;
                }
            }
            if (myFileLoadedSuccessful) {
                showUpdatedMessages();
            }
            input.close();
            myPcs.firePropertyChange(PROPERTY_PARTICIPANT_LIST, null, myParticipants);
            myPcs.firePropertyChange(PROPERTY_RACE_INFO, null, myRaceInfoList);
            myPcs.firePropertyChange(PROPERTY_SET_MAXIMUM_SLIDER, null, myTimeTotal);
        }
        reader.close();
    }

    /**
     * get all the messages into to list.
     * @param theCurrentLine current line of race file
     */
    private void getMessageList(final String theCurrentLine) {
        final String[] tempString = theCurrentLine.split(COLON);
        final String timeStamp = tempString[1];
        if (theCurrentLine.startsWith(TELEMETRY_STARTWITH)) {
            checkSimilarTimeStamp(timeStamp, myTempTimeStamp);

            myTempTimeStamp = timeStamp;
            myMessageList.add(new ArrayList<Message>());
            final String raceID = tempString[RACE_ID_LOCATION];
            final String distance = tempString[DISTANCE_LOCATION];
            final String lap = tempString[LAP_LOCATION];
            final TelemetryMessage telMessage =
                            new TelemetryMessage(timeStamp, raceID, distance, lap);
            new TelemetryMessage(timeStamp, raceID, distance, lap);
            myMessageList.get(myCounterNumberOfMessages).add(telMessage);
        }

        if (theCurrentLine.startsWith(LEADER_STARTWITH)) {
            checkSimilarTimeStamp(timeStamp, myTempTimeStamp);
            myTempTimeStamp = timeStamp;
            final List<String> raceIDs = new ArrayList<String>();
            myMessageList.add(new ArrayList<Message>());
            for (int i = 1; i < tempString.length; i++) {
                raceIDs.add(tempString[i]);
            }
            final LeaderBoardMessage leadMessage = new LeaderBoardMessage(timeStamp, raceIDs);
            new LeaderBoardMessage(timeStamp, raceIDs);
            myMessageList.get(myCounterNumberOfMessages).add(leadMessage);
        }

        if (theCurrentLine.startsWith(LINE_CROSSING_STARTWITH)) {
            checkSimilarTimeStamp(timeStamp, myTempTimeStamp);
            myTempTimeStamp = timeStamp;
            myMessageList.add(new ArrayList<Message>());
            final String raceID = tempString[RACE_ID_LOCATION];
            final String newLap = tempString[NEW_LAP_LOCATION];
            final boolean isFinished = Boolean.valueOf(tempString[IFINISHED_LOCATION]);

            final LineCrossingMessage lineMessage =
                            new LineCrossingMessage(timeStamp, raceID, newLap, isFinished);
            new LineCrossingMessage(timeStamp, raceID, newLap, isFinished);
            myMessageList.get(myCounterNumberOfMessages).add(lineMessage);
        }
    }

    /**
     * check if the same time stamp is repeating, if not increase the number of messages.
     * @param theCurrentTimeStamp current time stamp
     * @param theOldTimeStamp the old time stamp
     */
    private void checkSimilarTimeStamp(final String theCurrentTimeStamp,
                                       final String theOldTimeStamp) {
        if (!theCurrentTimeStamp.equals(theOldTimeStamp)) {
            myCounterNumberOfMessages++;
        }
    }

    /**
     * 
     * @param theLine the current line of the race file.
     * @return the status of the race file.
     */
    private boolean checkRaceFile(final String theLine) {
        boolean checkState = true;
        for (int i = 0; i < myCheckHeaderList.size(); i++) {
            if (!theLine.matches(myCheckHeaderList.get(i))) {
                checkState = false;
            } else {
                checkState = true;
                break;
            }
        }
        final String[] array = theLine.split(COLON);
        if (theLine.startsWith(LEADER_STARTWITH)) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].startsWith(LEADER_STARTWITH) || array[i].matches(LEADER_RACEID)
                    || array[i].matches(LEADER_TIMESTAMP)) {
                    checkState = true;
                } else {
                    checkState = false;
                    break;
                }
            }
        }
        return checkState;
    }

    /**
     * 
     */
    private void createCheckHeaderFile() {
        myCheckHeaderList.add(RACE_FORMAT);
        myCheckHeaderList.add(TRACK_FORMAT);
        myCheckHeaderList.add(WIDTH_FORMAT);
        myCheckHeaderList.add(HEIGHT_FORMAT);
        myCheckHeaderList.add(DISTANCE_FORMAT);
        myCheckHeaderList.add(TIME_FORMAT);
        myCheckHeaderList.add(PARTICIPANTS_FORMAT);
        myCheckHeaderList.add(PARTICIPANT_FORMAT);
        myCheckHeaderList.add(TELEMETRY_FORMAT);
        myCheckHeaderList.add(LINE_CROSSING_FORMAT);
    }

    /**
     * Check if any missing header in race file.
     * 
     * @param theLine current line of the file
     * @return the status of header.
     */
    private boolean checkHeaderIsDeleted(final String theLine) {
        final int firstLine = 0;
        final int secondLine = 1;
        final int thirdLine = 2;
        final int fourthLine = 3;
        final int fifthLine = 4;
        final int sixthLine = 5;
        final int seventhLine = 6;
        boolean isValid = false;

        if (myHeaderList.get(firstLine).startsWith(RACE_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(secondLine).startsWith(TRACK_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(thirdLine).startsWith(WIDTH_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(fourthLine).startsWith(HEIGHT_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(fifthLine).startsWith(DISTANCE_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(sixthLine).startsWith(TIME_STARTWITH)) {
            isValid = true;
        }
        if (myHeaderList.get(seventhLine).startsWith(PARTICIPANTS_STARTWITH)) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * get the header of file.
     * @param theFile the race file
     * @throws IOException exception
     */
    private void getHeaderOfRaceFile(final File theFile) throws IOException {
        int headerCount = 0;
        final Scanner input = new Scanner(theFile);
        String currentLine = "";
        String raceHeight = "";
        String raceWidth = "";
        String raceDistance = "";
        while (input.hasNextLine() && headerCount < HEADERS_SIZE) {
            currentLine = input.nextLine();
            final String[] tempHeader = currentLine.split(COLON);
            if (currentLine.startsWith(RACE_STARTWITH)) {
                myRaceInfoList.add(tempHeader[1]);
            }
            if (currentLine.startsWith(TRACK_STARTWITH)) {
                myRaceInfoList.add("Track type: " + tempHeader[1]);
            }
            
            if (currentLine.startsWith(WIDTH_STARTWITH)) {
                raceWidth = tempHeader[1];
            }
            if (currentLine.startsWith(HEIGHT_STARTWITH)) {
                raceHeight = tempHeader[1];
            }
            
            if (currentLine.startsWith(TIME_STARTWITH)) {
                myRaceInfoList.add("Total time: " + RaceController.
                                   formatTime(Long.valueOf(tempHeader[1])));
                
                myTimeTotal = Integer.valueOf(tempHeader[1]);
            }
            if (currentLine.startsWith(DISTANCE_STARTWITH)) {
                myRaceInfoList.add("Lap distance: " + tempHeader[1]);
                raceDistance = tempHeader[1];
            }
            myHeaderList.add(currentLine);
            headerCount++;
        }
        input.close();
        if (myHeaderList.isEmpty()) {
            throw new IOException("The header is empty!");
        } else {
            myTrack = new RaceTrack(raceWidth, raceHeight, raceDistance);
            myPcs.firePropertyChange(PROPERTY_TRACK_INFO, null, myTrack);
        }
    }

    /**
     * get participants from race file.
     * @param theFile the race file
     * @throws IOException exception
     */
    private void getParticipants(final File theFile) throws IOException {
        int count = 0;
        // Split the participant information
        final String[] participants = myHeaderList.get(myHeaderList.size() - 1).split(COLON);
        String[] participantsInfo = null;
        // Get the mount of participants
        final int participantAmount = Integer.valueOf(participants[1]);
        final Scanner input = new Scanner(theFile);
        String currentLine = "";
        while (input.hasNextLine() && count < participantAmount) {
            currentLine = input.nextLine();
            if (currentLine.matches(PARTICIPANT_FORMAT)) {
                participantsInfo = currentLine.split(COLON);
                // myParticipant = new ArrayList<>();
                final Participant participant =
                                new Participant(participantsInfo[0].substring(1), 
                                                participantsInfo[1],
                                                participantsInfo[2]);
                myParticipants.add(participant);
                count++;
            }
        }
        input.close();
    }
    
    /**
     * return participants list.
     * @return participants list
     */
    public List<Participant> getParticipants() {
        return myParticipants;
    }

    /**
     * 
     */
    private void showUpdatedMessages() {
        myUpdatedMessageList.add(
                                 "Load file: Start - This may take a while."
                                 + " Please be patient.");
        myUpdatedMessageList.add("Load file: Race information loaded.");
        myUpdatedMessageList.add("Load file: Loading telemetry information");
        myUpdatedMessageList.add("Load file: Still loading...");
        myUpdatedMessageList.add("Load file: Complete!");
        myPcs.firePropertyChange(PROPERTY_UPDATE_MESSAGES, null, myUpdatedMessageList);
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);

    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);

    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);

    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);

    }

}
