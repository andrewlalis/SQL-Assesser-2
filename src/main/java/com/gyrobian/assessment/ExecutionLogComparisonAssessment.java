package com.gyrobian.assessment;

import com.gyrobian.database.CachedResultSet;
import com.gyrobian.database.ExecutionLog;
import com.gyrobian.database.QueryAction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExecutionLogComparisonAssessment {

	private boolean sameNumberOfActions;

	private boolean sameNumberOfSelectQueries;

	private List<SQLException> templateExceptions;

	private List<SQLException> testingExceptions;

	private List<String> queryDiscrepancies;

	private ExecutionLogComparisonAssessment() {
		this.queryDiscrepancies = new ArrayList<>();
	}

	/**
	 * Builds an assessment from two execution logs.
	 * @param template The template log to check the testing log against.
	 * @param testing The testing log, to check for errors in.
	 * @return A new assessment object containing details about how these two execution logs differ.
	 */
	public static ExecutionLogComparisonAssessment fromExecutionLogs(ExecutionLog template, ExecutionLog testing) {
		ExecutionLogComparisonAssessment assessment = new ExecutionLogComparisonAssessment();
		assessment.sameNumberOfActions = template.getActions().size() == testing.getActions().size();
		assessment.templateExceptions = template.getExceptions();
		assessment.testingExceptions = testing.getExceptions();

		assessment.sameNumberOfSelectQueries = template.getQueryActions().size() == testing.getQueryActions().size();
		// If both scripts have the same number of selects, check if they're similar.
		if (assessment.sameNumberOfSelectQueries) {
			List<QueryAction> templateQueries = template.getQueryActions();
			List<QueryAction> testingQueries = testing.getQueryActions();
			for (int i = 0; i < templateQueries.size(); i++) {
				CachedResultSet templateResult = templateQueries.get(i).getResultSet();
				CachedResultSet testingResult = testingQueries.get(i).getResultSet();
				if (templateResult.getRowCount() != testingResult.getRowCount()) {
					assessment.queryDiscrepancies.add("Template row count doesn't match testing row count for query " + i + ".");
				}
				if (templateResult.getColumnNames().length != testingResult.getColumnNames().length) {
					assessment.queryDiscrepancies.add("Template query has different number of columns, compared to testing query, for query " + i + ".");
				}
			}
		}

		return assessment;
	}

	public boolean hasSameNumberOfActions() {
		return sameNumberOfActions;
	}

	public boolean hasSameNumberOfSelectQueries() {
		return sameNumberOfSelectQueries;
	}

	public List<SQLException> getTemplateExceptions() {
		return templateExceptions;
	}

	public List<SQLException> getTestingExceptions() {
		return testingExceptions;
	}

	public List<String> getQueryDiscrepancies() {
		return queryDiscrepancies;
	}
}
