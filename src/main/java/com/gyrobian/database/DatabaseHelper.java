package com.gyrobian.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class that performs step-by-step SQL code execution.
 */
public class DatabaseHelper {

    private String jdbcUrl;

    public DatabaseHelper(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * Executes an SQL script and provides an execution log as a result.
     * @param script The script to execute, containing possibly many individual queries.
     * @return A log of all the actions that took place.
     */
    public ExecutionLog executeSQLScript(String script) {
        ExecutionLog log = new ExecutionLog();
        try {
            Connection connection = DriverManager.getConnection(this.jdbcUrl);

            if (!connection.isValid(1000)) {
                throw new SQLException("Invalid connection.");
            }

            List<String> queries = splitQueries(script);
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );

            queries.forEach(query -> log.recordAction(this.executeQuery(query, statement)));

        } catch (SQLException e) {
            // In case some exception occurred that wasn't related to any particular query.
            log.recordAction(new ExecutionAction(null, e));
        }

        return log;
    }

    /**
     * Executes a single query and outputs the results.
     * @param query The query to execute. Must be only one query in the string.
     * @param statement The statement used to execute the query.
     * @return The execution action which was done by executing this query.
     */
    private ExecutionAction executeQuery(String query, Statement statement) {
        ExecutionAction action;
        try {
            if (isSQLStatementQuery(query)) {
                action = new QueryAction(query, statement.executeQuery(query), isQueryOrdered(query));
            } else {
                action = new UpdateAction(query, statement.executeUpdate(query));
            }
        } catch (SQLException e) {
            action = new ExecutionAction(query, e);
        }
        return action;
    }

    /**
     * Splits and cleans each query so that it will run properly.
     * @param queriesString A string containing one or more queries to execute.
     * @return A list of individual queries.
     */
    private static List<String> splitQueries(String queriesString) {
        String[] sections = queriesString.split(";");
        List<String> strings = new ArrayList<>();

        for (String section : sections) {
            String s = section.trim();
            if (!s.isEmpty()) {
                strings.add(s);
            }
        }

        return strings;
    }

    /**
     * Determines if an SQL string is a query (it should return a result set)
     * @param str The string to check.
     * @return True if this is a query, or false if it is an update.
     */
    private static boolean isSQLStatementQuery(String str) {
        String upper = str.toUpperCase();
        return upper.trim().startsWith("SELECT");
    }

    /**
     * Determines if a query is ordered by something.
     * @param query The query to check.
     * @return True if the query makes use of the 'ORDER BY' clause.
     */
    private static boolean isQueryOrdered(String query) {
        return query.toUpperCase().contains("ORDER BY");
    }

}
