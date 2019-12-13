/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACE_INFO;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TEXT_AREA_MESSAGES;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_SET_MAXIMUM_SLIDER;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_UPDATE_MESSAGES;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIMER_END;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TEXT_CLEAR;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_MOVE_TO_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANT_LIST;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Message;
import model.Participant;
import model.RaceControls;
import model.RaceModel;
import view.RaceDayPanel;

/**
 * 
 * @author chinh
 * @version 11/18/2018
 */
public class RaceController extends JPanel implements PropertyChangeListener {
    /** A formatter to require at least 2 digits, leading 0s. */
    public static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("00");
    
    /** A formatter to require at least 3 digits, leading 0s. */
    public static final DecimalFormat THREE_DIGIT_FORMAT = new DecimalFormat("000");
    /** A formatter to require at least 1 digit, leading 0. */
    public static final DecimalFormat ONE_DIGIT_FORMAT = new DecimalFormat("0");
    /** The number of milliseconds in a second. */
    public static final int MILLIS_PER_SEC = 1000;
    
    /** The maximum value of slider. */
    public static final int MAXIMUM_VALUE = 1000;
    
    /** The number of seconds in a minute. */
    public static final int SEC_PER_MIN = 60;
    
    /** The number of minute in a hour. */
    public static final int MIN_PER_HOUR = 60;
    
    /** The separator for formatted. */
    public static final String SEPARATOR = ":";
    
    /** The separator for formatted. */
    public static final String LINE_BREAK = "\n";
    
    /** Padding for the border around the label. */
    private static final int PADDING = 5;
    
    /**
     * 
     */
    private static ToggleAction playPauseAction;
//    /**
//     * 
//     */
//    private static RaceModel myRaceModel = new RaceModel();
    /**
     * 
     */
    private static final long serialVersionUID = 7211163271822852202L;
    
    /**
     * 
     */
    private static final int TIME_FREQUENCY = 30;
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_LOAD_FILE = "Load File...";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_EXIT = "Exit";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_PLAY = "Play";
    /**
     * 
     */
    private static final String BUTTON_TEXT_RESTART = "Restart";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_PAUSE = "Pause";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_TIME_ONE = "Times One";

    /**
     * 
     */
    private static final String BUTTON_TEXT_TIME_FOUR = "Times Four";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_SINGLE_RACE = "Single Race";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_LOOP_RACE = "Loop Race";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_CLEAR = "Clear";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_RACE_INFO = "Race Info...";
    
    /**
     * 
     */
    private static final String BUTTON_TEXT_ABOUT = "About...";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_RESTART = "./images/ic_restart.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_PLAY = "./images/ic_play.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_PAUSE = "./images/ic_pause.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_REPEAT_OFF = "./images/ic_repeat.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_REPEAT_ON = "./images/ic_repeat_color.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_TIME_ONE = "./images/ic_one_times.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_TIME_FOUR = "./images/ic_four_times.png";
    
    /**
     * 
     */
    private static final String BUTTON_ICON_CLEAR = "./images/ic_clear.png";
    
    /**
     * 
     */
    private static final String RACE_FLAG_IMAGE = "./images/raceflag.png";

    /**
     * 
     */
    private static final int MULTIPLIER_X4 = 4;
    
    /**
     * 
     */
    private static final int MULTIPLIER_X1 = 1;
    
    /** The default border size. */
    private static final int BORDER_SIZE = 5;
    
    /**
    *
    */
    private static final int MINOR_TICK_SPACING = 1000;

    /**
    *
    */
    private static final int MAJOR_TICK_SPACING = 60000;

    /**
     * 
     */
    private static final int TEXT_AREA_COLUMNS = 50;
    
    /**
     * 
     */
    private static final int TEXT_AREA_ROWS = 10;
    
    /**
     * 
     */
    private JFileChooser myFileChooser;
    /**
     * 
     */
    private List<Action> myFileMenuActions;
    
