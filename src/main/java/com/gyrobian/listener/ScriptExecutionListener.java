package com.gyrobian.listener;

import com.gyrobian.database.DatabaseHelper;
import com.gyrobian.view.ExecutionLogDisplay;

import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that, when triggered by a button press, executes an SQL script from some text component,
 * and then renders the resulting execution log in another component.
 */
public class ScriptExecutionListener implements ActionListener {

	private final JTextComponent jdbcUrlInput;
	private final JTextComponent scriptContainer;
	private final ExecutionLogDisplay executionLogDisplay;

	public ScriptExecutionListener(
			JTextComponent jdbcUrlInput,
			JTextComponent scriptContainer,
			ExecutionLogDisplay executionLogDisplay
	) {
		this.jdbcUrlInput = jdbcUrlInput;
		this.scriptContainer = scriptContainer;
		this.executionLogDisplay = executionLogDisplay;
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String script = this.scriptContainer.getText();
		DatabaseHelper helper = new DatabaseHelper(this.jdbcUrlInput.getText().trim());
		this.executionLogDisplay.displayExecutionLog(helper.executeSQLScript(script));
	}
}
