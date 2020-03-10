package com.gyrobian.listener;

import com.gyrobian.util.FileLoader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Listener for when a user clicks to load the contents of a file into a text component.
 */
public class LoadTextComponentFromFileListener implements ActionListener {
	private final JTextComponent textComponent;

	public LoadTextComponentFromFileListener(JTextComponent textComponent) {
		this.textComponent = textComponent;
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("SQL, Text", "sql", "txt"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);

		int result = fileChooser.showOpenDialog(textComponent);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				this.textComponent.setText(FileLoader.readFile(fileChooser.getSelectedFile()));
			} catch (IOException exception) {
				String message = "Could not read file.";
				if (exception instanceof FileNotFoundException) {
					message = "File not found.";
				}
				JOptionPane.showMessageDialog(this.textComponent, message, "Error Reading File", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
