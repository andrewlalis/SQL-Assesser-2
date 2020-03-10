package com.gyrobian.database;

/**
 * Represents an action in which the schema or data was updated and no result set was returned.
 */
public class UpdateAction extends ExecutionAction {

    private int rowsAffected;

    public UpdateAction(String query, int rowsAffected) {
        super(query);
        this.rowsAffected = rowsAffected;
    }

    public int getRowsAffected() {
        return this.rowsAffected;
    }
}
