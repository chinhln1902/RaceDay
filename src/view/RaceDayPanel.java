/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import model.PropertyChangeEnabledRaceControls;


/**
 * 
 * @author chinh le
 * @version 12/05/2018
 */
public class RaceDayPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4221209610429499705L;

    /**
     * 
     */
    private RaceTrackPanel myRaceTrackPanel;
    
    /**
     * 
     */
    private LeaderBoardPanel myLeaderBoardPanel;
    
    /**
     * 
     */
    private StatusPanel myStatusPanel;
    
    /**
     * Constructor to create a panel that contains the other panels.
     * @param theModel the race model.
     */
    public RaceDayPanel(final PropertyChangeEnabledRaceControls theModel) {
       // super();
        myRaceTrackPanel = new RaceTrackPanel();
        myLeaderBoardPanel = new LeaderBoardPanel();
        myStatusPanel = new StatusPanel(myLeaderBoardPanel);
        addPropertyChangeListener(theModel);
        setLayout(new BorderLayout());
        setupPanels();
    }
    
    /**
     * 
     * @param theModel the connection between controller and view
     */
    private void addPropertyChangeListener(final PropertyChangeEnabledRaceControls theModel) {
        theModel.addPropertyChangeListener(myRaceTrackPanel);
        theModel.addPropertyChangeListener(myLeaderBoardPanel);
        theModel.addPropertyChangeListener(myStatusPanel);
//        theModel.addPropertyChangeListener(PROPERTY_RACE_INFO, myRaceTrackPanel);
//        theModel.addPropertyChangeListener(PROPERTY_PARTICIPANT_LIST, myRaceTrackPanel);
//        theModel.addPropertyChangeListener(PROPERTY_TRACK_INFO, myRaceTrackPanel);
//        theModel.addPropertyChangeListener(PROPERTY_MOVE_RACER, myRaceTrackPanel);
        
    }

    /**
     * 
     */
    private void setupPanels() {
        final TitledBorder border = new TitledBorder("Race Track");
        border.setTitlePosition(TitledBorder.TOP);
        myRaceTrackPanel.setBorder(border);
        add(myRaceTrackPanel, BorderLayout.WEST);
        add(myLeaderBoardPanel, BorderLayout.EAST);
        add(myStatusPanel, BorderLayout.SOUTH);
    }
}


