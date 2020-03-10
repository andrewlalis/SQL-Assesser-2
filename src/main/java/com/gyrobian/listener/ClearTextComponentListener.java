package com.gyrobian.listener;

import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for when a user clicks a button to clear a text component.
 */
public class ClearTextComponentListener implements ActionListener {
	private final JTextComponent textComponent;

	public ClearTextComponentListener(JTextComponent textComponent) {
		this.textComponent = textComponent;
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.textComponent.setText(null);
	}
}