    /**
     * 
     */
    private List<Action> myControlsMenuActions;
    
    /**
     * 
     */
    private List<Action> myHelpMenuActions;
    
    
    /**
     * 
     */
    private int myMultiplier;
    
    /**
     * 
     */
    private Timer myTimer;
    
    /**
     * 
     */
    private List<String> myRaceInfo;
    
    /**
     * 
     */
    private List<JCheckBox> myParticipantCheckBoxes; 
    
    /**
     * 
     */
    private JToolBar myToolBar;
    
    /**
     * 
     */
    private JCheckBox mySelectAll;
    /**
     * 
     */
    private JPanel myCenterPanel;
    
    /**
     * 
     */
    private JSlider mySlider;
    
    /**
     * 
     */
    private JPanel myContainer;
    
    /**
     * 
     */
    private JPanel mySliderPanel;
    
    /**
     * 
     */
    private JPanel myCheckBoxesPanel;
    
    /**
     * 
     */
    private JTabbedPane myTabbedPane;
    
    /**
     * 
     */
    private JTextArea myTextArea;
    
    /**
     * 
     */
    private JLabel myTimerLabel;
 
    /**
     * 
     */
    private int myRaceTotalTime;
    
    /**
     * 
     */
    private boolean myLoopToggle;
    
    /**
     * 
     */
    private boolean myTimerEndFlag;
    
    /**
     * 
     */
    private JMenu myControls;
    
    /**
     * 
     */
    private RaceControls myRaceModel;
    /**
     * 
     */
    private JMenu myHelps;
    
    /**
     * 
     */
    private List<Participant> myParticipantsList;
    
    /**
     * The constructor for initializing all variables and actions.
     * @param theRace the race control.
     */
    public RaceController(final RaceControls theRace) {
        myRaceModel = theRace;
        initialVariables();
        createActions();

    }
    
    /**
     * 
     */
    private void initialVariables() {
        myTimer = new Timer(TIME_FREQUENCY, this::handleTimer);
        myLoopToggle = false;
        myTimerEndFlag = false;
        myFileMenuActions = new ArrayList<>();
        myParticipantsList = new ArrayList<Participant>();
        myControlsMenuActions = new ArrayList<>();
        myHelpMenuActions = new ArrayList<>();
        myRaceInfo = new ArrayList<String>();
        mySelectAll = new JCheckBox("Select All", true);
        myParticipantCheckBoxes = new ArrayList<JCheckBox>();
        myFileChooser = new JFileChooser("./race_files");
        myToolBar = new JToolBar();
        mySliderPanel = new JPanel();
        myCheckBoxesPanel = new JPanel();
        myTimerLabel = new JLabel(formatTime(0));
        myContainer = new JPanel();
        myCenterPanel = new JPanel();
        myControls = new JMenu("Controls");
        myHelps = new JMenu("Help");
        myMultiplier = 1;
    }
    
