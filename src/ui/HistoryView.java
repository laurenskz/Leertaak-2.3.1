package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jules on 13/02/2016.
 */
public class HistoryView extends JComponent {

    public static final int MAX_DISPLAYED = 5;
    ArrayList<String> history = new ArrayList<String>();

    private int commands = 0;
    private JLabel commandsLabel = new JLabel("Commands entered: " + commands);
    private JLabel[] commandHistoryLabels = new JLabel[MAX_DISPLAYED];

    public HistoryView() {
        GridLayout layout = new GridLayout(7, 1, 15, 0);
        setLayout(layout);

        add(commandsLabel);

        for (int i = commandHistoryLabels.length - 1; i >= 0; i--) {
            commandHistoryLabels[i] = new JLabel(":");
            add(commandHistoryLabels[i], BorderLayout.CENTER);
        }
    }

    public void addCommand(String command) {
        history.add(command);
        commands++;

        commandsLabel.setText("Commands entered: " + commands);

        for (int i = 0; i < MAX_DISPLAYED; i++) {
            try {
                String cmd = history.get(history.size() - 1 - i);
                commandHistoryLabels[i].setText(cmd);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
    }
}
