package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.model.Task;

public class LoadTask {

    public static  void load(Task task) {
        Database.getInstance(task.name);
    }

}
