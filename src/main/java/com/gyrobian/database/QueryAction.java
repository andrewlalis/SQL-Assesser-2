package com.gyrobian.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An action in which a query result set is returned. Note that SCROLL_INSENSITIVE statements must be used, otherwise
 * an SQL exception will be thrown at each attempt to go through the result set.
 */
public class QueryAction extends ExecutionAction {

    private CachedResultSet resultSet;
    private boolean isOrdered;

    public QueryAction(String query, ResultSet resultSet, boolean isOrdered) throws SQLException {
        super(query);
        this.resultSet = CachedResultSet.fromResultSet(resultSet);
        this.isOrdered = isOrdered;
    }

    public CachedResultSet getResultSet() {
        return this.resultSet;
    }
}
