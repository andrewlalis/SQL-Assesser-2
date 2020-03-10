package com.gyrobian.database;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Represents an action performed on a database.
 */
public class ExecutionAction {
	/**
	 * The time at which this action occurred.
	 */
	private final LocalDateTime occurredAt;

	/**
	 * The query that was executed.
	 */
	private final String query;

	/**
	 * An exception that occurred when this action was executed, if any.
	 */
	private SQLException exception;

	public ExecutionAction(String query) {
		this.query = query;
		this.occurredAt = LocalDateTime.now();
	}

	public ExecutionAction(String query, SQLException exception) {
		this(query);
		this.exception = exception;
	}

	public String getQuery() {
		return this.query;
	}

	public SQLException getException() {
		return this.exception;
	}

	public LocalDateTime getOccurredAt() {
		return this.occurredAt;
	}
}
