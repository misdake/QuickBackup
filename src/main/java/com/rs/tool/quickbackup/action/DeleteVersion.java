package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.model.Version;

public class DeleteVersion {

    public static void delete(Version version) {
        Database db = Database.getInstance();
        db.versionTable().delete(version.time);
        db.filePackTable().delete(version.time);
        for (Version child : version.children) {
            UpdateVersion.updateParent(child, version.parent == null ? 0 : version.parent.time);
        }
    }

}
