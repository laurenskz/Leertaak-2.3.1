package ui;

import multiformat.Calculator;

import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JFrame {

	CalculatorView calculatorView;
	StateView stateView;
	HistoryView historyView;
	VariableView variableView;

	Controller controller;
	Command command;
	
	public View() {
		super("Multiformat Calculator");
		setLayout(new BorderLayout());

		Container container = getContentPane();

		command = new Command();
		controller = new Controller(command);

		calculatorView = new CalculatorView(controller);
		container.add(calculatorView, BorderLayout.CENTER);

		stateView = new StateView(command.getCalculatorState());
		container.add(stateView, BorderLayout.NORTH);

		historyView = new HistoryView();
		container.add(historyView, BorderLayout.WEST);

		variableView = new VariableView(command.getVariables());
		container.add(variableView, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}
}
