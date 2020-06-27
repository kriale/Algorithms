package edu.kriale.algorithms.sem6.task2.other;

import javax.swing.*;
import java.awt.*;

public class SwingTableExample extends JPanel {
    private boolean DEBUG = false;

    public SwingTableExample() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
