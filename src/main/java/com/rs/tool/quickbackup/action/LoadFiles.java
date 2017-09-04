package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.db.model.FilePackEntry;
import com.rs.tool.quickbackup.model.Version;
import com.rs.tool.quickbackup.model.VersionFiles;

import java.util.HashMap;

public class LoadFiles {

    public static VersionFiles load(Version version) {
        VersionFiles vf = new VersionFiles(version);
        vf.files = new HashMap<>();

        FilePackEntry fe = Database.getInstance().filePackTable().get(version.time);
        if (fe == null) return null;
        for (int i = 0; i < fe.contents.size(); i++) {
            vf.files.put(fe.paths.get(i), fe.contents.get(i));
        }

        return vf;
    }

}
