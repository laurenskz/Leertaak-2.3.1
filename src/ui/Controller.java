package ui;

import multiformat.FormatException;
import multiformat.InvalidCommandException;
import multiformat.NumberBaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Jules on 13/02/2016.
 */
public class Controller implements ActionListener {

    Command command;

    public Controller(Command command) {
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton button = (JButton) o;

            switch (button.getText()) {

                case CalculatorView.CALCULATE_BUTTON_TXT:
                    Component[] components = button.getParent().getComponents();

                    for (Component component : components) {
                        if (component instanceof JTextField) {
                            System.out.println(((JTextField) component).getText());
                            calculate((JTextField) component, ((JTextField) component).getText());
                            break;
                        }
                    }

                    break;
            }
        } else if (o instanceof JTextField) {
            System.out.println(((JTextField) o).getText());
            calculate((JTextField) o, ((JTextField) o).getText());
        }
    }

    private void calculate(JTextField component, String command) {
        try {
            this.command.parseCommand(command);
            component.setText("");
            System.out.println();

            for (Component component1 : component.getParent().getParent().getComponents()) {
                if (component1 instanceof StateView) {
                    ((StateView) component1).setState(this.command.getCalculatorState());
                } else if (component1 instanceof HistoryView) {
                    ((HistoryView) component1).addCommand(command);
                }
            }
            ((JFrame) SwingUtilities.getRoot(component)).pack();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(component, "Unable to open file", e1.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        } catch (FormatException e1) {
            JOptionPane.showMessageDialog(component, "Invalid format", e1.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        } catch (InvalidCommandException e1) {
            JOptionPane.showMessageDialog(component, e1.getMessage(), e1.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        } catch (NumberBaseException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
