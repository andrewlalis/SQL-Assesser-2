package com.gyrobian.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Represents a result set from a SELECT query.
 */
public class CachedResultSet {
	private String[] columnNames;

	private List<Map<String, String>> rows;

	private CachedResultSet(String[] columnNames, List<Map<String, String>> rows) {
		this.columnNames = columnNames;
		this.rows = rows;
	}

	public int getRowCount() {
		return this.rows.size();
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public List<Map<String, String>> getRows() {
		return this.rows;
	}

	public static CachedResultSet fromResultSet(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();

		int columnCount = metaData.getColumnCount();
		String[] columnNames = new String[columnCount];
		for (int i = 1; i <= columnCount; i++) {
			columnNames[i - 1] = metaData.getColumnName(i);
		}

		List<Map<String, String>> rows = new ArrayList<>();
		while (resultSet.next()) {
			Map<String, String> row = new HashMap<>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				row.put(columnNames[columnIndex - 1], resultSet.getString(columnIndex));
			}
			rows.add(row);
		}

		return new CachedResultSet(columnNames, rows);
	}

	public String toFormattedTableString() {
		int[] optimumColumnWidths = new int[this.columnNames.length];
		int totalTableWidth = 0;
		for (int columnIndex = 0; columnIndex < this.columnNames.length; columnIndex++) {
			int optimumWidth = 8;
			for (Map<String, String> row : this.rows) {
				int thisRowsValueLength = row.get(this.columnNames[columnIndex]).trim().length();
				if (thisRowsValueLength > optimumWidth) {
					optimumWidth = thisRowsValueLength;
				}
			}
			optimumColumnWidths[columnIndex] = optimumWidth;
			totalTableWidth += optimumWidth;
		}
		totalTableWidth += this.columnNames.length + 1; // Account for vertical separators.
		totalTableWidth += this.columnNames.length; // Account for one space of padding before each value.

		StringBuilder sb = new StringBuilder("-".repeat(totalTableWidth) + '\n');
		for (int columnIndex = 0; columnIndex < this.columnNames.length; columnIndex++) {
			sb.append('|');
			sb.append(String.format(" %-" + optimumColumnWidths[columnIndex] + "s", this.columnNames[columnIndex]));
		}
		sb.append("|\n").append("-".repeat(totalTableWidth)).append('\n');

		for (Map<String, String> row : this.rows) {
			for (int columnIndex = 0; columnIndex < this.columnNames.length; columnIndex++) {
				sb.append('|');
				sb.append(String.format(" %-" + optimumColumnWidths[columnIndex] + "s", row.get(this.columnNames[columnIndex])));
			}
			sb.append("|\n").append("-".repeat(totalTableWidth)).append('\n');
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "CachedResultSet{" +
				"columnNames=" + Arrays.toString(columnNames) +
				", rows=" + rows +
				'}';
	}
}
