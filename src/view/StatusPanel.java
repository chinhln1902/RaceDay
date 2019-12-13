/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;

import controller.RaceController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * @author chinh le
 * @version 12/05/2018
 */
public class StatusPanel extends JLabel implements PropertyChangeListener {

    
    /**
     * 
     */
    private static final long serialVersionUID = 7293815398758841049L;

    /** The size of the Race Track Panel. */
    private static final Dimension STATUSBAR_SIZE = new Dimension(500, 20);
    
    /**
     * 
     */
    private static final String TIMER_LABEL = "Time: ";
    
    /**
     * 
     */
    private static final String PARTICIPANT_LABEL = "Participant: ";
    /**
     * 
     */
    private JLabel myTimerLabel;
    
    /**
     * 
     */
    private JLabel myParticipantLabel;
    
    /**
     * 
     * @param thePanel the leaderboard panel
     */
    public StatusPanel(final LeaderBoardPanel thePanel) {
        myTimerLabel = new JLabel(TIMER_LABEL + RaceController.formatTime(0));
        new JPanel();

        new LeaderBoardPanel();
        setLayout(new BorderLayout());
        setupComponents();
        setPreferredSize(STATUSBAR_SIZE);
    }
    
    /**
     * setup and layout all components.
     */
    private void setupComponents() {
        myParticipantLabel = new JLabel(PARTICIPANT_LABEL);
        add(myTimerLabel, BorderLayout.EAST);
        add(myParticipantLabel, BorderLayout.WEST);
        revalidate();
    }
    
    /**
     * 
     * @param theID the racer id
     * @param theName the racer name
     */
    public void setParticipantText(final int theID, final String theName) {

        myParticipantLabel.setText(theID + "---" + theName);
    }
    
    /**
     * return the participant label.
     * @return the participant label
     */
    public JLabel getParticipantLabel() {
        return myParticipantLabel;
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PROPERTY_TIME.equals(theEvt.getPropertyName())) {
            myTimerLabel.setText(TIMER_LABEL 
                                 + RaceController.formatTime((Integer) theEvt.getNewValue()));
        }
    }

}
