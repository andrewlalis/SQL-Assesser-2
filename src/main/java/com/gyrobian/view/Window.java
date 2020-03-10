package com.gyrobian.view;

import com.gyrobian.listener.ClearTextComponentListener;
import com.gyrobian.listener.LoadTextComponentFromFileListener;

import javax.swing.*;
import java.awt.*;

/**
 * The window that's used for displaying the application.
 */
public class Window extends JFrame {
	private JPanel mainPanel;
	private JPanel assessmentPanel;
	private JPanel inputPanel;
	private JPanel templateInputPanel;
	private JPanel testingInputPanel;
	private JPanel outputPanel;
	private JTextArea templateTextArea;
	private JPanel templateTextEditorPanel;
	private JPanel testingTextEditorPanel;
	private JTextArea testingTextArea;
	private JButton loadTemplateFromFileButton;
	private JButton clearTemplateButton;
	private JButton loadTestingFromFileButton;
	private JButton clearTestingButton;
	private JPanel templateTextEditorButtonPanel;
	private JPanel testingTextEditorButtonPanel;
	private JLabel outputPanelTitle;
	private JPanel templateOutputPanel;
	private JPanel testingOutputPanel;
	private JTextPane templateOutputTextPane;
	private JTextPane testingOutputTextPane;
	private JLabel assessmentPanelTitle;
	private JTextPane assessmentTextPane;
	private JPanel mainControlPanel;
	private JButton executeBothButton;
	private JButton executeTemplateButton;
	private JButton executeTestingButton;
	private JPanel databaseConfigurationPanel;
	private JPanel scriptExecutionPanel;
	private JTextField jdbcUrlInput;
	private JCheckBox enableForeignKeysCheckbox;

	public Window() {
		super("SQL-Assesser-2");
	}

	/**
	 * Initializes the window and makes it visible.
	 */
	public void init() {
		this.initializeEventListeners();

		this.setContentPane(mainPanel);
		this.pack();
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setVisible(true);
	}

	/**
	 * Initializes all event listening for any components that need it.
	 */
	private void initializeEventListeners() {
		this.clearTemplateButton.addActionListener(new ClearTextComponentListener(this.templateTextArea));
		this.clearTestingButton.addActionListener(new ClearTextComponentListener(this.testingTextArea));

		this.loadTemplateFromFileButton.addActionListener(new LoadTextComponentFromFileListener(this.templateTextArea));
		this.loadTestingFromFileButton.addActionListener(new LoadTextComponentFromFileListener(this.testingTextArea));
	}
}
