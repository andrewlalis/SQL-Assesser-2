package com.gyrobian.view;

import com.gyrobian.listener.ClearTextComponentListener;
import com.gyrobian.listener.LoadTextComponentFromFileListener;
import com.gyrobian.listener.ScriptExecutionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
	private ExecutionLogDisplay templateOutputTextPane;
	private ExecutionLogDisplay testingOutputTextPane;
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
	private JButton clearExecutionOutputsButton;
	private JButton compareExecutionsButton;

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
		this.clearExecutionOutputsButton.addActionListener(new ClearTextComponentListener(this.templateOutputTextPane));
		this.clearExecutionOutputsButton.addActionListener(new ClearTextComponentListener(this.testingOutputTextPane));

		this.loadTemplateFromFileButton.addActionListener(new LoadTextComponentFromFileListener(this.templateTextArea));
		this.loadTestingFromFileButton.addActionListener(new LoadTextComponentFromFileListener(this.testingTextArea));

		ActionListener executeTemplateListener = new ScriptExecutionListener(this.jdbcUrlInput, this.templateTextArea, this.templateOutputTextPane);
		ActionListener executeTestingListener = new ScriptExecutionListener(this.jdbcUrlInput, this.testingTextArea, this.testingOutputTextPane);
		this.executeTemplateButton.addActionListener(executeTemplateListener);
		this.executeTestingButton.addActionListener(executeTestingListener);
		this.executeBothButton.addActionListener(executeTemplateListener);
		this.executeBothButton.addActionListener(executeTestingListener);
	}

	protected void createUIComponents() {
		this.templateOutputTextPane = new ExecutionLogDisplay();
		this.testingOutputTextPane = new ExecutionLogDisplay();
	}
}
