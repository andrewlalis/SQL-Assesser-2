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

    public void recordAction(ExecutionAction action) {
        this.actions.add(action);
    }

    public List<ExecutionAction> getActions() {
        return this.actions;
    }
}
