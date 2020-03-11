package com.gyrobian.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains a log of all actions performed to a database.
 */
public class ExecutionLog {

    private List<ExecutionAction> actions;

    public ExecutionLog() {
        this.actions = new ArrayList<>();
    }

    /**
     * Records a new action.
     * @param action The action to record.
     */
    public void recordAction(ExecutionAction action) {
        this.actions.add(action);
    }

    public List<ExecutionAction> getActions() {
        return this.actions;
    }

    /**
     * @return The list of all exceptions encountered.
     */
    public List<SQLException> getExceptions() {
        List<SQLException> exceptions = new ArrayList<>();
        this.actions.forEach(action -> {
            if (action.getException() != null) {
                exceptions.add(action.getException());
            }
        });
        return exceptions;
    }

    /**
     * @return The list of all schema update actions encountered.
     */
    public List<UpdateAction> getSchemaUpdateActions() {
        List<UpdateAction> updateActions = new ArrayList<>();
        this.actions.forEach(action -> {
            if (action instanceof UpdateAction) {
                updateActions.add((UpdateAction) action);
            }
        });
        return updateActions;
    }

    /**
     * @return The list of all query actions encountered.
     */
    public List<QueryAction> getQueryActions() {
        List<QueryAction> queryActions = new ArrayList<>();
        this.actions.forEach(action -> {
            if (action instanceof QueryAction) {
                queryActions.add((QueryAction) action);
            }
        });
        return queryActions;
    }
}
