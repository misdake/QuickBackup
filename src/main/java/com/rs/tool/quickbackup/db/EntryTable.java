package com.rs.tool.quickbackup.db;

import com.rs.tool.quickbackup.db.model.VersionEntry;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.ArrayList;
import java.util.List;

public class EntryTable {
    private ObjectRepository<VersionEntry> table;
    public EntryTable(ObjectRepository<VersionEntry> entryTable) {
        table = entryTable;
    }

    public void add(VersionEntry versionEntry) {
        table.insert(versionEntry);
    }

    public List<VersionEntry> list() {
        Cursor<VersionEntry> results = table.find();
        List<VersionEntry> r = new ArrayList<>();
        for (VersionEntry versionEntry : results) {
            r.add(versionEntry);
        }
        return r;
    }

    public void update(VersionEntry versionEntry) {
        table.update(ObjectFilters.eq("time", versionEntry.time), versionEntry);
    }

    public void delete(long time) {
        table.remove(ObjectFilters.eq("time", time));
    }

    public void clear() {
        table.remove(ObjectFilters.ALL);
    }
}
