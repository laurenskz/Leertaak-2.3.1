package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jules on 14/02/2016.
 */
public class VariableView extends JComponent {

    private JLabel variables;

    public VariableView(String text) {
        setLayout(new FlowLayout());

        variables = new JLabel(text);

        add(variables);
    }

    public void setVariables(String text) {
        variables.setText(text);
    }
}
