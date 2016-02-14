package ui;

import multiformat.Calculator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jules on 13/02/2016.
 */
public class CalculatorView extends JComponent {

    public static final String CALCULATE_BUTTON_TXT = "Enter";

    private JTextField inputField = new JTextField(25);
    private JButton submitButton = new JButton(CALCULATE_BUTTON_TXT);

    public CalculatorView(Controller controller) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputField.addActionListener(controller);
        this.add(inputField);

        submitButton.addActionListener(controller);
        this.add(submitButton);
    }
}
