package edu.kriale.algorithms.sem6.task2.other;

import javax.swing.*;

public class GuiRunner {
    public static void main(String[] args) {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        SwingTableExample newContentPane = new SwingTableExample();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