    /**
     * 
     */
    public static void createAndShowGUI() {
        final JFrame frame = new JFrame("Race Day!");

        final RaceModel raceModel = new RaceModel();
        
//        final RaceTrackPanel raceTrackPanel = new RaceTrackPanel();
//        raceModel.addPropertyChangeListener(PROPERTY_TIME, raceTrackPanel);
//        raceModel.addPropertyChangeListener(PROPERTY_RACE_INFO, raceTrackPanel);
//        raceModel.addPropertyChangeListener(PROPERTY_PARTICIPANT_LIST, raceTrackPanel);
//        raceModel.addPropertyChangeListener(PROPERTY_TRACK_INFO, raceTrackPanel);
//        raceModel.addPropertyChangeListener(PROPERTY_MOVE_RACER, raceTrackPanel);
        
        final RaceController menuController = new RaceController(raceModel);
        
        
        final RaceDayPanel raceDayPanel = new RaceDayPanel(raceModel);
//
//        raceModel.addPropertyChangeListener(PROPERTY_PARTICIPANT_LIST, raceDayPanel);

        
        raceModel.addPropertyChangeListener(PROPERTY_RACE_INFO, menuController);
        raceModel.addPropertyChangeListener(PROPERTY_TIMER_END, menuController);
        raceModel.addPropertyChangeListener(PROPERTY_SET_MAXIMUM_SLIDER, menuController);
        raceModel.addPropertyChangeListener(PROPERTY_TEXT_CLEAR, menuController);
        raceModel.addPropertyChangeListener(PROPERTY_TEXT_AREA_MESSAGES, menuController);
        raceModel.addPropertyChangeListener(PROPERTY_UPDATE_MESSAGES, menuController);
        raceModel.addPropertyChangeListener(model.PropertyChangeEnabledRaceControls.
                                             PROPERTY_TIME, 
                                             menuController);
        raceModel.addPropertyChangeListener(PROPERTY_PARTICIPANT_LIST, menuController);
        
        raceModel.addPropertyChangeListener(PROPERTY_TIMER_END, playPauseAction);
        
        frame.setIconImage(new ImageIcon(RACE_FLAG_IMAGE).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.add(menuController.createToolsBar(), BorderLayout.SOUTH);
        frame.add(menuController.createCenterPanel(), BorderLayout.CENTER);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        menuController.createRaceDayPanel(raceDayPanel);
    }
    
    /**
     * Add Race Day panel to Jframe and show it.
     * @param theRaceDayPanel the race day panel
     */
    private void createRaceDayPanel(final RaceDayPanel theRaceDayPanel) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                final JFrame raceViewFrame = new JFrame("Race Track");
                raceViewFrame.setIconImage(new ImageIcon(RACE_FLAG_IMAGE).getImage());
                raceViewFrame.setContentPane(theRaceDayPanel);
                raceViewFrame.setResizable(false);
                raceViewFrame.pack();
                raceViewFrame.setVisible(true);
            }
        });
    }
    /**
     * 
     * @return the center panel.
     */
    private JPanel createCenterPanel() {
        myCenterPanel.setLayout(new BorderLayout());
        myContainer.setLayout(new BorderLayout());
        mySliderPanel.setLayout(new BorderLayout());
        makeSlider();
        makeTimer();
        createTabbedPane();
        
        myCenterPanel.add(myContainer, BorderLayout.CENTER);
        return myCenterPanel;
    }

    /**
     * 
     */
    private void makeSlider() {
        
        mySlider = new JSlider(JSlider.HORIZONTAL, 0, MAXIMUM_VALUE, 0);
        mySlider.addChangeListener(theEvent -> myRaceModel.moveTo(mySlider.getValue()));
        mySlider.addPropertyChangeListener(new PropertyChangeListener() {
            
            @Override
            public void propertyChange(final PropertyChangeEvent theEvt) {

                if (PROPERTY_MOVE_TO_MESSAGE.equals(theEvt.getPropertyName())) {
                    myTextArea.append(theEvt.getNewValue().toString() + LINE_BREAK);
                }
            }
        });
        
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setPaintTicks(true);
        mySlider.setEnabled(false);
        mySliderPanel.add(mySlider, BorderLayout.CENTER);
    }
    

    
    /**
     * 
     */
    private void makeTimer() {
        myTimerLabel.setBorder(BorderFactory.
                             createCompoundBorder(BorderFactory.
                                              createEtchedBorder(),
                                              BorderFactory.createEmptyBorder(PADDING, PADDING,
                                                                              PADDING,
                                                                              PADDING)));
        mySliderPanel.add(myTimerLabel, BorderLayout.EAST);
        myContainer.add(mySliderPanel, BorderLayout.NORTH);
    }
    
    /**
     * This formats a positive integer into minutes, seconds, and milliseconds. 
     * @param theTime the time to be formatted
     * @return the formatted string
     */
    public static String formatTime(final long theTime) {
        long time = theTime;
        final long milliseconds = time % MILLIS_PER_SEC;
        time /= MILLIS_PER_SEC;
        final long seconds = time % SEC_PER_MIN;
        time /= SEC_PER_MIN;
        final long min = time % MIN_PER_HOUR;
        time /= MIN_PER_HOUR;
        return TWO_DIGIT_FORMAT.format(min) + SEPARATOR
                        + TWO_DIGIT_FORMAT.format(seconds) 
                        + SEPARATOR + THREE_DIGIT_FORMAT.format(milliseconds);
    }
    
    /**
     * 
     */
    private void createTabbedPane() {
        myTabbedPane = new JTabbedPane();
        
        myTextArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        final JScrollPane scroll = new 
                        JScrollPane(myTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        myTextArea.setEditable(false);
        final JScrollPane scroll1 = new 
                        JScrollPane(myCheckBoxesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        myTabbedPane.addTab("Data Output Stream", scroll);
        
        myTabbedPane.addTab("Race Participants", scroll1);
        
        myTabbedPane.setEnabledAt(1, false);
        
        myContainer.add(myTabbedPane, BorderLayout.CENTER);
            
        myContainer.setBorder(BorderFactory.createEmptyBorder(
                BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
    }
    
    /**
     * 
     * @return menubar
     */
    private JMenuBar createMenuBar() {
        final JMenuBar bar = new JMenuBar();
        final JMenu file = new JMenu("File");
        
        for (final Action a : myFileMenuActions) {
            file.add(new JMenuItem(a));
        }
        bar.add(file);
        
//        final JMenu controls = new JMenu("Controls");
        
        for (final Action a : myControlsMenuActions) {
            myControls.add(new JMenuItem(a));
        }

        bar.add(myControls);
        setMenuItemEnable(myControls, false);
        
//        final JMenu help = new JMenu("Help");
        
        for (final Action a : myHelpMenuActions) {
            myHelps.add(new JMenuItem(a));
        }
        myHelps.getMenuComponent(0).setEnabled(false);
        bar.add(myHelps);
        return bar;
    }
    
    /**
     * 
     * @return the tools bar.
     */
    private JToolBar createToolsBar() {

        for (final Action a : myControlsMenuActions) {
            final JButton butt = new JButton(a);
            butt.setHideActionText(true);
            myToolBar.add(butt);
        }
        setButtonEnable(false);
        return myToolBar;
    }
    
    /**
     * Create list of participant check boxes in tabbedpane.
     * @return the list of check boxes.
     */
    private List<JCheckBox> createParticipantCheckBoxes() {
        myParticipantCheckBoxes = new ArrayList<>();
        myCheckBoxesPanel.removeAll();
        myCheckBoxesPanel.setLayout(new 
                                    GridLayout(myParticipantsList.size() + 1, 1));
        myCheckBoxesPanel.add(mySelectAll);
        for (Participant p : myParticipantsList) {
            final JCheckBox cb = new JCheckBox(p.getRacerName(), true);
            cb.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(final ItemEvent theE) {
                    if (theE.getStateChange() == 2) {
                        myRaceModel.toggleParticipant(Integer.valueOf(p.getRacerID()), false);
                    } else {
                        myRaceModel.toggleParticipant(Integer.valueOf(p.getRacerID()), true);
                    }
                }
            });
            myParticipantCheckBoxes.add(cb);
            myCheckBoxesPanel.add(cb);
        }
//        myCheckBoxesPanel = new JPanel();
        return myParticipantCheckBoxes;
    }
    
    /**
     * 
     */
    private void createActions() {
        final NoneToggleAction loadFileAction = new 
                        NoneToggleAction(BUTTON_TEXT_LOAD_FILE, null, () -> loadRaceFile());
        
        final NoneToggleAction exitAction = new 
                        NoneToggleAction(BUTTON_TEXT_EXIT, null, () -> System.exit(0));
        
        final NoneToggleAction restartAction = new 
                        NoneToggleAction(BUTTON_TEXT_RESTART, 
                                         BUTTON_ICON_RESTART, () -> restart());

        playPauseAction = new 
                        ToggleAction(BUTTON_TEXT_PLAY, 
                                     BUTTON_TEXT_PAUSE, 
                                     BUTTON_ICON_PLAY, BUTTON_ICON_PAUSE, 
            () -> myTimer.start(), () -> myTimer.stop());
        
        playPauseAction.addPropertyChangeListener(new PropertyChangeListener() {
            
            @Override
            public void propertyChange(final PropertyChangeEvent theEvt) {
                if (!myTimer.isRunning()) {
                    mySlider.setEnabled(true);
                } else {
                    mySlider.setEnabled(false);
                }
            }
        });

        
        final ToggleAction timesAction = new ToggleAction(BUTTON_TEXT_TIME_ONE, 
                                                          BUTTON_TEXT_TIME_FOUR, 
                                                          BUTTON_ICON_TIME_ONE, 
                                                          BUTTON_ICON_TIME_FOUR, 
            () -> myMultiplier = MULTIPLIER_X4, () -> myMultiplier = MULTIPLIER_X1);
        
        final ToggleAction raceModeAction = new ToggleAction(BUTTON_TEXT_SINGLE_RACE, 
                                                       BUTTON_TEXT_LOOP_RACE, 
                                                       BUTTON_ICON_REPEAT_OFF, 
                                                       BUTTON_ICON_REPEAT_ON, 
            () -> myLoopToggle = true, () -> myLoopToggle = false);
        
        
        
        final NoneToggleAction clearAction = new 
                        NoneToggleAction(BUTTON_TEXT_CLEAR, 
                                         BUTTON_ICON_CLEAR, () -> clearTextArea());
        
        final NoneToggleAction raceInfoAction =
                        new NoneToggleAction(BUTTON_TEXT_RACE_INFO, 
                                             null, () -> showRaceInfoPanel());
        
        final NoneToggleAction aboutAction = new 
                        NoneToggleAction(BUTTON_TEXT_ABOUT, null, () -> showAboutMenuItem());
        
        mySelectAll.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(final ItemEvent theE) {
                if (theE.getStateChange() == 2) {
                    unselectedParticipantsCheckboxes(false);
                } else {
                    unselectedParticipantsCheckboxes(true);
                }
            }
        });
        
        myFileMenuActions.add(loadFileAction);
        myFileMenuActions.add(exitAction);
        myControlsMenuActions.add(restartAction);
        myControlsMenuActions.add(playPauseAction);
        myControlsMenuActions.add(timesAction);
        myControlsMenuActions.add(raceModeAction);
        myControlsMenuActions.add(clearAction);
        myHelpMenuActions.add(raceInfoAction);
        myHelpMenuActions.add(aboutAction);
    }
    
    /**
     * set or unselect all the participants check boxes.
     * @param theSelect state of check box
     */
    private void unselectedParticipantsCheckboxes(final boolean theSelect) {
        for (JCheckBox j : myParticipantCheckBoxes) {
            j.setSelected(theSelect);
        }
    }
    /**
     * 
     */
    private void loadRaceFile() {
        final FileNameExtensionFilter filter =
                        new FileNameExtensionFilter("Race File", "rce");
        myFileChooser.setFileFilter(filter);
        myFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        final int result = myFileChooser.showOpenDialog(RaceController.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File file = myFileChooser.getSelectedFile();
            try {
                myRaceModel.loadRace(file);
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(this, "The selected file is not "
                                + "in correct format", "Error!", 
                                JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * 
     * @param theToggle
     */
    private void restart() {
        myTimerLabel.setText(formatTime(0));
        myTimer.setInitialDelay(0);
        mySlider.setValue(0);
        myTextArea.setText("");
    }
    
    /**
     * call the advance method to change timer label.
     * @param theEvent argument
     */
    private void handleTimer(final ActionEvent theEvent) {
        myRaceModel.advance(TIME_FREQUENCY * myMultiplier);
    }
    
    /**
     * 
     */
    private void showRaceInfoPanel() {
        JOptionPane.showMessageDialog(
                                      new JPanel(), myRaceInfo.toArray(), "Race Information!", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 
     */
    private void showAboutMenuItem() {
        ImageIcon icon = new ImageIcon(RACE_FLAG_IMAGE);
        final Image image = icon.getImage().
                        getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        final List<String> about = new ArrayList<String>();
        about.add("Chinh Le");
        about.add("Autumn 2018");
        about.add("TCSS 305 Assignment 5");
        JOptionPane.showMessageDialog(new JPanel(), 
                                      about.toArray(), 
                                      "About", 
                                      JOptionPane.INFORMATION_MESSAGE, 
                                      icon);
    }
    
    /**
     * 
     */
    private void clearTextArea() {
        myTextArea.setText("");
    }
    /**
     * set button enable.
     * @param theEnable status of button.
     */
    private void setButtonEnable(final boolean theEnable) {
        for (Component cp: myToolBar.getComponents()) {
            cp.setEnabled(theEnable);
        }
    }
    
    /**
     * set all the menu item of a menu enable or disable.
     * @param theMenu the menu item container
     * @param theEnable state of the menu item
     */
    private void setMenuItemEnable(final JMenu theMenu, final boolean theEnable) {
        for (Component cp: theMenu.getMenuComponents()) {
            cp.setEnabled(theEnable);
        }
    }
    
    /**
     * check if the loop button is pressed.
     */
    private void checkLoopMode() {
        if (myTimerEndFlag && myLoopToggle) {
            restart();
            myTimer.start();
        } else {
            myTimer.stop();
            playPauseAction.actionPerformed(null);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PROPERTY_RACE_INFO.equals(theEvt.getPropertyName())) {
            myRaceInfo = (List<String>) theEvt.getNewValue();
            setButtonEnable(true);
            setMenuItemEnable(myControls, true);
            myHelps.getMenuComponent(0).setEnabled(true);
        }
        if (PROPERTY_TIMER_END.equals(theEvt.getPropertyName())) {
            myTimerEndFlag = (boolean) theEvt.getNewValue();
            
            checkLoopMode();
        }
        if (PROPERTY_TIME.equals(theEvt.getPropertyName())) {
            myTimerLabel.setText(formatTime((Integer) theEvt.getNewValue()));
            mySlider.setValue((Integer) theEvt.getNewValue());
        }
        if (PROPERTY_PARTICIPANT_LIST.equals(theEvt.getPropertyName())) {
            myParticipantsList = (List<Participant>) theEvt.getNewValue();
        }
        if (PROPERTY_SET_MAXIMUM_SLIDER.equals(theEvt.getPropertyName())) {
            myRaceTotalTime = (Integer) theEvt.getNewValue();
            mySlider.setMaximum(myRaceTotalTime);
            mySlider.setEnabled(true);
            createParticipantCheckBoxes();
            
            myTabbedPane.setEnabledAt(1, true);
        }
        if (PROPERTY_TEXT_AREA_MESSAGES.equals(theEvt.getPropertyName())) {
            final Message message = (Message) theEvt.getNewValue();

            myTextArea.append(message.toString() + LINE_BREAK);
        }
        if (PROPERTY_UPDATE_MESSAGES.equals(theEvt.getPropertyName())) {
            final List<String> updateMessages = (List<String>) theEvt.getNewValue();
            for (String a : updateMessages) {
                myTextArea.append(a + LINE_BREAK);
            }
        }
    }

}

