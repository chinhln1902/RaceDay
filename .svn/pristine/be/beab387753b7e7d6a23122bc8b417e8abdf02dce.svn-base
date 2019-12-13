/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_LEADER_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANT_LIST;

import controller.NoneToggleAction;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.LeaderBoardMessage;
import model.Message;
import model.ParticipantShape;

/**
 * 
 * @author chinh le
 * @version 12/05/2018
 */
public class LeaderBoardPanel extends JPanel implements PropertyChangeListener {

    /**
     * 
     */
    public static final String LEADER_STARTWITH = "$L";
    
    /** The size of the Leaderboard Panel. */
    private static final Dimension LEADBOARD_SIZE = new Dimension(250, 400);
    
    /** The size of the Button. */
    private static final Dimension BUTTON_SIZE = new Dimension(250, 30);
    
    /** The size of the Button. */
    private static final int BUTTON_FONT = 20;
    /**
     * 
     */
    private static final long serialVersionUID = -9206279153534974879L;
    

    /**
     * 
     */
    private List<ParticipantShape> myCircleParticipants;
    
    /**
     * 
     */
    private List<Action> myLeadBoardAction;
    
    /**
     * 
     */
    private JPanel myLeaderBoard;
    
    /**
     * 
     */
    private RaceTrackPanel myRaceTrackPanel;
    
    /**
     * 
     */
    private int myRacerID;
    
    /**
     * 
     */
    private String myRacerName = "";
    
    /**
     * 
     */
    public LeaderBoardPanel() {
        myLeaderBoard = new JPanel();
        myCircleParticipants = new ArrayList<ParticipantShape>();
        myLeaderBoard.setLayout(new GridLayout(myCircleParticipants.size(), 1));
        myLeadBoardAction = new ArrayList<Action>();
        myRaceTrackPanel = new RaceTrackPanel();
        myCircleParticipants = myRaceTrackPanel.getMyCircleParticipants();
        setPreferredSize(LEADBOARD_SIZE);
        
    }
    
    /**
     * the method that create a single racer label.
     * @param thePartcipant the participant
     */
    private void setupLeaderBoardLabel(final ParticipantShape thePartcipant) {
        
        final NoneToggleAction displayRacerInfo =
                        new NoneToggleAction(thePartcipant.getRacerID() 
                                             + ":" + thePartcipant.getRacerName(), null, 
                            () -> getParticipantInfo(thePartcipant));
        myLeadBoardAction.add(displayRacerInfo);

        final JButton racerButton = new JButton(displayRacerInfo);
        
        //racerButton.setIcon((ImageIcon) thePartcipant.getRacerShape());
        racerButton.setBackground(thePartcipant.getColor());
        racerButton.setBorderPainted(true);
        racerButton.setPreferredSize(BUTTON_SIZE);
        racerButton.setFont(new Font("Arial", Font.PLAIN, BUTTON_FONT));
        myLeaderBoard.add(racerButton);
        add(myLeaderBoard);
        revalidate();
    }
    
    /**
     * 
     */
    
    
    /**
     * get participant information.
     * @param theParticipant the participant
     */
    private void getParticipantInfo(final ParticipantShape theParticipant) {
//        final StatusPanel statusPanel = new StatusPanel(this);
//        myRacerID = Integer.valueOf(theParticipant.getRacerID());
//        myRacerName = theParticipant.getRacerName();
//        statusPanel.setParticipantText(myRacerID, myRacerName);
    }
    
    /**
     * check if the leaderboard panel have the components.
     * @param thePanel the leaderboard panel
     */
    private void checkPanelNotNull(final JPanel thePanel) {
        if (thePanel.getComponentCount() != 0) {
            thePanel.removeAll();
        }
    }
    /**
     * the method that creates a label and add it to leader board panel.
     * @param theMessage the message contain racer ids
     */
    private void setupLeaderBoardLabels(final LeaderBoardMessage theMessage) {
        for (String s : theMessage.getRacerIds()) {
            for (ParticipantShape p : myRaceTrackPanel.getMyCircleParticipants()) {
                if (s.equals(p.getRacerID())) {
                    setupLeaderBoardLabel(p);
                }
            }
        }

    }
    
    /**
     * return the racer id from leader board.
     * @return racer id
     */
    public int getRacerID() {
        return myRacerID;
    }
    
    /**
     * return the racer name from leader board.
     * @return racer name
     */
    public String getRacerName() {
        return myRacerName;
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PROPERTY_PARTICIPANT_LIST.equals(theEvt.getPropertyName())) {
            checkPanelNotNull(myLeaderBoard);
            for (ParticipantShape p : myRaceTrackPanel.getMyCircleParticipants()) {
                setupLeaderBoardLabel(p);
            }
        }
        if (PROPERTY_LEADER_MESSAGE.equals(theEvt.getPropertyName())) {
            final Message message = (LeaderBoardMessage) theEvt.getNewValue();
            if ((message.getType()).startsWith(LEADER_STARTWITH)) {
                checkPanelNotNull(myLeaderBoard);
                final LeaderBoardMessage leaderMessage =
                                (LeaderBoardMessage) theEvt.getNewValue();
                setupLeaderBoardLabels(leaderMessage);
            }
        }
    }
}
