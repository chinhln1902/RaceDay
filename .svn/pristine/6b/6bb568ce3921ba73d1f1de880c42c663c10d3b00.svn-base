/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANT_LIST;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TRACK_INFO;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_MOVE_RACER;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.Participant;
import model.ParticipantShape;
import model.RaceTrack;
import model.TelemetryMessage;
import track.VisibleRaceTrack;

/**
 * 
 * @author chinh le
 * @version 12/05/2018
 */
public class RaceTrackPanel extends JPanel implements PropertyChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 925775267319310885L;
    /** The size of the Race Track Panel. */
    private static final Dimension TRACK_SIZE = new Dimension(500, 400);

    /** The x and y location of the Track. */
    private static final int OFF_SET = 40;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 25;

    /** The size of participants moving around the track. */
    private static final int OVAL_SIZE = 20;
    
    /** The visible track. */
    private static VisibleRaceTrack myTrack;
    /**
     * 
     */
    private static List<ParticipantShape> myCircleParticipants;


    /**
     * 
     */
    private List<Participant> myParticipantsList;
    

    
    /**
     * 
     */
    private RaceTrack myTrackInfo;
    

  
    /**
     * Construct a Race Track panel.
     */
    public RaceTrackPanel() {
        super();
        myParticipantsList = new ArrayList<Participant>();
        myCircleParticipants = new ArrayList<ParticipantShape>();
        new JPanel();
        new JPanel();
        setLayout(new BorderLayout());
        setPreferredSize(TRACK_SIZE);
    }

    
    /**
     * Setup and layout components.
     */
    private void setupTrackComponents() {
        
        final int raceWidth = Integer.valueOf(myTrackInfo.getRaceWidth()) 
                        * 100 - (OFF_SET * 2);
        final int raceHeight = Integer.valueOf(myTrackInfo.getRaceHeight()) 
                        * 100 - (OFF_SET * 2);
        final int x = OFF_SET;
        final int y = (int) TRACK_SIZE.getHeight()  / 2 - raceHeight / 2;

        
        myTrack = new VisibleRaceTrack(x, y, 
                                       raceWidth, 
                                       raceHeight, 
                                       Integer.valueOf(myTrackInfo.getRaceDisntace()));
        
    }

    /**
     * 
     * @param theGraphics
     */
    private void createRacers() {
        
        myCircleParticipants = new ArrayList<ParticipantShape>();
        for (Participant p : myParticipantsList) {
            final Point2D startedDistance = myTrack.
                            getPointAtDistance(Double.valueOf(p.getRacerDistance()));
                      
            final Ellipse2D circle = new 
                            Ellipse2D.Double(startedDistance.getX() - OVAL_SIZE / 2, 
                                             startedDistance.getY() - OVAL_SIZE / 2, 
                                             OVAL_SIZE, OVAL_SIZE);

            myCircleParticipants.add(new 
                                     ParticipantShape(p.getRacerID(), 
                                                      circle, 
                                                      p.getRacerName()));
        }
    }
    
    /**
     * get the information of the race track.
     * @return the race track
     */
    public VisibleRaceTrack getMyTrack() {
        return myTrack;
    }
    
    /**
     * Paints the VisibleTrack with a single ellipse moving around it.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    
    /**
     * 
     * @return the participant list.
     */
    public List<ParticipantShape> getMyCircleParticipants() {
        return myCircleParticipants;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        
        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        if (myTrack != null) {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(STROKE_WIDTH));
            g2d.draw(myTrack);
        }
        
        if (!myCircleParticipants.isEmpty()) {
            for (ParticipantShape p : myCircleParticipants) {
                g2d.setPaint(p.getColor());
                g2d.setStroke(new BasicStroke(1));
                g2d.fill(p.getRacerShape());
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PROPERTY_MOVE_RACER.equals(theEvt.getPropertyName())) {
            final TelemetryMessage message = (TelemetryMessage) theEvt.getNewValue();
            for (ParticipantShape p : myCircleParticipants) {
                final Point2D racerDistance = myTrack.
                                getPointAtDistance(Double.valueOf(message.getDistance()));
                if (message.getRaceID().equals(p.getRacerID())) {
                    p.getRacerShape().setFrame(racerDistance.getX() - OVAL_SIZE / 2, 
                                                 racerDistance.getY() - OVAL_SIZE / 2, 
                                                 OVAL_SIZE, OVAL_SIZE);
                }
                repaint();
            }

            
        }
        if (PROPERTY_PARTICIPANT_LIST.equals(theEvt.getPropertyName())) {
            myParticipantsList = (List<Participant>) theEvt.getNewValue();
            setupTrackComponents();
            createRacers();
            repaint();
        }
        if (PROPERTY_TRACK_INFO.equals(theEvt.getPropertyName())) {
            myTrackInfo = (RaceTrack) theEvt.getNewValue();
        }
    }

}
