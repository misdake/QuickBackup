package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.db.model.FilePackEntry;
import com.rs.tool.quickbackup.db.model.VersionEntry;
import com.rs.tool.quickbackup.model.Version;
import com.rs.tool.quickbackup.model.VersionFiles;

import java.util.ArrayList;
import java.util.Map;

public class AddVersion {

    public static void add(Version parent, VersionFiles versionFiles) {
        VersionEntry ve = new VersionEntry();
        ve.name = versionFiles.name;
        ve.time = versionFiles.time;
        ve.description = versionFiles.description;
        ve.parent = parent != null ? parent.time : 0;
        FilePackEntry fe = new FilePackEntry();
        fe.time = ve.time;
        fe.paths = new ArrayList<>();
        fe.contents = new ArrayList<>();
        for (Map.Entry<String, byte[]> e : versionFiles.files.entrySet()) {
            fe.paths.add(e.getKey());
            fe.contents.add(e.getValue());
        }

        Database db = Database.getInstance();
        db.entryTable().add(ve);
        db.filePackTable().add(fe);
    }

}
