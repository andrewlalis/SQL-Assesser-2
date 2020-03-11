package com.gyrobian.view;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

/**
 * A slightly modified text pane that makes appending text a bit easier.
 */
public class AppendableJTextPane extends JTextPane {
	/**
	 * Appends a styled string to the document.
	 * @param str The string to append.
	 * @param style The style to use for the string.
	 */
	public void appendToDocument(String str, Style style) {
		StyledDocument document = this.getStyledDocument();
		try {
			document.insertString(document.getLength(), str, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
