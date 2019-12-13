/*
 * TCSS 305 - Race Day
 * Assignment 5
 */
package application;

import controller.RaceController;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Run the Race Day program.
 * @author Chinh Le
 * @version 11/08/2018
 */
public final class Main {
    /**
     * Do nothing.
     */
    private Main() {
        
    }
    /**
     * 
     * @param theArgs Command line arguments
     */
    public static void main(final String...theArgs) {
        try {
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RaceController.createAndShowGUI();
            }
        });
    }
}
