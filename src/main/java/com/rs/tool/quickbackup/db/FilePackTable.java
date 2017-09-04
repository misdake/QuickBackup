package com.rs.tool.quickbackup.db;

import com.rs.tool.quickbackup.db.model.FilePackEntry;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.ArrayList;
import java.util.List;

public class FilePackTable {
    private ObjectRepository<FilePackEntry> table;
    public FilePackTable(ObjectRepository<FilePackEntry> entryTable) {
        table = entryTable;
    }

    public void add(FilePackEntry filePackEntry) {
        table.insert(filePackEntry);
    }

    public FilePackEntry get(long time) {
        Cursor<FilePackEntry> results = table.find(ObjectFilters.eq("time", time));
        List<FilePackEntry> r = new ArrayList<>();
        for (FilePackEntry filePackEntry : results) {
            r.add(filePackEntry);
        }
        return r.isEmpty() ? null : r.get(0);
    }

    public void delete(long time) {
        table.remove(ObjectFilters.eq("time", time));
    }

    public void clear() {
        table.remove(ObjectFilters.ALL);
    }
}
