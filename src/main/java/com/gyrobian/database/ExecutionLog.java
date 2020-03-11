package com.gyrobian.database;

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
     * @return True if there's an exception somewhere in this execution log, or false otherwise.
     */
    public boolean containsExceptions() {
        for (ExecutionAction action : this.getActions()) {
            if (action.getException() != null) {
                return true;
            }
        }
        return false;
    }
}
