package com.gyrobian.listener;

import com.gyrobian.database.ExecutionLog;
import com.gyrobian.view.AssessmentDisplay;
import com.gyrobian.view.ExecutionLogDisplay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for when pressing a button triggers comparing the executions of the template and testing
 * scripts.
 */
public class AssessExecutionsListener implements ActionListener {
	private ExecutionLogDisplay templateExecutionLogDisplay;
	private ExecutionLogDisplay testingExecutionLogDisplay;
	private JButton executeTemplateButton;
	private JButton executeTestingButton;
	private AssessmentDisplay assessmentDisplay;

	public AssessExecutionsListener(
			ExecutionLogDisplay templateExecutionLogDisplay,
			ExecutionLogDisplay testingExecutionLogDisplay,
			JButton executeTemplateButton,
			JButton executeTestingButton,
			AssessmentDisplay assessmentDisplay
	) {
		this.templateExecutionLogDisplay = templateExecutionLogDisplay;
		this.testingExecutionLogDisplay = testingExecutionLogDisplay;
		this.executeTemplateButton = executeTemplateButton;
		this.executeTestingButton = executeTestingButton;
		this.assessmentDisplay = assessmentDisplay;
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ExecutionLog lastTemplateExecution = this.templateExecutionLogDisplay.getLastExecutionLogDisplayed();
		ExecutionLog lastTestingExecution = this.testingExecutionLogDisplay.getLastExecutionLogDisplayed();
		if (lastTemplateExecution == null || lastTestingExecution == null) {
			this.assessmentDisplay.appendToDocument("Could not find a recent execution for one of the scripts, re-running...\n", null);
			triggerButton(this.executeTemplateButton, this);
			triggerButton(this.executeTestingButton, this);
			lastTemplateExecution = this.templateExecutionLogDisplay.getLastExecutionLogDisplayed();
			lastTestingExecution = this.testingExecutionLogDisplay.getLastExecutionLogDisplayed();

			if (lastTemplateExecution == null || lastTestingExecution == null) {
				JOptionPane.showMessageDialog(this.assessmentDisplay, "Could not execute both scripts.", "Assessment Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}


	}

	/**
	 * Triggers all action listeners on a particular button.
	 * @param button The button to trigger.
	 * @param source The object that triggered the button.
	 */
	private static void triggerButton(JButton button, Object source) {
		for (ActionListener listener : button.getActionListeners()) {
			listener.actionPerformed(new ActionEvent(source, ActionEvent.ACTION_PERFORMED, null));
		}
	}
}
