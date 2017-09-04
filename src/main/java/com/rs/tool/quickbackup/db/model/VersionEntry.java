package com.rs.tool.quickbackup.db.model;

import org.dizitart.no2.objects.Id;

public class VersionEntry {
    @Id
    public long   time;
    public String name;
    public String description;
    public long   parent;

    public VersionEntry() {
    }

    @Override
    public String toString() {
        return "VersionEntry{" +
               "time=" + time +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", parent=" + parent +
               '}';
    }
}
