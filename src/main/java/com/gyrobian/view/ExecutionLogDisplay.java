package com.gyrobian.view;

import com.gyrobian.database.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * A type of text pane that's built for displaying SQL script execution logs.
 */
public class ExecutionLogDisplay extends JTextPane {

	private Style timestampStyle;
	private Style actionSubsectionLabelStyle;
	private Style queryStyle;
	private Style exceptionStyle;

	private ExecutionLog lastExecutionLogDisplayed;

	public ExecutionLogDisplay() {
		this.setEditable(false);
		this.initializeStyles();
	}

	public ExecutionLog getLastExecutionLogDisplayed() {
		return this.lastExecutionLogDisplayed;
	}

	/**
	 * Displays an execution log which was generated from an SQL script.
	 * @param log The log to display.
	 */
	public void displayExecutionLog(ExecutionLog log) {
		this.setText(null);

		for (ExecutionAction action : log.getActions()) {
			this.appendToDocument(action.getOccurredAt().toString() + ":\n", this.timestampStyle);
			this.appendExecutionAction(action);
			this.appendToDocument("\n", null);
		}

		this.lastExecutionLogDisplayed = log;
	}

	/**
	 * Appends a single action to this display's styled document.
	 * @param action The action to display.
	 */
	private void appendExecutionAction(ExecutionAction action) {
		if (action.getQuery() != null) {
			this.appendToDocument("Executing query:\n", this.actionSubsectionLabelStyle);
			this.appendToDocument(action.getQuery() + '\n', this.queryStyle);
		}
		if (action.getException() != null) {
			this.appendToDocument("An exception occurred:\n", this.actionSubsectionLabelStyle);
			this.appendToDocument(action.getException().getMessage() + '\n', this.exceptionStyle);
			action.getException().printStackTrace();
		}

		if (action instanceof UpdateAction) {
			UpdateAction updateAction = (UpdateAction) action;
			this.appendToDocument("Schema updated: " + updateAction.getRowsAffected() + " rows affected.\n", this.actionSubsectionLabelStyle);
		} else if (action instanceof QueryAction) {
			QueryAction queryAction = (QueryAction) action;
			CachedResultSet resultSet = queryAction.getResultSet();
			this.appendToDocument("Schema queried: " + resultSet.getRowCount() + " rows returned.\n", this.actionSubsectionLabelStyle);
			this.appendToDocument(resultSet.toFormattedTableString(), this.queryStyle);
		}
	}

	/**
	 * Initializes some styling that's needed for showing the various things that happen.
	 */
	private void initializeStyles() {
		this.timestampStyle = this.addStyle("timestamp", null);
		StyleConstants.setForeground(timestampStyle, Color.GRAY);
		StyleConstants.setItalic(timestampStyle, true);

		this.actionSubsectionLabelStyle = this.addStyle("subsection_label", null);
		StyleConstants.setBold(this.actionSubsectionLabelStyle, true);

		this.queryStyle = this.addStyle("query", null);
		StyleConstants.setFontSize(this.queryStyle, 12);
		StyleConstants.setFontFamily(this.queryStyle, "monospaced");
		StyleConstants.setLeftIndent(this.queryStyle, 8.0f);

		this.exceptionStyle = this.addStyle("exception", null);
		StyleConstants.setForeground(this.exceptionStyle, Color.red);
		StyleConstants.setBold(this.exceptionStyle, true);
	}

	/**
	 * Appends a styled string to the document.
	 * @param str The string to append.
	 * @param style The style to use for the string.
	 */
	private void appendToDocument(String str, Style style) {
		StyledDocument document = this.getStyledDocument();
		try {
			document.insertString(document.getLength(), str, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overrides the behavior of JTextPane so that any time this display's text disappears, we wipe
	 * any memory of a previous execution log.
	 * @param t The string to set the text to.
	 */
	@Override
	public void setText(String t) {
		super.setText(t);
		if (t == null) {
			this.lastExecutionLogDisplayed = null;
		}
	}
}
