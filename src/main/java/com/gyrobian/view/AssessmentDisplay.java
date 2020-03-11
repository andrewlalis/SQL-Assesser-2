package com.gyrobian.view;

import com.gyrobian.assessment.ExecutionLogComparisonAssessment;

/**
 * A text pane that's specially suited for displaying the results of assessing two execution logs.
 */
public class AssessmentDisplay extends AppendableJTextPane {

	public void displayAssessment(ExecutionLogComparisonAssessment assessment) {
		if (assessment.hasSameNumberOfActions()) {
			this.appendToDocument("Scripts perform the same number of actions.\n", null);
		} else {
			this.appendToDocument("Warning! Scripts do not perform the same number of actions. This does not explicitly mean the testing script is wrong, but may indicate so.\n", null);
		}

		if (assessment.hasSameNumberOfSelectQueries()) {
			this.appendToDocument("Scripts perform the same number of SELECT queries.\n", null);
		} else {
			this.appendToDocument("Warning! Scripts do not perform the same number of SELECT queries. While not a 100% indicator, it is very likely that the testing script is incorrect.\n", null);
		}

		if (assessment.getTemplateExceptions().size() > 0) {
			this.appendToDocument("Warning! Template script throws exceptions! Check execution output.\n", null);
		}
		if (assessment.getTestingExceptions().size() > 0) {
			this.appendToDocument("Warning! Testing script throws exceptions! Check execution output. This is indicative of an incorrect script.\n", null);
		}

		if (assessment.getQueryDiscrepancies().size() > 0) {
			this.appendToDocument("Warning! There are discrepancies in the results of SELECT queries done by the template and testing scripts.\n", null);
			for (String message : assessment.getQueryDiscrepancies()) {
				this.appendToDocument("\t" + message + "\n", null);
			}
		}
	}
}
