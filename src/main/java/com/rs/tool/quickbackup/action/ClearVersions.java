package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;

public class ClearVersions {

    public static void clear() {
        Database db = Database.getInstance();
        db.entryTable().clear();
        db.filePackTable().clear();
    }

}
