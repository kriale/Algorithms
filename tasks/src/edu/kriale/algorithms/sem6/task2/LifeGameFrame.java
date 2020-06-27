package edu.kriale.algorithms.sem6.task2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class LifeGameFrame extends JFrame {
    protected static final int WINDOW_WIDTH = 500;
    protected static final int WINDOW_HEIGHT = 500;
    private static final int X_CELLS_COUNT = 50;
    private static final int Y_CELLS_COUNT = 50;
    private static final int TIMEOUT = 300;

    protected DefaultTableModel tableModel;
    protected JTable table = new JTable();

    private JButton startButton = new JButton("Start");
    private JButton nextButton = new JButton("Next step");
    private JButton initRandomFieldButton = new JButton("Init random field");;


    public LifeGameFrame(String windowName) {
        super(windowName);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        tableModel = new DefaultTableModel(X_CELLS_COUNT, Y_CELLS_COUNT);
        initEmptyTable(tableModel);
        table.setModel(tableModel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        add(table, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            new Thread(() -> {
                System.out.println("Started");
                startGame();
            }).start();
        });
        nextButton.addActionListener(e -> nextStep());
        initRandomFieldButton.addActionListener(e -> initRandomTable(tableModel));

        buttonPanel.add(startButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(initRandomFieldButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startGame() {
        try {
            while (true) {
                nextStep();
                Thread.sleep(TIMEOUT);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void nextStep() {
        DefaultTableModel bufferTableModel = getCopyOf(tableModel);
        for (int x = 0; x < tableModel.getRowCount(); x++) {
            for (int y = 0; y < tableModel.getColumnCount(); y++) {
                int count = getNeighbourCountFor(bufferTableModel, x, y);
                if (getCell(bufferTableModel, x, y) == 1) {
                    if (count < 2 || count > 3) {
                        setCellValue(tableModel, x, y, 0);
                    } else {
                        setCellValue(tableModel, x, y, 1);
                    }
                } else {
                    if (count == 3) {
                        setCellValue(tableModel, x, y, 1);
                    } else {
                        setCellValue(tableModel, x, y, 0);
                    }
                }
            }
        }
    }

    private DefaultTableModel getCopyOf(DefaultTableModel model) {
        DefaultTableModel returnedModel = new DefaultTableModel(model.getRowCount(), model.getColumnCount());
        for (int x = 0; x < model.getRowCount(); x++) {
            for (int y = 0; y < model.getColumnCount(); y++) {
                returnedModel.setValueAt(model.getValueAt(x, y), x, y);
            }
        }
        return returnedModel;
    }

    private boolean isCleanField() {
        for (int x = 0; x < tableModel.getRowCount(); x++) {
            for (int y = 0; y < tableModel.getColumnCount(); y++) {
                if (getCell(tableModel, x, y) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getNeighbourCountFor(DefaultTableModel tableModel, int x, int y) {
        return getCell(tableModel, x-1, y) + getCell(tableModel, x+1, y)
                + getCell(tableModel, x, y+1) + getCell(tableModel, x, y-1);
    }

    private int getCell(DefaultTableModel tableModel, int x, int y) {
        return Integer.parseInt(tableModel.getValueAt((x + tableModel.getRowCount())%tableModel.getRowCount(),
                (y + tableModel.getColumnCount())%tableModel.getColumnCount()).toString());
    }

    private void setCellValue(DefaultTableModel tableModel, int x, int y, int value) {
        tableModel.setValueAt(value, (x + tableModel.getRowCount())%tableModel.getRowCount(),
                (y + tableModel.getColumnCount())%tableModel.getColumnCount());
    }

    private void initRandomTable(DefaultTableModel model) {
        Random random = new Random();
        for (int x = 0; x < model.getRowCount(); x++) {
            for (int y = 0; y < model.getColumnCount(); y++) {
                model.setValueAt(random.nextInt(2), x, y);
            }
        }
    }

    private void initEmptyTable(DefaultTableModel model) {
        for (int x = 0; x < model.getRowCount(); x++) {
            for (int y = 0; y < model.getColumnCount(); y++) {
                model.setValueAt(0, x, y);
            }
        }
    }
}
