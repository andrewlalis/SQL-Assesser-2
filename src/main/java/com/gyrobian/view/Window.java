package com.gyrobian.view;

import com.gyrobian.listener.AssessExecutionsListener;
import com.gyrobian.listener.ClearTextComponentListener;
import com.gyrobian.listener.LoadTextComponentFromFileListener;
import com.gyrobian.listener.ScriptExecutionListener;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
	private AssessmentDisplay assessmentTextPane;
	private JPanel mainControlPanel;
	private JButton executeBothButton;
	private JButton executeTemplateButton;
	private JButton executeTestingButton;
	private JPanel databaseConfigurationPanel;
	private JPanel scriptExecutionPanel;
	private JTextField jdbcUrlInput;
	private JButton clearExecutionOutputsButton;
	private JButton assessExecutionsButton;

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

		this.assessExecutionsButton.addActionListener(new AssessExecutionsListener(
				this.templateOutputTextPane,
				this.testingOutputTextPane,
				this.executeTemplateButton,
				this.executeTestingButton,
				this.assessmentTextPane
		));
	}

	protected void createUIComponents() {
		this.templateOutputTextPane = new ExecutionLogDisplay();
		this.testingOutputTextPane = new ExecutionLogDisplay();
		this.assessmentTextPane = new AssessmentDisplay();
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		createUIComponents();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.add(inputPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		templateInputPanel = new JPanel();
		templateInputPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		inputPanel.add(templateInputPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		templateInputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label1 = new JLabel();
		label1.setHorizontalTextPosition(11);
		label1.setText("Template - The code to check against.");
		templateInputPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		templateTextEditorPanel = new JPanel();
		templateTextEditorPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		templateInputPanel.add(templateTextEditorPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		templateTextEditorButtonPanel = new JPanel();
		templateTextEditorButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		templateTextEditorPanel.add(templateTextEditorButtonPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		loadTemplateFromFileButton = new JButton();
		loadTemplateFromFileButton.setText("Load From File");
		templateTextEditorButtonPanel.add(loadTemplateFromFileButton);
		clearTemplateButton = new JButton();
		clearTemplateButton.setText("Clear");
		templateTextEditorButtonPanel.add(clearTemplateButton);
		final JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setVerticalScrollBarPolicy(22);
		templateTextEditorPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		templateTextArea = new JTextArea();
		Font templateTextAreaFont = UIManager.getFont("TextArea.font");
		if (templateTextAreaFont != null) templateTextArea.setFont(templateTextAreaFont);
		scrollPane1.setViewportView(templateTextArea);
		testingInputPanel = new JPanel();
		testingInputPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		inputPanel.add(testingInputPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		testingInputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label2 = new JLabel();
		label2.setText("Testing - The code to test correctness of.");
		testingInputPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		testingTextEditorPanel = new JPanel();
		testingTextEditorPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		testingInputPanel.add(testingTextEditorPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		testingTextEditorButtonPanel = new JPanel();
		testingTextEditorButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		testingTextEditorPanel.add(testingTextEditorButtonPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		loadTestingFromFileButton = new JButton();
		loadTestingFromFileButton.setText("Load From File");
		testingTextEditorButtonPanel.add(loadTestingFromFileButton);
		clearTestingButton = new JButton();
		clearTestingButton.setText("Clear");
		testingTextEditorButtonPanel.add(clearTestingButton);
		final JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(22);
		testingTextEditorPanel.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		testingTextArea = new JTextArea();
		Font testingTextAreaFont = UIManager.getFont("TextArea.font");
		if (testingTextAreaFont != null) testingTextArea.setFont(testingTextAreaFont);
		scrollPane2.setViewportView(testingTextArea);
		assessmentPanel = new JPanel();
		assessmentPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.add(assessmentPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(504, 55), null, 0, false));
		assessmentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label3 = new JLabel();
		label3.setText("Assessment - Compare the template's output to the testing output.");
		assessmentPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(22);
		assessmentPanel.add(scrollPane3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		scrollPane3.setViewportView(assessmentTextPane);
		outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.add(outputPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(504, 59), null, 0, false));
		outputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		outputPanelTitle = new JLabel();
		outputPanelTitle.setText("Execution Output - Log of actions performed by each script.");
		outputPanel.add(outputPanelTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		templateOutputPanel = new JPanel();
		templateOutputPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		outputPanel.add(templateOutputPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setVerticalScrollBarPolicy(22);
		templateOutputPanel.add(scrollPane4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		scrollPane4.setViewportView(templateOutputTextPane);
		testingOutputPanel = new JPanel();
		testingOutputPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		outputPanel.add(testingOutputPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setVerticalScrollBarPolicy(22);
		testingOutputPanel.add(scrollPane5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		scrollPane5.setViewportView(testingOutputTextPane);
		mainControlPanel = new JPanel();
		mainControlPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.add(mainControlPanel, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		mainControlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label4 = new JLabel();
		label4.setText("Controls");
		mainControlPanel.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainControlPanel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label5 = new JLabel();
		label5.setText("Database Configuration");
		panel1.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		databaseConfigurationPanel = new JPanel();
		databaseConfigurationPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.add(databaseConfigurationPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
		databaseConfigurationPanel.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("JDBC Connection URL");
		panel2.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel2.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		jdbcUrlInput = new JTextField();
		Font jdbcUrlInputFont = UIManager.getFont("TextArea.font");
		if (jdbcUrlInputFont != null) jdbcUrlInput.setFont(jdbcUrlInputFont);
		jdbcUrlInput.setText("jdbc:sqlite::memory:");
		panel2.add(jdbcUrlInput, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final Spacer spacer2 = new Spacer();
		databaseConfigurationPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainControlPanel.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label7 = new JLabel();
		label7.setText("Script Execution");
		panel3.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		scriptExecutionPanel = new JPanel();
		scriptExecutionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel3.add(scriptExecutionPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		executeBothButton = new JButton();
		executeBothButton.setText("Execute Both");
		scriptExecutionPanel.add(executeBothButton);
		executeTemplateButton = new JButton();
		executeTemplateButton.setText("Execute Template Script");
		scriptExecutionPanel.add(executeTemplateButton);
		executeTestingButton = new JButton();
		executeTestingButton.setText("Execute Testing Script");
		scriptExecutionPanel.add(executeTestingButton);
		clearExecutionOutputsButton = new JButton();
		clearExecutionOutputsButton.setText("Clear Outputs");
		scriptExecutionPanel.add(clearExecutionOutputsButton);
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainControlPanel.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JLabel label8 = new JLabel();
		label8.setHorizontalAlignment(11);
		label8.setText("Assessment");
		panel4.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel4.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel4.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		assessExecutionsButton = new JButton();
		assessExecutionsButton.setText("Assess Template and Testing Scripts");
		panel5.add(assessExecutionsButton);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return mainPanel;
	}

}
