package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.db.EntryTable;
import com.rs.tool.quickbackup.db.model.VersionEntry;
import com.rs.tool.quickbackup.model.Version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListVersions {

    public static List<Version> list() {
        EntryTable entryTable = Database.getInstance().versionTable();
        List<VersionEntry> list = entryTable.list();
        Map<Long, Version> map = new HashMap<>();
        for (VersionEntry e : list) {
            Version v = new Version();
            v.time = e.time;
            v.name = e.name;
            v.parent = null;
            v.description = e.description;
            v.children = new ArrayList<>();
            map.put(v.time, v);
        }
        for (VersionEntry e : list) {
            if (e.parent > 0) {
                Version f = map.get(e.time);
                Version t = map.get(e.parent);
                f.parent = t;
                t.children.add(f);
            }
        }
        return new ArrayList<>(map.values());
    }

}
