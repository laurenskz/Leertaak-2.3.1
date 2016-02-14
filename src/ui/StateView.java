package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jules on 13/02/2016.
 */
public class StateView extends JComponent {

    private JLabel state;

    public StateView(String state) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.state = new JLabel(state);
        add(this.state);
    }

    public void setState(String text) {
        state.setText(text);
    }
}
