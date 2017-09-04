package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.db.EntryTable;
import com.rs.tool.quickbackup.db.model.VersionEntry;
import com.rs.tool.quickbackup.model.Version;

public class UpdateVersion {

    public static void updateContent(Version version, String name, String description) {
        VersionEntry ve = new VersionEntry();
        ve.time = version.time;
        ve.name = name;
        ve.description = description;
        ve.parent = version.parent == null ? 0 : version.parent.time;

        EntryTable entryTable = Database.getInstance().entryTable();
        entryTable.update(ve);
    }

    public static void updateParent(Version version, long parent) {
        VersionEntry ve = new VersionEntry();
        ve.time = version.time;
        ve.name = version.name;
        ve.description = version.description;
        ve.parent = parent;

        EntryTable entryTable = Database.getInstance().entryTable();
        entryTable.update(ve);
    }

}
