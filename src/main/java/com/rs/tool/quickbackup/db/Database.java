package com.rs.tool.quickbackup.db;

import com.rs.tool.quickbackup.db.model.FilePackEntry;
import com.rs.tool.quickbackup.db.model.VersionEntry;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public class Database {
    private static Database instance = null;
    public static Database getInstance() {
        if (instance == null) throw new RuntimeException("database not initialized!");
        return instance;
    }
    public static Database getInstance(String name) {
        if (instance != null && instance.name.equals(name)) {
            return instance;
        }
        Database database = new Database(name);
        instance = database;
        return database;
    }

    private final Nitrite db;

    private final ObjectRepository<VersionEntry>  entryTable;
    private final ObjectRepository<FilePackEntry> filePackTable;

    private String name;
    private Database(String name) {
        this.name = name;

        db = Nitrite.builder()
                .compressed()
                .filePath(name + ".db")
                .openOrCreate();

        entryTable = db.getRepository(VersionEntry.class);
        filePackTable = db.getRepository(FilePackEntry.class);
    }

    public void save() {
        db.commit();
    }
    public void close() {
        db.close();
    }

    public EntryTable versionTable() {
        return new EntryTable(entryTable);
    }
    public FilePackTable filePackTable() {
        return new FilePackTable(filePackTable);
    }
}
